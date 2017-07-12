/**
 *
 */
package com.amway.core.processes.dao;

import de.hybris.platform.processengine.model.BusinessProcessModel;

import java.util.Date;
import java.util.List;


/**
 * To fetch the process status.
 */
public interface AmwayFetchProcessStatusDao
{
	/**
	 * Method for to get the list of all failed process status.
	 *
	 * @param processesStatusCode
	 * @param cronjobEndDate
	 * @return List<BusinessProcessModel>
	 */
	public List<BusinessProcessModel> getListOfAllFailedProcessStatus(String processesStatusCode, final Date cronjobEndDate);
}
