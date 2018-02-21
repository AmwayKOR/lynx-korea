package com.amway.integration.cis.los.sponsorvalidationservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.dms.data.LosSponsorValidationResultData;
import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.integration.cis.los.sponsorvalidationservice.mock.impl.MockSponsorValidationSrevice;


public class DefaultSponsorValidationServiceTest extends ServicelayerTest
{

	private static final String SPONSOR_NO = "1";
	private static final String SPONSOR_TYPE_CODE = "A";
	private static final String COUNTRY_CODE = "310";
	private static final String BUSINESS_NATURE = SPONSOR_NO;
	private static final String AFFlI_NO = "170";

	@Resource(name = "mockSponsorValidationSrevice")
	private MockSponsorValidationSrevice sponsorValidationSrevice;

	private VerifySponsorRequestData verifySponsorRequestData;

	@Before
	public void setUp()
	{
		verifySponsorRequestData = new VerifySponsorRequestData();
		verifySponsorRequestData.setAffiliateNo(AFFlI_NO);
		verifySponsorRequestData.setBusinessNature(BUSINESS_NATURE);
		verifySponsorRequestData.setCountryCode(COUNTRY_CODE);
		verifySponsorRequestData.setSponsorNo(SPONSOR_NO);
		verifySponsorRequestData.setSponsorTypeCode(SPONSOR_TYPE_CODE);

	}

	@Test
	public void shouldValidateSponsorTest()
	{
		final LosSponsorValidationResultData responce = sponsorValidationSrevice.process(verifySponsorRequestData);
		Assert.assertEquals(1, responce.getReturnCd());
	}

}
