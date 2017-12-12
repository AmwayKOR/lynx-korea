package com.amway.core.order.dao.impl;


import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.daos.impl.DefaultOrderDao;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.core.data.AmwayOrderSearchParameters;
import com.amway.core.order.dao.AmwayOrderDao;


/**
 * Default Implementation to retrieve orders with payment status not PAID
 */
public class DefaultAmwayOrderDao extends DefaultOrderDao implements AmwayOrderDao
{
	@Autowired
	private PagedFlexibleSearchService pagedFlexibleSearchService;

	private static final String FIND_ORDERS = "Select {order." + OrderModel.PK + "} from { " + OrderModel._TYPECODE + " as order JOIN "
			+ BaseStoreModel._TYPECODE + " as store on {order." + OrderModel.STORE + "}={store." + BaseStoreModel.PK + "} ";
	private static final String JOIN = " JOIN ";
	private static final String AND_OPERATOR = " AND ";
	private static final String DESC = " DESC ";
	private static final String ASC = " ASC ";
	private static final String WHERE = " WHERE ";

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.core.order.dao.AmwayOrderDao#getOrders(com.amway.lynxfacades.data.AmwayOrderSearchParameters ,
	 * de.hybris.platform.commerceservices.search.pagedata.PageableData)
	 */
	@Override
	public SearchPageData<OrderModel> getOrders(final AmwayOrderSearchParameters amwayOrderSearchParameters,
			final PageableData pageableData)
	{
		String sortOrder = DESC;

		final List<SortQueryData> sortQueries = new ArrayList<>();

		final SortQueryData sqd = new SortQueryData();

		final Map<String, Object> param = new HashMap<>();

		final StringBuilder queryBuilder = new StringBuilder(FIND_ORDERS);

		joinQueryForOrdersList(queryBuilder, amwayOrderSearchParameters);
		queryBuilder.append(WHERE);
		addOrderListCondition(param, queryBuilder, amwayOrderSearchParameters);
		final String query = queryBuilder.toString();
		if (amwayOrderSearchParameters.isSortOrderASC())
		{
			sortOrder = ASC;
		}
		sqd.setQuery(query + " ORDER BY {order." + pageableData.getSort() + "} " + sortOrder);
		sqd.setSortCode("by" + pageableData.getSort());
		sortQueries.add(sqd);
		//add logger to print query & share query
		return pagedFlexibleSearchService.search(sortQueries, sqd.getSortCode(), param, pageableData);
	}

	private void joinQueryForOrdersList(final StringBuilder queryBuilder, final AmwayOrderSearchParameters amwayOrderSearchParameters)
	{
		if (amwayOrderSearchParameters.getOrderingABOName() != null || amwayOrderSearchParameters.getOrderingABOID() != null)
		{
			queryBuilder.append(JOIN).append(UserModel._TYPECODE).append(" as user on {order." + OrderModel.USER + "}={user." + UserModel.PK + "}");
			queryBuilder.append(JOIN).append(CustomerModel._TYPECODE).append(" as customer on {user." + UserModel.PK + "}={customer." + CustomerModel.PK + "}");
		}

		if (amwayOrderSearchParameters.getSalesChannel() != null)
		{
			queryBuilder.append(JOIN).append(SalesApplication._TYPECODE)
					.append(" as orderChannel on {order." + OrderModel.SALESAPPLICATION + "}={orderChannel.pk}");
		}
		queryBuilder.append("}");
	}

	private void addOrderListCondition(final Map<String, Object> param, final StringBuilder queryBuilder,
			final AmwayOrderSearchParameters amwayOrderSearchParameters)
	{
		if (amwayOrderSearchParameters.getOrderDateFrom() != null && amwayOrderSearchParameters.getOrderDateTo() != null)
		{
			final boolean appendAndOperator = true;
			addDateConditionForSearch(param, queryBuilder, amwayOrderSearchParameters, appendAndOperator);
		}
		if (amwayOrderSearchParameters.getOrderingABOName() != null)
		{
			queryBuilder.append(AND_OPERATOR);
			queryBuilder.append("({customer." + CustomerModel.NAME + "} LIKE UPPER(?orderingABOName) OR {customer." + CustomerModel.NAME + "} LIKE LOWER(?orderingABOName)) ");
			param.put("orderingABOName", "%" + amwayOrderSearchParameters.getOrderingABOName() + "%");
		}
		if (amwayOrderSearchParameters.getOrderingABOID() != null)
		{
			queryBuilder.append(AND_OPERATOR);
			queryBuilder.append("{customer." + CustomerModel.CUSTOMERID + "} LIKE ?orderingABOID ");
			param.put("orderingABOID", "%" + amwayOrderSearchParameters.getOrderingABOID() + "%");
		}

		if (amwayOrderSearchParameters.getOrderNo() != null)
		{
			queryBuilder.append(AND_OPERATOR);
			queryBuilder.append("{order." + OrderModel.CODE + "} LIKE ?orderNo");
			param.put("orderNo", "%" + amwayOrderSearchParameters.getOrderNo() + "%");
		}

		if (amwayOrderSearchParameters.getSalesChannel() != null)
		{
			queryBuilder.append(AND_OPERATOR);
			queryBuilder.append("({orderChannel.code} =?orderChannel OR {orderChannel.codeLowerCase} =?orderChannel) ");
			param.put("orderChannel", amwayOrderSearchParameters.getSalesChannel());
		}
	}

	private void addDateConditionForSearch(final Map<String, Object> param, final StringBuilder queryBuilder,
			final AmwayOrderSearchParameters amwayOrderSearchParameters, final boolean appendAndOperator)
	{
		if (appendAndOperator)
		{
			queryBuilder.append(AND_OPERATOR);
		}

		param.put("dateFrom", amwayOrderSearchParameters.getOrderDateFrom());
		param.put("dateTo", amwayOrderSearchParameters.getOrderDateTo());
		queryBuilder.append("{").append(OrderModel.DATE).append("} >= ?dateFrom AND {").append(OrderModel.DATE)
				.append("} <= ?dateTo");
	}
}
