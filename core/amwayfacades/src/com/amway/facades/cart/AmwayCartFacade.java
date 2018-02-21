/**
 * Extended cart facade for Amway specific featurese
 */
package com.amway.facades.cart;

import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.exceptions.CalculationException;


/**
 *
 * Interface for amway cart
 *
 */
public interface AmwayCartFacade extends CartFacade
{
	/**
	 * calculates all totals. this does not trigger price, tax and discount calculation but takes all currently set
	 * price, tax and discount values as base. this method requires the correct subtotal to be set before and the correct
	 * tax value map.
	 *
	 * @param order
	 * @throws CalculationException
	 */
	void calculateGiftWrap(final AbstractOrderModel order) throws CalculationException;

	/**
	 *
	 * @param cartType
	 */
	void setCartType(String cartType);

	void addPickupDateTime(String pickupDate);
}

