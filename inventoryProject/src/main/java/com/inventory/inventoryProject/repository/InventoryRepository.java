package com.inventory.inventoryProject.repository;


import com.inventory.inventoryProject.domain.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {


    @Query("select i.quantity from Inventory i where i.productId = :productId")
    Inventory findQuantityByProductId(@Param("productId") String productId);


}
