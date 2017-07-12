package com.amway.core.events.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.dto.AmwayBusinessEventWsDTO;


/**
 * Populates business event to its ws representation.
 */
public class AmwayBusinessEventToAmwayBusinessEventWsPopulator implements Populator<AmwayBusinessEvent, AmwayBusinessEventWsDTO>
{
	@Override
	public void populate(AmwayBusinessEvent source, AmwayBusinessEventWsDTO target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setGenerationTime(source.getGenerationTime());
		target.setEntityID(source.getEntityID());
		target.setEntityName(source.getEntityName());
		target.setRetrievalTime(source.getRetrievalTime());
		target.setTriggeredBy(source.getTriggeredBy());
		target.setAccountID(source.getAccountID());
		target.setCountryCode(source.getCountryCode());
	}
}
