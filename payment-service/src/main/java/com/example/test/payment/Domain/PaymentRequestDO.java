package com.example.test.payment.Domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

//@JsonRootName("payment_request")
public class PaymentRequestDO {

    @JsonProperty
    private String userId;


    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("produtId")
    private String productId;

    @JsonProperty("price")
    private double price;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PaymentRequestDO{" +
                "quantity=" + quantity +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                '}';
    }
}
