package com.order.demo.model;


import lombok.Data;

@Data
public class PaymentRequest {

    private int orderId;    
    private double amount;
}
