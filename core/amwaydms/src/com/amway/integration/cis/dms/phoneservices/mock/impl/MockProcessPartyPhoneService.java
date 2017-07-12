/**
 *
 */
package com.amway.integration.cis.dms.phoneservices.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Mock Service for to process the party phone.
 */
public class MockProcessPartyPhoneService
		extends AbstractDmsService<CommonResponseFieldsData, PartyPhoneDetailsRequestData, ReturnInfoService>
		implements DmsService<PartyPhoneDetailsRequestData, CommonResponseFieldsData>
{

	@Override
	public CommonResponseFieldsData process(final PartyPhoneDetailsRequestData requestData)
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
	protected CommonResponseFieldsData createResultObject()
	{

		return new CommonResponseFieldsData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData responseData = new CommonResponseFieldsData();
		responseData.setReturnCd(-1);
		responseData.setReturnMessage("Failed to process party phone.");
		return responseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		final ReturnInfoService responce = new ReturnInfoService();
		responce.setReturnCd(1);
		responce.setReturnMessage("Successfully process party phone.");
		return responce;
	}

}
