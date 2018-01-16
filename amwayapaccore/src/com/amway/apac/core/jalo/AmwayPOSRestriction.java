package com.amway.apac.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.storelocator.jalo.PointOfService;
import de.hybris.platform.util.localization.Localization;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;


/**
 * The Class AmwayPOSRestriction.
 */
public class AmwayPOSRestriction extends GeneratedAmwayPOSRestriction
{
	@SuppressWarnings("deprecation")
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

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public String getDescription(final SessionContext paramSessionContext)
	{
		final Collection<PointOfService> shops = getShops();
		final StringBuilder result = new StringBuilder();
		if (CollectionUtils.isNotEmpty(shops))
		{
			final String localizedString = Localization.getLocalizedString("type.AmwayPOSRestriction.description.text");
			result.append((localizedString == null) ? " The cms content only applies on shops:" : localizedString);
			for (final PointOfService shop : shops)
			{
				result.append(" ").append(shop.getName());
			}
		}
		return result.toString();
	}
}
