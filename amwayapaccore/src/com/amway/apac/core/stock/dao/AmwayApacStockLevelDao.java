/**
 *
 */
package com.amway.apac.core.stock.dao;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import com.amway.core.stock.dao.AmwayStockLevelDao;


/**
 * Class used to extend methods of AmwayStockLevelDao
 */
public interface AmwayApacStockLevelDao extends AmwayStockLevelDao
{

	/**
	 * Method to commit stock for stock level. This method updates only the available amount in stock level
	 *
	 * @param stockLevel
	 *           - on which the commit operation is to be performed
	 * @param amount
	 *           - The amount to be set in the Available field of Stock
	 */
	void updateAvailableAmount(final StockLevelModel stockLevel, final int amount);
}
