package com.amway.amwayinventory.strategy.stock.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.service.stock.AmwayInventoryStockService;
import com.amway.amwayinventory.strategy.stock.AmwayInventoryStockChangeStrategy;


/**
 * Strategy for calculation of stock levels in full updates by available amount from {@link AmwayConsolidatedInventoryBean}.
 */
public class AmwayInventoryFullUpdateStockStrategyImpl implements AmwayInventoryStockChangeStrategy
{
	@Autowired
	private AmwayInventoryStockService amwayInventoryStockService;

	@Override
	public StockLevelModel changeStockLevel(AmwayConsolidatedInventoryBean consolidatedInventoryBean)
	{
		String productCode = consolidatedInventoryBean.getProductCode();
		String warehouseCode = consolidatedInventoryBean.getWarehouseCode();
		Integer amount = consolidatedInventoryBean.getAmount();
		StockLevelModel currentStock = amwayInventoryStockService.getStockLevel(productCode, warehouseCode);
		if (currentStock == null)
		{
			return amwayInventoryStockService.createStockLevel(productCode, warehouseCode, amount);
		}
		currentStock.setAvailable(amount);
		return currentStock;
	}
}
