package com.amway.integration.cis.los.sponsorvalidationservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Test;

import com.amway.core.dms.data.ValidateIntlBusSponsorResponseData;
import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.integration.cis.los.sponsorvalidationservice.mock.impl.MockIntlBusSponsorValidationService;

import junit.framework.Assert;


public class DefaultIntlBusSponsorValidationServiceTest extends ServicelayerTest
{

	private static final String SPONSOR_NO = "999";
	private static final String AFFlI_NO = "170";
	@Resource(name = "mockIntlBusSponsorValidationService")
	private MockIntlBusSponsorValidationService intlBusSponsorValidationService;

	private final VerifySponsorRequestData verifyIntlBusSponsorReq;

	public DefaultIntlBusSponsorValidationServiceTest()
	{
		verifyIntlBusSponsorReq = new VerifySponsorRequestData();
		verifyIntlBusSponsorReq.setAffiliateNo(AFFlI_NO);
		verifyIntlBusSponsorReq.setSponsorNo(SPONSOR_NO);
	}

	@Test
	public void shouldValidateIntlSponsor()
	{
		final ValidateIntlBusSponsorResponseData responce = intlBusSponsorValidationService.process(verifyIntlBusSponsorReq);
		Assert.assertEquals(1, responce.getReturnCd());
	}

	@Test
	public void shouldValidateIntlSponsorLmsCertified()
	{
		final ValidateIntlBusSponsorResponseData responce = intlBusSponsorValidationService.process(verifyIntlBusSponsorReq);
		Assert.assertTrue(responce.getLmscertified());
	}

}
