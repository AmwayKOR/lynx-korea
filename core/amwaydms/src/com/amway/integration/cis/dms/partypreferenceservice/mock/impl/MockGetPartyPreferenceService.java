/**
 *
 */
package com.amway.integration.cis.dms.partypreferenceservice.mock.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyPreferenceResponse;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyPreferenceResponse;
import com.amway.integration.dms.services.PrefAffPartyData;


/**
 * @author admin
 */
public class MockGetPartyPreferenceService extends
		AbstractDmsService<PartyPreferenceResponse, CommonRequestFieldsData, GetPartyPreferenceResponse> implements
		DmsService<CommonRequestFieldsData, PartyPreferenceResponse>
{

	@Override
	public PartyPreferenceResponse process(final CommonRequestFieldsData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	@Override
	protected PartyPreferenceResponse createResultObject()
	{
		return new PartyPreferenceResponse();
	}

	@Override
	protected PartyPreferenceResponse createDefaultResult()
	{
		final PartyPreferenceResponse getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get party preference details");
		return getBalanceResult;
	}

	@Override
	protected GetPartyPreferenceResponse executeEvent(final Object input)
	{
		final GetPartyPreferenceResponse response = new GetPartyPreferenceResponse();

		final PrefAffPartyData prefAffMasterData = new PrefAffPartyData();
		prefAffMasterData.setPreferenceId(convertToJAXBString("preferenceId", "Confidentiality"));
		prefAffMasterData.setAboNo(convertToJAXBString("AboNo", "7000020315"));
		prefAffMasterData.setIsTrueFlg(convertToJAXBString("isTrueFlg", "0"));
		prefAffMasterData.setPartyId(convertToJAXBString("partyId", "151058196"));
		prefAffMasterData.setSalesPlanAff(convertToJAXBString("salesPlanAff", "170"));
		prefAffMasterData.setPreferenceNote(convertToJAXBString("preferenceNote", "1"));
		response.getPrefAffPartyData().add(prefAffMasterData);
		response.setReturnCd(1);
		response.setReturnMessage("Success");

		return response;
	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;
	}


}
