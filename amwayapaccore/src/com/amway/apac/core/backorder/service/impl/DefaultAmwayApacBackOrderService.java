/**
 *
 */
package com.amway.apac.core.backorder.service.impl;

import de.hybris.platform.core.GenericSearchConstants.LOG;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.StockService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderReleaseSelectionStrategy;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;import com.amway.apac.core.model.AmwayBackOrderModel;>>>>>>>dd5ecd01eb601630bd1f7122684c96837f686e50


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
	private ModelService modelService;

	private AmwayApacBackOrderReleaseSelectionStrategy amwayApacBackOrderReleaseSelectionStrategy;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void releaseBackOrders(final List<AmwayBackOrderModel> amwayBackOrders, final StockLevelModel stockLevel)
	{
		if (CollectionUtils.isNotEmpty(amwayBackOrders) && Objects.nonNull(stockLevel))
		{
			for (final AmwayBackOrderModel amwayBackOrder : amwayBackOrders)
			{
				//TODO checking the stock level vikrant
				//Trigger consignment process
				triggerConsignmentProcess(amwayBackOrder.getConsignment());

			}
		}
		return backorderReleased;

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void releaseBackOrdersForStocks(final List<StockLevelModel> stockLevels)
	{
		final Map<StockLevelModel, List<AmwayBackOrderModel>> backOrderMap = getAmwayApacBackOrderSelectionStrategy()
				.getBackOrdersForRelease(stockLevels);
		for (final Entry<StockLevelModel, List<AmwayBackOrderModel>> entry : backOrderMap.entrySet())
		{
			//release all the backOrders for particular stocks
			releaseBackOrders(entry.getValue(), entry.getKey());
		}

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
	 * @return the amwayApacBackOrderSelectionStrategy
	 */
	public AmwayApacBackOrderSelectionStrategy getAmwayApacBackOrderSelectionStrategy()
	{
		return amwayApacBackOrderSelectionStrategy;
	}

	/**
	 * @param amwayApacBackOrderSelectionStrategy
	 *           the amwayApacBackOrderSelectionStrategy to set
	 */
	@Required
	public void setAmwayApacBackOrderSelectionStrategy(
			final AmwayApacBackOrderSelectionStrategy amwayApacBackOrderSelectionStrategy)
	{
		this.amwayApacBackOrderSelectionStrategy = amwayApacBackOrderSelectionStrategy;
	}


	@Override
	public Boolean expireBackOrder(final String status, final Date date, List<AmwayBackOrderModel> backOrders)
	{
		boolean backorderReleased = false;

		if (CollectionUtils.isNotEmpty(backOrders))
		{
			for (final AmwayBackOrderModel amwayBackOrder : entry.getValue())
			{
				amwayBackOrder.setStatus(AmwayBackOrderStatus.valueOf(status));
				modelService.save(amwayBackOrder);
				modelService.refresh(amwayBackOrder);

			}

		}


	}

}
