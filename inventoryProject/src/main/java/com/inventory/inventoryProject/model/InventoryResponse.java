package com.inventory.inventoryProject.model;

import com.inventory.inventoryProject.domain.Inventory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown =true)
@JsonAutoDetect(fieldVisibility =JsonAutoDetect.Visibility.ANY)
@JsonRootName(value = "inventoryresponse")
public class InventoryResponse {

    @JsonProperty("totalquantity")
    private Inventory totalquantity;

    public Inventory getTotalquantity() {
        return totalquantity;
    }

    public void setTotalquantity(Inventory totalquantity) {
        this.totalquantity = totalquantity;
    }
}
