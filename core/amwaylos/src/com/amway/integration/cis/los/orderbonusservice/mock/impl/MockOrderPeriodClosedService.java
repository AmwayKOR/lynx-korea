/**
 *
 */
package com.amway.integration.cis.los.orderbonusservice.mock.impl;

import com.amway.core.los.data.OrderPeriodCloseResultData;
import com.amway.core.los.service.LosService;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.integration.cis.los.service.impl.AbstractLosService;


/**
 * Mock Service for Order period closed.
 */
public class MockOrderPeriodClosedService extends AbstractLosService<OrderPeriodCloseResultData, AmwayOrderPeriodModel, String>
		implements LosService<AmwayOrderPeriodModel, OrderPeriodCloseResultData>
{
	private static final String HttpStatus_OK = "200";
	private static final String REJECT = "REJECT";


	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createResultObject()
	 */
	@Override
	protected OrderPeriodCloseResultData createResultObject()
	{
		return new OrderPeriodCloseResultData();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected OrderPeriodCloseResultData createDefaultResult()
	{
		final OrderPeriodCloseResultData resultData = new OrderPeriodCloseResultData();
		resultData.setReturnMessage(REJECT);
		resultData.setDecision(REJECT);
		return resultData;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected String executeEvent(final Object input)
	{
		return HttpStatus_OK;
	}
}
