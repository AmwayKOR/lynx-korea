package com.amway.apac.core.stock.strategies.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.amway.apac.core.stock.strategies.AmwayApacCommerceStockLevelStatusStrategy;
import com.amway.core.stock.strategies.impl.AmwayCommerceStockLevelStatusStrategy;


/**
 * Commerce stock level status strategy overridden to add custom InStockStatus at APAC level
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacCommerceStockLevelStatusStrategy extends AmwayCommerceStockLevelStatusStrategy
		implements AmwayApacCommerceStockLevelStatusStrategy
{
	/**
	 * Returns Stock level status enum for provided stock level model.
	 *
	 * @param stockLevel
	 *           the stock level
	 * @return the stock level status
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
		else if ((null != stockLevel) && (InStockStatus.SHIPELSEBACKORDER.equals(stockLevel.getInStockStatus())))
		{
			resultStatus = stockLevel.getAvailable() > 0 ? StockLevelStatus.INSTOCK : StockLevelStatus.BACKORDER;
		}
		else
		{
			resultStatus = super.checkStatus(stockLevel);
		}
		return resultStatus;
	}

	/**
	 * Returns Stock level status enum for provided stock level model collection.
	 *
	 * @param stockLevels
	 *           the stock levels
	 * @return the stock level status
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


	/**
	 * {@inheritDoc}
	 */
	@Override
	public StockLevelStatus evaluateKitProductStockStatus(final Set<StockLevelStatus> majorItemsStatusSet,
			final Set<StockLevelStatus> minorItemsStatusSet)
	{
		StockLevelStatus finalStatus = null;
		if (CollectionUtils.isNotEmpty(majorItemsStatusSet))
		{
			if (majorItemsStatusSet.contains(StockLevelStatus.TEMPORARYNOTAVAILABLE))
			{
				finalStatus = StockLevelStatus.TEMPORARYNOTAVAILABLE;
			}
			else if (majorItemsStatusSet.contains(StockLevelStatus.NOTYETAVAILABLE))
			{
				finalStatus = StockLevelStatus.NOTYETAVAILABLE;
			}
			else if (majorItemsStatusSet.contains(StockLevelStatus.NOLONGERAVAILABLE))
			{
				finalStatus = StockLevelStatus.NOLONGERAVAILABLE;
			}
			else if ((majorItemsStatusSet.size() == 1) && (majorItemsStatusSet.contains(StockLevelStatus.BACKORDER)))
			{
				finalStatus = StockLevelStatus.BACKORDER;
			}
		}
		else if ((minorItemsStatusSet.size() == 1) && (minorItemsStatusSet.contains(StockLevelStatus.BACKORDER)))
		{
			finalStatus = StockLevelStatus.BACKORDER;
		}

		return finalStatus;
	}
}
