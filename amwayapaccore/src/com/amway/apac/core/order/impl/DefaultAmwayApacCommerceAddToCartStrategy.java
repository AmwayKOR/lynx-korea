package com.amway.apac.core.order.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceAddToCartStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;


/**
 * Overriding {@link DefaultCommerceAddToCartStrategy} to calculate cart AFTER merging the entries.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCommerceAddToCartStrategy extends DefaultCommerceAddToCartStrategy
{

	/**
	 * Overriding the method to calculate cart AFTER merging the entries.
	 *
	 * @param parameter
	 *           Cart parameters
	 * @return Cart modification information
	 * @throws de.hybris.platform.commerceservices.order.CommerceCartModificationException
	 *
	 */
	@Override
	public CommerceCartModification addToCart(final CommerceCartParameter parameter) throws CommerceCartModificationException
	{
		final CommerceCartModification modification = doAddToCart(parameter);
		afterAddToCart(parameter, modification);
		// Here the entry is fully populated, so we can search for a similar one and merge.
		mergeEntry(modification, parameter);
		getCommerceCartCalculationStrategy().calculateCart(parameter);
		return modification;
	}
}
