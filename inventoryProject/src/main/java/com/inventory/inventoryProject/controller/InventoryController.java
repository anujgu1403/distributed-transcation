package com.inventory.inventoryProject.controller;

import com.inventory.inventoryProject.model.InventoryRequest;
import com.inventory.inventoryProject.model.InventoryResponse;
import com.inventory.inventoryProject.model.Response;
import com.inventory.inventoryProject.domain.InventoryServiceConstants;
import com.inventory.inventoryProject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inventory")
public class InventoryController {


    @Autowired
    InventoryService inventoryService;


    @GetMapping(path = "/inventory/{productId}")
    public ResponseEntity<?> getQuantityByProductId(@PathVariable(value = "productId") String productId) {

        System.out.println("getQuantityByProductId deatils");

        InventoryResponse inventoryResponse = inventoryService.findQuantityByProductId(productId);

        return new ResponseEntity<InventoryResponse>(inventoryResponse, HttpStatus.OK);
    }




    @PostMapping(path = "/update")
    public ResponseEntity<?> updateInventoryQty(@RequestBody InventoryRequest inventoryRequest) {
        System.out.println("updateInventoryQty deatils "+inventoryRequest.getProductId()+"quantity"+inventoryRequest.getQuantity());

        Response resp = inventoryService.updateInventory(inventoryRequest.getProductId(),inventoryRequest.getQuantity());
        if(null!=resp.getErrorMessage() && resp.getErrorMessage().equalsIgnoreCase(InventoryServiceConstants.ERROR_RESPONSE)){
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<Response>(resp, HttpStatus.CREATED);


    }


}
