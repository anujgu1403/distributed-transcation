package com.order.demo.saga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.order.demo.controller.OrderdemoController;
import com.order.demo.listener.CustomEvent;
import com.order.demo.model.OrderRequest;
import com.order.demo.model.Response;
import com.order.demo.service.OrderdemoService;
import com.order.demo.util.OrderServiceConstants;

@Component
public class CreateOrderSaga {
	private static final Logger logger = LoggerFactory.getLogger(OrderdemoController.class);
	
	@Autowired
	OrderdemoService orderdemoService;
	
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

}
