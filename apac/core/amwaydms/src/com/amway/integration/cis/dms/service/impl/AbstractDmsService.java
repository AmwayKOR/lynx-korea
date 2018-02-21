package com.amway.integration.cis.dms.service.impl;

import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandConfiguration;
import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandFactory;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.amway.core.enums.AmwayInfraAvlStatus;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityService;
import com.amway.core.model.AmwayInfraAvailabilityModel;
import com.amway.integration.cis.dms.client.DMSClient;


/**
 * @param <X>
 * @param <Y>
 * @param <Z>
 * @author ashishalishetty
 */
public abstract class AbstractDmsService<X, Y, Z>
{

	private static final Logger LOG = Logger.getLogger(AbstractDmsService.class);

	private OndemandHystrixCommandConfiguration hystrixCommandConfig;
	private DMSClient dmsClient;
	private OndemandHystrixCommandFactory ondemandHystrixCommandFactory;
	private static final String xclientRefId = "x-ref";
	private String urlPath;
	private String serviceAvailCode;
	private AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService;

	private Converter inputConverter;
	private Converter outputConverter;


	protected abstract X createResultObject();

	protected abstract X createDefaultResult();

	protected abstract Z executeEvent(Object input);

	protected Z executeFallbackEvent(final Object input)
	{
		LOG.debug("calling execute fallback event " + input);
		return null;
	}

	/**
	 * Handles the default fall back and run events for DMS service by checking the infrastructure (health check)
	 * availability for LOS
	 *
	 * @param requestData
	 * @return dmsResponse
	 */
	public X process(final Y requestData)
	{

		final Object input;
		if (getInputConverter() == null) {
			input = requestData;
		} else {
			input = getInputConverter().convert(requestData);
		}

		final Z dmsResponse = getOndemandHystrixCommandFactory()
				.newCommand(getHystrixCommandConfig(), new de.hybris.platform.integration.commons.hystrix.HystrixExecutable<Z>()
						{
							@Override
							public Z runEvent()
							{
								if (!shouldFallback())
								{
									final long startTime = Calendar.getInstance().getTimeInMillis();
									final Z result = executeEvent(input);
									LOG.info("Time-DmsService (" + urlPath + ") :" + (Calendar.getInstance().getTimeInMillis() - startTime)
											+ "ms");
									return result;
								}
								LOG.info("Time-DmsService (" + urlPath + ") : fallback");
								return fallbackEvent();
							}

							@Override
							public Z fallbackEvent()
							{
								LOG.debug("calling fallback event");
								return executeFallbackEvent(input);
							}

							@Override
							public Z defaultEvent()
							{
								LOG.debug("calling default event");
								return null;
							}

						}).execute();

		if (dmsResponse == null)
		{
			return createDefaultResult();
		}
		return extractOutput(dmsResponse);

	}

	/**
	 * Check the availability status of the DMS system.
	 *
	 * @return ACTIVE -> true, DISABLED / INACTIVE -> false
	 */
	protected boolean shouldFallback()
	{
		final AmwayInfraAvailabilityModel infraAvailForCode = amwayInfraAvailabilityService.getInfraAvailForCode(serviceAvailCode);
		if (infraAvailForCode != null && AmwayInfraAvlStatus.ACTIVE.equals(infraAvailForCode.getStatus()))
		{
			return false;
		}
		LOG.error("Fallabck for the service :" + this.getClass() + " because the current infrastructure availablity for :"
				+ infraAvailForCode + " is " + (infraAvailForCode != null ? infraAvailForCode.getStatus() : " null "));
		return true;
	}

	protected X extractOutput(final Object result)
	{
		if (getOutputConverter() == null) {
			return (X) result;
		}
		return (X) getOutputConverter().convert(result, createResultObject());
	}

	/**
	 * @return hystrixCommandConfig
	 */
	public OndemandHystrixCommandConfiguration getHystrixCommandConfig()
	{
		return hystrixCommandConfig;
	}

	/**
	 * @param hystrixCommandConfig the hystrixCommandConfig to set
	 */
	public void setHystrixCommandConfig(final OndemandHystrixCommandConfiguration hystrixCommandConfig)
	{
		this.hystrixCommandConfig = hystrixCommandConfig;
	}

	/**
	 * @return dmsClient
	 */
	public DMSClient getDmsClient()
	{
		return dmsClient;
	}

	/**
	 * @param dmsClient the dmsClient to set
	 */
	public void setDmsClient(final DMSClient dmsClient)
	{
		this.dmsClient = dmsClient;
	}

	/**
	 * @return ondemandHystrixCommandFactory
	 */
	public OndemandHystrixCommandFactory getOndemandHystrixCommandFactory()
	{
		return ondemandHystrixCommandFactory;
	}

	/**
	 * @param ondemandHystrixCommandFactory the ondemandHystrixCommandFactory to set
	 */
	public void setOndemandHystrixCommandFactory(final OndemandHystrixCommandFactory ondemandHystrixCommandFactory)
	{
		this.ondemandHystrixCommandFactory = ondemandHystrixCommandFactory;
	}

	/**
	 * @return urlPath
	 */
	public String getUrlPath()
	{
		return urlPath;
	}

	/**
	 * @param urlPath the urlPath to set
	 */
	public void setUrlPath(final String urlPath)
	{
		this.urlPath = urlPath;
	}

	/**
	 * @return xclientRefId
	 */
	public String getXclientRefId()
	{
		return xclientRefId;
	}

	/**
	 * @return inputConverter
	 */
	public Converter getInputConverter()
	{
		return inputConverter;
	}

	/**
	 * @param inputConverter the inputConverter to set
	 */
	public void setInputConverter(final Converter inputConverter)
	{
		this.inputConverter = inputConverter;
	}

	/**
	 * @return outputConverter
	 */
	public Converter getOutputConverter()
	{
		return outputConverter;
	}

	/**
	 * @param outputConverter the outputConverter to set
	 */
	public void setOutputConverter(final Converter outputConverter)
	{
		this.outputConverter = outputConverter;
	}

	/**
	 * @return serviceAvailCode
	 */
	public String getServiceAvailCode()
	{
		return serviceAvailCode;
	}

	/**
	 * @param serviceAvailCode the serviceAvailCode to set
	 */
	public void setServiceAvailCode(final String serviceAvailCode)
	{
		this.serviceAvailCode = serviceAvailCode;
	}

	/**
	 * @return amwayInfraAvailabilityService
	 */
	public AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> getAmwayInfraAvailabilityService()
	{
		return amwayInfraAvailabilityService;
	}

	/**
	 * @param amwayInfraAvailabilityService the amwayInfraAvailabilityService set
	 */
	public void setAmwayInfraAvailabilityService(
			final AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService)
	{
		this.amwayInfraAvailabilityService = amwayInfraAvailabilityService;
	}
}
