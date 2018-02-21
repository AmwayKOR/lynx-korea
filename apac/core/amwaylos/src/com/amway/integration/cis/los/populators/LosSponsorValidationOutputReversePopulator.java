package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.LosSponsorValidationResultData;
import com.amway.glos.dataobject.LosSponsorValidationResults;


/**
 * Reverse output populator for LosSponsorValidation.
 */
public class LosSponsorValidationOutputReversePopulator
		implements Populator<LosSponsorValidationResults, LosSponsorValidationResultData>
{
	@Override
	public void populate(final LosSponsorValidationResults source, final LosSponsorValidationResultData target)
			throws ConversionException
	{
		if (source != null)
		{
			target.setReturnMessage(source.getLosValReturnMessage());
			target.setReturnCd(source.getLosValReturnCode());
			target.setSponsorName(source.getSponsorName());
		}
	}



}
