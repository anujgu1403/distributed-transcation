package com.inventory.inventoryProject.service;

import com.inventory.inventoryProject.domain.Inventory;
import com.inventory.inventoryProject.model.InventoryRequest;
import com.inventory.inventoryProject.model.Response;
import com.inventory.inventoryProject.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	@Override
	public Response revertInventory(InventoryRequest inventoryRequest) {

		logger.info("Request: "+inventoryRequest);
		Response response = new Response();
		try {
			if (null != inventoryRequest) {
				if (!inventoryRequest.getProductId().isEmpty()) {
					// find the available inventory of given product.
					Inventory inventoryData = inventoryRepository
							.findQuantityByProductId(inventoryRequest.getProductId());
					logger.info("inventoryData: "+inventoryData);
		
					if (inventoryRequest.getQuantity() >0) {
						int totalQty = inventoryData.getQuantity() + inventoryRequest.getQuantity();

						// update the qty in database
						inventoryData.setQuantity(totalQty);
						inventoryRepository.save(inventoryData);
						response.setCode("201");
						response.setMessage("Successfully reverted quantity.");
					} else {
						response.setCode("400");
						response.setMessage("Product is not having sufficient quantity.");
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while reverting quantity: ", e.getCause());
			response.setCode("500");
			response.setMessage("Error while reverting quantity.");
		}
		logger.info("Response: "+response);
		return response;
	}
	
	@Cacheable("inventory")
	public Inventory findInventoryByProductId(String productId) {
		try {
			System.out.println("Making thread sleep for some time.");
			Thread.sleep(1000);
			clearAllCacheEntries();
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		return inventoryRepository.findQuantityByProductId(productId);
	}
	
	@CacheEvict(value="inventory", allEntries = true)
	public void clearAllCacheEntries() {
		System.out.println("Cleared all cache entries.");
	}
}
