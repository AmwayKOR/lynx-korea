package com.amway.apac.core.stock.strategies.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.warehousing.atp.strategy.impl.WarehousingAvailabilityCalculationStrategy;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;
import de.hybris.platform.warehousing.model.InventoryEventModel;
import de.hybris.platform.warehousing.model.ShrinkageEventModel;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * Availability calculation strategy overridden to add custom InStockStatus at APAC level
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacCommerceAvailabilityCalculationStrategy extends WarehousingAvailabilityCalculationStrategy
{
	private InventoryEventService inventoryEventService;

	/**
	 * Returns available quantity for provided stock level models.
	 *
	 * @param stockLevels
	 *           the stock levels
	 * @return Available quantity
	 */
	@Override
	public Long calculateAvailability(final Collection<StockLevelModel> stockLevels)
	{
		long totalActualAmount = 0;
		for (final StockLevelModel stockLevel : stockLevels)
		{
			// If any stock level is flagged as FORCEINSTOCK or BACKORDER then return null to indicate in stock
			if (InStockStatus.FORCEINSTOCK.equals(stockLevel.getInStockStatus()))
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
	 * evaluates and returns the amount of inventory available for stock levels
	 *
	 * @param stockLevel
	 *           - The stock level model
	 * @param inventoryEventClass
	 *           - The inventory event class
	 * @return int - quantity of that inventory event in stock level
	 */
	private int getInventoryEventQuantity(final StockLevelModel stockLevel,
			final Class<? extends InventoryEventModel> inventoryEventClass)
	{
		int totalInventoryEventQuantity = 0;
		final Collection<? extends InventoryEventModel> inventoryEvents = inventoryEventService
				.getInventoryEventsForStockLevel(stockLevel, inventoryEventClass);
		if (CollectionUtils.isNotEmpty(inventoryEvents))
		{
			totalInventoryEventQuantity = (int) inventoryEvents.stream().mapToLong(InventoryEventModel::getQuantity).sum();
		}
		return totalInventoryEventQuantity;
	}

	/**
	 * evaluates the availability of stock
	 *
	 * @param stockLevel
	 * @return boolean - true, (if status is BackOrder) or (status is shipElseBackOrder with quantity less than equal to
	 *         0), else returns false
	 */
	private boolean isBackOrderStock(final StockLevelModel stockLevel)
	{
		return (stockLevel.getInStockStatus().equals(InStockStatus.BACKORDER))
				|| (stockLevel.getInStockStatus().equals(InStockStatus.SHIPELSEBACKORDER) && stockLevel.getAvailable() <= 0);
	}

	/**
	 * @return the inventoryEventService
	 */
	public InventoryEventService getInventoryEventService()
	{
		return inventoryEventService;
	}

	/**
	 * @param inventoryEventService
	 *           the inventoryEventService to set
	 */
	@Required
	public void setInventoryEventService(final InventoryEventService inventoryEventService)
	{
		this.inventoryEventService = inventoryEventService;
	}
}
