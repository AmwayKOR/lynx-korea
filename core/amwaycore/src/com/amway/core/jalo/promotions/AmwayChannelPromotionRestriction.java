package com.amway.core.jalo.promotions;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * Restriction for Amway Channel Promotion
 */
public class AmwayChannelPromotionRestriction extends GeneratedAmwayChannelPromotionRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(AmwayChannelPromotionRestriction.class.getName());

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
	@SuppressWarnings("deprecation")
	@Override
	public RestrictionResult evaluate(final SessionContext context, final Collection<Product> arg1, final Date arg2,
			final AbstractOrder order)
	{
		if (order != null)
		{
			Object sessionChannel = null;
			if (null != JaloSession.getCurrentSession())
			{
				sessionChannel = JaloSession.getCurrentSession().getAttribute("currentChannel");
			}
			final Collection<EnumerationValue> restrictedChannels = getChannel(context);
			if (restrictedChannels != null && sessionChannel != null)
			{
				final ArrayList<String> restChannels = new ArrayList<String>();
				final String schannel = sessionChannel.toString();
				for (final EnumerationValue eval : restrictedChannels)
				{
					restChannels.add(eval.getCode());
				}

				if (restChannels.contains(schannel))
				{
					return AbstractPromotionRestriction.RestrictionResult.ALLOW;
				}
			}

		}
		return AbstractPromotionRestriction.RestrictionResult.DENY;
	}
}
