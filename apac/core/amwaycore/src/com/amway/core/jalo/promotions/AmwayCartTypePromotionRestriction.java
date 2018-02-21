package com.amway.core.jalo.promotions;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * Restriction for Amway Cart Type Promotion
 */
public class AmwayCartTypePromotionRestriction extends GeneratedAmwayCartTypePromotionRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(AmwayCartTypePromotionRestriction.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}



	/**
	 * To evaluate restriction
	 *
	 * @param context
	 * @param arg1
	 * @param arg2
	 * @param order
	 */
	@Override
	public RestrictionResult evaluate(final SessionContext context, final Collection<Product> arg1, final Date arg2,
			final AbstractOrder order)
	{
		EnumerationValue cartType = null;

		if (order != null)
		{
			try
			{
				cartType = (EnumerationValue) order.getAttribute(context, "type");
			}
			catch (JaloInvalidParameterException | JaloSecurityException e)
			{
				LOG.error(e.getMessage(), e);
			}

			final boolean positive = isPositiveAsPrimitive(context);
			if ((cartType != null) && (isInCartTypeCollection(context, cartType)))
			{
				return ((positive) ?
						AbstractPromotionRestriction.RestrictionResult.ALLOW :
						AbstractPromotionRestriction.RestrictionResult.DENY);
			}
			return ((positive) ?
					AbstractPromotionRestriction.RestrictionResult.DENY :
					AbstractPromotionRestriction.RestrictionResult.ALLOW);
		}

		return AbstractPromotionRestriction.RestrictionResult.DENY;
	}

	private boolean isInCartTypeCollection(final SessionContext context, final EnumerationValue cartType)
	{
		final Collection<EnumerationValue> restrictedTypes = getCartTypes(context);

		for (final EnumerationValue act : restrictedTypes)
		{
			if (isInCartTypeCollection(act, cartType))
			{
				return true;
			}
		}

		return false;
	}


	private boolean isInCartTypeCollection(final EnumerationValue act, final EnumerationValue cartType)
	{
		return act.equals(cartType);
	}
}
