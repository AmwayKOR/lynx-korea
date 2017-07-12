/**
 *
 */
package com.amway.integration.cis.dms.personalid.services.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PersonalIdDetailsData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


public class DefaultUpdatePartyPersonalIdServiceTest extends ServicelayerTest
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";
	private static final String PARTY_ID = "171956";


	@Resource(name = "mockUpdatePartyPersonalIdService")
	private CommonMockService defaultUpdatePartyPersonalIdService;

	private PersonalIdDetailsData requestData;


	@Before
	public void setUp()
	{
		requestData = new PersonalIdDetailsData();
		requestData.setEffectiveDate("2016-04-02T21:00:00-03:00");
		requestData.setPersonalId("60946106");
		requestData.setPersonalIdTypeCd("GeneralRegistration");
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFlI_NO);
		requestData.setPartyId(PARTY_ID);
	}

	@Test
	public void shouldUpdatePartyCreditProfileTest()
	{
		final CommonResponseFieldsData responce = defaultUpdatePartyPersonalIdService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}
}
