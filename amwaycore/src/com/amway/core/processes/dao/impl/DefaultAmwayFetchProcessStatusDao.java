package com.amway.core.processes.dao.impl;

import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.amway.core.processes.dao.AmwayFetchProcessStatusDao;


/**
 * Default Implementation
 */
public class DefaultAmwayFetchProcessStatusDao implements AmwayFetchProcessStatusDao
{
	private FlexibleSearchService flexibleSearchService;

	/**
	 * @return flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}


	/**
	 * @param flexibleSearchService the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}


	/**
	 * Method for to get the list of all failed process status.
	 *
	 * @param processesStatusCode
	 * @param cronjobEndDate
	 * @return productQuestionList
	 */
	@Override
	public List<BusinessProcessModel> getListOfAllFailedProcessStatus(final String processesStatusCode, final Date cronjobEndDate)
	{
		final Date todaysDate = new Date();

		final String cronjobModifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cronjobEndDate);
		final String todayDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(todaysDate);

		final StringBuilder query = new StringBuilder();
		query.append("select {op:pk} from {OrderProcess as op}, {ProcessState as ps} where {op:state} = {ps:pk} and {ps:code} ='");
		query.append(processesStatusCode);
		query.append("'");
		query.append("AND");
		query.append("{op:modifiedtime} >'");
		query.append(cronjobModifiedDate);
		query.append("'AND {op:modifiedtime} <='");
		query.append(todayDate);
		query.append("'");

		final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		final SearchResult searchResult = getFlexibleSearchService().search(fsQuery);
		final List<BusinessProcessModel> productQuestionList = searchResult.getResult();
		return productQuestionList;
	}
}
