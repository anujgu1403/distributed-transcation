package com.inventory.inventoryProject.service;
import com.inventory.inventoryProject.model.Response;
import org.springframework.stereotype.Service;
import com.inventory.inventoryProject.model.InventoryResponse;

public interface InventoryService {

    InventoryResponse findQuantityByProductId(String productId);
    Response updateInventory(String productId, int quantity);
}
