/**
 *
 */
package com.amway.apac.core.backorder.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Date;
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
	private GenericDao<AmwayBackOrderModel> amwayBackOrderGenericDao;
	private static final String DEFAULT_BACKORDER_FETCH_QUERY = "SELECT {ABO:pk} from {amwaybackorder as ABO join amwaybackorderstatus as BS on {ABO:status}={BS:pk}} where {BS:code}=?status ";
	private static final String RELEASE_BY_DATE = "releaseByDate";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrders(final AmwayBackOrderStatus status, final WarehouseModel warehouse,
			final ProductModel product, final BaseStoreModel baseStore)
	{
		validateParameterNotNull(status, "AmwayBackOrderStatus must not be null!");

		final StringBuilder query = new StringBuilder(DEFAULT_BACKORDER_FETCH_QUERY);
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(AmwayBackOrderModel.STATUS, status.getCode());
		if (Objects.nonNull(warehouse))
		{
			query.append(" AND {").append(AmwayBackOrderModel.WAREHOUSE).append("}=?").append(AmwayBackOrderModel.WAREHOUSE);
			queryParams.put(AmwayBackOrderModel.WAREHOUSE, warehouse);
		}
		if (Objects.nonNull(product))
		{
			query.append(" AND {").append(AmwayBackOrderModel.PRODUCT).append("}=?").append(AmwayBackOrderModel.PRODUCT);
			queryParams.put(AmwayBackOrderModel.PRODUCT, product);
		}
		if (Objects.nonNull(baseStore))
		{
			//TODO
			query.append(" AND {").append(AmwayBackOrderModel.BASESTORE).append("}=?").append(AmwayBackOrderModel.BASESTORE);
			queryParams.put(AmwayBackOrderModel.BASESTORE, baseStore);
		}
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query.toString(), queryParams);
		final SearchResult<AmwayBackOrderModel> result = getFlexibleSearchService().search(flexQuery);
		logDebugInfo(query.toString(), result.getResult());
		return result.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayBackOrderModel> getBackOrdersByStatusAndDate(final AmwayBackOrderStatus status, final Date date)
	{
		validateParameterNotNull(status, "AmwayBackOrderStatus must not be null!");
		final Map queryParams = new HashMap(2);
		queryParams.put(AmwayBackOrderModel.STATUS, status.getCode());
		queryParams.put(RELEASE_BY_DATE, date);
		final StringBuilder query = new StringBuilder(DEFAULT_BACKORDER_FETCH_QUERY);
		query.append(" and {ABO:releasebydate} < ?releaseByDate");
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query.toString(), queryParams);
		final SearchResult<AmwayBackOrderModel> result = getFlexibleSearchService().search(flexQuery);
		logDebugInfo(flexQuery.toString(), result.getResult());
		return result.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayBackOrderModel getBackOrdersByConsignment(final ConsignmentModel consignmentModel)
	{
		validateParameterNotNull(consignmentModel, "ConsignmentModel must not be null!");
		final Map queryParams = new HashMap();
		queryParams.put(AmwayBackOrderModel.CONSIGNMENT, consignmentModel);

		final List<AmwayBackOrderModel> result = amwayBackOrderGenericDao.find(queryParams);
		logDebugInfo("Found Backorder with code : ", result);
		if (result.size() > 1)
		{
			LOG.warn("Found multiple backorders attached to same consignment!!");
		}
		return result.get(0);
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
			final List<String> orderCodeList = new ArrayList<>();
			for (final AmwayBackOrderModel backOrder : backOrders)
			{
				orderCodeList.add(backOrder.getOriginalOrder().getCode() + "-");
			}
			LOG.debug("Query : " + query);
			LOG.debug("\t Order : " + orderCodeList.toString());
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

	/**
	 * @param amwayBackOrderGenericDao
	 *           the amwayBackOrderGenericDao to set
	 */
	@Required
	public void setAmwayBackOrderGenericDao(final GenericDao<AmwayBackOrderModel> amwayBackOrderGenericDao)
	{
		this.amwayBackOrderGenericDao = amwayBackOrderGenericDao;
	}
}
