package com.example.test.payment.Domain;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
@Table(name="paymentdetails")
public class PaymentDetails implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String orderId;
    private double amount;
}
