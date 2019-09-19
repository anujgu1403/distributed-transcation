package com.order.demo.model;

import lombok.Data;

/**
 * @author Anuj Kumar
 * 
 * This class represent order request which holds parameters from front end
 */
@Data
public class OrderRequest {
	private int orderId;
	private String productId;
	private int quantity;
	private double totalPrice;
}
