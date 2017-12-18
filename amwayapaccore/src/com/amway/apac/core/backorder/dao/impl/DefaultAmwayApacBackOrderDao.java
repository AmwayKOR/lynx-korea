/**
 *
 */
package com.amway.apac.core.backorder.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;



/**
 * Default Implementation of AmwayApacBackOrderDao
 *
 * @author ankushbhatia
 */
public class DefaultAmwayApacBackOrderDao implements AmwayApacBackOrderDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacBackOrderDao.class);

	private FlexibleSearchService flexibleSearchService;

	private static final String DEFAULT_BACKORDER_FETCH_QUERY = new StringBuilder().append("SELECT {")
			.append(AmwayBackOrderModel.PK).append("} FROM {").append(AmwayBackOrderModel._TYPECODE)
			.append("as BO join AmwayBackOrderStatus as BOS on {BO.status}={BOS.pk}} WHERE {BOS.code} =? ")
			.append(AmwayBackOrderModel.STATUS).toString().intern();
	private static final String DEFAULT_EXPIRED_BACKORDER_FETCH_QUERY = "SELECT {ABO:pk} from {amwaybackorder as ABO join amwaybackorderstatus as BS on {ABO:status}={BS:pk}} where {BS:code}=?statusCode and {ABO:releasebydate}<?date";

	private static final String STATUS_CODE = "statusCode";
	private static final Date DATE = "date";


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrders(final AmwayBackOrderStatus status, final WarehouseModel warehouse,
			final ProductModel product, final boolean isAscending)
	{
		validateParameterNotNull(status, "AmwayBackOrderStatus must not be null!");
		final StringBuilder query = new StringBuilder(DEFAULT_BACKORDER_FETCH_QUERY);
		final Map<String, Object> params = new HashMap<>();
		params.put(AmwayBackOrderModel.STATUS, status);
		if (Objects.nonNull(warehouse))
		{
			query.append(" AND {").append(AmwayBackOrderModel.WAREHOUSE).append("} =? ").append(AmwayBackOrderModel.WAREHOUSE);
			params.put(AmwayBackOrderModel.WAREHOUSE, warehouse);
		}
		if (Objects.nonNull(product))
		{
			query.append(" AND {").append(AmwayBackOrderModel.PRODUCT).append("} =? ").append(AmwayBackOrderModel.PRODUCT);
			params.put(AmwayBackOrderModel.PRODUCT, product);
		}
		if (isAscending)
		{
			query.append("ORDER BY {BO.creationtime} ASC");
		}
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query.toString(), params);
		final SearchResult<AmwayBackOrderModel> result = getFlexibleSearchService().search(flexQuery);
		//log info in debug before returning
		logDebugInfo(query.toString(), result.getResult());
		return result.getResult();
	}


	/**
	 * To print result and query in debug mode
	 *
	 * @param query
	 * @param backOrders
	 */
	private void logDebugInfo(final String query, final List<AmwayBackOrderModel> backOrders)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(query);
			for (final AmwayBackOrderModel backOrder : backOrders)
			{
				LOG.debug("\t Order : " + backOrder.getOriginalOrder().getCode());
			}
		}
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao#getBackOrdersForExpiring()
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrdersForExpiring(final String status, final Date date)
	{
		validateParameterNotNull(status, "AmwayBackOrderStatus must not be null!");
		final Map queryParams = new HashMap();
		queryParams.put(STATUS_CODE, status);
		queryParams.put(DATE, date);
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(DEFAULT_EXPIRED_BACKORDER_FETCH_QUERY);
		query.addQueryParameters(queryParams);
		final SearchResult<AmwayBackOrderModel> result = getFlexibleSearchService().search(flexQuery);
		//log info in debug before returning
		logDebugInfo(flexQuery.toString(), result.getResult());
		return result.getResult();
	}

}
