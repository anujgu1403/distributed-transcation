package com.order.demo.service;

import org.springframework.stereotype.Service;
import com.order.demo.model.OrderRequest;
import com.order.demo.model.Response;
/**
 * @author Anuj Kumar
 * 
 *         This service class is to implement the service layer methods to write
 *         the business logic
 */
@Service
public interface OrderdemoService {
	
	/**
	 * This method is used to add items into cart
	 * 
	 * @param orderRequest
	 * @return Response
	 */
	Response createOrder(OrderRequest orderRequest);
	
	
	//Response findByOrdersId(int orderId);
}
