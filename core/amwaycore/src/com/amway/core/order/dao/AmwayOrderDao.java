package com.amway.core.order.dao;


import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.daos.OrderDao;

import com.amway.core.data.AmwayOrderSearchParameters;


/**
 * Extended DAO for orders
 */
public interface AmwayOrderDao extends OrderDao
{
	SearchPageData<OrderModel> getOrders(AmwayOrderSearchParameters amwayOrderSearchParameters, PageableData pageableData);
}
