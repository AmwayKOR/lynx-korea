package com.amway.core.account.service.impl;

import com.amway.core.los.data.BonusStatementRequestData;
import com.amway.core.los.data.BonusStatementResultData;
import com.amway.core.los.service.LosService;


public class MockLosBonusStatementService implements LosService<BonusStatementRequestData, BonusStatementResultData>
{
	@Override
	public BonusStatementResultData process(final BonusStatementRequestData requestData)
	{
		final BonusStatementResultData bonusStatementResultData = new BonusStatementResultData();
		bonusStatementResultData.setReturnCode(new Integer(-1));
		bonusStatementResultData.setReturnMessage("Error to get bonus statement");
		return bonusStatementResultData;
	}
}