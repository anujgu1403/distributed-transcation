package com.inventory.inventoryProject.service;

import com.inventory.inventoryProject.model.Response;
import com.inventory.inventoryProject.domain.Inventory;
import com.inventory.inventoryProject.model.InventoryRequest;

public interface InventoryService {

    Response updateInventory(InventoryRequest inventoryRequest);
    
    Response revertInventory(InventoryRequest inventoryRequest);
    
    Inventory findInventoryByProductId(String productId);
    
    void clearAllCacheEntries();
}
