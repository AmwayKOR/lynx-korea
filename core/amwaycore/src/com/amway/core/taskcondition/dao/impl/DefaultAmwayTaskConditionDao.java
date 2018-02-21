package com.amway.core.taskcondition.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.task.TaskConditionModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amway.core.taskcondition.dao.AmwayTaskConditionDao;


/**
 * Default implementation
 */
public class DefaultAmwayTaskConditionDao extends DefaultGenericDao<TaskConditionModel> implements AmwayTaskConditionDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayTaskConditionDao.class);

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayTaskConditionDao()
	{
		super(TaskConditionModel._TYPECODE);
	}

	/**
	 * find TaskConditionModel for the given regex code {@link #findWaitingLikeTasks(java.lang.String)}
	 *
	 * @param uniqueIdRegex
	 */
	@Override
	public List<TaskConditionModel> findWaitingLikeTasks(final String uniqueIdRegex)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("uniqueID", "%" + uniqueIdRegex);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {tc:").append(TaskConditionModel.PK).append("}, {tc:").append(TaskConditionModel.UNIQUEID)
				.append("} FROM { ").append(TaskConditionModel._TYPECODE).append(" AS tc }");
		queryString.append(" WHERE {tc:");
		queryString.append(TaskConditionModel.UNIQUEID).append("} like ?uniqueID and {");
		queryString.append(TaskConditionModel.FULFILLED).append("}=0 and {");
		queryString.append(TaskConditionModel.CONSUMED).append("}=0");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<TaskConditionModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}
}
