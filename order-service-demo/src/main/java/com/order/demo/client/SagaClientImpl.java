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
		ClientResponse clientResponse = new ClientResponse();
		try {
			clientResponse = getRestTemplate().postForObject(inventoryUrl, inventoryRequest, ClientResponse.class);
		} catch (Exception e) {
			logger.error("Error while calling inventory update service: ", e.getCause());
			clientResponse.setCode("500");
			clientResponse.setMessage("Error while calling inventory update service.");
		}
		return clientResponse;
	}

	@Override
	public ClientResponse invokeInventoryRevertService(InventoryRequest inventoryRequest) {
		logger.info("invokeInventoryRevertService:: InventoryRequest: ", inventoryRequest);
		ClientResponse clientResponse = new ClientResponse();
		try {
			clientResponse = getRestTemplate().postForObject(inventoryRevertServiceUrl, inventoryRequest,
					ClientResponse.class);
		} catch (Exception e) {
			logger.error("Error while calling inventory rollback service: ", e.getCause());
			clientResponse.setCode("500");
			clientResponse.setMessage("Error while calling inventory rollback service.");
		}
		return clientResponse;
	}

	@Override
	public ClientResponse invokePaymentService(PaymentRequest paymentRequest) {
		logger.info("invokePaymmentService:: PaymentRequest: ", paymentRequest);
		ClientResponse clientResponse = new ClientResponse();
		try {
			clientResponse = getRestTemplate().postForObject(paymentUrl, paymentRequest, ClientResponse.class);
		} catch (Exception e) {
			logger.error("Error while calling payment update service: ", e.getCause());
			clientResponse.setCode("500");
			clientResponse.setMessage("Error while calling payment update service.");
		}
		return clientResponse;
	}

	@Override
	public ClientResponse invokePaymentRevertService(PaymentRequest paymentRequest) {
		logger.info("invokePaymentRevertService:: PaymentRequest: ", paymentRequest);
		ClientResponse clientResponse = new ClientResponse();
		try {
			clientResponse = getRestTemplate().postForObject(paymentRevertServiceUrl, paymentRequest,
					ClientResponse.class);
		} catch (Exception e) {
			logger.error("Error while calling payment rollback service: ", e.getCause());
			clientResponse.setCode("500");
			clientResponse.setMessage("Error while calling payment rollback service.");
		}
		return clientResponse;
	}

}
