package com.order.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.demo.domain.Ordersdemo;
import com.order.demo.model.OrderRequest;
import com.order.demo.model.Response;
import com.order.demo.repository.OrdersdemoRepository;
import com.order.demo.util.CartServiceConstants;

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
			order.setStatus("P");
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
			resp.setErrorMessage(CartServiceConstants.ERROR_RESPONSE);
			logger.debug("Error occured: ", ex.getMessage());
		}
		logger.info("End createOrder method: ", OrderdemoServiceImpl.class.getName());
		return resp;
	}

	/**
	 * This method is used update the quantity of order item
	 * 
	 * @param orderItemId
	 * @param quantity
	 * @return Response
	 *//*
		 * @Override public Response updateOrder(int orderItemId, int quantity) {
		 * logger.info("Start updateOrder method: ", CartServiceImpl.class.getName());
		 * Response resp = new Response(); Orders order = new Orders(); try { // Find
		 * order item and update the quantity OrderItems orderItem =
		 * orderItemsRepository.findOrderItemsId(orderItemId); double unitPrice =
		 * orderItem.getPrice() / orderItem.getQuantity();
		 * orderItem.setQuantity(quantity); orderItem.setPrice(unitPrice * quantity);
		 * orderItem = orderItemsRepository.save(orderItem);
		 * 
		 * // To prepare response with order & order items order =
		 * ordersRepository.findOrderByOrdersId(orderItem.getOrders().getId());
		 * List<OrderItems> ordItemList =
		 * orderItemsRepository.findOrderItemsByOrdersId(order);
		 * 
		 * // To re-calculate order & update the DB order =
		 * ordersRepository.save(CartServiceUtil.recalculateOrder(order, ordItemList));
		 * resp = CartServiceUtil.prepareFinalOrderResponse(order, ordItemList);
		 * resp.setMessage(CartServiceConstants.ORDERITEM_UPDATED); } catch (Exception
		 * ex) { resp.setErrorMessage(CartServiceConstants.ERROR_RESPONSE);
		 * logger.debug("Error occured: ", ex.getMessage()); }
		 * logger.info("End updateOrder method: ", CartServiceImpl.class.getName());
		 * return resp; }
		 */
}
