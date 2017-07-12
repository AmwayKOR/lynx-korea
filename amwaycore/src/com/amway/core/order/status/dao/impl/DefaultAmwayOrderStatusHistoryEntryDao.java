package com.amway.core.order.status.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.model.AmwayOrderStatusHistoryEntryModel;
import com.amway.core.order.status.dao.AmwayOrderStatusHistoryEntryDao;


/**
 * Default implementation
 */
public class DefaultAmwayOrderStatusHistoryEntryDao extends DefaultGenericDao<AmwayOrderStatusHistoryEntryModel>
		implements AmwayOrderStatusHistoryEntryDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayOrderStatusHistoryEntryDao.class);

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayOrderStatusHistoryEntryDao()
	{
		super(AmwayOrderStatusHistoryEntryModel._TYPECODE);
	}

	/**
	 * Method to find the order status transitions.
	 *
	 * @param orderCode
	 */
	@Override
	public List<AmwayOrderStatusHistoryEntryModel> findOrderStatusTransitions(final String orderCode)
	{
		Assert.notNull(orderCode, "Order Code cannot be null");
		final Map<String, Object> attributes = new HashMap();
		attributes.put("orderCode", orderCode);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {oshe:").append(AmwayOrderStatusHistoryEntryModel.PK).append("} FROM { ")
				.append(AmwayOrderStatusHistoryEntryModel._TYPECODE).append(" AS oshe }");
		queryString.append(" WHERE {");
		queryString.append(AmwayOrderStatusHistoryEntryModel.ORDER).append("}=?orderCode");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayOrderStatusHistoryEntryModel> result = getFlexibleSearchService().search(query);
		return result.getResult();
	}
}
