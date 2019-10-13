package com.order.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.demo.domain.Ordersdemo;
import com.order.demo.model.OrderRequest;
import com.order.demo.model.Response;
import com.order.demo.repository.OrdersdemoRepository;
import com.order.demo.util.OrderServiceConstants;

/**
 * @author Anuj Kumar
 * 
 *         This service class is to implement the service layer methods to write
 *         the business logic
 */
@Service
public class OrderdemoServiceImpl implements OrderdemoService {

	private static final Logger logger = LoggerFactory.getLogger(OrderdemoServiceImpl.class);

	@Autowired
	protected OrdersdemoRepository ordersRepository;

	/**
	 * This method is used to add items into cart
	 * 
	 * @param orderRequest
	 * @return Response
	 */
	@Override
	public Response createOrder(OrderRequest orderRequest) {
		logger.info("Start createOrder method: ", OrderdemoServiceImpl.class.getName());
		Response resp = new Response();
		Ordersdemo order = new Ordersdemo();
		try {
			// create new order for guest user
			order.setProductId(orderRequest.getProductId());
			order.setQuantity(orderRequest.getQuantity());
			order.setStatus(OrderServiceConstants.PENDING_ORDER);
			order.setTotalPrice(orderRequest.getTotalPrice());
			order = ordersRepository.save(order);
			
			if(order.getId()!=0) {
				resp.setOrderId(order.getId());
				resp.setProductId(order.getProductId());
				resp.setTotalPrice(order.getTotalPrice());
				resp.setTimePlaced(order.getTimePlaced());
				resp.setTimeUpdate(order.getTimeUpdate());
				resp.setStatus(order.getStatus());
				resp.setQuantity(order.getQuantity());
			}

		} catch (Exception ex) {
			resp.setErrorMessage(OrderServiceConstants.ERROR_RESPONSE);
			logger.debug("Error occured: ", ex.getMessage());
		}
		logger.info("End createOrder method: ", OrderdemoServiceImpl.class.getName());
		return resp;
	}

	/**
	 * This method is used to update item in db
	 * 
	 * @param orderRequest
	 * @return Response
	 */
	@Override
	public Response updateOrder(OrderRequest orderRequest) {
		logger.info("Start updateOrder method: ", OrderdemoServiceImpl.class.getName());
		Response resp = new Response();
		Ordersdemo order = new Ordersdemo();
		try {
			// create new order for guest user
			order.setId(orderRequest.getOrderId());
			order.setProductId(orderRequest.getProductId());
			order.setQuantity(orderRequest.getQuantity());
			order.setStatus("S");
			order.setTotalPrice(orderRequest.getTotalPrice());
			order = ordersRepository.save(order);
			
			if(order.getId()!=0) {
				resp.setOrderId(order.getId());
				resp.setProductId(order.getProductId());
				resp.setTotalPrice(order.getTotalPrice());
				resp.setTimePlaced(order.getTimePlaced());
				resp.setTimeUpdate(order.getTimeUpdate());
				resp.setStatus(order.getStatus());
				resp.setQuantity(order.getQuantity());
			}

		} catch (Exception ex) {
			resp.setErrorMessage(OrderServiceConstants.ERROR_RESPONSE);
			logger.debug("Error occured: ", ex.getMessage());
		}
		logger.info("End updateOrder method: ", OrderdemoServiceImpl.class.getName());
		return resp;
	}
	
	/**
	 * This method is used to delete the order from db
	 * 
	 * @param orderRequest
	 * @return Response
	 */
	@Override
	public Response deleteOrder(int orderId) {
		logger.info("Start deleteOrder method: ", OrderdemoServiceImpl.class.getName());
		Response resp = new Response();
		try {
			// delete the order by Id					
			if(orderId!=0) {
				ordersRepository.deleteById(orderId);
				resp.setMessage(OrderServiceConstants.ORDER_TRANSACTION_DELETED);
			}

		} catch (Exception ex) {
			resp.setErrorMessage(OrderServiceConstants.ERROR_RESPONSE);
			logger.debug("Error occured: ", ex.getMessage());
		}
		logger.info("End deleteOrder method: ", OrderdemoServiceImpl.class.getName());
		return resp;
	}
}
