package com.amway.core.events.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.core.events.dao.EventQueueDao;
import com.amway.core.events.model.AmwayEventQueueEntryModel;


/**
 * Implements events storage in database.
 */
public class DefaultEventQueueDao implements EventQueueDao
{
	/**
	 * Default list size for response.
	 */
	public static final int DEFAULT_LIST_SIZE = 50;

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	private static final String DATE = "date";

	private static final String CODE = "code";

	private static final String TARGET = "target";

	// @formatter:off
	private static final String UNEXPORTED_EVENTS_QUERY =
			"SELECT {e:" + AmwayEventQueueEntryModel.PK + "}"
					+ "FROM {" + AmwayEventQueueEntryModel._TYPECODE + " AS e}"
					+ "WHERE "
					+ "{e." + AmwayEventQueueEntryModel.RETRIEVALTIME + "} IS NULL "
					+ " AND {e." + AmwayEventQueueEntryModel.TARGETSYSTEM + "} = ?target "
					+ "ORDER BY {e." + AmwayEventQueueEntryModel.PK + "}";

	private static final String OLDER_EVENTS_QUERY =
			"SELECT {e:" + AmwayEventQueueEntryModel.PK + "}"
					+ "FROM {" + AmwayEventQueueEntryModel._TYPECODE + " AS e}"
					+ "WHERE "
					+ "({e." + AmwayEventQueueEntryModel.RETRIEVALTIME + "} IS NULL "
					+ "and {e." + AmwayEventQueueEntryModel.GENERATIONTIME + "} < ?date) OR "
					+ "({e." + AmwayEventQueueEntryModel.RETRIEVALTIME + "} IS NOT NULL "
					+ "and {e." + AmwayEventQueueEntryModel.RETRIEVALTIME + "} < ?date)";

	private static final String EVENTS_BY_CODE_QUERY =
			"SELECT {e:" + AmwayEventQueueEntryModel.PK + "}"
					+ "FROM {" + AmwayEventQueueEntryModel._TYPECODE + " AS e}"
					+ "WHERE "
					+ "{e." + AmwayEventQueueEntryModel.CODE + "} = ?code ";
	// @formatter:on

	/**
	 * {@inheritDoc}
	 * Implementation uses flexible search with {@link #UNEXPORTED_EVENTS_QUERY} query to
	 * retrieve events from the database. It reduces the result to meet 'limit' parameter after retrieval.
	 */
	@Override
	public List<AmwayEventQueueEntryModel> getUnexportedEvents(Integer limit, String targetSystem)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(UNEXPORTED_EVENTS_QUERY);
		query.addQueryParameter(TARGET, targetSystem);
		SearchResult<AmwayEventQueueEntryModel> res = flexibleSearchService.<AmwayEventQueueEntryModel>search(query);
		int toIndex = limit == null ? DEFAULT_LIST_SIZE : limit;
		List<AmwayEventQueueEntryModel> list = res.getResult();
		if (list.size() == 0)
		{
			return list;
		}
		if (toIndex >= list.size())
		{
			toIndex = list.size();
		}
		return list.subList(0, toIndex);
	}

	/**
	 * {@inheritDoc}
	 * Implementation uses flexible search with {@link #OLDER_EVENTS_QUERY} query.
	 */
	@Override
	public List<AmwayEventQueueEntryModel> getOutdatedEvents(Date date)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(OLDER_EVENTS_QUERY);
		query.addQueryParameter(DATE, date);
		return flexibleSearchService.<AmwayEventQueueEntryModel>search(query).getResult();
	}

	/**
	 * {@inheritDoc}
	 * Implementation returns the first found element using query {@link #EVENTS_BY_CODE_QUERY}.
	 */
	@Override
	public AmwayEventQueueEntryModel getEventByCode(String code)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(EVENTS_BY_CODE_QUERY);
		query.addQueryParameter(CODE, code);
		List<AmwayEventQueueEntryModel> res = flexibleSearchService.<AmwayEventQueueEntryModel>search(query).getResult();
		if (res.size() > 0)
		{
			return res.get(0);
		}
		else
		{
			return null;
		}
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
