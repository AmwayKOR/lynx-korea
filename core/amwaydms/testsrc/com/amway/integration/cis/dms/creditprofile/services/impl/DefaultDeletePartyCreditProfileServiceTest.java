/**
 *
 */
package com.amway.integration.cis.dms.creditprofile.services.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.CreditProfileData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


public class DefaultDeletePartyCreditProfileServiceTest extends ServicelayerTest
{

	private static final String PARTY_ID = "171956";


	@Resource(name = "mockDeletePartyCreditProfileService")
	private CommonMockService defaultDeletePartyCreditProfileService;

	private CreditProfileData requestData;


	@Before
	public void setUp()
	{
		requestData = new CreditProfileData();
		requestData.setCntryCd("BR");
		requestData.setPartyId(PARTY_ID);
	}

	@Test
	public void shouldDeletePartyCreditProfileTest()
	{
		final CommonResponseFieldsData responce = defaultDeletePartyCreditProfileService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}

}
