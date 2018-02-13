package com.amway.apac.facades.order;

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
	 * Fetches the number of orders for the current user in current base store.
	 *
	 * @return number of orders found.
	 */
	Integer getCustomerOrderCounts();

	/**
	 * Returns the Order History Date Options
	 *
	 * @return List of date options
	 */
	List<String> getOrderHistoryDateOptions();

	/**
	 * Returns the paginated order history for the given date and type.
	 *
	 * @param pageableData
	 * @param date
	 *           Date
	 * @param type
	 *           Type
	 * @return Order History Data
	 */
	SearchPageData<OrderHistoryData> getPagedOrderHistoryByFilterAndSearch(final PageableData pageableData, final String date,
			final String type);

}
