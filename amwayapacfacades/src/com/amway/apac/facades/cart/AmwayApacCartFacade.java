package com.amway.apac.facades.cart;

import de.hybris.platform.commercefacades.order.data.CartData;

import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;
import com.amway.facades.cart.AmwayCartFacade;


/**
 * Created by Govind on 11/27/2017.
 */
public interface AmwayApacCartFacade extends AmwayCartFacade
{
	/**
	 *
	 * @param data
	 * @param sortBy
	 * @return CartData This method accepts CartData and sort by and returns CartData in given order.
	 */
	CartData getSessionCartWithSortBySortCode(AmwayApacCartSortCode sortBy);
}
