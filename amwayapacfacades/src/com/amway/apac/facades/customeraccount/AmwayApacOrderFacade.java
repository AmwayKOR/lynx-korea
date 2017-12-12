package com.amway.apac.facades.customeraccount;

import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;

import com.amway.core.facades.order.AmwayOrderFacade;


/**
 * Extending {@link AmwayOrderFacade} to add more order functionalities
 *
 * @author Aaron Yong
 *
 */
public interface AmwayApacOrderFacade extends AmwayOrderFacade
{
	/**
	 * Featches the number of orders for the current user in current base store.
	 *
	 * @return number of orders found.
	 */
	Integer getCustomerOrderCounts();

	List<String> getOrderHistoryDateOptions();

	List<String> getOrderHistoryTypeOptions();

	SearchPageData<OrderHistoryData> getPagedOrderHistoryByFilter(PageableData pageableData, String date, String type);
}
