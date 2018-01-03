/**
 *
 */
package com.amway.apac.core.stock.strategies.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.warehousing.atp.strategy.impl.WarehousingAvailabilityCalculationStrategy;

import java.util.Collection;


public class AmwayApacCommerceAvailabilityCalculationStrategy extends WarehousingAvailabilityCalculationStrategy
{
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.stock.strategies.impl.DefaultCommerceAvailabilityCalculationStrategy#
	 * calculateAvailability(java.util.Collection)
	 */
	@Override
	public Long calculateAvailability(final Collection<StockLevelModel> stockLevels)
	{
		long totalActualAmount = 0;
		for (final StockLevelModel stockLevel : stockLevels)
		{
			// If any stock level is flagged as FORCEINSTOCK or BACKORDER then return null to indicate in stock
			if (InStockStatus.FORCEINSTOCK.equals(stockLevel.getInStockStatus())
					|| InStockStatus.BACKORDER.equals(stockLevel.getInStockStatus()))
			{
				return null;
			}

			// If any stock level is flagged as FORCEOUTOFSTOCK then we skip over it
			if (!(InStockStatus.FORCEOUTOFSTOCK.equals(stockLevel.getInStockStatus())
					|| InStockStatus.TEMPORARYNOTAVAILABLE.equals(stockLevel.getInStockStatus())
					|| InStockStatus.NOTYETAVAILABLE.equals(stockLevel.getInStockStatus())
					|| InStockStatus.NOLONGERAVAILABLE.equals(stockLevel.getInStockStatus())))
			{
				final long availableToSellQuantity = getAvailableToSellQuantity(stockLevel);
				if (availableToSellQuantity > 0 || !stockLevel.isTreatNegativeAsZero())
				{
					totalActualAmount += availableToSellQuantity;
				}
			}
		}
		return Long.valueOf(totalActualAmount);
	}
}
