/**
 *
 */
package com.amway.integration.cis.dms.ecommservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyEcommDetailsResponseData;
import com.amway.integration.cis.dms.ecommservice.mock.impl.MockGetPartyEcommService;


public class DefaultGetPartyEcommServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";
	private static final String PARTY_ID = "171956";

	@Resource(name = "mockGetPartyEcommService")
	private MockGetPartyEcommService defaultGetPartyEcommService;

	private CommonRequestFieldsData requestData;



	@Before
	public void setUp()
	{
		requestData = new CommonRequestFieldsData();
		requestData.setAboNum(ABO_NO);
		requestData.setPartyId(PARTY_ID);
		requestData.setSalesPlanAff(AFFLI_NO);
	}

	@Test
	public void shouldGetPartyEcomm()
	{
		final PartyEcommDetailsResponseData response = defaultGetPartyEcommService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
		Assert.assertTrue(PARTY_ID.equals(response.getEcommMasterListData().get(0).getPartyId()));
	}

}
