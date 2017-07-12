package com.amway.core.jalo.promotions;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.amway.core.jalo.AmwayAccount;
import com.amway.core.jalo.BonusPercentageRange;


/**
 * Restriction for Amway Bonus Percentage Promotion
 */
public class AmwayBonusPercentagePromotionRestriction extends GeneratedAmwayBonusPercentagePromotionRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(AmwayBonusPercentagePromotionRestriction.class.getName());

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
	 * Evaluate the bonus percentage
	 *
	 * @param ctx
	 * @param arg1
	 * @param arg2
	 * @param order
	 */
	@SuppressWarnings("deprecation")
	@Override
	public RestrictionResult evaluate(final SessionContext ctx, final Collection<Product> arg1, final Date arg2,
			final AbstractOrder order)
	{
		final BonusPercentageRange bonusPercentageRange = getBonusPercentageRange(ctx);
		final AmwayAccount account = ctx.getAttribute("account");
		try
		{
			if (null != account && null != account.getLevel(ctx).getAttribute(ctx, "bonuspercentage"))
			{
				final Double bonusPercentage = (Double) account.getLevel().getAttribute(ctx, "bonuspercentage");
				if (null != bonusPercentageRange && null != bonusPercentage)
				{
					final String rangeFrom = bonusPercentageRange.getFrom();
					final String rangeTo = bonusPercentageRange.getTo();
					if (bonusPercentage.doubleValue() >= Double.parseDouble(rangeFrom) && bonusPercentage.doubleValue() <= Double
							.parseDouble(rangeTo))
					{
						return AbstractPromotionRestriction.RestrictionResult.ALLOW;
					}
				}
			}
		}
		catch (JaloInvalidParameterException | NumberFormatException | JaloSecurityException e)
		{
			LOG.error(e.getMessage(), e);
		}
		return AbstractPromotionRestriction.RestrictionResult.DENY;
	}
}
