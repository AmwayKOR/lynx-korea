/**
 *
 */
package com.amway.integration.cis.dms.customeraddress.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.dms.data.AddressResultData;
import com.amway.dms.data.AddressInformationRequestData;
import com.amway.integration.cis.dms.customeraddress.mock.impl.MockAddressInformationImpl;


@UnitTest
public class DefaultAddressInformationServiceTest extends ServicelayerTest
{
	@Resource(name = "mockAddressInformationService")
	private MockAddressInformationImpl defaultAddressInformationService;

	private AddressInformationRequestData requestData;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		requestData = new AddressInformationRequestData();
		requestData.setAboNum("3109215040");
		requestData.setPartyId("171956");
		requestData.setContactPointPurposeCd("HomeAddress");
		requestData.setPrimaryFlag("Y");
	}

	@Test
	public void shouldGetAddressInformation()
	{
		final AddressResultData<AddressProcessDecision> response = defaultAddressInformationService.process(requestData);
		Assert.assertTrue(response.getDecision().equals(AddressProcessDecision.ACCEPT));
	}

}
