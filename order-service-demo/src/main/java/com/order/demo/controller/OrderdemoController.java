package com.order.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.order.demo.model.OrderRequest;
import com.order.demo.model.Response;
import com.order.demo.service.OrderdemoService; 

/**
 * @author Anuj Kumar
 * 
 *         This class main controller which have all the end points exposed for
 *         cart service
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class OrderdemoController {
	private static final Logger logger = LoggerFactory.getLogger(OrderdemoController.class);

	@Autowired
	OrderdemoService orderdemoService;

	/**
	 * This end point is used to add items into cart
	 * 
	 * @param OrderRequest
	 * @return ResponseEntity<?>
	 */
	@PostMapping(path = "/shoppingcart/add")
	public ResponseEntity<?> addItemToCart(@RequestBody OrderRequest orderRequest) {
		logger.info("Start addItemToCart method: ", OrderdemoController.class.getName());
		logger.debug("OrderRequest: " + orderRequest);

		Response resp = orderdemoService.createOrder(orderRequest);
		logger.debug("Response: " + resp);
		logger.info("End addItemToCart method: ", OrderdemoController.class.getName());
		return new ResponseEntity<Response>(resp, HttpStatus.CREATED);
	}

}
