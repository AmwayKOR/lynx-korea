package com.amway.amwayinventory.service.stock;

import java.util.Collection;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryBean;


/**
 * Service to consolidate inventory beans
 */
public interface AmwayStockConsolidationService
{
	/**
	 * Consolidate inventory beans
	 * @param inventoryBeans - inventory beans
	 * @return consolidated inventory beans
	 */
	Collection<AmwayConsolidatedInventoryBean> consolidateInventory(Collection<AmwayInventoryBean> inventoryBeans);
}
