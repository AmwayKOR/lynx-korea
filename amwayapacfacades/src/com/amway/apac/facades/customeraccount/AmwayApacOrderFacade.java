package com.amway.apac.facades.customeraccount;

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
}
