package com.amway.amwayinventory.strategy.stock;


import de.hybris.platform.ordersplitting.model.StockLevelModel;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;


/**
 * Strategy for calculation of stock level on inventory updates processes.
 */
public interface AmwayInventoryStockChangeStrategy
{
	/**
	 * Change stock level by data form {@link AmwayConsolidatedInventoryBean}
	 *
	 * @param consolidatedInventoryBean
	 * 		contains data to perform change
	 * @return updated or created stock level
	 */
	StockLevelModel changeStockLevel(AmwayConsolidatedInventoryBean consolidatedInventoryBean);
}
