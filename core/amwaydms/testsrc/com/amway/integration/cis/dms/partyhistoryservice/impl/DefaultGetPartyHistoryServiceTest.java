/**
 *
 */
package com.amway.integration.cis.dms.partyhistoryservice.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyHistoryDataResponse;
import com.amway.integration.cis.dms.partyhistoryservice.mock.impl.MockGetPartyHistoryService;

import junit.framework.Assert;


@UnitTest
public class DefaultGetPartyHistoryServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";
	private static final String PARTY_ID = "171956";


	@Resource(name = "mockGetPartyHistoryService")
	private MockGetPartyHistoryService defaultGetPartyHistoryService;

	private CommonRequestFieldsData requestData;

	@Before
	public void setUp() throws Exception
	{
		requestData = new CommonRequestFieldsData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFLI_NO);
		requestData.setPartyId(PARTY_ID);
	}

	@Test
	public void shouldGetPartyHistory()
	{
		final PartyHistoryDataResponse response = defaultGetPartyHistoryService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
