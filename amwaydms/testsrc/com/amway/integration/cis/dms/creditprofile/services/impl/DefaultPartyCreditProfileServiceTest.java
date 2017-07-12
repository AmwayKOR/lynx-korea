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
import com.amway.integration.cis.dms.creditprofile.services.mock.impl.MockGetPartyProfileService;


@UnitTest
public class DefaultPartyCreditProfileServiceTest extends ServicelayerTest
{
	private static final String PARTY_ID = "130030";
	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";


	@Resource(name = "mockPartyCreditProfileService")
	private MockGetPartyProfileService defaultPartyCreditProfileService;

	private CreditProfileData requestData;


	@Before
	public void setUp()
	{
		requestData = new CreditProfileData();
		requestData.setAboNum(ABO_NO);
		requestData.setPartyId(PARTY_ID);
		requestData.setSalesPlanAff(AFFlI_NO);
	}

	@Test
	public void shouldGetPartyCreditProfileTest()
	{
		final CommonResponseFieldsData responce = defaultPartyCreditProfileService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}

}
