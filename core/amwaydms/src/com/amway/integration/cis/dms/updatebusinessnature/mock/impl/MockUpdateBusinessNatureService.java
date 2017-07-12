/**
 *
 */
package com.amway.integration.cis.dms.updatebusinessnature.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;
import com.amway.dms.data.UpdateBusinessNatureInputRequestData;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Mock Service for to update the business nature.
 */

public class MockUpdateBusinessNatureService
		extends AbstractDmsService<CommonResponseFieldsData, UpdateBusinessNatureInputRequestData, ReturnInfoService>
		implements DmsService<UpdateBusinessNatureInputRequestData, CommonResponseFieldsData>
{

	@Override
	public CommonResponseFieldsData process(final UpdateBusinessNatureInputRequestData requestData)
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
		responseData.setReturnMessage("Faile to update business nature");
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
		final ReturnInfoService response = new ReturnInfoService();
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
	}

}
