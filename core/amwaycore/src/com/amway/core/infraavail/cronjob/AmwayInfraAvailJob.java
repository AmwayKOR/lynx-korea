/**
 *
 */
package com.amway.core.infraavail.cronjob;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import com.amway.core.cronjob.model.AmwayInfraAvailCronJobModel;
import com.amway.core.enums.AmwayInfraAvlStatus;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityEventPublishService;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityService;
import com.amway.core.model.AmwayInfraAvailabilityModel;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * Job to check the availability of infrastructure service and update the appropriate status against the base store.
 */
public class AmwayInfraAvailJob extends AbstractJobPerformable<AmwayInfraAvailCronJobModel>
{
	private static final Logger LOG = Logger.getLogger(AmwayInfraAvailJob.class);

	private AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService;

	private AmwayInfraAvailabilityEventPublishService amwayInfraAvailabilityEventPublishService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.
	 * CronJobModel )
	 */
	@Override
	public PerformResult perform(final AmwayInfraAvailCronJobModel cronjob)
	{
		final HttpGet method = new HttpGet(cronjob.getInfraAvailCheckUrl());
		try
		{
			final CloseableHttpClient httpclient = HttpClients.createDefault();
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(
						final HttpResponse response) throws ClientProtocolException, IOException {
					return response.getStatusLine().getStatusCode()+"";
				}

			};
			// Execute the method.
			final String statusCode = httpclient.execute(method,responseHandler);

			final AmwayInfraAvailabilityModel infraAvailForCode = amwayInfraAvailabilityService
					.getInfraAvailForStoreAndCode(cronjob.getInfraAvailCode(), cronjob.getBaseStore());

			if (infraAvailForCode == null)
			{
				throw new UnknownIdentifierException(
						" no valid AmwayInfraAvailabilityModel found for code : " + cronjob.getInfraAvailCode());
			}


			if (Integer.parseInt(statusCode) == HttpStatus.SC_OK)
			{
				//update the AmwayInfraAvailability status to active
				updateInfraAvailabilityStatus(infraAvailForCode, AmwayInfraAvlStatus.ACTIVE);
				getAmwayInfraAvailabilityEventPublishService().publishWaitingEvents(cronjob.getInfraAvailCode());
			}
			else
			{
				//update the AmwayInfraAvailability status to inactive
				updateInfraAvailabilityStatus(infraAvailForCode, AmwayInfraAvlStatus.INACTIVE);
			}

		}
		catch (final Exception e)
		{
			LOG.error(String.format("Could not check the availablity for url [%s] and code [%s]",
					    cronjob.getInfraAvailCheckUrl(),
					    cronjob.getInfraAvailCode() ));
			if (LOG.isDebugEnabled())
			{
				LOG.error("Could not check the availablity for url", e);

			}
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}
		finally
		{
			// Release the connection.
			method.releaseConnection();
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param infraAvailForCode
	 * @param status
	 */
	private void updateInfraAvailabilityStatus(final AmwayInfraAvailabilityModel infraAvailForCode,
			final AmwayInfraAvlStatus status)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("status check result for :" + infraAvailForCode.getCode() + " is :" + status);
		}

		//update the status only if not same as previous
		if (!infraAvailForCode.getStatus().equals(status))
		{
			LOG.info("updating status check result for :" + infraAvailForCode.getCode() + " from :" + infraAvailForCode.getStatus()
					+ " to :" + status);
			infraAvailForCode.setStatus(status);
			modelService.save(infraAvailForCode);
			modelService.refresh(infraAvailForCode);
		}
	}

	/**
	 * @return amwayInfraAvailabilityService
	 */
	public AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> getAmwayInfraAvailabilityService()
	{
		return amwayInfraAvailabilityService;
	}

	/**
	 * @param amwayInfraAvailabilityService the amwayInfraAvailabilityService to set
	 */
	public void setAmwayInfraAvailabilityService(
			final AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService)
	{
		this.amwayInfraAvailabilityService = amwayInfraAvailabilityService;
	}

	/**
	 * @return amwayInfraAvailabilityEventPublishService
	 */
	public AmwayInfraAvailabilityEventPublishService getAmwayInfraAvailabilityEventPublishService()
	{
		return amwayInfraAvailabilityEventPublishService;
	}

	/**
	 * @param amwayInfraAvailabilityEventPublishService the amwayInfraAvailabilityEventPublishService to set
	 */
	public void setAmwayInfraAvailabilityEventPublishService(
			final AmwayInfraAvailabilityEventPublishService amwayInfraAvailabilityEventPublishService)
	{
		this.amwayInfraAvailabilityEventPublishService = amwayInfraAvailabilityEventPublishService;
	}
}
