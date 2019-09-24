/*
 * package com.order.demo.listener;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.ApplicationEventPublisher; import
 * org.springframework.context.event.EventListener; import
 * org.springframework.stereotype.Component; import
 * com.order.demo.client.SagaClient; import com.order.demo.model.ClientResponse;
 * import com.order.demo.model.InventoryRequest; import
 * com.order.demo.util.OrderServiceConstants;
 * 
 * @Component public class InventoryListener {
 * 
 * @Autowired SagaClient sagaClient;
 * 
 * @Autowired ApplicationEventPublisher applicationEventPublisher;
 * 
 * @EventListener(CustomEvent.class) public void onOrderCreate(CustomEvent
 * customEvent) { if (null != customEvent &&
 * customEvent.getEvent().equalsIgnoreCase(OrderServiceConstants.
 * ORDER_CREATED_EVENT)) { InventoryRequest inventoryRequest = new
 * InventoryRequest();
 * inventoryRequest.setProductId(customEvent.getProductId());
 * inventoryRequest.setQuantity(customEvent.getQuantity()); ClientResponse
 * clientResponse = sagaClient.invokeInventoryService(inventoryRequest); if
 * (clientResponse.getCode().equals("201")) { // Raise event to update payment
 * applicationEventPublisher.publishEvent(new CustomEvent(this,
 * customEvent.getOrderId(), customEvent.getProductId(),
 * customEvent.getQuantity(), customEvent.getAmount(),
 * OrderServiceConstants.INVENTORY_UPDATED)); } else { // raise event to
 * rollback order tansaction applicationEventPublisher.publishEvent(new
 * CustomEvent(this, customEvent.getOrderId(), customEvent.getProductId(),
 * customEvent.getQuantity(), customEvent.getAmount(),
 * OrderServiceConstants.INVENTORY_UPDATION_FAILED)); } }
 * 
 * } }
 */