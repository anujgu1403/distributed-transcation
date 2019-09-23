package com.inventory.inventoryProject.controller;

import com.inventory.inventoryProject.model.InventoryRequest;
import com.inventory.inventoryProject.model.Response;
import com.inventory.inventoryProject.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	InventoryService inventoryService;

	@PostMapping(path = "/update")
	public ResponseEntity<?> updateInventoryQty(@RequestBody InventoryRequest inventoryRequest) {
		logger.info("InventoryRequest: " + inventoryRequest);
		ResponseEntity<Response> responseEntity = null;
		Response resp = inventoryService.updateInventory(inventoryRequest);

		if (resp.getCode().equals("201")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.CREATED);
		}
		if (resp.getCode().equals("400")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		if (resp.getCode().equals("500")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
