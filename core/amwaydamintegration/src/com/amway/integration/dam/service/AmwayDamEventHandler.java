package com.amway.integration.dam.service;

import com.amway.integration.dam.model.AmwayDamQueueEntryModel;


/**
 * Service for working with {@link AmwayDamQueueEntryModel}
 */
public interface AmwayDamEventHandler
{
	/**
	 * Handling defined {@link AmwayDamQueueEntryModel}
	 *
	 * @param event
	 * 	  {@link AmwayDamQueueEntryModel} for handling
	 */
	void handleEvent(AmwayDamQueueEntryModel event);
}
