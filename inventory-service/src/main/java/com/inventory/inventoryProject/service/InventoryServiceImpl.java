package com.inventory.inventoryProject.service;

import com.inventory.inventoryProject.domain.Inventory;
import com.inventory.inventoryProject.model.InventoryRequest;
import com.inventory.inventoryProject.model.Response;
import com.inventory.inventoryProject.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
	private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	public Response updateInventory(InventoryRequest inventoryRequest) {

		logger.info("Request: "+inventoryRequest);
		Response response = new Response();
		try {
			if (null != inventoryRequest) {
				if (!inventoryRequest.getProductId().isEmpty()) {
					// find the available inventory of given product.
					Inventory inventoryData = inventoryRepository
							.findQuantityByProductId(inventoryRequest.getProductId());
					logger.info("inventoryData: "+inventoryData);
					if (inventoryData.getQuantity() >= inventoryRequest.getQuantity()) {
						int remainingQty = inventoryData.getQuantity() - inventoryRequest.getQuantity();

						// update the qty in database
						inventoryData.setQuantity(remainingQty);
						inventoryRepository.save(inventoryData);
						response.setCode("201");
						response.setMessage("Successfully updated quantity.");
					} else {
						response.setCode("400");
						response.setMessage("Product is not having sufficient quantity.");
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while updating quantity: ", e.getCause());
			response.setCode("500");
			response.setMessage("Error while updating quantity.");
		}
		logger.info("Response: "+response);
		return response;
	}
}