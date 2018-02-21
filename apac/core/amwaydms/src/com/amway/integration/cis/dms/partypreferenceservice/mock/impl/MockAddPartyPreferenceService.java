/**
 *
 */
package com.amway.integration.cis.dms.partypreferenceservice.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PartyPreferenceData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * @author admin
 */
public class MockAddPartyPreferenceService
		extends AbstractDmsService<CommonResponseFieldsData, PartyPreferenceData, ReturnInfoService>
		implements DmsService<PartyPreferenceData, CommonResponseFieldsData>
{

	@Override
	public CommonResponseFieldsData process(final PartyPreferenceData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{

		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("Failed to add party preference details");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		final ReturnInfoService response = new ReturnInfoService();
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
	}

}
