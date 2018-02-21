/**
 *
 */
package com.amway.integration.cis.dms.phoneservices.impl;

import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.DeletePersonalIdDetailsData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;

import java.util.Collections;


/**
 * @author SumitS
 */
public class DefaultDeletePartyPhoneServiceTest extends ServicelayerTest
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";
	private static final String PARTY_ID = "171956";


	@Resource(name = "mockDeletePartyPhoneService")
	private CommonMockService defaultDeletePartyPhoneService;

	private PartyPhoneDetailsRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new PartyPhoneDetailsRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFlI_NO);
		requestData.setPartyId(PARTY_ID);

		final PhoneMasterRequestData phoneMasterListData = new PhoneMasterRequestData();

		phoneMasterListData.setContactPointName("MobilePhone");
		phoneMasterListData.setContactPointTypeCd("Mobile");

		requestData.setPhoneMasterListData(Collections.singletonList(phoneMasterListData));

	}

	@Test
	public void shouldDeletePartyCreditProfileTest()
	{
		final CommonResponseFieldsData responce = defaultDeletePartyPhoneService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}
}

