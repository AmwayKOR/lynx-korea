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
	Integer getCustomerOrderCounts();
}
