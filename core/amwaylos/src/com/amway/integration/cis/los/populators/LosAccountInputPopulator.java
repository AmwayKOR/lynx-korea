/**
 *
 */
package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.los.data.LosAccountRequestData;
import com.amway.integration.cis.los.pojo.LosAccountRequest;


/**
 * populator to populate LosAccountRequestData to LosAccountRequest
 */
public class LosAccountInputPopulator implements Populator<LosAccountRequestData, LosAccountRequest>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final LosAccountRequestData source, final LosAccountRequest target) throws ConversionException
	{
		target.setAff(source.getSalesPlanAff());
		target.setRequestingAbo(source.getRequestingAbo());
		target.setDepth(source.getDepth());
		target.setBonusPeriod(source.getBonusPeriod());
		target.setAbo(source.getAbo());
	}

}
