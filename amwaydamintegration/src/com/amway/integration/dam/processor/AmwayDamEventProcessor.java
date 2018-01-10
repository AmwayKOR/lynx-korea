package com.amway.integration.dam.processor;

import com.amway.integration.dam.model.AmwayDamQueueEntryModel;


/**
 * Processing DAM events
 */
public interface AmwayDamEventProcessor
{
	/**
	 * Checking that processor is able to process defined event
	 *
	 * @param event
	 * 	  defined event
	 * @return true if processor is able to process event and false otherwise
	 */
	boolean isApplicable(AmwayDamQueueEntryModel event);

	/**
	 * Process defined event
	 *
	 * @param event
	 * 	  defined event
	 */
	void process(AmwayDamQueueEntryModel event);
}
