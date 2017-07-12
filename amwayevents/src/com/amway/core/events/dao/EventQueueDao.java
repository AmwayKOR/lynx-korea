package com.amway.core.events.dao;

import java.util.Date;
import java.util.List;

import com.amway.core.events.model.AmwayEventQueueEntryModel;


/**
 * Repository for business events.
 */
public interface EventQueueDao
{
	/**
	 * <p>Get all events which haven't been exported yet.</p>
	 * <p>An event considered unexported in case field {@link AmwayEventQueueEntryModel#RETRIEVALTIME} is null.</p>
	 *
	 * @param limit maximum number of returning events. Returns 50 elements by default if the parameter is null.
	 * @param target target system (wm, internalCron, etc).
	 * @return unexported events for the specified target system.
	 */
	List<AmwayEventQueueEntryModel> getUnexportedEvents(Integer limit, String target);

	/**
	 * <p>Get all events older than the specified date.</p>
	 * <p>Field {@link AmwayEventQueueEntryModel#GENERATIONTIME} is used to determine event's age.</p>
	 *
	 * @param date the date to compare field {@link AmwayEventQueueEntryModel#GENERATIONTIME} with.
	 * @return all events which are older than the specified date.
	 */
	List<AmwayEventQueueEntryModel> getOutdatedEvents(Date date);

	/**
	 * Get event by its code.
	 *
	 * @param code event's code.
	 * @return event model, null if the event is not found.
	 */
	AmwayEventQueueEntryModel getEventByCode(String code);

}
