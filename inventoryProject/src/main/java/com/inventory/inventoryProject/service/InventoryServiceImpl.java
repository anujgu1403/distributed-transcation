package com.inventory.inventoryProject.service;


import com.inventory.inventoryProject.domain.Inventory;
import com.inventory.inventoryProject.model.InventoryResponse;
import com.inventory.inventoryProject.model.Response;
import com.inventory.inventoryProject.repository.InventoryRepository;
import com.inventory.inventoryProject.domain.InventoryServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements  InventoryService {

    @Autowired
InventoryRepository inventoryRepository;

    Response response=new Response();

    InventoryResponse inventoryResponse=new InventoryResponse();

    @Override
    public InventoryResponse findQuantityByProductId(String productId) {

        Inventory quantity = inventoryRepository.findQuantityByProductId(productId);
        inventoryResponse.setTotalquantity(quantity);


        return inventoryResponse;
    }

    @Override
    public Response updateInventory(String productId, int quantity) {


        System.out.println(" productId deatils "+productId);

        System.out.println(" quantity deatils "+quantity);
        String totalInventory=null;
        try {
            Inventory inventory = inventoryRepository.findQuantityByProductId(productId);
            System.out.println("inventory deatils from db "+inventory);
           /* if(inventory!=null) {
                totalInventory = inventory.toString();
            }
            Integer total = Integer.valueOf(totalInventory);
            Integer newquantity = total - quantity;

            System.out.println("newquantity deatils "+newquantity);
            response.setProductId(productId);
            response.setQuantity(newquantity);
            response.setMessage(InventoryServiceConstants.UPDATED_SUCCESFULLY);
            //response = inventoryRepository.save(response);*/
        }
        catch (Exception ex) {
            System.out.println("exception orrcured"+ex.getMessage());
            response.setErrorMessage(InventoryServiceConstants.ERROR_RESPONSE);

        }

        return response;
    }
}
