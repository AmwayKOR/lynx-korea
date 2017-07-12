/**
 *
 */
package com.amway.integration.cis.dms.customer.balance.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;


import com.amway.core.dms.data.CreateBalanceRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Mock Service for to create account balance.
 */
public class MockCreateAccountBalanceService
		extends AbstractDmsService<CommonResponseFieldsData, CreateBalanceRequestData, ReturnInfoService>
		implements DmsService<CreateBalanceRequestData, CommonResponseFieldsData>
{
	@Override
	public CommonResponseFieldsData process(final CreateBalanceRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{

		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnCd(-1);
		commonResponseFieldsData.setReturnMessage("Fail to created balance");
		return commonResponseFieldsData;
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
}
