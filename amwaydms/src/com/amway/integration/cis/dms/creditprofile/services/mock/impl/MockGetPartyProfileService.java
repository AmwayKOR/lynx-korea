/**
 *
 */
package com.amway.integration.cis.dms.creditprofile.services.mock.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.CreditProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.CreditProfileResponse;
import com.amway.integration.dms.services.PartyCreditPofileObject;


/**
 * Mock Service for get party profile.
 */
public class MockGetPartyProfileService
		extends AbstractDmsService<CreditProfileResponseData, CommonRequestFieldsData, CreditProfileResponse>
		implements DmsService<CommonRequestFieldsData, CreditProfileResponseData>
{

	@Override
	public CreditProfileResponseData process(final CommonRequestFieldsData requestData)
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
	protected CreditProfileResponseData createResultObject()
	{
		return new CreditProfileResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected CreditProfileResponseData createDefaultResult()
	{
		final CreditProfileResponseData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get credit profile");
		return getBalanceResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected CreditProfileResponse executeEvent(final Object input)
	{
		final CreditProfileResponse response = new CreditProfileResponse();

		final PartyCreditPofileObject data = new PartyCreditPofileObject();
		data.setCntryCd("BR");
		data.setCreditScore("0.0");
		data.setCreditStatusCd("AP");
		data.setFicoScore("0.0");
		data.setPartyId("171956");
		response.getPartyCreditProfileObjList().add(data);
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
	}
}
