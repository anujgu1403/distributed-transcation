package com.order.demo.saga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.order.demo.client.SagaClient;
import com.order.demo.controller.OrderdemoController;
import com.order.demo.listener.CustomEvent;
import com.order.demo.model.ClientResponse;
import com.order.demo.model.InventoryRequest;
import com.order.demo.model.OrderRequest;
import com.order.demo.model.PaymentRequest;
import com.order.demo.model.Response;
import com.order.demo.service.OrderdemoService;
import com.order.demo.util.OrderServiceConstants;

@Component
public class CreateOrderSaga {
	private static final Logger logger = LoggerFactory.getLogger(OrderdemoController.class);

	@Autowired
	OrderdemoService orderdemoService;

	@Autowired
	SagaClient sagaClient;

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@EventListener(CustomEvent.class)
	public Response orderCreated(CustomEvent customEvent) {
		logger.debug("customEvent: " + customEvent);
		Response resp = new Response();
		if (customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.CREATE_ORDER_EVENT)) {
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setOrderId(customEvent.getOrderId());
			orderRequest.setProductId(customEvent.getProductId());
			orderRequest.setQuantity(customEvent.getQuantity());
			orderRequest.setTotalPrice(customEvent.getAmount());
			resp = orderdemoService.createOrder(orderRequest);
			if (null == resp.getErrorMessage() || resp.getErrorMessage().isEmpty()) {
				// publish event for inventory update
				applicationEventPublisher.publishEvent(new CustomEvent(this, resp.getOrderId(), resp.getProductId(),
						resp.getQuantity(), resp.getTotalPrice(), OrderServiceConstants.ORDER_CREATED_EVENT, null));
			}
		} else {
			// Send the final response for transaction completion.
			if (customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.ORDER_CONFIMED)) {
				if (null != customEvent.getResponse()) {
					resp = customEvent.getResponse();
					resp.setMessage(OrderServiceConstants.ORDER_CONFIMED);
					return resp;
				}
			} else if (customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.ORDER_TRANSACTION_REVERTED)) {
				resp = new Response();
				resp.setMessage(OrderServiceConstants.ORDER_TRANSACTION_REVERTED);
				return resp;
			}
		}
		logger.debug("Response: " + resp);
		return resp;
	}

	@EventListener(CustomEvent.class)
	public void onOrderCreate(CustomEvent customEvent) {
		if (null != customEvent && customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.ORDER_CREATED_EVENT)) {
			InventoryRequest inventoryRequest = new InventoryRequest();
			inventoryRequest.setProductId(customEvent.getProductId());
			inventoryRequest.setQuantity(customEvent.getQuantity());
			ClientResponse clientResponse = sagaClient.invokeInventoryService(inventoryRequest);
			if (clientResponse.getCode().equals("201")) {
				// Raise event to update payment
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.INVENTORY_UPDATED, null));
			} else {
				// raise event to rollback order tansaction
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.INVENTORY_UPDATION_FAILED, null));
			}
		}

	}

	@EventListener(CustomEvent.class)
	public void onInventoryUpdate(CustomEvent customEvent) {
		if (null != customEvent && customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.INVENTORY_UPDATED)) {
			PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest.setOrderId(customEvent.getOrderId());
			paymentRequest.setAmount(customEvent.getAmount());
			ClientResponse clientResponse = sagaClient.invokePaymentService(paymentRequest);
			if (clientResponse.getCode().equals("201")) {
				// Raise event to update payment
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.PAYMENT_UPDATED, null));
			} else {
				// raise event to rollback order transaction
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.PAYMENT_UPDATION_FAILED, null));
			}
		}
	}

	@EventListener(CustomEvent.class)
	public void onPaymentUpdated(CustomEvent customEvent) {
		if (null != customEvent && customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.PAYMENT_UPDATED)) {
			// Update final order status as placed 'S'
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setOrderId(customEvent.getOrderId());
			orderRequest.setProductId(customEvent.getProductId());
			orderRequest.setQuantity(customEvent.getQuantity());
			orderRequest.setTotalPrice(customEvent.getAmount());
			Response resp = orderdemoService.updateOrder(orderRequest);

			if (null == resp.getErrorMessage() || resp.getErrorMessage().isEmpty()) {
				// Order successfully updated and send the final response
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.ORDER_CONFIMED, resp));
			} else {
				// raise event to roll back order transaction
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.ORDER_CONFIMATION_FAILED, null));
			}
		}
	}

	@EventListener(CustomEvent.class)
	public void onTransactionFailed(CustomEvent customEvent) {
		if (null != customEvent
				&& customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.INVENTORY_UPDATION_FAILED)) {

			// Delete the pending order created in recently
			Response resp = orderdemoService.deleteOrder(customEvent.getOrderId());
			if (!resp.getMessage().isEmpty()) {
				// raise event to return response for successful transaction rolledback
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.ORDER_TRANSACTION_REVERTED, null));
			}

		} else if (null != customEvent
				&& customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.PAYMENT_UPDATION_FAILED)) {
			// Revert the inventory which was updated earlier
			InventoryRequest inventoryRequest = new InventoryRequest();
			inventoryRequest.setProductId(customEvent.getProductId());
			inventoryRequest.setQuantity(customEvent.getQuantity());
			ClientResponse clientResponse = sagaClient.invokeInventoryRevertService(inventoryRequest);

			if (null != clientResponse.getCode() && clientResponse.getCode().equals("201")) {
				// Inventory has been reverted successfully and delete the order
				Response resp = orderdemoService.deleteOrder(customEvent.getOrderId());

				if (!resp.getMessage().isEmpty()) {
					// raise event to return response for successful transaction rolledback
					applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
							customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
							OrderServiceConstants.ORDER_TRANSACTION_REVERTED, null));
				}

			}

		}
	}

}
