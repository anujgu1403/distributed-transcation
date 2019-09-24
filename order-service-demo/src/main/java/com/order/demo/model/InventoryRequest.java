package com.order.demo.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class InventoryRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productId;
    private int quantity;
}





