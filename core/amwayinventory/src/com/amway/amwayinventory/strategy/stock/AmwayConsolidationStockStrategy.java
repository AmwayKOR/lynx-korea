package com.amway.amwayinventory.strategy.stock;


import java.util.Collection;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryBean;


/**
 * Strategy consolidating beans
 */
public interface AmwayConsolidationStockStrategy
{
	/**
	 * Consolidate inventory beans
	 * @param inventoryBeans - inventory beans
	 * @return consolidated inventory beans
	 */
	Collection<AmwayConsolidatedInventoryBean> consolidateStocks(Collection<AmwayInventoryBean> inventoryBeans);
}
