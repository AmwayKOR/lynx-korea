/**
 *
 */
package com.amway.apac.core.stock.strategies.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.Objects;

import com.amway.core.stock.strategies.impl.AmwayCommerceStockLevelStatusStrategy;


/**
 * Created to override methods for Amway StockLevelStatus Strategy
 */
public class AmwayApacCommerceStockLevelStatusStrategy extends AmwayCommerceStockLevelStatusStrategy
{

	/**
	 * Method to identify the stock level status
	 */
	@Override
	public StockLevelStatus checkStatus(final StockLevelModel stockLevel)
	{
		// Stock level to be considered as SHIPPED if qty is greater than 0, else consider it as BACKORDER
		if (Objects.nonNull(stockLevel) && (InStockStatus.SHIPELSEBACKORDER.equals(stockLevel.getInStockStatus())))
		{
			return stockLevel.getAvailable() > 0 ? StockLevelStatus.INSTOCK : StockLevelStatus.BACKORDER;
		}
		else
		{
			return super.checkStatus(stockLevel);
		}
	}
}
