/**
 *
 */
package com.amway.apac.core.backorder.strategies.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.stock.StockService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao;
import com.amway.apac.core.backorder.service.AmwayApacOrderService;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;


/**
 * Created to fetch all the available back orders for release in fifo
 *
 * @author ankushbhatia
 */
public class DefaultAmwayApacBackOrderSelectionStrategy implements AmwayApacBackOrderSelectionStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacBackOrderSelectionStrategy.class);
	private AmwayApacBackOrderDao amwayApacBackOrderDao;
	private StockService stockService;
	private AmwayApacOrderService amwayApacOrderService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final List<StockLevelModel> stockLevels)
	{
		if (CollectionUtils.isNotEmpty(stockLevels))
		{
			try
			{
				final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = new HashMap<StockLevelModel, List<AmwayBackOrderModel>>();
				for (final StockLevelModel stockLevel : stockLevels)
				{
					final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao()
							.getBackOrders(AmwayBackOrderStatus.ACTIVE, stockLevel.getWarehouse(), stockLevel.getProduct(), true, null);
					//Verify the payment of backOrders
					validatePaymentAndUpdateList(amwayBackOrdersList);
					amwayBackOrdersMap.put(stockLevel, amwayBackOrdersList);

				}
				return amwayBackOrdersMap;
			}
			catch (final Exception e)
			{
				logDebugInfo(e, "Failed to fetch Amway Back Orders ");
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final BaseSiteModel baseSite)
	{
		try
		{
			final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao()
					.getBackOrders(AmwayBackOrderStatus.ACTIVE, null, null, true, baseSite);
			validatePaymentAndUpdateList(amwayBackOrdersList);
			//Grouping the AmwayBackOrders as per the stockLevels for stock calculation
			final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = amwayBackOrdersList.stream()
					.collect(Collectors.groupingBy(backOrder -> getStockLevel(backOrder), Collectors.toList()));
			return amwayBackOrdersMap;
		}
		catch (final Exception e)
		{
			logDebugInfo(e, "Failed to fetch Amway Back Orders ");
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrdersForExpiring(final AmwayBackOrderStatus status, final Date date)
	{
		final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao().getBackOrdersForExpiring(status, date);
		return amwayBackOrdersList;
	}


	/**
	 * Used to get Stocklevel for AmwayBackOrder product and warehouse
	 *
	 * @param backOrder
	 * @return StockLevelModel
	 */
	private StockLevelModel getStockLevel(final AmwayBackOrderModel backOrder)
	{
		if (Objects.nonNull(backOrder))
		{
			return getStockService().getStockLevel(backOrder.getProduct(), backOrder.getWarehouse());
		}
		return null;
	}


	/**
	 * Validate payment for Amway backOrders
	 *
	 * @param amwayBackOrdersList
	 */
	private List<AmwayBackOrderModel> validatePaymentAndUpdateList(final List<AmwayBackOrderModel> amwayBackOrdersList)
	{
		final List<AmwayBackOrderModel> amwayBackOrdersNewList = new ArrayList<AmwayBackOrderModel>();
		for (final AmwayBackOrderModel amwayBackOrder : amwayBackOrdersList)
		{
			if (getAmwayApacOrderService().isOrderPaymentCaptured(amwayBackOrder.getOriginalOrder()))
			{
				amwayBackOrdersNewList.add(amwayBackOrder);
			}
		}
		return amwayBackOrdersNewList;
	}

	/**
	 * To print log in debug mode
	 *
	 * @param exc
	 * @param message
	 */
	private void logDebugInfo(final Exception exc, final String message)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(message, exc);
		}
	}

	/**
	 * @return the amwayApacBackOrderDao
	 */
	public AmwayApacBackOrderDao getAmwayApacBackOrderDao()
	{
		return amwayApacBackOrderDao;
	}

	/**
	 * @param amwayApacBackOrderDao
	 *           the amwayApacBackOrderDao to set
	 */
	@Required
	public void setAmwayApacBackOrderDao(final AmwayApacBackOrderDao amwayApacBackOrderDao)
	{
		this.amwayApacBackOrderDao = amwayApacBackOrderDao;
	}

	/**
	 * @return the stockService
	 */
	public StockService getStockService()
	{
		return stockService;
	}

	/**
	 * @param stockService
	 *           the stockService to set
	 */
	@Required
	public void setStockService(final StockService stockService)
	{
		this.stockService = stockService;
	}

	/**
	 * @return the amwayApacOrderService
	 */
	public AmwayApacOrderService getAmwayApacOrderService()
	{
		return amwayApacOrderService;
	}

	/**
	 * @param amwayApacOrderService
	 *           the amwayApacOrderService to set
	 */
	@Required
	public void setAmwayApacOrderService(final AmwayApacOrderService amwayApacOrderService)
	{
		this.amwayApacOrderService = amwayApacOrderService;
	}
}
