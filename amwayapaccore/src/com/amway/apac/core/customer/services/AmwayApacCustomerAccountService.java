/**
 *
 */
package com.amway.apac.core.customer.services;

import de.hybris.platform.core.model.user.CustomerModel;

import com.amway.core.customer.service.AmwayCustomerAccountService;


/**
 * @author Aaron Yong
 *
 */
public interface AmwayApacCustomerAccountService extends AmwayCustomerAccountService
{
	CustomerModel getOrdersCount();
}
