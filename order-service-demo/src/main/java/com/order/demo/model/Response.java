package com.order.demo.model;

import java.util.Date;
import lombok.Data;

/**
 * @author Anuj Kumar
 * 
 * This class represent final response which to be consumed by front end
 */
@Data
public class Response {
	private String errorMessage;
	private String message;
	private int orderId;
	private int quantity;
	private String productId;
	private double totalPrice;
	private String status;
	private Date timePlaced;
	private Date timeUpdate;
}
