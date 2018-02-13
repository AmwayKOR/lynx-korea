/**
 *
 */
package com.amway.core.cart.strategy.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartRestoration;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartRestorationStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * @author mohit2496
 *
 */
public class AmwayCommerceCartRestorationStrategy extends DefaultCommerceCartRestorationStrategy
{

	private static final Logger LOG = Logger.getLogger(AmwayCommerceCartRestorationStrategy.class);

	@Override
	public CommerceCartRestoration restoreCart(final CommerceCartParameter parameter) throws CommerceCartRestorationException
	{
		final CartModel cartModel = parameter.getCart();
		final CommerceCartRestoration restoration = new CommerceCartRestoration();
		final List<CommerceCartModification> modifications = new ArrayList<>();
		if (cartModel != null)
		{
			if (getBaseSiteService().getCurrentBaseSite().equals(cartModel.getSite()))
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Restoring from cart " + cartModel.getCode() + ".");
				}
				if (isCartInValidityPeriod(cartModel))
				{
					cartModel.setCalculated(Boolean.FALSE);
					if (!cartModel.getPaymentTransactions().isEmpty())
					{
						// Do not clear payment transactions as opposed to OOTB DefaultCommerceCartRestorationStrategy in order to support multiple/split payments via OCC

						// reset guid since its used as a merchantId for payment subscriptions and is a base id for generating PaymentTransaction.code
						// see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl.authorize(DefaultPaymentServiceImpl.java:177)
						cartModel.setGuid(getGuidKeyGenerator().generate().toString());
					}

					getModelService().save(cartModel);
					try
					{
						getCommerceCartCalculationStrategy().recalculateCart(parameter);
					}
					catch (final IllegalStateException ex)
					{
						LOG.error("Failed to recalculate order [" + cartModel.getCode() + "]", ex);
					}

					getCartService().setSessionCart(cartModel);

					if (LOG.isDebugEnabled())
					{
						LOG.debug("Cart " + cartModel.getCode() + " was found to be valid and was restored to the session.");
					}
				}
				else
				{
					try
					{
						modifications.addAll(rebuildSessionCart(parameter));
					}
					catch (final CommerceCartModificationException e)
					{
						throw new CommerceCartRestorationException(e.getMessage(), e);
					}
				}
				getCommerceCommonI18NService().setCurrentCurrency(cartModel.getCurrency());
				getCommerceCartCalculationStrategy().calculateCart(parameter);
			}
			else
			{
				LOG.warn(String.format("Current Site %s does not equal to cart %s Site %s", getBaseSiteService().getCurrentBaseSite(),
						cartModel, cartModel.getSite()));
			}
		}
		restoration.setModifications(modifications);
		return restoration;
	}

}
