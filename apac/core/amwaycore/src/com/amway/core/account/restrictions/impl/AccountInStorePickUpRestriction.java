/**
 *
 */
package com.amway.core.account.restrictions.impl;

import de.hybris.platform.core.model.order.CartModel;

import com.amway.core.account.restrictions.BusinessRestriction;
import com.amway.core.model.AmwayAccountModel;


/**
 * evaluate account in-store pick-up restriction
 */
public class AccountInStorePickUpRestriction extends AbstractBusinessRestriction
{

	/**
	 * {@link #evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 */
	@Override
	public boolean evaluate(final CartModel cartModel, final AmwayAccountModel accountModel)
	{
		// YTODO Auto-generated method stub
		return false;
	}

}
