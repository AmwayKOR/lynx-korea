package com.amway.integration.cis.dms.customeraddress.mock.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.dms.data.AddressResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.dms.data.AddressInformationRequestData;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AddressInformationResponse;
import com.amway.integration.dms.services.AddressObjectService;
import com.amway.integration.dms.services.UsageData;



/**
 * Mock Service for Address Information.
 */

public class MockAddressInformationImpl extends
		AbstractDmsService<AddressResultData<AddressProcessDecision>, AddressInformationRequestData, AddressInformationResponse>
		implements DmsService<AddressInformationRequestData, AddressResultData<AddressProcessDecision>>
{

	@Override
	public AddressResultData<AddressProcessDecision> process(final AddressInformationRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
	 */
	@Override
	protected AddressResultData<AddressProcessDecision> createResultObject()
	{
		return new AddressResultData<AddressProcessDecision>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected AddressResultData<AddressProcessDecision> createDefaultResult()
	{
		final AddressResultData<AddressProcessDecision> result = createResultObject();
		result.setDecision(AddressProcessDecision.REJECT);
		result.setResponseMessage("Fail to get Assress information");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected AddressInformationResponse executeEvent(final Object input)
	{
		final AddressInformationResponse response = new AddressInformationResponse();
		final AddressObjectService address1 = new AddressObjectService();

		address1.setAddrStreet(convertToJAXBString("addrStreet", "AV MARIANA UBALDINA DO ESPIRITO SANTO, 249"));
		address1.setAddrLineTwo(convertToJAXBString("addrLineTwo", "APTO 102A "));
		address1.setAddrLineThree(convertToJAXBString("addrLineThree", "MACEDO "));
		address1.setCityName(convertToJAXBString("addrLineThree", ""));
		address1.setPostalCd(convertToJAXBString("postalCd", "07197000"));
		address1.setContactPointName(convertToJAXBString("contactPointName", "omeAddress"));
		address1.setAddrLineFour(convertToJAXBString("addrLineFour", ""));
		address1.setPartyId(convertToJAXBString("partyId", "171956"));
		address1.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", "HomeAddress"));
		address1.setCntryCd(convertToJAXBString("cntryCd", "BR"));
		address1.setStateCd(convertToJAXBString("stateCd", "SP"));

		final UsageData usageData1 = new UsageData();
		usageData1.setCareOf("BATISTA FERNANDA");
		usageData1.setContactPointPurposeCd("Billing");
		usageData1.setPrimaryFlag("Y");

		final UsageData usageData2 = new UsageData();
		usageData2.setCareOf("BATISTA FERNANDA");
		usageData2.setContactPointPurposeCd("Mailing");
		usageData2.setPrimaryFlag("Y");

		final UsageData usageData3 = new UsageData();
		usageData3.setCareOf("BATISTA FERNANDA");
		usageData3.setContactPointPurposeCd("Shipping");
		usageData3.setPrimaryFlag("Y");
		address1.getUsageDataList().add(usageData1);
		address1.getUsageDataList().add(usageData2);
		address1.getUsageDataList().add(usageData3);

		response.getAddressMasterList().add(address1);
		response.setReturnCd(1);

		return response;
	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;
	}

}
