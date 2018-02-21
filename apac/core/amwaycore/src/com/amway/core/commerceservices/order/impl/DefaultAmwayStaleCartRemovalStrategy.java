/**
 *
 */
package com.amway.core.commerceservices.order.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.commerceservices.strategies.impl.DefaultStaleCartRemovalStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.amway.core.enums.AmwayCartType;


/**
 * Overridden method to skip recurring carts fomr the removal process
 */
public class DefaultAmwayStaleCartRemovalStrategy extends DefaultStaleCartRemovalStrategy
{
	@Override
	public void removeStaleCarts(final CommerceCartParameter parameters)
	{
		final CartModel currentCart = parameters.getCart();
		final BaseSiteModel baseSite = parameters.getBaseSite();
		final UserModel user = parameters.getUser();

		final List<CartModel> cartsToRemove = new ArrayList();

		final List<CartModel> oldCarts = getCommerceCartDao()
				.getCartsForRemovalForSiteAndUser(currentCart.getModifiedtime(), baseSite, user);

		for (final CartModel cart : oldCarts)
		{
			if ((StringUtils.equals(cart.getGuid(), currentCart.getGuid())) || (StringUtils
					.equals(cart.getCode(), currentCart.getCode())) || cart.getType().equals(AmwayCartType.WEBRECURRING))
			{
				continue;
			}
			cartsToRemove.add(cart);
		}

		getModelService().removeAll(cartsToRemove);
	}
}
