/**
 *
 */
package com.amway.apac.core.backorder.strategies.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderReleaseSelectionStrategy;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.core.model.AmwayBackOrderModel;


/**
 * Created to fetch all the available back orders for release in fifo
 *
 * @author ankushbhatia
 */
public class DefaultAmwayApacBackOrderReleaseSelectionStrategy implements AmwayApacBackOrderReleaseSelectionStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacBackOrderReleaseSelectionStrategy.class);

	private AmwayApacBackOrderDao amwayApacBackOrderDao;

	/**
	 * {@link #getBackOrders(List)}
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrders(final List<StockLevelModel> stockLevels)
	{
		try
		{
			if (CollectionUtils.isEmpty(stockLevels))
			{
				return getAmwayApacBackOrderDao().getBackOrders(AmwayBackOrderStatus.ACTIVE, null, null, true);
			}
			else
			{
				final List<AmwayBackOrderModel> amwayBackOrdersListToRelease = new ArrayList<AmwayBackOrderModel>();
				for (final StockLevelModel stockLevel : stockLevels)
				{
					final List<AmwayBackOrderModel> amwayBackOrdersList = getAmwayApacBackOrderDao().getBackOrders(
							AmwayBackOrderStatus.ACTIVE, stockLevel.getWarehouse(), stockLevel.getProduct(), true);
					amwayBackOrdersListToRelease.addAll(amwayBackOrdersList);
				}
				return amwayBackOrdersListToRelease;
			}
		}
		catch (final Exception e)
		{
			logDebugInfo(e, "Failed to fetch Amway Back Orders ");
		}
		return null;
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


}
