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

import com.amway.core.jalo.AmwayAccount;


/**
 * Restriction for Amway Segmentation Level Promotion
 */
public class AmwaySegmentaionLevelPromotionRestriction extends GeneratedAmwaySegmentaionLevelPromotionRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(AmwaySegmentaionLevelPromotionRestriction.class.getName());

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
	 * @param products
	 * @param date
	 * @param order
	 */
	@SuppressWarnings("deprecation")
	@Override
	public RestrictionResult evaluate(final SessionContext context, final Collection<Product> products, final Date date,
			final AbstractOrder order)
	{

		final EnumerationValue restSegmentationLevel = getSegmentationLevel();

		final AmwayAccount account = context.getAttribute("account");
		try
		{
			if (account != null && null != account.getLevel(context).getAttribute(context, "segmentaionlevel"))
			{
				final EnumerationValue segmentationLevel = (EnumerationValue) account.getLevel(context)
						.getAttribute(context, "segmentaionlevel");

				if (null != segmentationLevel && segmentationLevel.equals(restSegmentationLevel))
				{
					return AbstractPromotionRestriction.RestrictionResult.ALLOW;
				}
			}
		}
		catch (JaloInvalidParameterException | JaloSecurityException e)
		{
			LOG.error(e.getMessage(), e);
		}

		return AbstractPromotionRestriction.RestrictionResult.DENY;

	}
}
