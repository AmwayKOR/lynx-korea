package com.amway.apac.core.customer.services;

import com.amway.core.customer.service.AmwayCustomerAccountService;


/**
 * Extending {@link AmwayCustomerAccountService} to add more order functionalities
 *
 * @author Aaron Yong
 *
 */
public interface AmwayApacCustomerAccountService extends AmwayCustomerAccountService
{
	Integer getOrdersCount();
}
