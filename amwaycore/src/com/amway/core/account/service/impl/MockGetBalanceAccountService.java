package com.amway.core.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.dms.service.DmsService;


public class MockGetBalanceAccountService implements DmsService<CommonRequestFieldsData, GetBalanceResponseData>
{

	@Override
	public GetBalanceResponseData process(final CommonRequestFieldsData requestData)
	{
		final GetBalanceResponseData getBalanceResponseData = new GetBalanceResponseData();
		final List<AccountBalanceData> accountBalanceDatas = new ArrayList<AccountBalanceData>();

		//Account Balance
		final AccountBalanceData accountBalanceData1 = new AccountBalanceData();
		accountBalanceData1.setBalanceTypeCd("Credit");
		accountBalanceData1.setBalanceAmount("1000");
		accountBalanceData1.setCurrencyCd("USD");
		accountBalanceDatas.add(accountBalanceData1);

		final AccountBalanceData accountBalanceData2 = new AccountBalanceData();
		accountBalanceData2.setBalanceTypeCd("Monetary");
		accountBalanceData2.setBalanceAmount("175");
		accountBalanceData2.setCurrencyCd("USD");
		accountBalanceDatas.add(accountBalanceData2);
		getBalanceResponseData.setAccountBalance(accountBalanceDatas);
		return getBalanceResponseData;
	}

}
