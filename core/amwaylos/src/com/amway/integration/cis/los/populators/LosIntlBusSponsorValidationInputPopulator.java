package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.glos.dataobject.LosIntlBusSponsorValidation;


/**
 * Input populator for LosIntlBusSponsorValidation
 */
public class LosIntlBusSponsorValidationInputPopulator implements Populator<VerifySponsorRequestData, LosIntlBusSponsorValidation>
{

	@Override
	public void populate(final VerifySponsorRequestData source, final LosIntlBusSponsorValidation target)
			throws ConversionException
	{
		if (source != null)
		{
			target.setAffiliate(Integer.parseInt(source.getAffiliateNo()));
			target.setIboNumber(Long.parseLong(source.getSponsorNo().replaceAll("\\s", "")));
		}

	}
}
