/**
 *
 */
package com.amway.core.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.los.data.LosAccountDetailDownlinesResponse;
import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.service.LosService;


/**
 * Mock service for Los Account Detail
 */
public class MockLosAccountDetailService implements LosService<LosAccountRequestData, LosAccountDetailResponseData>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.core.los.service.LosService#process(java.lang.Object)
	 */
	@Override
	public LosAccountDetailResponseData process(final LosAccountRequestData requestData)
	{
		final LosAccountDetailResponseData accountDetailResponseData = new LosAccountDetailResponseData();
		final List<LosAccountDetailDownlinesResponse> downlinesResponseList = new ArrayList<LosAccountDetailDownlinesResponse>();
		final LosAccountDetailDownlinesResponse downlinesResponse = new LosAccountDetailDownlinesResponse();

		downlinesResponse.setAboNo("111");
		downlinesResponse.setBonus("6");
		downlinesResponse.setGpv(new Double(309.87));
		downlinesResponse.setGroupOrders(5);
		downlinesResponse.setNewAbos(1);
		downlinesResponse.setPersonalOrders(3);
		downlinesResponse.setTotalAbos(1);

		downlinesResponseList.add(downlinesResponse);
		accountDetailResponseData.setLosAccountDetailResponseListData(downlinesResponseList);
		accountDetailResponseData.setReturnCode(1);

		return accountDetailResponseData;
	}

}
