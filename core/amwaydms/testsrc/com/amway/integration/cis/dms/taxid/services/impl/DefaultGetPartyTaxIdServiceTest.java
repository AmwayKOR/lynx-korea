/**
 *
 */
package com.amway.integration.cis.dms.taxid.services.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.data.TaxDetailsData;
import com.amway.integration.cis.dms.taxservices.mock.impl.MockGetPartyTaxService;


public class DefaultGetPartyTaxIdServiceTest extends ServicelayerTest
{
	@Resource(name = "mockGetPartyTaxIdService")
	private MockGetPartyTaxService defaultGetPartyTaxIdService;

	private TaxDetailsData requestData;


	@Before
	public void setUp()
	{
		requestData = new TaxDetailsData();
		requestData.setSalesPlanAff("170");
		requestData.setPartyId("171956");
		requestData.setAboNum("3109215040");

	}

	@Test
	public void shouldGetPartyTaxId()
	{
		final CommonResponseFieldsData result = defaultGetPartyTaxIdService.process(requestData);
		Assert.assertTrue(result.getReturnCd() == 1);
	}

}
