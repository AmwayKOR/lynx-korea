package com.amway.integration.dam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.integration.dam.processor.AmwayDamEventProcessor;
import com.amway.integration.dam.service.AmwayDamEventHandler;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of service for working with {@link AmwayDamQueueEntryModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamEventHandlerImpl implements AmwayDamEventHandler
{
	private List<AmwayDamEventProcessor> amwayDamEventProcessors;

	@Override
	public void handleEvent(AmwayDamQueueEntryModel event)
	{
		//@formatter:off
		AmwayDamEventProcessor eventProcessor = amwayDamEventProcessors.stream()
			  .filter(processor -> processor.isApplicable(event))
			  .findFirst()
			  .orElse(null);
		//@formatter:on

		if (eventProcessor != null)
		{
			eventProcessor.process(event);
		}

	}

	@Required
	public void setAmwayDamEventProcessors(List<AmwayDamEventProcessor> amwayDamEventProcessors)
	{
		this.amwayDamEventProcessors = amwayDamEventProcessors;
	}
}
