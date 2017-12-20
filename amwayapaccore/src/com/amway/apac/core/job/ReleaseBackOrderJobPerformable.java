/**
 *
 */
package com.amway.apac.core.job;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.model.AmwayApacStoreSpecificCronJobModel;


/**
 *
 * Cron job class for Releasing the backorders which are active
 */
public class ReleaseBackOrderJobPerformable extends AbstractJobPerformable<AmwayApacStoreSpecificCronJobModel>
{
	private AmwayApacBackOrderService backOrderService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.
	 * CronJobModel) Get all the amway backorders which are active and their release by date is later than today and
	 * release them if stock allows them to be released
	 */
	@Override
	public PerformResult perform(final AmwayApacStoreSpecificCronJobModel releaseBackOrderJobPerformable)
	{
		final BaseSiteModel baseSite = releaseBackOrderJobPerformable.getBaseStore().getCmsSites().iterator().next();
		if (backOrderService.releaseBackOrdersForStocks(baseSite))
		{
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		else
		{
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
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
