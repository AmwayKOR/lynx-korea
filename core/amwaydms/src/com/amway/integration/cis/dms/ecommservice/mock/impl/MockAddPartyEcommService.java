/**
 *
 */
package com.amway.integration.cis.dms.ecommservice.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Mock Service to add the party ecomm details.
 */
public class MockAddPartyEcommService
		extends AbstractDmsService<CommonResponseFieldsData, AddUpdatePartyEcommRequestData, ReturnInfoService>
		implements DmsService<AddUpdatePartyEcommRequestData, CommonResponseFieldsData>
{

	@Override
	public CommonResponseFieldsData process(final AddUpdatePartyEcommRequestData requestData)
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
		responseData.setReturnMessage("Fail to added Ecomm details for Party");
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
