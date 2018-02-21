/**
 *
 */
package com.amway.integration.cis.dms.partypreferenceservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyPreferenceResponse;
import com.amway.integration.cis.dms.partypreferenceservice.mock.impl.MockGetPartyPreferenceService;


/**
 * @author admin
 */
public class DefaultGetPartyPreferenceServiceTest extends ServicelayerTransactionalTest
{
	@Resource(name = "mockGetPartyPreferenceService")
	private MockGetPartyPreferenceService defaultGetPartyPreferenceService;

	private CommonRequestFieldsData commonRequestFieldData;


	@Before
	public void setUp()
	{
		commonRequestFieldData = new CommonRequestFieldsData();
		commonRequestFieldData.setAboNum("7001002605");
		commonRequestFieldData.setSalesPlanAff("170");
		commonRequestFieldData.setPartyId("151058196");
	}


	@Test
	public void shouldGetDmsPartyPreferenceTest()
	{
		final PartyPreferenceResponse responce = defaultGetPartyPreferenceService.process(commonRequestFieldData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}



}
