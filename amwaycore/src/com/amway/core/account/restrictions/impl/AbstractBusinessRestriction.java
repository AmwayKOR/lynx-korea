/**
 *
 */
package com.amway.core.account.restrictions.impl;

import com.amway.core.account.restrictions.BusinessRestriction;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.platform.core.model.order.CartModel;
import org.apache.commons.lang.NotImplementedException;


/**
 */

public abstract class AbstractBusinessRestriction implements BusinessRestriction
{

	/**
	 * {@link #evaluate(CartModel, AmwayAccountModel)}
	 */
	@Override
	public boolean evaluate(final CartModel cartModel, final AmwayAccountModel accountModel)
	{
		throw new NotImplementedException();
	}

	/**
	 * {@link #evaluate(AmwayAccountModel)}
	 */
	@Override
	public boolean evaluate(final AmwayAccountModel accountModel)
	{
		throw new NotImplementedException();
	}
}
