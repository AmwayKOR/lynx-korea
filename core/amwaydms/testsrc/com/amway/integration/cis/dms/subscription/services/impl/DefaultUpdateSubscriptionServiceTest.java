package com.amway.integration.cis.dms.subscription.services.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.constants.AmwaycoreConstants.SessionVariables;
import com.amway.core.dms.data.SubscriptionData;
import com.amway.core.dms.data.SubscriptionDataResponse;
import com.amway.core.dms.data.SubscriptionOption;
import com.amway.core.dms.data.SubscriptionOptionRequestData;
import com.amway.core.model.AmwayAccountModel;
import com.amway.integration.cis.dms.subscription.services.mock.impl.MockUpdateSubscriptionService;


public class DefaultUpdateSubscriptionServiceTest extends ServicelayerTest
{

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private SessionService sessionService;

	@Resource(name = "mockUpdateSubscriptionService")
	private MockUpdateSubscriptionService defaultUpdateSubscriptionService;

	private SubscriptionOptionRequestData requestData;

	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");


		baseSiteService.setCurrentBaseSite("storetemplate", true);

		final AmwayAccountModel acountModel = new AmwayAccountModel();
		acountModel.setCode("3109215040");
		sessionService.getCurrentSession().setAttribute(SessionVariables.ACCOUNT, acountModel);

		requestData = new SubscriptionOptionRequestData();
		final SubscriptionData data = new SubscriptionData();
		data.setAboNumber("3109215040");
		final SubscriptionOption option = new SubscriptionOption();
		option.setName("AD");
		option.setValue(true);
		data.setSubscriptionOptionList(Collections.singletonList(option));
		data.setSubscriptionStatus("SUBSCRIBED");

		requestData.setSubscriptionDataList(Collections.singletonList(data));
	}

	@Test
	public void shouldUpdateSubscriptionService()
	{
		final SubscriptionDataResponse response = defaultUpdateSubscriptionService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
