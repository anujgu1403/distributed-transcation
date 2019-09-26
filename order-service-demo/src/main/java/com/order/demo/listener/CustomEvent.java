package com.order.demo.listener;

import org.springframework.context.ApplicationEvent;
import com.order.demo.model.Response;
import lombok.Data;

@Data
public class CustomEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private String productId;
	private int quantity;
	private double amount;
	private String  event;
	private Response response;
	
	public CustomEvent(Object source, int orderId, String productId, int quantity, double amount, String event, Response response) {
		super(source);
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.amount = amount;
		this.event = event;
		this.response= response;
	}
}
