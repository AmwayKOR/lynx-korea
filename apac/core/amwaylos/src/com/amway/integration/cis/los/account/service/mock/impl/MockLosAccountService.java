/**
 *
 */
package com.amway.integration.cis.los.account.service.mock.impl;

import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.pojo.LineOfSponsorship;
import com.amway.integration.cis.los.pojo.LosResponse;
import com.amway.integration.cis.los.service.impl.AbstractLosService;


/**
 * mock service for Los Account
 */
public class MockLosAccountService extends AbstractLosService<LosAccountResponseData, LosAccountRequestData, LosResponse>
		implements LosService<LosAccountRequestData, LosAccountResponseData>
{

	@Override
	public LosAccountResponseData process(final LosAccountRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected LosAccountResponseData createDefaultResult()
	{
		final LosAccountResponseData losAccountResponseData = new LosAccountResponseData();
		losAccountResponseData.setReturnCode(-1);
		losAccountResponseData.setReturnMessage("Default Result.");
		return losAccountResponseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createResultObject()
	 */
	@Override
	protected LosAccountResponseData createResultObject()
	{
		return new LosAccountResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected LosResponse executeEvent(final Object input)
	{

		final LosResponse losResponse = new LosResponse();

		final LineOfSponsorship lineOfSponsorship = new LineOfSponsorship();
		lineOfSponsorship.setABONo(new Long("3109215040"));
		lineOfSponsorship.setAff("170");
		losResponse.setLOS(lineOfSponsorship);

		final LineOfSponsorship downlines = new LineOfSponsorship();
		downlines.setABONo(new Long("3109216023"));
		downlines.setAff("170");
		downlines.setName("CAVALCANTE,NELY BACIC");
		downlines.setCountry("BR");
		downlines.setEntryDate("2009-12-08");
		downlines.setCurrentAwardRank("342");
		downlines.setHighestAwardRank("342");
		downlines.setStatus("1");
		downlines.setGroupABOCount(new Integer("163"));
		downlines.setHasDownlines(Boolean.TRUE);
		lineOfSponsorship.getDownlines().add(downlines);

		losResponse.setReturnCode("1");
		losResponse.setReturnMessage("Valid Result.");

		return losResponse;
	}

}
