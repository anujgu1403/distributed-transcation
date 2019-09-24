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
	
	public Response orderCreated(OrderRequest orderRequest) {
		logger.debug("OrderRequest: " + orderRequest);
		Response resp = orderdemoService.createOrder(orderRequest);
		if(null==resp.getErrorMessage() || resp.getErrorMessage().isEmpty()) {
			//publish event for inventory update
			applicationEventPublisher.publishEvent(new CustomEvent(this, resp.getOrderId(), resp.getProductId(), resp.getQuantity(), resp.getTotalPrice(), OrderServiceConstants.ORDER_CREATED_EVENT));
			
			
		}else {
			// Abort the tranaction
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
						OrderServiceConstants.INVENTORY_UPDATED));
			} else {
				// raise event to rollback order tansaction
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.INVENTORY_UPDATION_FAILED));
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
						OrderServiceConstants.PAYMENT_UPDATED));
			} else {
				// raise event to rollback order tansaction
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.PAYMENT_UPDATED_FAILED));
			}
		}
	}
	
	@EventListener(CustomEvent.class)
	public void onPaymentUpdated(CustomEvent customEvent) {
		if (null != customEvent && customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.PAYMENT_UPDATED)) {
			//Update final order status as placed 'S'
			
			
			if (clientResponse.getCode().equals("201")) {
				// Raise event to update payment
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.ORDER_CONFIM));
			} else {
				// raise event to rollback order tansaction
				applicationEventPublisher.publishEvent(new CustomEvent(this, customEvent.getOrderId(),
						customEvent.getProductId(), customEvent.getQuantity(), customEvent.getAmount(),
						OrderServiceConstants.PAYMENT_UPDATED_FAILED));
			}
		}
	}

}
