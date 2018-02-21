package com.amway.apac.core.checkout.services;

import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;


/**
 * Strategy layer for warehouse evaluations.
 *
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
	 *            if parameter is null or it does not have cart.
	 */
	boolean setWarehouse(final CommerceCheckoutParameter parameter);
}
