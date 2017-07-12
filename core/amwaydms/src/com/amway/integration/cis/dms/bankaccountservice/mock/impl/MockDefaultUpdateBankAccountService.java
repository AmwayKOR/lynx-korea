/**
 *
 */
package com.amway.integration.cis.dms.bankaccountservice.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.ProcessBankAccountRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Mock service for update the bank account details.
 */
public class MockDefaultUpdateBankAccountService
		extends AbstractDmsService<CommonResponseFieldsData, ProcessBankAccountRequestData, ReturnInfoService>
		implements DmsService<ProcessBankAccountRequestData, CommonResponseFieldsData>
{

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
		responseData.setReturnCd(1);
		responseData.setReturnMessage("Successfully updated Bank Account for Party");
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
		// YTODO Auto-generated method stub
		return null;
	}

}
