package com.amway.integration.cis.dms.partyandnameservice.impl;

import com.amway.core.account.service.PartyProfileAndNameService;
import com.amway.core.dms.data.PartyNameDetailResponseData;
import com.amway.core.dms.data.PartyProfileAndNameInputRequestData;
import com.amway.integration.cis.dms.client.DMSClient;
import com.amway.integration.cis.dms.customerregistration.impl.DefaultProcessCustomerRegistrationService;
import com.amway.integration.dms.services.PartyNameDetailResponse;
import com.amway.integration.dms.services.PartyProfileAndNameInputRequest;
import com.hybris.commons.client.RestResponse;
import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandConfiguration;
import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandFactory;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.log4j.Logger;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to get the party profile details from magic.
 */
@Deprecated
public class DefaultPartyProfileAndNameService implements PartyProfileAndNameService
{
	private static final Logger LOG = Logger.getLogger(DefaultProcessCustomerRegistrationService.class);
	private OndemandHystrixCommandConfiguration hystrixCommandConfig;
	private DMSClient dmsClient;
	private OndemandHystrixCommandFactory ondemandHystrixCommandFactory;
	private Converter<PartyProfileAndNameInputRequestData, PartyProfileAndNameInputRequest> dmsPartyProfileAndNameInputConverter;
	private Converter<PartyNameDetailResponse, PartyNameDetailResponseData> dmsPartyProfileNameDetailResponseConverter;

	@Override
	public PartyNameDetailResponseData getPartyProfileAndNameService(final PartyProfileAndNameInputRequestData partyInfo)
	{
		validateParameterNotNullStandardMessage("ABO Number", partyInfo.getAboNum());
		validateParameterNotNullStandardMessage("Affiliate Number", partyInfo.getSalesPlanAff());
		final PartyProfileAndNameInputRequest dmsRequest = getDmsPartyProfileAndNameInputConverter().convert(partyInfo);

		final PartyNameDetailResponse dmsResponse = getOndemandHystrixCommandFactory().newCommand(getHystrixCommandConfig(),
				new de.hybris.platform.integration.commons.hystrix.HystrixExecutable<PartyNameDetailResponse>()
				{
					@Override
					public PartyNameDetailResponse runEvent()
					{
						LOG.debug("Executing request");
						final RestResponse<PartyNameDetailResponse> dmsResultRestResponse = getDmsClient()
								.executeDmsRequest("x-ref", "v2/PartyService/getPartyProfileAndName", dmsRequest,
										PartyNameDetailResponse.class);
						return dmsResultRestResponse.getResult();
					}

					@Override
					public PartyNameDetailResponse fallbackEvent()
					{
						LOG.debug("Executing fallback");
						return null;
					}

					@Override
					public PartyNameDetailResponse defaultEvent()
					{
						return null;
					}


				}).execute();

		if (dmsResponse == null)
		{
			return createPartyDataResult();

		}
		return extractOutput(dmsResponse);
	}

	private PartyNameDetailResponseData createPartyDataResult()
	{
		return new PartyNameDetailResponseData();
	}

	private PartyNameDetailResponseData extractOutput(final PartyNameDetailResponse result)
	{
		return getDmsPartyProfileNameDetailResponseConverter().convert(result, createPartyDataResult());
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
	 * @param dmsClient the dmsClient to client
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
	 * @return dmsPartyProfileAndNameInputConverter
	 */
	public Converter<PartyProfileAndNameInputRequestData, PartyProfileAndNameInputRequest> getDmsPartyProfileAndNameInputConverter()
	{
		return dmsPartyProfileAndNameInputConverter;
	}

	/**
	 * @param dmsPartyProfileAndNameInputConverter the dmsPartyProfileAndNameInputConverter to set
	 */
	public void setDmsPartyProfileAndNameInputConverter(
			final Converter<PartyProfileAndNameInputRequestData, PartyProfileAndNameInputRequest> dmsPartyProfileAndNameInputConverter)
	{
		this.dmsPartyProfileAndNameInputConverter = dmsPartyProfileAndNameInputConverter;
	}

	/**
	 * @return dmsPartyProfileNameDetailResponseConverter
	 */
	public Converter<PartyNameDetailResponse, PartyNameDetailResponseData> getDmsPartyProfileNameDetailResponseConverter()
	{
		return dmsPartyProfileNameDetailResponseConverter;
	}

	/**
	 * @param dmsPartyProfileNameDetailResponseConverter the dmsPartyProfileNameDetailResponseConverter to set
	 */
	public void setDmsPartyProfileNameDetailResponseConverter(
			final Converter<PartyNameDetailResponse, PartyNameDetailResponseData> dmsPartyProfileNameDetailResponseConverter)
	{
		this.dmsPartyProfileNameDetailResponseConverter = dmsPartyProfileNameDetailResponseConverter;
	}


}
