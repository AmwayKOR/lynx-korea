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
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


public class DefaultAddPartyTaxIdServiceTest extends ServicelayerTest
{

	@Resource(name = "mockAddPartyTaxIdService")
	private CommonMockService defaultAddPartyTaxIdService;

	private TaxDetailsData requestData;


	@Before
	public void setUp()
	{
		requestData = new TaxDetailsData();
		requestData.setCntryCd("BR");
		requestData.setPartyId("171956");
		requestData.setTaxId("02198196964");
		requestData.setTaxIdType("CPF");
	}

	@Test
	public void shouldUpdatePartyTaxId()
	{
		final CommonResponseFieldsData result = defaultAddPartyTaxIdService.process(requestData);
		Assert.assertTrue(result.getReturnCd() == 1);
	}

}
