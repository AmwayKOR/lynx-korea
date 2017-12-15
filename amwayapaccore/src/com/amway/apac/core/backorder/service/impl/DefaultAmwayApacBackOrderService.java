/**
 *
 */
package com.amway.apac.core.backorder.service.impl;

import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.stock.StockService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderReleaseSelectionStrategy;
import com.amway.apac.core.model.AmwayBackOrderModel;


/**
 * Default implementation for AmwayApacBackOrderReleaseStrategy
 *
 * @author ankushbhatia
 *
 */
public class DefaultAmwayApacBackOrderService implements AmwayApacBackOrderService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacBackOrderService.class);

	private static final String BACK_ORDER_RELEASE_EVENT_CODE = "WaitForRelease";

	private BusinessProcessService businessProcessService;

	private StockService stockService;

	private AmwayApacBackOrderReleaseSelectionStrategy amwayApacBackOrderReleaseSelectionStrategy;

	/**
	 * {@inheritDoc} #release(List)}
	 */
	@Override
	public void release(final List<StockLevelModel> stockLevels)
	{
		//If stockLevels are empty or null than all AmwayBackOrders will be returned.
		final List<AmwayBackOrderModel> backOrders = getAmwayApacBackOrderReleaseSelectionStrategy().getBackOrders(stockLevels);
		if (CollectionUtils.isNotEmpty(backOrders))
		{
			//Grouping the AmwayBackOrders as per the stockLevels for stock calculation
			final Map<StockLevelModel, List<AmwayBackOrderModel>> result = backOrders.stream().collect(
					Collectors.groupingBy(backOrder -> getStockLevel(backOrder), Collectors.toList()));
			for (final Entry<StockLevelModel, List<AmwayBackOrderModel>> entry : result.entrySet())
			{
				final StockLevelModel stockLevel = entry.getKey();
				for (final AmwayBackOrderModel amwayBackOrder : entry.getValue())
				{
					if (Objects.nonNull(stockLevel))
					{
						//TODO checking the stock level
						//Trigger consignment process
						triggerConsignmentProcess(amwayBackOrder.getConsignment());
					}
				}
			}
		}
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
	 * Used to trigger Consignment process wait node
	 *
	 * @param ConsignmentModel
	 */
	private void triggerConsignmentProcess(final ConsignmentModel consModel)
	{
		if (Objects.nonNull(consModel))
		{
			try
			{
				for (final Iterator<ConsignmentProcessModel> iterator = consModel.getConsignmentProcesses().iterator(); iterator
						.hasNext();)
				{
					final ConsignmentProcessModel process = iterator.next();
					final String eventCode = (new StringBuilder(String.valueOf(process.getConsignment().getCode()))).append("_")
							.append(BACK_ORDER_RELEASE_EVENT_CODE).toString();
					//Trigger the business process event which release AmwayBackOrder
					getBusinessProcessService().triggerEvent(eventCode);
					LOG.info("BackOrder consignment triggered successfully for code : " + consModel.getCode());
				}
			}
			catch (final Exception e)
			{
				logDebugInfo(e, "BackOrder consignment process not triggered successfully for code : " + consModel.getCode());
			}
		}

	}


	/**
	 * To print log in debug mode
	 *
	 * @param exc
	 * @param message
	 */
	protected void logDebugInfo(final Exception exc, final String message)
	{
		if (Objects.nonNull(exc) && LOG.isDebugEnabled())
		{
			LOG.debug(message, exc);
		}
	}

	/**
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * @param businessProcessService
	 *           the businessProcessService to set
	 */
	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the amwayApacBackOrderReleaseSelectionStrategy
	 */
	public AmwayApacBackOrderReleaseSelectionStrategy getAmwayApacBackOrderReleaseSelectionStrategy()
	{
		return amwayApacBackOrderReleaseSelectionStrategy;
	}

	/**
	 * @param amwayApacBackOrderReleaseSelectionStrategy
	 *           the amwayApacBackOrderReleaseSelectionStrategy to set
	 */
	@Required
	public void setAmwayApacBackOrderReleaseSelectionStrategy(
			final AmwayApacBackOrderReleaseSelectionStrategy amwayApacBackOrderReleaseSelectionStrategy)
	{
		this.amwayApacBackOrderReleaseSelectionStrategy = amwayApacBackOrderReleaseSelectionStrategy;
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


}
