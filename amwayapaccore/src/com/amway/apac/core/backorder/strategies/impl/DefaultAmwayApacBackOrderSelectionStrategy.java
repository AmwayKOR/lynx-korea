/**
 *
 */
package com.amway.apac.core.backorder.strategies.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.core.order.service.AmwayApacOrderService;


/**
 * Created to fetch all the available back orders for release in fifo
 *
 * @author ankushbhatia
 */
public class DefaultAmwayApacBackOrderSelectionStrategy implements AmwayApacBackOrderSelectionStrategy
{
	private AmwayApacBackOrderDao amwayApacBackOrderDao;

	private StockService stockService;

	private AmwayApacOrderService amwayApacOrderService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final List<StockLevelModel> stockLevels)
	{
		final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = new HashMap<StockLevelModel, List<AmwayBackOrderModel>>();
		if (CollectionUtils.isNotEmpty(stockLevels))
		{
			for (final StockLevelModel stockLevel : stockLevels)
			{
				final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao().getBackOrders(
						AmwayBackOrderStatus.ACTIVE, stockLevel.getWarehouse(), stockLevel.getProduct(), null);
				//Verify the payment of backOrders
				validatePaymentAndUpdateList(amwayBackOrdersList);
				amwayBackOrdersMap.put(stockLevel, amwayBackOrdersList);
			}
		}
		return amwayBackOrdersMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final BaseStoreModel baseStore)
	{
		final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao().getBackOrders(AmwayBackOrderStatus.ACTIVE,
				null, null, baseStore);
		validatePaymentAndUpdateList(amwayBackOrdersList);
		//Grouping the AmwayBackOrders as per the stockLevels for stock calculation
		final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = amwayBackOrdersList.stream().collect(
				Collectors.groupingBy(backOrder -> getStockLevel(backOrder), Collectors.toList()));
		return amwayBackOrdersMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrdersForExpiring(final AmwayBackOrderStatus status, final Date date)
	{
		return getAmwayApacBackOrderDao().getBackOrdersByStatusAndDate(status, date);
	}


	/**
	 * Used to get Stock level for AmwayBackOrder product and warehouse
	 *
	 * @param backOrder
	 *           the AmwayBackOrder for getting Stock Level
	 * @return StockLevelModel for AmwayBackOrder
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
		final List<AmwayBackOrderModel> amwayBackOrdersNewList = new ArrayList<>();
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
