/**
 *
 */
package com.amway.integration.cis.los.orderbonusservice.mock.impl;

import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.BonusOrderResultData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.service.impl.AbstractLosService;



/**
 * Mock service for order bonus.
 */
public class MockOrderBonusService extends AbstractLosService<BonusOrderResultData, BonusOrderRequestData, String>
		implements LosService<BonusOrderRequestData, BonusOrderResultData>
{

	private static final String HttpStatus_OK = "200";
	private static final String REJECT = "REJECT";


	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createResultObject()
	 */
	@Override
	protected BonusOrderResultData createResultObject()
	{
		return new BonusOrderResultData();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected BonusOrderResultData createDefaultResult()
	{
		final BonusOrderResultData resultData = new BonusOrderResultData();
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
