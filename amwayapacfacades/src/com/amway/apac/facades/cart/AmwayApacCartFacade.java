package com.amway.apac.facades.cart;

import de.hybris.platform.commercefacades.order.data.CartData;

import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;
import com.amway.facades.cart.AmwayCartFacade;


/**
 * Facade layer for cart specific operations.
 *
 * Created by Govind on 11/27/2017.
 */
public interface AmwayApacCartFacade extends AmwayCartFacade
{
	/**
	 * Returns session cart as {@link CartData} in the sorting order passed as the parameter.
	 *
	 * @param sortBy
	 *           Sort Code
	 * @return CartData This method accepts CartData and sort by and returns CartData in given order.
	 */
	CartData getSessionCartWithSortBySortCode(final AmwayApacCartSortCode sortBy);
}
