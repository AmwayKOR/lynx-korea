/**
 *
 */
package com.amway.core.customer.dao.impl;

import de.hybris.platform.commerceservices.customer.dao.impl.DefaultCustomerAccountDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.core.customer.dao.AmwayCustomerAccountDao;
import com.amway.core.enums.AmwayCartType;


/**
 * Default Implementation for Amway customer account to find the orders.
 */
public class DefaultAmwayCustomerAccountDao extends DefaultCustomerAccountDao implements AmwayCustomerAccountDao
{

	/**
	 * {@link #findOrderByCustomerAndStoreAndType(de.hybris.platform.core.model.user.CustomerModel, de.hybris.platform.commerceservices.search.pagedata.PageableData, de.hybris.platform.store.BaseStoreModel, com.amway.core.enums.AmwayCartType)}
	 */
	@Override
	public SearchPageData<OrderModel> findOrderByCustomerAndStoreAndType(final CustomerModel customerModel,
			final PageableData pageableData, final BaseStoreModel store, final AmwayCartType type)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer must not be null");
		ServicesUtil.validateParameterNotNull(store, "Store must not be null");

		final Map queryParams = new HashMap();
		queryParams.put("customer", customerModel);
		queryParams.put("store", store);
		queryParams.put("type", type);

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[] { createSortQueryData("byDate",
				"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL AND {store} = ?store AND {type} = ?type ORDER BY {creationtime} DESC, {pk}"),
				createSortQueryData("byOrderNumber",
						"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL AND {store} = ?store AND {type} = ?type ORDER BY {code},{creationtime} DESC, {pk}") });

		return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
	}

	/**
	 * {@link #findOrderByCodeAndCustomerAndStoreAndType(de.hybris.platform.core.model.user.CustomerModel, java.lang.String, de.hybris.platform.store.BaseStoreModel, com.amway.core.enums.AmwayCartType)}
	 */
	@Override
	public OrderModel findOrderByCodeAndCustomerAndStoreAndType(final CustomerModel currentUser, final String code,
			final BaseStoreModel store, final AmwayCartType type)
	{
		ServicesUtil.validateParameterNotNull(currentUser, "Customer must not be null");
		ServicesUtil.validateParameterNotNull(code, "Code must not be null");
		ServicesUtil.validateParameterNotNull(store, "Store must not be null");
		final Map queryParams = new HashMap();
		queryParams.put("customer", currentUser);
		queryParams.put("code", code);
		queryParams.put("store", store);
		queryParams.put("type", type);
		final OrderModel result = (OrderModel) getFlexibleSearchService().searchUnique(new FlexibleSearchQuery(
						"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {code} = ?code AND {versionID} IS NULL AND {user} = ?customer AND {store} = ?store AND {type} = ?type",
						queryParams));
		return result;
	}
}
