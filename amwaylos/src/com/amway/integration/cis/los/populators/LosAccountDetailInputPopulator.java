/**
 *
 */
package com.amway.integration.cis.los.populators;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.los.data.LosAccountRequestData;
import com.amway.integration.cis.los.pojo.LosAccountRequest;


/**
 * populator to populate LosAccountRequestData to LosAccountRequest
 */
public class LosAccountDetailInputPopulator extends LosAccountInputPopulator
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final LosAccountRequestData source, final LosAccountRequest target) throws ConversionException
	{
		super.populate(source, target);
		target.setAbo(source.getAbo());
		target.setGetVolume(source.getGetVolume());
		target.setGetExtAttributes(source.getGetExtAttributes());
		target.setGetSponsorStats(source.getGetSponsorStats());
		target.setBonusPeriodCount(source.getBonusPeriodCount());
		target.setGetQualification(source.getGetQualification());
		//target.setGetCustomers(source.getGetCustomers());
	}
}
