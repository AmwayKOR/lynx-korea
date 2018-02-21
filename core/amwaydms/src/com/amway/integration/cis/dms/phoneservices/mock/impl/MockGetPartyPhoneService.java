/**
 *
 */
package com.amway.integration.cis.dms.phoneservices.mock.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyPhoneDetailsResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyPhoneResponse;
import com.amway.integration.dms.services.PartyPhoneData;


/**
 * Mock Service to get party phone details.
 */
public class MockGetPartyPhoneService
		extends AbstractDmsService<PartyPhoneDetailsResponseData, CommonRequestFieldsData, GetPartyPhoneResponse>
		implements DmsService<CommonRequestFieldsData, PartyPhoneDetailsResponseData>
{

	@Override
	public PartyPhoneDetailsResponseData process(final CommonRequestFieldsData requestData)
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
	protected PartyPhoneDetailsResponseData createResultObject()
	{

		return new PartyPhoneDetailsResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected PartyPhoneDetailsResponseData createDefaultResult()
	{
		final PartyPhoneDetailsResponseData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get ecomm details");
		return getBalanceResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected GetPartyPhoneResponse executeEvent(final Object input)
	{
		final GetPartyPhoneResponse responce = new GetPartyPhoneResponse();
		final PartyPhoneData phoneData = new PartyPhoneData();
		phoneData.setPartyId(convertToJAXBString("partyId", "3400029"));
		phoneData.setContactPointName(convertToJAXBString("contactPointName", "HomePhone1"));
		phoneData.setPhoneCntryCd(convertToJAXBString("phoneCntryCd", "55"));
		phoneData.setPhoneAreaCd(convertToJAXBString("phoneAreaCd", "41"));
		phoneData.setSmsCapableFlag(convertToJAXBString("smsCapableFlag", "Y"));
		phoneData.setCntryCd(convertToJAXBString("cntryCd", "BR"));
		phoneData.setStatusCd(convertToJAXBString("statusCd", "Valid"));
		phoneData.setPhoneLocalNum(convertToJAXBString("phoneLocalNum", "89876789"));
		phoneData.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", "HomePhone"));
		phoneData.setDayFlag(convertToJAXBString("dayFlag", "N"));
		phoneData.setEveningFlag(convertToJAXBString("eveningFlag", "Y"));

		responce.getPartyPhoneList().add(phoneData);
		responce.setReturnCd(1);
		responce.setReturnMessage("Success");

		return responce;

	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;
	}
}
