/**
 *
 */
package com.amway.integration.cis.los.sponsorvalidationservice.mock.impl;

import com.amway.core.dms.data.ValidateIntlBusSponsorResponseData;
import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.core.los.service.LosService;
import com.amway.glos.dataobject.LosIntlBusSponsorValidationResult;
import com.amway.glos.dataobject.ResultStatus;
import com.amway.integration.cis.los.service.impl.AbstractLosService;


/**
 * Mock service for validate internal/business sponsor
 */
public class MockIntlBusSponsorValidationService
		extends AbstractLosService<ValidateIntlBusSponsorResponseData, VerifySponsorRequestData, LosIntlBusSponsorValidationResult>
		implements LosService<VerifySponsorRequestData, ValidateIntlBusSponsorResponseData>
{

	@Override
	public ValidateIntlBusSponsorResponseData process(final VerifySponsorRequestData requestData)
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
	protected ValidateIntlBusSponsorResponseData createResultObject()
	{
		return new ValidateIntlBusSponsorResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected ValidateIntlBusSponsorResponseData createDefaultResult()
	{
		final ValidateIntlBusSponsorResponseData validateIntlBusSponsorResultData = new ValidateIntlBusSponsorResponseData();
		validateIntlBusSponsorResultData.setLmscertified(false);
		validateIntlBusSponsorResultData.setQualifieddPlatinum(false);
		validateIntlBusSponsorResultData.setReturnCd(-1);
		return validateIntlBusSponsorResultData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected LosIntlBusSponsorValidationResult executeEvent(final Object input)
	{
		final LosIntlBusSponsorValidationResult result = new LosIntlBusSponsorValidationResult();
		result.setLmscertified("Y");
		result.setQualifiedPlatinum("Y");
		final ResultStatus status = new ResultStatus();
		status.setReturnCode(1);
		result.setResultStatus(status);
		return result;
	}
}
