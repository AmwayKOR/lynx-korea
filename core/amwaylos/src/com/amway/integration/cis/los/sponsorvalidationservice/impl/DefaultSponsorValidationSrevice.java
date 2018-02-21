package com.amway.integration.cis.los.sponsorvalidationservice.impl;

import com.amway.core.dms.data.LosSponsorValidationResultData;
import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.core.los.service.LosService;
import com.amway.glos.dataobject.LosSponsorValidationResults;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/**
 * Service to validate sponsor
 */
public class DefaultSponsorValidationSrevice
		extends AbstractLosService<LosSponsorValidationResultData, VerifySponsorRequestData, LosSponsorValidationResults>
		implements LosService<VerifySponsorRequestData, LosSponsorValidationResultData>
{
	private static final Logger LOG = Logger.getLogger(DefaultSponsorValidationSrevice.class);

	@Override
	protected LosSponsorValidationResultData createResultObject()
	{
		return new LosSponsorValidationResultData();
	}

	@Override
	protected LosSponsorValidationResultData createDefaultResult()
	{
		final LosSponsorValidationResultData verifySponsorInfoResultData = createResultObject();
		verifySponsorInfoResultData.setReturnMessage("validation.sponsorId.invalid");
		verifySponsorInfoResultData.setReturnCd(-1);
		return verifySponsorInfoResultData;
	}

	@Override
	protected LosSponsorValidationResults executeEvent(final Object input)
	{
		final RestResponse<LosSponsorValidationResults> losResultRestResponse = getLosClient()
				.executeLosRequest(getXclientRefId(), getUrlPath(), input,
						LosSponsorValidationResults.class);
		Assert.notNull(losResultRestResponse, "Sponsor id validation failure");
		return losResultRestResponse.getResult();
	}
}
