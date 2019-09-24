package com.order.demo.util;

/**
 * @author Anuj Kumar
 * 
 *  This is keep all the constants
 */
public class OrderServiceConstants {
	
	public static final int TAX_RATE = 7;
	public static final double SHIPPING_CHARGE = 10.00;
	public static final double FREE_SHIPPING = 0.00;
	public static final int SHIPPING_TAX_RATE = 2;
	public static final String PENDING_ORDER = "P";
	public static final String CONFIRMED_ORDER = "C";
	public static final String CURRENCY = "USD";
	public static final String FULLFILLMENT_TYPE = "ShipToHome";
	public static final String SEARCH_SERVICE_URL = "${search.service.url}";
	public static final String ERROR_RESPONSE = "Service unavailable.";
	public static final String ORDER_DELETED = "No more items in order hence order is deleted";
	public static final String ORDERITEM_DELETED = "Order item is deleted";
	public static final String ORDERITEM_ADDED = "Item added into cart";
	public static final String ORDERITEM_UPDATED = "Item updated";
	public static final String ORDER_PLACED = "Order submitted successfully";
	public static final String ORDER_DETAILS = "Order details";
	public static final String ORDERS_LIST = "Listed are orders for customer";
	public static final String ORDER_NOT_FOUND = "No matching order found.";
	public static final String TEST_COMMIT= "TEST_COMMIT";
	public static final String ORDER_CREATED_EVENT="OrderCreated";
	public static final String INVENTORY_UPDATED="InventoryUpdated";
	public static final String INVENTORY_UPDATION_FAILED="InventoryUpdationFailed";
	public static final String PAYMENT_UPDATED="PaymentUpdated";
	public static final String PAYMENT_UPDATED_FAILED="PaymentUpdatedFailed";
	public static final String ORDER_CONFIM="OrderConfirmed";
	public static final String ORDER_CONFIM_FAILED="OrderConfirmedFailed";
}