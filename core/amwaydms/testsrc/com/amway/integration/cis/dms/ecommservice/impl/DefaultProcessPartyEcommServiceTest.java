/**
 *
 */
package com.amway.integration.cis.dms.ecommservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
import com.amway.core.dms.data.PartyEcommRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.cis.dms.ecommservice.mock.impl.MockProcessPartyEcommService;


public class DefaultProcessPartyEcommServiceTest extends ServicelayerTest
{

	@Resource(name = "mockProcessPartyEcommService")
	private MockProcessPartyEcommService defaultProcessPartyEcommService;

	private AddUpdatePartyEcommRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new AddUpdatePartyEcommRequestData();
		final PartyEcommRequestData ecommRequestData = new PartyEcommRequestData();
		requestData.setAboNum("3109215040");
		requestData.setPartyId("171956");
		requestData.setSalesPlanAff("170");

		ecommRequestData.setContactPointName("BusinessEmail");
		ecommRequestData.setContactId("1203314");
		ecommRequestData.setEcommAddr("comu@comu.com.br");

		final UsageRequestData usageData = new UsageRequestData();
		usageData.setContactPointPurposeCd("GeneralPurpose");
		usageData.setPrimaryFlag("Y");
		ecommRequestData.setUsageData(Collections.singletonList(usageData));

		requestData.setPartyEcommData(Collections.singletonList(ecommRequestData));
	}

	@Test
	public void test()
	{
		final CommonResponseFieldsData responce = defaultProcessPartyEcommService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}

}
