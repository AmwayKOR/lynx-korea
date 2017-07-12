/**
 *
 */
package com.amway.integration.cis.dms.customeraddress.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AddUpdatePartyAddressRequestData;
import com.amway.core.dms.data.AddressRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.cis.dms.customeraddress.mock.impl.MockUpdateAddressInformationImpl;
import com.hybris.cis.common.utils.StringUtils;


public class DefaultUpdateAddressInformationServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";
	private static final String PARTY_ID = "171956";
	private static final String BR = "BR";

	@Resource(name = "mockUpdateAddressInformationService")
	private MockUpdateAddressInformationImpl defaultUpdateAddressInformationService;

	private AddUpdatePartyAddressRequestData requestData;

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@Before
	public void setUp()
	{
		//TODO: This is to support the TODO in the below 2 methods
		//should be removed after it is fixed there
		//com.amway.integration.cis.dms.populators.AbstractDmsPopulator.formatDate(String, String, String)
		//com.amway.integration.cis.dms.populators.AbstractDmsPopulator.formatInputDate(Date, String)
		configurationService.getConfiguration().setProperty("lynxbrazil.cmssite.timezone", "Brazil/East");

		requestData = new AddUpdatePartyAddressRequestData();
		final AddressRequestData data = new AddressRequestData();
		data.setSalesPlanAff(AFFLI_NO);
		data.setAboNum(ABO_NO);
		data.setPartyId(PARTY_ID);
		data.setCompliment(StringUtils.EMPTY);
		data.setAddressLine1("Livia Amaral");
		data.setNumber("1024");
		data.setNeighbour(" Bela Vista");
		data.setAttention(StringUtils.EMPTY);
		data.setCityName("SaoPaulo");
		data.setCntryCd(BR);
		data.setPostalCd("01310000");
		data.setState("SP");
		data.setLanguageCd("pt");

		final UsageRequestData usageData1 = new UsageRequestData();
		usageData1.setCareOf("BATISTA FERNANDA");
		usageData1.setContactPointPurposeCd("Billing");
		usageData1.setPrimaryFlag("Y");

		final UsageRequestData usageData2 = new UsageRequestData();
		usageData2.setCareOf("BATISTA FERNANDA");
		usageData2.setContactPointPurposeCd("Mailing");
		usageData2.setPrimaryFlag("Y");

		final UsageRequestData usageData3 = new UsageRequestData();
		usageData3.setCareOf("BATISTA FERNANDA");
		usageData3.setContactPointPurposeCd("Shipping");
		usageData3.setPrimaryFlag("Y");

		final List<UsageRequestData> usageList = new ArrayList<UsageRequestData>();
		usageList.add(usageData1);
		usageList.add(usageData2);
		usageList.add(usageData3);
		data.setUsageData(usageList);
		requestData.setPartyAddress(data);
	}

	@Test
	public void shouldUpdateAddressInformation()
	{
		final CommonResponseFieldsData response = defaultUpdateAddressInformationService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}
}
