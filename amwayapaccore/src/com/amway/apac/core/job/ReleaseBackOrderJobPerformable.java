/**
 *
 */
package com.amway.apac.core.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import org.apache.log4j.Logger;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.model.AmwayApacStoreSpecificCronJobModel;


/**
 * CronJob class for Releasing the BackOrders which are active
 */
public class ReleaseBackOrderJobPerformable extends AbstractJobPerformable<AmwayApacStoreSpecificCronJobModel>
{
	private static final Logger LOG = Logger.getLogger(ReleaseBackOrderJobPerformable.class);
	private AmwayApacBackOrderService backOrderService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PerformResult perform(final AmwayApacStoreSpecificCronJobModel releaseBackOrderJobPerformable)
	{
		PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		if (!getBackOrderService().releaseBackOrdersForBaseStore(releaseBackOrderJobPerformable.getBaseStore()))
		{
			LOG.error("Not able to release the ACTIVE AmwayBackOrders");
			result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		return result;
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

}
