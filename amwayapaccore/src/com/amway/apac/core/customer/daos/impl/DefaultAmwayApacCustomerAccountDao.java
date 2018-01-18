package com.amway.apac.core.customer.daos.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.BASE_STORE_STRING;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.USER_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import static java.time.ZoneId.systemDefault;
import static java.util.Collections.singletonList;

import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.apac.core.customer.daos.AmwayApacCustomerAccountDao;
import com.amway.core.customer.dao.impl.DefaultAmwayCustomerAccountDao;


/**
 * Default implementation of {@link AmwayApacCustomerAccountDao}
 *
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacCustomerAccountDao extends DefaultAmwayCustomerAccountDao implements AmwayApacCustomerAccountDao
{
	/**
	 * Query to search for order count based on user.
	 */
	private static final String FIND_ORDERCOUNT_FOR_CUSTOMER = new StringBuilder(TWO_HUNDRED_INT).append("SELECT COUNT({o.")
			.append(OrderModel.PK).append("}) FROM {").append(OrderModel._TYPECODE).append(" as o} WHERE {o.")
			.append(OrderModel.USER).append("} = ?").append(OrderModel.USER).append(" AND {o.").append(OrderModel.STORE)
			.append("} = ?").append(OrderModel.STORE).toString();

	/**
	 * Query to search for order history based on filter by user.
	 */
	private static final String FIND_ORDERS_BY_CUSTOMER_STORE_FILTER_QUERY = new StringBuilder(TWO_HUNDRED_INT).append("SELECT {")
			.append(OrderModel.PK).append("}, {").append(OrderModel.CREATIONTIME).append("}, {").append(OrderModel.CODE)
			.append("} FROM {").append(OrderModel._TYPECODE).append("} WHERE {").append(OrderModel.USER)
			.append("} = ?customer AND {").append(OrderModel.VERSIONID).append("} IS NULL AND {").append(OrderModel.STORE)
			.append("} = ?store ").append("AND {").append(OrderModel.DATE).append("} <= ?endDate ").append("AND {")
			.append(OrderModel.DATE).append("} >= ?startDate ").toString();

	/**
	 * Query to sort results by date.
	 */
	private static final String SORT_ORDERS_BY_DATE = new StringBuilder(TWO_HUNDRED_INT).append(" ORDER BY {")
			.append(OrderModel.CREATIONTIME).append("} DESC, {").append(OrderModel.PK).append("}").toString();

	/**
	 * Query to sort results by code
	 */
	private static final String SORT_ORDERS_BY_CODE = new StringBuilder(TWO_HUNDRED_INT).append(" ORDER BY {")
			.append(OrderModel.CODE).append("},{").append(OrderModel.CREATIONTIME).append("} DESC, {").append(OrderModel.PK)
			.append("}").toString();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer findOrderCountsForUser(final UserModel user, final BaseStoreModel baseStore)
	{
		validateParameterNotNullStandardMessage(USER_STRING, user);
		validateParameterNotNullStandardMessage(BASE_STORE_STRING, baseStore);

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_ORDERCOUNT_FOR_CUSTOMER);
		fQuery.addQueryParameter(OrderModel.USER, user);
		fQuery.addQueryParameter(OrderModel.STORE, baseStore);
		fQuery.setResultClassList(singletonList(Integer.class));

		final SearchResult<Integer> searchResult = search(fQuery);
		return searchResult.getResult().iterator().next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<OrderModel> findOrdersByCustomerAndStoreAndFilterByDateAndType(final CustomerModel customerModel,
			final BaseStoreModel store, final LocalDate datefrom, final LocalDate dateto, final String type,
			final PageableData pageableData)
	{
		validateParameterNotNull(customerModel, "Customer must not be null");
		validateParameterNotNull(store, "Store must not be null");

		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("customer", customerModel);
		queryParams.put("store", store);

		final Date dateFrom = Date.from(datefrom.atStartOfDay(systemDefault()).toInstant());
		final Date dateTo = Date.from(dateto.atStartOfDay(systemDefault()).toInstant());

		queryParams.put("startDate", dateFrom);
		queryParams.put("endDate", dateTo);

		validateParameters(queryParams);

		final List<SortQueryData> sortQueries;
		sortQueries = Arrays.asList(
				createSortQueryData("byDate", createQuery(FIND_ORDERS_BY_CUSTOMER_STORE_FILTER_QUERY, "", SORT_ORDERS_BY_DATE)),
				createSortQueryData("byOrderNumber",
						createQuery(FIND_ORDERS_BY_CUSTOMER_STORE_FILTER_QUERY, "", SORT_ORDERS_BY_CODE)));

		return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
	}

	/**
	 * Validates each parameter for null value.
	 *
	 * @param parameters
	 * @throws IllegalArgumentException
	 *            if any of the parameters is null.
	 */
	protected void validateParameters(final Map<String, Object> parameters)
	{
		parameters.forEach(ServicesUtil::validateParameterNotNullStandardMessage);
	}

}
