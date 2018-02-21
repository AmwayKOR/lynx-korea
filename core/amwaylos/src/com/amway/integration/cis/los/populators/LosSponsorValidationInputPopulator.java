package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.los.data.VerifySponsorRequestData;
import com.amway.glos.dataobject.LosSponsorValidation;


/**
 * Input populator for Los Sponsor Validation
 */
public class LosSponsorValidationInputPopulator implements Populator<VerifySponsorRequestData, LosSponsorValidation>
{


	@Override
	public void populate(final VerifySponsorRequestData source, final LosSponsorValidation target) throws ConversionException
	{

		if (source != null)
		{
			target.setAffiliate(Integer.parseInt(source.getAffiliateNo()));
			target.setBusNatr(source.getBusinessNature());
			target.setCountry(source.getCountryCode());
			target.setSponsorNo(Long.parseLong(source.getSponsorNo()));
			target.setSponTypeCd(source.getSponsorTypeCode());
		}
	}

}
