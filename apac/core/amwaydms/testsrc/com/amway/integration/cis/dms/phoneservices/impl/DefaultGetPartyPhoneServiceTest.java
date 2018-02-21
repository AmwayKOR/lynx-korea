/**
 *
 */
package com.amway.integration.cis.dms.phoneservices.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.integration.cis.dms.phoneservices.mock.impl.MockGetPartyPhoneService;


public class DefaultGetPartyPhoneServiceTest extends ServicelayerTest
{

	@Resource(name = "mockGetPartyPhoneService")
	private MockGetPartyPhoneService defaultGetPartyPhoneService;
	private CommonRequestFieldsData requestData;

	@Before
	public void setUp()
	{
		requestData = new CommonRequestFieldsData();
		requestData.setAboNum("3109215040");
		requestData.setSalesPlanAff("170");
		requestData.setPartyId("171956");
	}

	@Test
	public void shouldGetPartyCreditProfileTest()
	{
		final CommonResponseFieldsData responce = defaultGetPartyPhoneService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}
}
