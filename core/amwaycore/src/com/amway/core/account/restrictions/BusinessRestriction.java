/**
 *
 */
package com.amway.core.account.restrictions;

import de.hybris.platform.core.model.order.CartModel;

import com.amway.core.model.AmwayAccountModel;


/**
 * evaluate business restriction
 */
public interface BusinessRestriction
{
	/**
	 * To evaluate Business restriction
	 *
	 * @param cartModel
	 * @param accountModel
	 * @return
	 */
	boolean evaluate(final CartModel cartModel, final AmwayAccountModel accountModel);

	/**
	 * @param accountModel
	 * @return
	 */
	boolean evaluate(final AmwayAccountModel accountModel);
}
