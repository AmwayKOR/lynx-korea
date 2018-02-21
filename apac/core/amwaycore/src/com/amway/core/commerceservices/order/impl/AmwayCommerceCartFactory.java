/**
 *
 */
package com.amway.core.commerceservices.order.impl;

import de.hybris.platform.commerceservices.order.impl.CommerceCartFactory;
import de.hybris.platform.core.model.order.CartModel;

import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;


/**
 * To set the {@link AmwayAccountModel} to the {@link CartModel}
 */
public class AmwayCommerceCartFactory extends CommerceCartFactory
{
	private AmwayAccountCommerceService amwayAccountCommerceService;

	@Override
	protected CartModel createCartInternal()
	{
		final CartModel cart = super.createCartInternal();
		final AmwayAccountModel currentAccount = getAmwayAccountCommerceService().getCurrentAccount();
		if (currentAccount != null)
		{
			cart.setAccount(currentAccount);
		}
		return cart;
	}

	/**
	 * @return amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService the amwayAccountCommerceService to set
	 */
	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}
}
