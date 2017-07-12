/**
 *
 */
package com.amway.core.infraavail.service;

import de.hybris.platform.task.TaskConditionModel;

import java.util.List;


/**
 * Interface dedicated for getting infra waiting state events and publish event
 */
public interface AmwayInfraAvailabilityEventPublishService
{
	/**
	 * Get Tasks which are waiting for event with code %infraAvailCode
	 *
	 * @param infraAvailCode
	 * @return List<TaskConditionModel>
	 */
	List<TaskConditionModel> getWaitingEvents(String infraAvailCode);

	/**
	 * Method to retrieve and publish events for tasks in waiting state for %infraAvailCode
	 *
	 * @param infraAvailCode
	 */
	void publishWaitingEvents(final String infraAvailCode);

	/**
	 * Method to publish corresponding events for the task conditions
	 *
	 * @param taskConditionModels
	 */
	void publishEvents(final List<TaskConditionModel> taskConditionModels);
}
