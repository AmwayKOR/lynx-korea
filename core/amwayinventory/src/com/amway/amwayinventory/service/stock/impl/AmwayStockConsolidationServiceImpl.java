package com.amway.amwayinventory.service.stock.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.service.stock.AmwayStockConsolidationService;
import com.amway.amwayinventory.strategy.stock.AmwayConsolidationStockStrategy;


/**
 * Consolidate beans by strategy
 */
public class AmwayStockConsolidationServiceImpl implements AmwayStockConsolidationService
{
	@Autowired
	private AmwayConsolidationStockStrategy amwayConsolidationStockStrategy;

	@Override
	public Collection<AmwayConsolidatedInventoryBean> consolidateInventory(Collection<AmwayInventoryBean> inventoryBeans)
	{
		return amwayConsolidationStockStrategy.consolidateStocks(inventoryBeans);
	}
}
