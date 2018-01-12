/**
 *
 */
package com.amway.apac.core.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.time.TimeService;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;


/**
 * CronJob class for setting the BackOrders as expired
 */
public class ExpiredBackorderJobPerformable extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(ExpiredBackorderJobPerformable.class);
	private AmwayApacBackOrderSelectionStrategy backOrderSelectionStrategy;
	private AmwayApacBackOrderService backOrderService;
	private TimeService timeService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		final List<AmwayBackOrderModel> amwayBackOrders = getBackOrderSelectionStrategy().getBackOrdersForExpiring(
				AmwayBackOrderStatus.ACTIVE, getTimeService().getCurrentTime());
		if (CollectionUtils.isNotEmpty(amwayBackOrders))
		{
			if (!getBackOrderService().expireBackOrder(amwayBackOrders).booleanValue())
			{
				LOG.error("Not able to expire the ACTIVE AmwayBackOrders");
				result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
			}
		}
		else
		{
			LOG.warn("No Active AmwayBackOrderFound for  expiring");
		}
		return result;
	}

	/**
	 * @return the backOrderSelectionStrategy
	 */
	public AmwayApacBackOrderSelectionStrategy getBackOrderSelectionStrategy()
	{
		return backOrderSelectionStrategy;
	}

	/**
	 * @param backOrderSelectionStrategy
	 *           the backOrderSelectionStrategy to set
	 */
	public void setBackOrderSelectionStrategy(final AmwayApacBackOrderSelectionStrategy backOrderSelectionStrategy)
	{
		this.backOrderSelectionStrategy = backOrderSelectionStrategy;
	}

	/**
	 * @return the backOrderService
	 */
	public AmwayApacBackOrderService getBackOrderService()
	{
		return backOrderService;
	}

	/**
	 * @param backOrderService
	 *           the backOrderService to set
	 */
	public void setBackOrderService(final AmwayApacBackOrderService backOrderService)
	{
		this.backOrderService = backOrderService;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	/**
	 * @param timeService
	 *           the timeService to set
	 */
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}

}
