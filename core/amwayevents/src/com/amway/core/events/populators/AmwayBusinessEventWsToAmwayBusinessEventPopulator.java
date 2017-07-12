package com.amway.core.events.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.dto.AmwayBusinessEventWsDTO;


/**
 * Populates event ws representation to event.
 */
public class AmwayBusinessEventWsToAmwayBusinessEventPopulator implements Populator<AmwayBusinessEventWsDTO, AmwayBusinessEvent>
{
	@Override
	public void populate(AmwayBusinessEventWsDTO source, AmwayBusinessEvent target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setGenerationTime(source.getGenerationTime());
		target.setEntityID(source.getEntityID());
		target.setEntityName(source.getEntityName());
		target.setRetrievalTime(source.getRetrievalTime());
		target.setTargetSystem(source.getTargetSystem());
		target.setTriggeredBy(source.getTriggeredBy());
		target.setAccountID(source.getAccountID());
		target.setCountryCode(source.getCountryCode());
	}
}
