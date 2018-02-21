package com.amway.integration.cis.dms.partyandname.impl;

import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import de.hybris.platform.integration.commons.hystrix.HystrixExecutable;
import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandConfiguration;
import de.hybris.platform.integration.commons.hystrix.OndemandHystrixCommandFactory;
import de.hybris.platform.integration.commons.services.OndemandPreferenceSelectorService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.dms.party.data.AccountInfoData;
import com.amway.core.dms.party.data.PartyData;
import com.amway.integration.cis.dms.client.DMSClient;
import com.amway.integration.cis.dms.partyandnameservice.impl.DefaultPartyAndNameService;
import com.amway.integration.dms.services.PartyNameDetailResponse;
import com.amway.integration.dms.services.PartyNameDetailsInput;


/* Integration Test for PartyAndNameService*/
/* Is this needed?  Looks like spring config was removed for HE-317, and test was overlooked?
	See:
	https://github.com/AmwayCoE/lynx-core/commit/06cf6e220559054ab0422cfff92f4ee65f465d33

public class DefaultPartyAndNameServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private DefaultPartyAndNameService partyAndNameService;

	@Mock
	private OndemandPreferenceSelectorService ondemandPreferenceSelectorService;
	@Mock
	private OndemandHystrixCommandFactory ondemandHystrixCommandFactory;
	@Mock
	private OndemandHystrixCommandConfiguration config;
	@Mock
	private DMSClient dmsClient;
	@Resource
	private Converter<AccountInfoData, PartyNameDetailsInput> dmsPartyAndNameInputConverter;
	@Resource
	private Converter<PartyNameDetailResponse, PartyData> dmsPartyNameDetailResponseConverter;
	@Resource
	private PartyNameDetailsInput dmsRequest;
	@Resource
	private PartyNameDetailResponse dmsResponse;

	private AccountInfoData accountInfo;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		accountInfo = new AccountInfoData();
		accountInfo.setAboNumber("670");
		accountInfo.setAffiliateNumber("010");
		accountInfo.setPartyId("320090");

		partyAndNameService = new DefaultPartyAndNameService();
		partyAndNameService.setOndemandHystrixCommandFactory(ondemandHystrixCommandFactory);
		partyAndNameService.setHystrixCommandConfig(config);
		partyAndNameService.setDmsClient(dmsClient);

	}

	@Test
	public void getPartyDetailsSuccessTest()
	{
		Mockito.when(dmsPartyAndNameInputConverter.convert(accountInfo)).thenReturn(dmsRequest);
		final OndemandHystrixCommandFactory.OndemandHystrixCommand command = Mockito
				.mock(OndemandHystrixCommandFactory.OndemandHystrixCommand.class);
		Mockito.when(
				ondemandHystrixCommandFactory.newCommand(Mockito.any(OndemandHystrixCommandConfiguration.class),
						Matchers.<HystrixExecutable<Object>>any())).thenReturn(command);
		Mockito.when(command.execute()).thenReturn(dmsResponse);
		final PartyData party = partyAndNameService.process(accountInfo);
		Assert.assertEquals("M", party.getGender());
		Assert.assertEquals("Single", party.getMaritalStatus());
		Assert.assertEquals("27-11-1990", party.getDateOfBirth());
	}

*/
	/**
	 * @return the dmsPartyAndNameInputConverter
	 */
//	public Converter<AccountInfoData, PartyNameDetailsInput> getDmsPartyAndNameInputConverter()
//	{
//		return dmsPartyAndNameInputConverter;
//	}

	/**
	 * @param dmsPartyAndNameInputConverter
	 *           the dmsPartyAndNameInputConverter to set
	 */
//	public void setDmsPartyAndNameInputConverter(
//			final Converter<AccountInfoData, PartyNameDetailsInput> dmsPartyAndNameInputConverter)
//	{
//		this.dmsPartyAndNameInputConverter = dmsPartyAndNameInputConverter;
//	}

	/**
	 * @return the dmsPartyNameDetailResponseConverter
	 */
//	public Converter<PartyNameDetailResponse, PartyData> getDmsPartyNameDetailResponseConverter()
//	{
//		return dmsPartyNameDetailResponseConverter;
//	}

	/**
	 * @param dmsPartyNameDetailResponseConverter
	 *           the dmsPartyNameDetailResponseConverter to set
	 */
//	public void setDmsPartyNameDetailResponseConverter(
//			final Converter<PartyNameDetailResponse, PartyData> dmsPartyNameDetailResponseConverter)
//	{
//		this.dmsPartyNameDetailResponseConverter = dmsPartyNameDetailResponseConverter;
//	}

	/**
	 * @return the dmsRequest
	 */
//	public PartyNameDetailsInput getDmsRequest()
//	{
//		return dmsRequest;
//	}

	/**
	 * @param dmsRequest
	 *           the dmsRequest to set
	 */
//	public void setDmsRequest(final PartyNameDetailsInput dmsRequest)
//	{
//		this.dmsRequest = dmsRequest;
//	}

	/**
	 * @return the dmsResponse
	 */
//	public PartyNameDetailResponse getDmsResponse()
//	{
//		return dmsResponse;
//	}

	/**
	 * @param dmsResponse
	 *           the dmsResponse to set
	 */
//	public void setDmsResponse(final PartyNameDetailResponse dmsResponse)
//	{
//		this.dmsResponse = dmsResponse;
//	}


//}
