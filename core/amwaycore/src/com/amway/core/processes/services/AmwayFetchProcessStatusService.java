/**
 *
 */
package com.amway.core.processes.services;

import java.util.Date;


/**
 * Service for to fetch the process status.
 */
public interface AmwayFetchProcessStatusService
{
	/**
	 * To send the email for failed processes.
	 *
	 * @param processesStatusCode
	 * @param returnStatusCode
	 * @param cronjobEndDate
	 * @return
	 */
	public boolean sendEmailForFailedProcesses(String processesStatusCode, String returnStatusCode, final Date cronjobEndDate);
}
