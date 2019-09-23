package com.example.test.payment.Domain;


import lombok.Data;

@Data
public class PaymentRequest {

    private String orderId;    
    private double amount;
}
