package com.inventory.inventoryProject.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;



@JsonIgnoreProperties(ignoreUnknown =true)
@JsonAutoDetect(fieldVisibility =JsonAutoDetect.Visibility.ANY)
@JsonRootName(value = "response")
public class Response {

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("message")
    private String message;

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("quantity")
    private int quantity;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
