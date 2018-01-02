package com.amway.apac.core.checkout.services;

import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;


/**
 * @author Shubham Goyal
 */
public interface AmwayApacCommerceWarehouseStrategy
{
	/**
	 * Sets the warehouse given in the parameter in current cart.
	 *
	 * @param parameter
	 *           parameter containing cart and warehouse to set.
	 * @return true, if warehouse was successfully updated in cart
	 *
	 * @throws IllegalArgumentException
	 *            if parameter does not have cart or warehouse
	 */
	boolean setWarehouse(CommerceCheckoutParameter parameter);
}
