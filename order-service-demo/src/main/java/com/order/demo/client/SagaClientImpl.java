package com.order.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.order.demo.controller.OrderdemoController;
import com.order.demo.model.ClientResponse;
import com.order.demo.model.InventoryRequest;

@Service
public class SagaClientImpl implements SagaClient {
	private static final Logger logger = LoggerFactory.getLogger(OrderdemoController.class);

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Value("${inventory.service.url}")
	String inventoryUrl;

	@Override
	public ClientResponse invokeInventoryService(InventoryRequest inventoryRequest) {
		logger.info("invokeInventoryService:: InventoryRequest: ", inventoryRequest);
		return getRestTemplate().postForObject(inventoryUrl, inventoryRequest, ClientResponse.class);
	}

}
