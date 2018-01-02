package com.amway.apac.core.checkout.services;

import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;

import com.amway.core.checkout.services.AmwayCommerceCheckoutService;


/**
 * @author Shubham Goyal
 */
public interface AmwayApacCommerceCheckoutService extends AmwayCommerceCheckoutService
{
	/**
	 * Sets the Warehouse through the CommerceCheckoutParameter containing warehouse.
	 *
	 * @param parameter
	 *           parameter containing warehouse.
	 * @return true if successful
	 */
	boolean setWarehouse(final CommerceCheckoutParameter parameter);

}
