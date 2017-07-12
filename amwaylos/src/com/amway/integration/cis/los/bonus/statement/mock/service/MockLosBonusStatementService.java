/**
 *
 */
package com.amway.integration.cis.los.bonus.statement.mock.service;

import java.io.File;

import com.amway.core.los.data.BonusStatementRequestData;
import com.amway.core.los.data.BonusStatementResultData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.service.impl.AbstractLosService;


public class MockLosBonusStatementService extends AbstractLosService<BonusStatementResultData, BonusStatementRequestData, File>
		implements LosService<BonusStatementRequestData, BonusStatementResultData>
{

	@Override
	public BonusStatementResultData process(final BonusStatementRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createResultObject()
	 */
	@Override
	protected BonusStatementResultData createResultObject()
	{
		return new BonusStatementResultData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected BonusStatementResultData createDefaultResult()
	{
		final BonusStatementResultData bonusStatementResultData = createResultObject();
		bonusStatementResultData.setReturnCode(new Integer(-1));
		bonusStatementResultData.setReturnMessage("Error to get bonus statement");
		return bonusStatementResultData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected File executeEvent(final Object input)
	{

		return new File("");
	}

}
