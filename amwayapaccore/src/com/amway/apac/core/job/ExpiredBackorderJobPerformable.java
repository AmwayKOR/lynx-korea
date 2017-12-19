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

import org.apache.log4j.Logger;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;
import com.amway.apac.core.model.AmwayBackOrderModel;


/**
 *
 * Cron job class for setting the backorders as expired
 */
public class ExpiredBackorderJobPerformable extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(ExpiredBackorderJobPerformable.class);

	private AmwayApacBackOrderSelectionStrategy backOrderSelectionStrategy;
	private AmwayApacBackOrderService backOrderService;
	private static final String ACTIVE = "ACTIVE";
	private TimeService timeService;



	/*
	 * (non-Javadoc) G
	 * 
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.
	 * CronJobModel) Get all the amway backorders which are active and their release by date is older than today. Then
	 * set their status to expired.
	 */
	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		final List<AmwayBackOrderModel> amwayBackOrders = getBackOrderSelectionStrategy().getBackOrdersForExpiring(ACTIVE,
				getTimeService().getCurrentTime());
		if (getBackOrderService().expireBackOrder(amwayBackOrders).booleanValue())
		{
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		else
		{
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}


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
