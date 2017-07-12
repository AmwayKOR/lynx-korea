package com.amway.integration.cis.dms.updatepartyname.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.LocaleNameData;
import com.amway.core.dms.data.PartyNameDetailsRequestData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


public class DefaultUpdatePartyNameServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";
	private static final String PARTY_ID = "171956";

	@Resource(name = "mockUpdatePartyNameService")
	private CommonMockService defaultUpdatePartyNameService;

	private PartyNameDetailsRequestData requestData;

	@Before
	public void setUp() throws Exception
	{
		requestData = new PartyNameDetailsRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setPartyId(PARTY_ID);
		requestData.setSalesPlanAff(AFFLI_NO);

		requestData.setPersonNameTypeCd("Legal");
		final LocaleNameData nameData = new LocaleNameData();
		nameData.setGivenName("UNCINI");
		nameData.setFamilyName("VILMAR");
		requestData.setLocaleNameData(nameData);

	}

	@Test
	public void shpuldUpdatePartyNameServiceTest()
	{
		final CommonResponseFieldsData response = defaultUpdatePartyNameService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
