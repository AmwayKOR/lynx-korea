package com.amway.integration.cis.dms.customer.balance.mock.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AccountBalanceResponse;
import com.amway.integration.dms.services.AccountBalanceSvcObject;


/**
 * Mock Service for to get account balance information.
 */

public class MockGetBalanceAccountService
		extends AbstractDmsService<GetBalanceResponseData, CommonRequestFieldsData, AccountBalanceResponse>
		implements DmsService<CommonRequestFieldsData, GetBalanceResponseData>
{
	@Override
	public GetBalanceResponseData process(final CommonRequestFieldsData requestData)
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
	protected GetBalanceResponseData createResultObject()
	{
		// YTODO Auto-generated method stub
		return new GetBalanceResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected GetBalanceResponseData createDefaultResult()
	{
		final GetBalanceResponseData response = createResultObject();
		response.setReturnCd(-1);
		response.setReturnMessage("Fail to get customer balance");
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected AccountBalanceResponse executeEvent(final Object input)
	{
		final AccountBalanceResponse response = new AccountBalanceResponse();
		final AccountBalanceSvcObject balance = new AccountBalanceSvcObject();
		balance.setBalanceAmount("123");
		balance.setBalanceTypeCd("Monetary");
		balance.setCurrencyCd("USD");
		balance.setInstrumentId("0");
		response.getAccountBalanceObjList().add(balance);
		response.setReturnCd(1);
		response.setReturnMessage("get customer balance successfully");
		return response;
	}

}
