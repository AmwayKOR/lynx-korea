/**
 *
 */
package com.amway.integration.cis.dms.deletepartyaddressservice.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.DeletePartyAddressRequestData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


@UnitTest
public class DefaultDeletePartyAddressServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";


	@Resource(name = "mockDeletePartyAddressService")
	private CommonMockService defaultDeletePartyAddressService;

	private DeletePartyAddressRequestData requestData;


	@Before
	public void setUp()
	{
		requestData = new DeletePartyAddressRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFlI_NO);
		requestData.setPartyId("129926");
		requestData.setContactPointName("AlternateAddress106");
		requestData.setContactPointTypeCd("AlternateAddress");

	}

	@Test
	public void shouldDeletePartyAddressTest()
	{
		final CommonResponseFieldsData responce = defaultDeletePartyAddressService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}

}
