/**
 *
 */
package com.amway.apac.core.stock.strategies.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.warehousing.atp.strategy.impl.WarehousingAvailabilityCalculationStrategy;
import de.hybris.platform.warehousing.model.AllocationEventModel;
import de.hybris.platform.warehousing.model.InventoryEventModel;
import de.hybris.platform.warehousing.model.ShrinkageEventModel;

import java.util.Collection;


/**
 * This class is used to override the OOTB Warehousing Availability Strategy
 */
public class AmwayApacCommerceAvailabilityCalculationStrategy extends WarehousingAvailabilityCalculationStrategy
{
	/**
	 * This method is overridden as the ATP formula per base store is not supported for now
	 *
	 * @param stockLevels
	 *           - The collection of stocks for which the availability is being evaluated
	 */
	@Override
	public Long calculateAvailability(final Collection<StockLevelModel> stockLevels)
	{
		long totalActualAmount = 0;
		for (final StockLevelModel stockLevel : stockLevels)
		{
			// If any stock level is flagged as FORCEINSTOCK then return null to indicate in stock
			if (InStockStatus.FORCEINSTOCK.equals(stockLevel.getInStockStatus()))
			{
				return null;
			}

			// If any stock level is flagged as FORCEOUTOFSTOCK then we skip over it
			if (!InStockStatus.FORCEOUTOFSTOCK.equals(stockLevel.getInStockStatus()))
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


	/**
	 * Get the Available To Sell quantity for a StockLevel.
	 *
	 * Available To Sell stock consists of on-hand stock minus stock reserved for orders that have been placed and stock
	 * that have been backordered.
	 *
	 * @param stockLevel
	 *           the stock level.
	 * @return the quantity available to sell.
	 */
	@Override
	protected long getAvailableToSellQuantity(final StockLevelModel stockLevel)
	{
		int availability = stockLevel.getAvailable() - stockLevel.getReserved();
		if (isBackOrderStock(stockLevel))
		{
			availability = availability + stockLevel.getOverSelling()
					- getInventoryEventQuantity(stockLevel, AllocationEventModel.class);
		}
		else
		{
			availability = availability - getInventoryEventQuantity(stockLevel, ShrinkageEventModel.class);
		}
		return availability;
	}


	/**
	 * @param stockLevel
	 *           - The stock level model
	 * @param eventClass
	 *           - The inventory event class
	 * @return quantity of that inventory event in stock level
	 */
	private int getInventoryEventQuantity(final StockLevelModel stockLevel, final Class<? extends InventoryEventModel> eventClass)
	{
		return (int) stockLevel.getInventoryEvents().stream().filter(event -> eventClass.isInstance(event))
				.mapToLong(InventoryEventModel::getQuantity).sum();
	}


	/**
	 * @param stockLevel
	 * @return true, (if status is BackOrder) or (status is shipElseBackOrder with quantity less than equal to 0), else
	 *         returns false
	 */
	private boolean isBackOrderStock(final StockLevelModel stockLevel)
	{
		return (stockLevel.getInStockStatus().equals(InStockStatus.BACKORDER))
				|| (stockLevel.getInStockStatus().equals(InStockStatus.SHIPELSEBACKORDER) && stockLevel.getAvailable() <= 0);
	}
}
