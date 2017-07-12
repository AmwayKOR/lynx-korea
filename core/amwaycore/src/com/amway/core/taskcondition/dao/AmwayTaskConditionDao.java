/**
 *
 */
package com.amway.core.taskcondition.dao;

import de.hybris.platform.task.TaskConditionModel;

import java.util.List;


/**
 * Data access to {@link TaskConditionModel}
 */
public interface AmwayTaskConditionDao
{
	/**
	 * find TaskConditionModel for the given regex code
	 *
	 * @param uniqueIdRegex
	 * @return the TaskConditionModel
	 * @code code of the TaskConditionModel
	 */
	List<TaskConditionModel> findWaitingLikeTasks(final String uniqueIdRegex);
}
