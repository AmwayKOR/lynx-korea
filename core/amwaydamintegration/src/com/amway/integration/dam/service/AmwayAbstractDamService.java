package com.amway.integration.dam.service;

import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandConfiguration;
import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandFactory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.client.AmwayDamClient;


/**
 * Abstract class that contains common logic for all DAM web services.
 * Encapsulates hystrix fallback logic.
 *
 * @param <DTO>
 * 		type of DTO object
 * @param <RQ>
 * 		type of request to DAM client
 * @param <RS>
 * 		type of response from DAM client
 */
public abstract class AmwayAbstractDamService<DTO, RQ, RS>
{
	@Autowired
	private OndemandHystrixCommandConfiguration amwayDamCommandConfig;
	@Autowired
	private OndemandHystrixCommandFactory ondemandHystrixCommandFactory;
	@Autowired
	private AmwayDamClient amwayDamClient;

	protected abstract DTO extractOutput(final RS response);

	protected abstract RS executeEvent(final RQ request);

	protected AmwayDamClient getAmwayDamClient()
	{
		return amwayDamClient;
	}

	/**
	 * Handles the default fallback and run events for DAM service
	 *
	 * @return response from the webservice or null in case of hit failure
	 */
	public DTO process(final RQ requestData)
	{
		final RS dmsResponse = ondemandHystrixCommandFactory.newCommand(amwayDamCommandConfig,
				new de.hybris.platform.integration.commons.hystrix.HystrixExecutable<RS>()
				{
					@Override
					public RS runEvent()
					{
						return executeEvent(requestData);
					}

					@Override
					public RS fallbackEvent()
					{
						return null;
					}

					@Override
					public RS defaultEvent()
					{
						return null;
					}

				}).execute();

		//@formatter:off
		return Optional.ofNullable(dmsResponse)
				.map(this::extractOutput)
				.orElse(null);
		//@formatter:on
	}
}
