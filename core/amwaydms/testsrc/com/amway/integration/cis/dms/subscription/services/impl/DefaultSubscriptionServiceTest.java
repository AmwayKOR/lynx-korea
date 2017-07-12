package com.amway.integration.cis.dms.subscription.services.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.dms.data.DmsSubscriptionResultData;
import com.amway.core.dms.data.SubscriptionInputData;
import com.amway.integration.cis.dms.subscription.services.mock.impl.MockSubscriptionService;


public class DefaultSubscriptionServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";

	@Resource(name = "mockSubscriptionService")
	private MockSubscriptionService defaultSubscriptionService;

	private SubscriptionInputData inputData;

	@Before
	public void setUp() throws Exception
	{
		inputData = new SubscriptionInputData();
		inputData.setAboNum(ABO_NO);
		inputData.setSalesPlanAff(AFFLI_NO);
	}

	@Test
	public void shouldGetSubscriptionInfo()
	{
		final DmsSubscriptionResultData response = defaultSubscriptionService.process(inputData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
