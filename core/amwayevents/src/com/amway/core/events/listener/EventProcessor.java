package com.amway.core.events.listener;

import com.amway.core.events.beans.AmwayBusinessEvent;


/**
 * An abstraction that allows to implement logic which must be executed before sending an event to external system
 * and after successful event delivery.
 */
public interface EventProcessor
{
	/**
	 * Implementation must return true or false to indicate whether it cares of the passed in event.
	 * Subsequent methods ({@link #beforeSend(AmwayBusinessEvent)} and {@link #afterSend(AmwayBusinessEvent)})
	 * will be executed only in case this method returns true.
	 *
	 * @param ev
	 * 		event to test.
	 * @return true if this processor is interested in the event.
	 */
	boolean applicable(AmwayBusinessEvent ev);

	/**
	 * Method executed before sending event to external system.
	 *
	 * @param ev
	 */
	void beforeSend(AmwayBusinessEvent ev);

	/**
	 * Method executed after sending event to external system.
	 * It is executed in transaction scope after all changes to the DB were made.
	 *
	 * @param ev
	 */
	void afterSend(AmwayBusinessEvent ev);
}
