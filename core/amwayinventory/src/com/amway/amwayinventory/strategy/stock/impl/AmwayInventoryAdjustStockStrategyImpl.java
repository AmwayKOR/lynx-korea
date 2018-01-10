package com.amway.amwayinventory.strategy.stock.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.service.stock.AmwayInventoryStockService;
import com.amway.amwayinventory.strategy.stock.AmwayInventoryStockChangeStrategy;


/**
 * Strategy for calculation of stock levels in delta updates by adjustment from {@link AmwayConsolidatedInventoryBean}.
 */
public class AmwayInventoryAdjustStockStrategyImpl implements AmwayInventoryStockChangeStrategy
{
	@Autowired
	private AmwayInventoryStockService amwayInventoryStockService;

	@Override
	public StockLevelModel changeStockLevel(AmwayConsolidatedInventoryBean consolidatedInventoryBean)
	{
		String productCode = consolidatedInventoryBean.getProductCode();
		String warehouseCode = consolidatedInventoryBean.getWarehouseCode();
		Integer adjustment = consolidatedInventoryBean.getAmount();
		StockLevelModel currentStock = amwayInventoryStockService.getStockLevel(productCode, warehouseCode);
		if (currentStock == null)
		{
			Integer available = calculateAvailable(adjustment);
			return amwayInventoryStockService.createStockLevel(productCode, warehouseCode, available);
		}
		Integer adjustedStock = currentStock.getAvailable() + adjustment;
		currentStock.setAvailable(calculateAvailable(adjustedStock));
		return currentStock;
	}

	private Integer calculateAvailable(Integer updatedValue)
	{
		return updatedValue > 0 ? updatedValue : 0;
	}
}
