/*
 * package com.order.demo.listener;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.ApplicationEventPublisher; import
 * org.springframework.context.event.EventListener; import
 * org.springframework.stereotype.Component; import
 * com.order.demo.client.SagaClient; import com.order.demo.model.ClientResponse;
 * import com.order.demo.model.PaymentRequest; import
 * com.order.demo.util.OrderServiceConstants;
 * 
 * @Component public class PaymentLintener {
 * 
 * @Autowired SagaClient sagaClient;
 * 
 * @Autowired ApplicationEventPublisher applicationEventPublisher;
 * 
 * @EventListener(CustomEvent.class) public void onInventoryUpdate(CustomEvent
 * customEvent) { if (null != customEvent &&
 * customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.
 * INVENTORY_UPDATED)) { PaymentRequest paymentRequest = new PaymentRequest();
 * paymentRequest.setOrderId(customEvent.getOrderId());
 * paymentRequest.setAmount(customEvent.getAmount()); ClientResponse
 * clientResponse = sagaClient.invokePaymentService(paymentRequest); if
 * (clientResponse.getCode().equals("201")) { // Raise event to update payment
 * applicationEventPublisher.publishEvent(new CustomEvent(this,
 * customEvent.getOrderId(), customEvent.getProductId(),
 * customEvent.getQuantity(), customEvent.getAmount(),
 * OrderServiceConstants.PAYMENT_UPDATED)); } else { // raise event to rollback
 * order tansaction applicationEventPublisher.publishEvent(new CustomEvent(this,
 * customEvent.getOrderId(), customEvent.getProductId(),
 * customEvent.getQuantity(), customEvent.getAmount(),
 * OrderServiceConstants.PAYMENT_UPDATED_FAILED)); } }
 * 
 * }
 * 
 * }
 */