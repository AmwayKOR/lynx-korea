package com.amway.amwayinventory.service.stock;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

/**
 * Service to manage stock levels by product code and warehouse code
 */
public interface AmwayInventoryStockService
{
	/**
	 * Get stock level by product and warehouse code
	 * @param productCode - product code
	 * @param warehouseCode - warehouse code
	 * @return stock level
	 */
	StockLevelModel getStockLevel(String productCode, String warehouseCode);

	/**
	 * Create stock level by product code, warehouse code and amount available
	 * @param productCode - product code
	 * @param warehouseCode - warehouse code
	 * @param available - amount available
	 * @return stock level
	 */
	StockLevelModel createStockLevel(String productCode, String warehouseCode, Integer available);
}
