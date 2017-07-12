/**
 *
 */
package com.amway.integration.cis.dms.updatepartyinformation.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PartyPersonalDetailsRequestData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


@UnitTest
public class DefaultUpdatePartyInformationServiceTest extends ServicelayerTest
{

	private static final String PARTY_ID = "171956";
	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";
	private static final String BR = "BR";

	@Resource(name = "mockUpdatePartyInformationService")
	private CommonMockService defaultUpdatePartyInformationService;

	private PartyPersonalDetailsRequestData requestData;

	public void setUp()
	{
		requestData = new PartyPersonalDetailsRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setBirthdate("03/02/1982");
		requestData.setBirthCountryCd(BR);
		requestData.setSalesPlanAff(AFFLI_NO);
		requestData.setGenderCd("Female");
		requestData.setLanguageCd("pt");
		requestData.setPartyId(PARTY_ID);
		requestData.setPartyTypeCd("Person");
	}

	public void shouldUpdatePartyInformationService()
	{
		final CommonResponseFieldsData request = defaultUpdatePartyInformationService.process(requestData);
		Assert.assertTrue(request.getReturnCd() == 1);
	}

}
