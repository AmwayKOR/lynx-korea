package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.ValidateIntlBusSponsorResponseData;
import com.amway.glos.dataobject.LosIntlBusSponsorValidationResult;


/**
 * Output populator for LosIntlBusSponsorValidation
 */
public class LosIntlBusSponsorValidationOutputPopulator
		implements Populator<LosIntlBusSponsorValidationResult, ValidateIntlBusSponsorResponseData>
{
	@Override
	public void populate(final LosIntlBusSponsorValidationResult source, final ValidateIntlBusSponsorResponseData target)
			throws ConversionException
	{
		target.setLmscertified(false);
		target.setQualifieddPlatinum(false);
		if ("Y".equalsIgnoreCase(source.getLmscertified()))
		{
			target.setLmscertified(true);
		}
		if ("Y".equalsIgnoreCase(source.getQualifiedPlatinum()))
		{
			target.setQualifieddPlatinum(true);
		}
		if (source.getResultStatus() != null)
		{
			target.setReturnCd(source.getResultStatus().getReturnCode());
			target.setReturnMessage(source.getResultStatus().getReturnMessage());
		}
	}
}
