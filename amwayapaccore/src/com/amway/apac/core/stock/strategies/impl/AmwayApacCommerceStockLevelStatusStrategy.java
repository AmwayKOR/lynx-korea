/**
 *
 */
package com.amway.apac.core.stock.strategies.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.Collection;

import com.amway.core.stock.strategies.impl.AmwayCommerceStockLevelStatusStrategy;


/**
 *
 */
public class AmwayApacCommerceStockLevelStatusStrategy extends AmwayCommerceStockLevelStatusStrategy
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.core.stock.strategies.impl.AmwayCommerceStockLevelStatusStrategy#checkStatus(de.hybris.platform.
	 * ordersplitting.model.StockLevelModel)
	 */
	@Override
	public StockLevelStatus checkStatus(final StockLevelModel stockLevel)
	{
		StockLevelStatus resultStatus;
		if ((null != stockLevel) && (InStockStatus.BACKORDER.equals(stockLevel.getInStockStatus())))
		{
			resultStatus = StockLevelStatus.BACKORDER;
		}
		else if ((null != stockLevel) && (InStockStatus.NOTYETAVAILABLE.equals(stockLevel.getInStockStatus())))
		{
			resultStatus = StockLevelStatus.NOTYETAVAILABLE;
		}
		else if ((null != stockLevel) && (InStockStatus.TEMPORARYNOTAVAILABLE.equals(stockLevel.getInStockStatus())))
		{
			resultStatus = StockLevelStatus.TEMPORARYNOTAVAILABLE;
		}
		else
		{
			resultStatus = super.checkStatus(stockLevel);
		}
		return resultStatus;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.core.stock.strategies.impl.AmwayCommerceStockLevelStatusStrategy#checkStatus(java.util.Collection)
	 */
	@Override
	public StockLevelStatus checkStatus(final Collection<StockLevelModel> stockLevels)
	{
		StockLevelStatus resultStatus = StockLevelStatus.OUTOFSTOCK;

		for (final StockLevelModel level : stockLevels)
		{
			final StockLevelStatus tmpStatus = checkStatus(level);
			if (StockLevelStatus.INSTOCK.equals(tmpStatus) || StockLevelStatus.BACKORDER.equals(tmpStatus)
					|| StockLevelStatus.TEMPORARYNOTAVAILABLE.equals(tmpStatus) || StockLevelStatus.NOTYETAVAILABLE.equals(tmpStatus)
					|| StockLevelStatus.NOLONGERAVAILABLE.equals(tmpStatus))
			{
				resultStatus = tmpStatus;
				break;
			}
			else if (StockLevelStatus.LOWSTOCK.equals(tmpStatus))
			{
				resultStatus = tmpStatus;
			}
		}
		return resultStatus;
	}
}
