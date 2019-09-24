package com.order.demo.client;

import com.order.demo.model.ClientResponse;
import com.order.demo.model.InventoryRequest;
import com.order.demo.model.PaymentRequest;

public interface SagaClient {
	
	public ClientResponse invokeInventoryService(InventoryRequest inventoryRequest);
	
	public ClientResponse invokePaymentService(PaymentRequest paymentRequest);
}
