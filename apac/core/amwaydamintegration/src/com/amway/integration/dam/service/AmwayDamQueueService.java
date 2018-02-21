package com.amway.integration.dam.service;

import java.util.List;

import com.amway.integration.dam.data.AmwayDamEventData;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;


/**
 * Service to work with DAM events in the queue.
 */
public interface AmwayDamQueueService
{
	/**
	 * Add list of the events into the queue.
	 *
	 * @param events
	 * 		events to put into the queue.
	 */
	void registerEvents(List<AmwayDamEventData> events);

	/**
	 * Get all events which haven't been unprocessed yet.
	 *
	 * @param limit
	 * 		max number of events to return.
	 * 		Returns 50 elements by default if the parameter is null.
	 * @return list of unprocessed events.
	 */
	List<AmwayDamQueueEntryModel> getEvents(Integer limit);
}
