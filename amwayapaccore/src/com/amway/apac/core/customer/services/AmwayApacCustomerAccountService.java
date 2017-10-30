/**
 *
 */
package com.amway.apac.core.customer.services;

import com.amway.core.customer.service.AmwayCustomerAccountService;


/**
 * @author Aaron Yong
 *
 */
public interface AmwayApacCustomerAccountService extends AmwayCustomerAccountService
{
	Integer getOrdersCount();
}
