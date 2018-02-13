package com.amway.integration.dam.dao;

import java.util.List;

import com.amway.integration.dam.model.AmwayDamQueueEntryModel;


/**
 * Dao for the {@link AmwayDamQueueEntryModel}
 */
public interface AmwayDamQueueDao
{
	/**
	 * <p>Get all events which haven't been processed yet.</p>
	 * <p>An event considered unprocessed in case it exist in queue.</p>
	 *
	 * @param limit
	 * 		maximum number of returning events
	 * @return unprocessed events.
	 */
	List<AmwayDamQueueEntryModel> findEvents(Integer limit);
}
