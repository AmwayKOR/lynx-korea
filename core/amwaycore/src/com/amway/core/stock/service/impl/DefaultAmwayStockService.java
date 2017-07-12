package com.amway.core.stock.service.impl;


import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.stock.exception.StockLevelNotFoundException;
import de.hybris.platform.stock.impl.DefaultStockService;
import de.hybris.platform.util.Utilities;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.model.AmwayKitProductModel;
import com.amway.core.stock.dao.AmwayStockLevelDao;
import com.amway.core.stock.service.AmwayStockService;


/**
 *
 * Default Implementation.
 *
 */
public class DefaultAmwayStockService extends DefaultStockService implements AmwayStockService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayStockService.class.getName());

	private AmwayStockLevelDao amwayStockLevelDao;
	private final String EXCEPTIONMESSAGE = "SKUID or VERSIONID cannot be empty.";

	/**
	 * Reserving stock amount for product and skuId.
	 *
	 * @param product
	 * @param warehouse
	 * @param amount
	 * @param skuId
	 *
	 * @throws InsufficientStockLevelException
	 */
	@Override
	public void reserve(final ProductModel product, final WarehouseModel warehouse, final int amount, final String skuId)
			throws InsufficientStockLevelException
	{
		if (!(product instanceof AmwayKitProductModel))
		{
			if (amount <= 0)
			{
				throw new IllegalArgumentException("amount must be greater than zero.");
			}
			if (StringUtils.isBlank(skuId))
			{
				throw new IllegalArgumentException(EXCEPTIONMESSAGE);
			}

			final StockLevelModel currentStockLevel = checkAndGetStockLevel(product, warehouse, skuId);

				LOG.info("Reserving stock amount of " + amount + " for product " + product.getCode() + " and skuId " + skuId);
				final Integer reserved = getAmwayStockLevelDao().reserve(currentStockLevel, amount);
				if (reserved == null)
				{
					throw new InsufficientStockLevelException("insufficient available amount for stock level ["
							+ currentStockLevel.getPk() + "]");

				}

			clearCacheForItem(currentStockLevel);
			//createStockLevelHistoryEntry(currentStockLevel, StockLevelUpdateType.CUSTOMER_RESERVE, reserved.intValue(), skuId); TODO: do we need history here?
		}
	}

	/**
	 * Releasing stock amount for product and skuId.
	 *
	 * @param product
	 * @param warehouse
	 * @param amount
	 * @param skuId
	 */
	@Override
	public void release(final ProductModel product, final WarehouseModel warehouse, final int amount, final String skuId)
	{
		if (!(product instanceof AmwayKitProductModel))
		{
			if (amount <= 0)
			{
				throw new IllegalArgumentException("amount must be greater than zero.");
			}
			final StockLevelModel currentStockLevel = checkAndGetStockLevel(product, warehouse, skuId);
			LOG.info("Releasing stock amount of " + amount + " for product " + product.getCode() + " and skuId " + skuId);
			final Integer reserved = getAmwayStockLevelDao().release(currentStockLevel, amount);
			if (reserved == null)
			{
				throw new SystemException("release failed for stock level [" + currentStockLevel.getPk() + "]!");

			}
			clearCacheForItem(currentStockLevel);
		}
	}

	/**
	 * This Method to commit stock for an order entry
	 *
	 * {@link #commit(de.hybris.platform.core.model.order.AbstractOrderEntryModel, de.hybris.platform.ordersplitting.model.WarehouseModel, int, java.lang.String)}
	 */
	@Override
	public void commit(final AbstractOrderEntryModel abstractOrderEntryModel, final WarehouseModel warehouse, final int amount,
			final String skuId)
	{
		if (!(abstractOrderEntryModel.getProduct() instanceof AmwayKitProductModel))
		{
			if (StringUtils.isBlank(skuId))
			{
				throw new IllegalArgumentException(EXCEPTIONMESSAGE);
			}
			final StockLevelModel currentStockLevel = checkAndGetStockLevel(abstractOrderEntryModel.getProduct(), warehouse, skuId);

			if (!InStockStatus.BACKORDER.equals(currentStockLevel.getInStockStatus()))
			{
				LOG.info("Commiting stock amount of " + amount + " for product " + abstractOrderEntryModel.getProduct().getCode()
						+ " and skuId " + skuId);
				getAmwayStockLevelDao().commit(currentStockLevel, amount);
			}

			clearCacheForItem(currentStockLevel);
		}
	}

	/**
	 * This Method to adjust/increase the stock level of returned item.
	 *
	 * {@link #adjust(de.hybris.platform.returns.model.ReturnEntryModel)}
	 */
	@Override
	public void adjust(final ReturnEntryModel returnEntryModel)
	{
		final ReturnRequestModel returnRequestModel = returnEntryModel.getReturnRequest();
		final AbstractOrderEntryModel abstractOrderEntryModel = returnEntryModel.getOrderEntry();
		if (StringUtils.isBlank(abstractOrderEntryModel.getSkuVersion()))
		{
			throw new IllegalArgumentException(EXCEPTIONMESSAGE);
		}

		final int amount = -returnEntryModel.getReceivedQuantity().intValue();
		final String skuId = abstractOrderEntryModel.getSkuVersion();
		final StockLevelModel currentStockLevel = checkAndGetStockLevel(abstractOrderEntryModel.getProduct(),
				returnRequestModel.getWarehouse(), skuId);

		LOG.info("Commiting stock amount of " + returnEntryModel.getReceivedQuantity().intValue() + " for product "
				+ abstractOrderEntryModel.getProduct().getCode() + " and skuId " + skuId);
		getAmwayStockLevelDao().commit(currentStockLevel, amount);

		clearCacheForItem(currentStockLevel);
	}


	private void clearCacheForItem(final StockLevelModel stockLevel)
	{
		Utilities.invalidateCache(stockLevel.getPk());
		getModelService().refresh(stockLevel);
	}

	private StockLevelModel checkAndGetStockLevel(final ProductModel product, final WarehouseModel warehouse, final String skuId)
	{
		//TODO: use DAO instead of loop
		for (final StockLevelModel stockLevelModel : getStockLevels(product, Arrays.asList(warehouse)))
		{
			if (stockLevelModel.getSkuId() != null && stockLevelModel.getSkuId().equals(skuId))
			{
				return stockLevelModel;
			}
		}
		throw new StockLevelNotFoundException("no stock level for product [" + product + "] in warehouse [" + warehouse.getName()
				+ "] found.");
	}

	/**
	 *
	 * @return amwayStockLevelDao
	 */
	public AmwayStockLevelDao getAmwayStockLevelDao()
	{
		return amwayStockLevelDao;
	}

	/**
	 *
	 * @param amwayStockLevelDao
	 *           the amwayStockLevelDao to set
	 */
	public void setAmwayStockLevelDao(final AmwayStockLevelDao amwayStockLevelDao)
	{
		this.amwayStockLevelDao = amwayStockLevelDao;
	}
}
