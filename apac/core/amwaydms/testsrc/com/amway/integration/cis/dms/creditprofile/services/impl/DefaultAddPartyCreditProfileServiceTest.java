/**
 *
 */
package com.amway.integration.cis.dms.creditprofile.services.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.CreditProfileData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


@UnitTest
public class DefaultAddPartyCreditProfileServiceTest extends ServicelayerTest
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";
	private static final String PARTY_ID = "171956";


	@Resource(name = "mockAddPartyCreditProfileService")
	private CommonMockService defaultAddPartyCreditProfileService;

	private CreditProfileData requestData;


	@Before
	public void setUp()
	{
		requestData = new CreditProfileData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFlI_NO);
		requestData.setPartyId(PARTY_ID);
		requestData.setCntryCd("BR");
		requestData.setCreditScore("0.0");
		requestData.setCreditStatusCd("AP");
		requestData.setFicoScore("0.0");
	}

	@Test
	public void shouldAddPartyCreditProfileTest()
	{
		final CommonResponseFieldsData responce = defaultAddPartyCreditProfileService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}
}
