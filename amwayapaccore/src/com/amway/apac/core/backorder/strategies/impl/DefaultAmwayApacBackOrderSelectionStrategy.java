/**
 *
 */
package com.amway.apac.core.backorder.strategies.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao;
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

	private static final String ACTIVE = "ACTIVE";

	private AmwayApacBackOrderDao amwayApacBackOrderDao;

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
					final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao().getBackOrders(
							AmwayBackOrderStatus.valueOf(ACTIVE), stockLevel.getWarehouse(), stockLevel.getProduct(), true);
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
	 * Validate payment for Amway backOrders
	 *
	 * @param amwayBackOrdersList
	 */
	private List<AmwayBackOrderModel> validatePaymentAndUpdateList(final List<AmwayBackOrderModel> amwayBackOrdersList)
	{
		final List<AmwayBackOrderModel> amwayBackOrdersNewList = new ArrayList<AmwayBackOrderModel>();
		//TODO payment Check
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
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrdersForExpiring(final String status, final Date date)
	{
		final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao().getBackOrdersForExpiring(status, date);
		return amwayBackOrdersList;
	}

}
