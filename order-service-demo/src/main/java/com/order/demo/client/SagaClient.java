package com.order.demo.client;

import com.order.demo.model.ClientResponse;
import com.order.demo.model.InventoryRequest;

public interface SagaClient {
	
	public ClientResponse invokeInventoryService(InventoryRequest inventoryRequest);

}
