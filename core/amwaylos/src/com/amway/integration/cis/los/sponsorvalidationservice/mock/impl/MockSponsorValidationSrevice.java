package com.amway.integration.cis.los.sponsorvalidationservice.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.LosSponsorValidationResultData;
import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.core.los.service.LosService;
import com.amway.glos.dataobject.LosSponsorValidationResults;
import com.amway.integration.cis.los.service.impl.AbstractLosService;


/**
 * Mock for Validation sponsor service.
 */
public class MockSponsorValidationSrevice
		extends AbstractLosService<LosSponsorValidationResultData, VerifySponsorRequestData, LosSponsorValidationResults>
		implements LosService<VerifySponsorRequestData, CommonResponseFieldsData>
{

	@Override
	public LosSponsorValidationResultData process(final VerifySponsorRequestData requestData)
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
	protected LosSponsorValidationResultData createResultObject()
	{
		return new LosSponsorValidationResultData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected LosSponsorValidationResultData createDefaultResult()
	{
		final LosSponsorValidationResultData verifySponsorInfoResultData = new LosSponsorValidationResultData();
		verifySponsorInfoResultData.setReturnMessage("validation.sponsorId.invalid");
		verifySponsorInfoResultData.setReturnCd(-1);
		return verifySponsorInfoResultData;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected LosSponsorValidationResults executeEvent(final Object input)
	{
		final LosSponsorValidationResults result = new LosSponsorValidationResults();
		result.setLosValReturnMessage("Success");
		result.setLosValReturnCode(1);
		return result;
	}
}
