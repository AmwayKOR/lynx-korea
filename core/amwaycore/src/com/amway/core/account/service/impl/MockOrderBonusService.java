/**
 *
 */
package com.amway.core.account.service.impl;

import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.BonusOrderResultData;
import com.amway.core.los.service.LosService;



public class MockOrderBonusService implements LosService<BonusOrderRequestData, BonusOrderResultData>
{

	@Override
	public BonusOrderResultData process(final BonusOrderRequestData requestData)
	{
		final BonusOrderResultData resultData = new BonusOrderResultData();
		resultData.setReturnMessage("ACCEPT");
		resultData.setDecision("ACCEPT");
		return resultData;
	}
}
