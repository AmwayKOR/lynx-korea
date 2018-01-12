package com.amway.core.order.services;


import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;

import com.amway.core.data.AmwayOrderSearchParameters;


/**
 * Interface for order service.
 */
public interface AmwayOrderService extends OrderService
{

	SearchPageData<OrderModel> getOrders(AmwayOrderSearchParameters amwayOrderSearchParameters, PageableData pageableData);

}
