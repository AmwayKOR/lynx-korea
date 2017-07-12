/**
 *
 */
package com.amway.integration.cis.dms.phoneservices.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


public class DefaultAddPartyPhoneServiceTest extends ServicelayerTest
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";
	private static final String PARTY_ID = "171956";


	@Resource(name = "mockAddPartyPhoneService")
	private CommonMockService defaultAddPartyPhoneService;

	private PartyPhoneDetailsRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new PartyPhoneDetailsRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFlI_NO);
		requestData.setPartyId(PARTY_ID);
		final PhoneMasterRequestData phoneMasterListData = new PhoneMasterRequestData();
		phoneMasterListData.setCntryCd("BR");
		phoneMasterListData.setDayFlag("N");
		phoneMasterListData.setPhoneAreaCd("16");
		phoneMasterListData.setPhoneCntryCd("55");
		phoneMasterListData.setPhoneLocalNum("981547962");
		phoneMasterListData.setPhoneId("153752");
		phoneMasterListData.setContactPointName("MobilePhone");
		phoneMasterListData.setContactPointTypeCd("Mobile");

		final UsageRequestData usageData = new UsageRequestData();
		usageData.setContactPointPurposeCd("GeneralPurpose");
		usageData.setPrimaryFlag("N");
		phoneMasterListData.setUsageData(Collections.singletonList(usageData));
		requestData.setPhoneMasterListData(Collections.singletonList(phoneMasterListData));
	}

	@Test
	public void shouldAddPartyPhoneTest()
	{
		final CommonResponseFieldsData responce = defaultAddPartyPhoneService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}
}
