/**
 *
 */
package com.amway.apac.core.model;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.util.localization.Localization;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;


/**
 * @author Ashish Sabal
 *
 */
public class AmwayApacPOSDescriptionHandler implements DynamicAttributeHandler<String, AmwayPOSRestrictionModel>
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model.
	 * AbstractItemModel)
	 */
	@Override
	public String get(final AmwayPOSRestrictionModel model)
	{
		final Collection<PointOfServiceModel> shops = model.getShops();
		final StringBuilder result = new StringBuilder();
		if (CollectionUtils.isNotEmpty(shops))
		{
			final String localizedString = Localization.getLocalizedString("type.AmwayPOSRestriction.description.text");
			result.append((localizedString == null) ? " The cms content only applies on shops:" : localizedString);
			for (final PointOfServiceModel shop : shops)
			{
				result.append(" ").append(shop.getName());
			}
		}
		return result.toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.
	 * AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(final AmwayPOSRestrictionModel model, final String value)
	{
		throw new UnsupportedOperationException();
	}
}
