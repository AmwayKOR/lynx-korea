/**
 *
 */
package com.amway.core.account.service.impl;

import com.amway.core.cis.los.pojo.LosOrderPeriodRequest;
import com.amway.core.los.data.OrderPeriodCloseResultData;
import com.amway.core.los.service.LosService;



public class MockOrderPeriodClosedService implements LosService<LosOrderPeriodRequest, OrderPeriodCloseResultData>
{

	@Override
	public OrderPeriodCloseResultData process(final LosOrderPeriodRequest requestData)
	{
		final OrderPeriodCloseResultData resultData = new OrderPeriodCloseResultData();
		resultData.setReturnMessage("ACCEPT");
		resultData.setDecision("ACCEPT");
		return resultData;
	}
}
