package com.amway.core.jalo.promotions;

import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;
import de.hybris.platform.promotions.util.Helper;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * Restriction for Product Promotion
 */
public class PromotionProductRestriction extends de.hybris.platform.promotions.jalo.PromotionProductRestriction
{
	private static final Logger log = Logger.getLogger(PromotionProductRestriction.class);

	public boolean isPositiveAsPrimitive()
	{
		return isPositiveAsPrimitive(getSession().getSessionContext());
	}

	public boolean isPositiveAsPrimitive(final SessionContext ctx)
	{
		final Boolean value = isPositive(ctx);
		return ((value != null) ? value.booleanValue() : false);
	}

	public Boolean isPositive()
	{
		return isPositive(getSession().getSessionContext());
	}

	public Boolean isPositive(final SessionContext ctx)
	{
		return ((Boolean) getProperty(ctx, "positive"));
	}

	/**
	 * To evaluate restriction
	 *
	 * @param ctx
	 * @param products
	 * @param date
	 * @param order
	 */
	@Override
	public AbstractPromotionRestriction.RestrictionResult evaluate(final SessionContext ctx, final Collection<Product> products,
			final Date date, final AbstractOrder order)
	{
		final Collection restrictedProducts = super.getProducts(ctx);
		final boolean positive = isPositiveAsPrimitive(ctx);
		if ((restrictedProducts != null) && (products != null) && (!(restrictedProducts.isEmpty())) && (!(products.isEmpty())))
		{
			for (final Product testProduct : products)
			{
				if ((testProduct != null) && (isRestrictedProduct(restrictedProducts, testProduct)))
				{
					return ((positive) ?
							AbstractPromotionRestriction.RestrictionResult.ALLOW :
							AbstractPromotionRestriction.RestrictionResult.DENY);
				}
			}
			return ((positive) ?
					AbstractPromotionRestriction.RestrictionResult.DENY :
					AbstractPromotionRestriction.RestrictionResult.ALLOW);
		}

		return AbstractPromotionRestriction.RestrictionResult.DENY;
	}

	protected boolean isRestrictedProduct(final Collection<Product> restrictedProducts, final Product testProduct)
	{
		boolean result = restrictedProducts.contains(testProduct);
		if (!(result))
		{
			final List<Product> baseProducts = Helper.getBaseProducts(getSession().getSessionContext(), testProduct);
			for (final Product baseProduct : baseProducts)
			{
				if (!(restrictedProducts.contains(baseProduct)))
				{
					continue;
				}
				result = true;
				break;
			}
		}
		return result;
	}
}
