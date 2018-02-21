/**
 *
 */
package com.amway.amwaydms.balance.services.impl.mock;

import com.amway.amwaydms.model.ErrorMessage;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.CreateBalanceRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.CommonResponse;


/**
 * Mock Service for to create account balance.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
		"https://jira.amway.com:8444/display/HC/Mocks")
public class MockCreateAccountBalanceService
		extends AbstractDmsService<CommonResponseFieldsData, CreateBalanceRequestData, CommonResponse>
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
		commonResponseFieldsData.setReturnMessage("Fail to create account balance");
		return commonResponseFieldsData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected CommonResponse executeEvent(final Object input)
	{
		final CommonResponse response = new CommonResponse();
		response.setErrorMessage(new ErrorMessage());
		response.getErrorMessage().setCode(1);
		response.getErrorMessage().setMessage("Success");
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
