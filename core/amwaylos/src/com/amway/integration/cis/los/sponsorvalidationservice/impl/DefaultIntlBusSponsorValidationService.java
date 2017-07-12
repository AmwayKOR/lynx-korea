/**
 *
 */
package com.amway.integration.cis.los.sponsorvalidationservice.impl;


import com.amway.core.dms.data.ValidateIntlBusSponsorResponseData;
import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.core.los.service.LosService;
import com.amway.glos.dataobject.LosIntlBusSponsorValidationResult;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;
import org.springframework.util.Assert;


/**
 * service to validate international/primary sponsor
 */
public class DefaultIntlBusSponsorValidationService
		extends AbstractLosService<ValidateIntlBusSponsorResponseData, VerifySponsorRequestData, LosIntlBusSponsorValidationResult>
		implements LosService<VerifySponsorRequestData, ValidateIntlBusSponsorResponseData>
{


	@Override
	protected ValidateIntlBusSponsorResponseData createResultObject()
	{
		return new ValidateIntlBusSponsorResponseData();
	}

	@Override
	protected ValidateIntlBusSponsorResponseData createDefaultResult()
	{
		final ValidateIntlBusSponsorResponseData validateIntlBusSponsorResultData = createResultObject();
		validateIntlBusSponsorResultData.setLmscertified(false);
		validateIntlBusSponsorResultData.setQualifieddPlatinum(false);
		validateIntlBusSponsorResultData.setReturnCd(-1);
		return validateIntlBusSponsorResultData;
	}

	@Override
	protected LosIntlBusSponsorValidationResult executeEvent(final Object input)
	{
		final RestResponse<LosIntlBusSponsorValidationResult> losResultRestResponse = getLosClient()
				.executeLosRequest(getXclientRefId(), getUrlPath(), input,
						LosIntlBusSponsorValidationResult.class);
		Assert.notNull(losResultRestResponse, "Sponsor id validation failure");
		return losResultRestResponse.getResult();
	}
}
