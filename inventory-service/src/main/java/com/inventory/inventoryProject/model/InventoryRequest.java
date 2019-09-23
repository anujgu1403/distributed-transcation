package com.inventory.inventoryProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class InventoryRequest implements Serializable {

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("quantity")
    private int quantity;

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





