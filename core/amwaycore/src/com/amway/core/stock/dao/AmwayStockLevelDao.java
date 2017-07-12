/**
 *
 */
package com.amway.core.stock.dao;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.stock.impl.StockLevelDao;


/**
 * 
 * Interface for Amway stock level.
 * 
 */
public interface AmwayStockLevelDao extends StockLevelDao
{
	/**
	 * Method to commit stock for stock level. Used once order is passed to OMS. This method updates Available Amount &
	 * Reserved Amount
	 * 
	 * @param stockLevel
	 * @param amount
	 */
	void commit(final StockLevelModel stockLevel, final int amount);
}
