/**
 *
 */
package com.amway.apac.facades.customeraccount;

import com.amway.core.facades.order.AmwayOrderFacade;


/**
 * @author MYU073HH
 *
 */
public interface AmwayApacCustomerAccountFacade extends AmwayOrderFacade
{
	Integer getCustomerOrderCounts();
}
