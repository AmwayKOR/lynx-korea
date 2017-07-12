/**
 *
 */
package com.amway.core.account.restrictions.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.util.Config;

import org.apache.log4j.Logger;

import com.amway.core.account.restrictions.BusinessRestriction;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;


/**
 * evaluate account order restriction
 */
public class AccountOrderRestriction extends AbstractBusinessRestriction
{
	private static final Logger LOG = Logger.getLogger(AccountOrderRestriction.class);

	/**
	 * {@link #evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 */
	@Override
	public boolean evaluate(final CartModel cart, final AmwayAccountModel amwayAccount)
	{
		if (amwayAccount != null)
		{
			for (final AmwayBusinessRestrictionModel restrictions : amwayAccount.getRestrictions())
			{
				if (Config.getString("checkout.order.restriction.id", "BlockOrdering")
						.equalsIgnoreCase(restrictions.getRestrictionId()))
				{
					LOG.error("Ordering Block on Account " + amwayAccount.getCode() + ", stopping place order");
					return true;
				}
				final DeliveryModeModel delivery = cart.getDeliveryMode();
				if ("pickup-bopis".equalsIgnoreCase(delivery.getCode()) && Config
						.getString("checkout.order.pickup.restriction.id", "BlockInStorePickUp")
						.equalsIgnoreCase(restrictions.getRestrictionId()))
				{
					LOG.error("PickUp Block on Account " + amwayAccount.getCode() + ", stopping place order");
					return true;
				}
			}
		}
		return false;
	}

}
