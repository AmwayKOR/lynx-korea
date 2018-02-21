package com.amway.integration.cis.dms.distributorservice.impl;

import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandConfiguration;
import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandFactory;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.account.applicant.services.ApplicantProcessDecision;
import com.amway.core.account.applicant.services.ApplicantService;
import com.amway.core.dms.applicant.ApplicantResultData;
import com.amway.core.dms.data.ApplicantRequestData;
import com.amway.integration.cis.dms.client.DMSClient;
import com.amway.integration.dms.services.AccountInput;
import com.amway.integration.dms.services.AccountPartyListResponse;
import com.hybris.commons.client.RestResponse;


/**
 * Service for to get the account party list information.
 */
public class DefaultGetApplicantListService implements ApplicantService
{

	private static final Logger Log = Logger.getLogger(DefaultGetApplicantListService.class);
	private OndemandHystrixCommandConfiguration hystrixCommandConfig;
	private DMSClient dmsClient;
	private OndemandHystrixCommandFactory ondemandHystrixCommandFactory;
	private Converter<ApplicantRequestData, AccountInput> dmsApplicantInputConverter;
	private Converter<AccountPartyListResponse, ApplicantResultData> dmsApplicantListOuputConverter;

	@Override
	public ApplicantResultData<ApplicantProcessDecision> getApplicantList(final String affNo, final String aboNo)
	{
		final ApplicantRequestData input = new ApplicantRequestData();
		input.setAffiliate(affNo);
		input.setIboNo(aboNo);
		final AccountInput request = getDmsApplicantInputConverter().convert(input);

		final AccountPartyListResponse dmsResponse = getOndemandHystrixCommandFactory().newCommand(getHystrixCommandConfig(),
				new de.hybris.platform.integration.commons.hystrix.HystrixExecutable<AccountPartyListResponse>()
				{

					@Override
					public AccountPartyListResponse runEvent()
					{

						final RestResponse<AccountPartyListResponse> dmsResultRestResponse = getDmsClient()
								.executeDmsRequest("x-ref", "v2/AccountService/getAccountPartyList",
										 request,
										AccountPartyListResponse.class);
						Assert.notNull(dmsResultRestResponse, "Registration failure");

						return dmsResultRestResponse.getResult();

					}

					@Override
					public AccountPartyListResponse fallbackEvent()
					{
						Log.debug("Executing fallback");
						return null;
					}

					@Override
					public AccountPartyListResponse defaultEvent()
					{
						return null;
					}

				}).execute();

		if (dmsResponse == null)
		{
			final ApplicantResultData<ApplicantProcessDecision> processApplicantList = createApplicantResultData();
			processApplicantList.setDecision(ApplicantProcessDecision.UNKNOWN);
			return processApplicantList;

		}
		return extractOutput(dmsResponse);
	}

	private ApplicantResultData<ApplicantProcessDecision> createApplicantResultData()
	{
		return new ApplicantResultData();
	}

	private ApplicantResultData extractOutput(final AccountPartyListResponse result)
	{

		return getDmsApplicantListOuputConverter().convert(result, createApplicantResultData());
	}





	/**
	 * @return the dmsApplicantListOuputConverter
	 */
	public Converter<AccountPartyListResponse, ApplicantResultData> getDmsApplicantListOuputConverter()
	{
		return dmsApplicantListOuputConverter;
	}

	/**
	 * @param dmsApplicantListOuputConverter the dmsApplicantListOuputConverter to set
	 */
	public void setDmsApplicantListOuputConverter(
			final Converter<AccountPartyListResponse, ApplicantResultData> dmsApplicantListOuputConverter)
	{
		this.dmsApplicantListOuputConverter = dmsApplicantListOuputConverter;
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
	 * @return dmsApplicantInputConverter
	 */
	public Converter<ApplicantRequestData, AccountInput> getDmsApplicantInputConverter()
	{
		return dmsApplicantInputConverter;
	}

	/**
	 * @param dmsApplicantInputConverter the dmsApplicantInputConverter to set
	 */
	public void setDmsApplicantInputConverter(final Converter<ApplicantRequestData, AccountInput> dmsApplicantInputConverter)
	{
		this.dmsApplicantInputConverter = dmsApplicantInputConverter;
	}
}
