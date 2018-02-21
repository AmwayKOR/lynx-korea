package com.amway.core.events.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.model.AmwayEventQueueEntryModel;


/**
 * Converts queue entity to event model.
 */
public class AmwayEventQueueEntityToAmwayBusinessEventPopulator implements Populator<AmwayEventQueueEntryModel, AmwayBusinessEvent>
{
	@Override
	public void populate(AmwayEventQueueEntryModel source, AmwayBusinessEvent target) throws ConversionException
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
		target.setTargetSystem(source.getTargetSystem());
		target.setMeta(source.getMeta());

	}
}
