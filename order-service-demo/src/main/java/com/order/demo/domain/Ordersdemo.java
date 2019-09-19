package com.order.demo.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author Anuj Kumar
 * 
 * This class represent orders entity table
 */
@Data
@Entity
@Table(name = "ordersdemo")
public class Ordersdemo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productId;
	private double totalPrice;
	private String status;
	private int quantity;
	private Date timePlaced;
	private Date timeUpdate;
	
	@PrePersist
	protected void onCreate() {
		this.timePlaced = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.timeUpdate = new Date();
	}
}
