/**
 *
 */
package com.amway.core.account.service.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.CreateBalanceRequestData;
import com.amway.core.dms.service.DmsService;

public class MockCreateAccountBalanceService implements DmsService<CreateBalanceRequestData, CommonResponseFieldsData>
{

	@Override
	public CommonResponseFieldsData process(final CreateBalanceRequestData requestData)
	{
		final CommonResponseFieldsData commonResponseFieldsData = createCommonResponseFieldsData();
		commonResponseFieldsData.setReturnCd(1);
		commonResponseFieldsData.setReturnMessage("Balance created successfully");
		return commonResponseFieldsData;
	}

	private CommonResponseFieldsData createCommonResponseFieldsData()
	{
		return new CommonResponseFieldsData();
	}
}
