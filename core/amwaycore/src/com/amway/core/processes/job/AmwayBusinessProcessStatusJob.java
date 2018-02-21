/**
 *
 */
package com.amway.core.processes.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import org.apache.log4j.Logger;

import com.amway.core.cronjob.model.AmwayPublishEventCronJobModel;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityEventPublishService;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityService;
import com.amway.core.model.AmwayInfraAvailabilityModel;


/**
 * Job for Business process status.
 */
public class AmwayBusinessProcessStatusJob extends AbstractJobPerformable<AmwayPublishEventCronJobModel>
{
	private static final Logger logger = Logger.getLogger(AmwayBusinessProcessStatusJob.class);

	private AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService;

	private AmwayInfraAvailabilityEventPublishService amwayInfraAvailabilityEventPublishService;

	/**
	 * Method to perform status of the cronjob and publish events for tasks in waiting state for %infraAvailCode
	 *
	 * @param cronJobModel
	 * @return PerformResult
	 */
	@Override
	public PerformResult perform(final AmwayPublishEventCronJobModel cronJobModel)
	{
		final AmwayInfraAvailabilityModel infraAvailForCode = amwayInfraAvailabilityService
				.getInfraAvailForCode(cronJobModel.getServiceId());

		if (infraAvailForCode.getCode() != null)
		{
			getAmwayInfraAvailabilityEventPublishService().publishWaitingEvents(cronJobModel.getServiceId());
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public AmwayInfraAvailabilityEventPublishService getAmwayInfraAvailabilityEventPublishService()
	{
		return amwayInfraAvailabilityEventPublishService;
	}

	public void setAmwayInfraAvailabilityEventPublishService(
			final AmwayInfraAvailabilityEventPublishService amwayInfraAvailabilityEventPublishService)
	{
		this.amwayInfraAvailabilityEventPublishService = amwayInfraAvailabilityEventPublishService;
	}

	public AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> getAmwayInfraAvailabilityService()
	{
		return amwayInfraAvailabilityService;
	}

	public void setAmwayInfraAvailabilityService(
			final AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService)
	{
		this.amwayInfraAvailabilityService = amwayInfraAvailabilityService;
	}
}
