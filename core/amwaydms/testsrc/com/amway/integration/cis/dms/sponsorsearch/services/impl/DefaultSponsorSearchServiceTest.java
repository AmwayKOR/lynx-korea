/**
 *
 */
package com.amway.integration.cis.dms.sponsorsearch.services.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.amway.core.dms.data.SponsorSearchRequestData;
import com.amway.core.dms.data.SponsorSearchResponseData;
import com.amway.integration.cis.dms.sponsorsearch.services.mock.MockSponsorSearchService;


public class DefaultSponsorSearchServiceTest extends ServicelayerTest
{
	@Resource(name = "mockSponsorSearchService")
	private MockSponsorSearchService defaultSponsorSearchService;

	private SponsorSearchRequestData requestData;

	@Before
	public void setUp() throws Exception
	{
		requestData = new SponsorSearchRequestData();
		requestData.setSalesPlanAff("170");
		requestData.setPostalCode("04246000");
		requestData.setCountryCode("BR");
		requestData.setSponsorCount("3");
	}

	@Test
	public void shouldSearchSponsor()
	{
		final SponsorSearchResponseData response = defaultSponsorSearchService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
