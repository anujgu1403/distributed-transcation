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
import com.order.demo.model.PaymentRequest;

@Service
public class SagaClientImpl implements SagaClient {
	private static final Logger logger = LoggerFactory.getLogger(OrderdemoController.class);

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Value("${inventory.service.url}")
	String inventoryUrl;
	
	@Value("${payment.service.url}")
	String paymentUrl;
	
	@Value("${inventory.service.revert.url}")
	String inventoryRevertServiceUrl;
	
	@Value("${payment.service.revert.url}")
	String paymentRevertServiceUrl;

	@Override
	public ClientResponse invokeInventoryService(InventoryRequest inventoryRequest) {
		logger.info("invokeInventoryService:: InventoryRequest: ", inventoryRequest);
		return getRestTemplate().postForObject(inventoryUrl, inventoryRequest, ClientResponse.class);
	}
	
	@Override
	public ClientResponse invokeInventoryRevertService(InventoryRequest inventoryRequest) {
		logger.info("invokeInventoryRevertService:: InventoryRequest: ", inventoryRequest);
		return getRestTemplate().postForObject(inventoryRevertServiceUrl, inventoryRequest, ClientResponse.class);
	}
	
	@Override
	public ClientResponse invokePaymentService(PaymentRequest paymentRequest) {
		logger.info("invokePaymmentService:: PaymentRequest: ", paymentRequest);
		return getRestTemplate().postForObject(paymentUrl, paymentRequest, ClientResponse.class);
	}

	@Override
	public ClientResponse invokePaymentRevertService(PaymentRequest paymentRequest) {
		logger.info("invokePaymmentService:: PaymentRequest: ", paymentRequest);
		return getRestTemplate().postForObject(paymentRevertServiceUrl, paymentRequest, ClientResponse.class);
	}

}
