/**
 *
 */
package com.amway.apac.core.backorder.service.impl;

import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.processengine.BusinessProcessService;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
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




}
