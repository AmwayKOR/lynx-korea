/**
 *
 */
package com.amway.core.account.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import com.amway.core.los.data.LosAccountDownlinesResponse;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.los.service.LosService;


/**
 * mock service for Los Account
 */
public class MockLosAccountService implements LosService<LosAccountRequestData, LosAccountResponseData>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.core.los.service.LosService#process(java.lang.Object)
	 */
	@Override
	public LosAccountResponseData process(final LosAccountRequestData requestData)
	{
		final LosAccountResponseData losAccountResponseData = new LosAccountResponseData();
		losAccountResponseData.setReturnCode(1);
		losAccountResponseData.setReturnMessage("Valid Result.");
		losAccountResponseData.setListOfAboAndAff(new ArrayList<String>());
		final LosAccountDownlinesResponse downlinesResponse = new LosAccountDownlinesResponse();
		downlinesResponse.setCountry("US");
		losAccountResponseData.setLosAccountResponseListData(Arrays.asList(downlinesResponse));
		return losAccountResponseData;
	}

}
