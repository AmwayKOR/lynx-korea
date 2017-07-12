package com.amway.core.events.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.amway.core.events.beans.AmwayBusinessEvent;


/**
 * Service to work with stored business events in the queue.
 */
public interface AmwayBusinessEventQueueService
{
	/**
	 * Add the specified event into the queue.
	 *
	 * @param e
	 * 		event to put into the queue.
	 */
	void queueEvent(AmwayBusinessEvent e);

	/**
	 * Get all events which haven't been exported yet.
	 *
	 * @param limit
	 * 		max number of events to return.
	 * @return list of unexported events.
	 */
	List<AmwayBusinessEvent> getUnexportedEvents(Integer limit, String target);

	/**
	 * Purge old events from DB.
	 *
	 * @param date
	 * 		date before which all events must be deleted.
	 */
	void purgeOldEvents(Date date);

	/**
	 * Set event properties.
	 *
	 * @param code
	 * 		event code to find.
	 * @param properties
	 * 		new event properties.
	 * @throws IllegalArgumentException
	 * 		in case event not found.
	 */
	void setEventProperties(String code, AmwayBusinessEvent properties) throws IllegalArgumentException;

	/**
	 * Confirm that the event has been already processed. It will not be returned by {@link #getUnexportedEvents(Integer, String)}  anymore.
	 *
	 * @param code
	 */
	void confirmExported(String code) throws Exception;

	/**
	 * Confirm that the event has been already processed. It will not be returned by {@link #getUnexportedEvents(Integer, String)} anymore.
	 *
	 * @param codes
	 * 		list of event codes.
	 */
	void confirmExported(Collection<String> codes) throws Exception;

	/**
	 * Trigger processing events in the queue.
	 * This method starts cron jobs for events which must be sent by hybris.
	 */
	void sendPostponedEvents();
}
