/**
 *
 */
package com.amway.core.processes.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.amway.core.cronjob.model.FetchProcessStatusEmailCronJobModel;
import com.amway.core.processes.services.AmwayFetchProcessStatusService;


/**
 * Job for to fetch the amway process status email.
 */
public class AmwayFetchProcessStatusEmailJob extends AbstractJobPerformable<FetchProcessStatusEmailCronJobModel>
{
	private static final Logger logger = Logger.getLogger(AmwayFetchProcessStatusEmailJob.class);
	private I18NService i18nService;
	private AmwayFetchProcessStatusService fetchProcessStatusService;

	/**
	 * Job to perfom sending an email.
	 *
	 * @param cronJobModel
	 * @return PerformResult
	 */
	@Override
	public PerformResult perform(final FetchProcessStatusEmailCronJobModel cronJobModel)
	{
		try
		{
			final Calendar currentDate = Calendar.getInstance(getI18nService().getCurrentLocale());
			final boolean successResult = fetchProcessStatusService
					.sendEmailForFailedProcesses(cronJobModel.getProcessStatusValue().toString(), cronJobModel.getReturnStatusCode(),
							cronJobModel.getLastRemoteUpdateTime() != null ?
									cronJobModel.getLastRemoteUpdateTime() :
									cronJobModel.getModifiedtime());
			if (successResult)
			{
				cronJobModel.setAlternativeDataSourceID(String.valueOf(currentDate.getTimeInMillis()));
				cronJobModel.setLastRemoteUpdateTime(currentDate.getTime());
				modelService.save(cronJobModel);
				modelService.refresh(cronJobModel);
				return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
			}
			else
			{
				cronJobModel.setLastRemoteUpdateTime(currentDate.getTime());
				modelService.save(cronJobModel);
				modelService.refresh(cronJobModel);
			}
		}
		catch (final Exception e)
		{
			logger.error("Error while sending the Logs Mail", e);
		}
		return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
	}


	/**
	 * @return i18nService
	 */
	public I18NService getI18nService()
	{
		return i18nService;
	}

	/**
	 * @param i18nService the i18nService to set
	 */
	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}

	/**
	 * @return fetchProcessStatusService
	 */
	public AmwayFetchProcessStatusService getFetchProcessStatusService()
	{
		return fetchProcessStatusService;
	}

	/**
	 * @param fetchProcessStatusService the fetchProcessStatusService to set
	 */
	public void setFetchProcessStatusService(final AmwayFetchProcessStatusService fetchProcessStatusService)
	{
		this.fetchProcessStatusService = fetchProcessStatusService;
	}
}
