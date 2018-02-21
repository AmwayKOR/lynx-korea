/**
 *
 */
package com.amway.integration.cis.dms.ecommservice.mock.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyEcommDetailsResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyEcommResponse;
import com.amway.integration.dms.services.PartyEcommData;
import com.amway.integration.dms.services.UsageData;


/**
 * Mock Service for to get party ecomm details.
 */
public class MockGetPartyEcommService
		extends AbstractDmsService<PartyEcommDetailsResponseData, CommonRequestFieldsData, GetPartyEcommResponse>
		implements DmsService<CommonRequestFieldsData, PartyEcommDetailsResponseData>
{

	@Override
	public PartyEcommDetailsResponseData process(final CommonRequestFieldsData requestData)
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
	protected PartyEcommDetailsResponseData createResultObject()
	{

		return new PartyEcommDetailsResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected PartyEcommDetailsResponseData createDefaultResult()
	{
		final PartyEcommDetailsResponseData responseData = new PartyEcommDetailsResponseData();
		responseData.setReturnCd(-1);
		responseData.setReturnMessage("Fail to get Party Ecomm");
		return responseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected GetPartyEcommResponse executeEvent(final Object input)
	{
		final GetPartyEcommResponse response = new GetPartyEcommResponse();
		final PartyEcommData partyEcommData = new PartyEcommData();
		partyEcommData.setPartyId(convertToJAXBString("partyId", "171956"));
		partyEcommData.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", "BusinessEmail"));
		partyEcommData.setContactPointName(convertToJAXBString("contactPointName", "BusinessEmail"));
		partyEcommData.setEcommAddr(convertToJAXBString("ecommAddr", "comu@comu.com.br"));
		partyEcommData.setStatusCd(convertToJAXBString("statusCd", "Valid"));


		final UsageData usageData = new UsageData();
		usageData.setContactPointPurposeCd("GeneralPurpose");
		usageData.setPrimaryFlag("Y");

		partyEcommData.getUsageDataList().add(usageData);

		response.getPartyEcommDataList().add(partyEcommData);
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;
	}
}
