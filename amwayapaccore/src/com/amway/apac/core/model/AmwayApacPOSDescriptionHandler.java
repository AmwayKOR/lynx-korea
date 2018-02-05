package com.amway.apac.core.model;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.util.localization.Localization;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;


/**
 * The Class POS Description Handler.
 *
 * @author Ashish Sabal
 */
public class AmwayApacPOSDescriptionHandler implements DynamicAttributeHandler<String, AmwayPOSRestrictionModel>
{

	/** The Constant POS_RESTRICTION. */
	private static final String POS_RESTRICTION = "POS Restriction Model";

	/**
	 * Returns string list of all shops with POS available.
	 *
	 * @param model
	 *           the model
	 * @return the string
	 *
	 * @throws IllegalArgumentException
	 *            if model is null.
	 */
	@Override
	public String get(final AmwayPOSRestrictionModel model)
	{
		validateParameterNotNullStandardMessage(POS_RESTRICTION, model);

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

	/**
	 * Setter method for message string in POS restriction model.
	 *
	 * @param model
	 *           the model
	 * @param value
	 *           the value
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void set(final AmwayPOSRestrictionModel model, final String value)
	{
		throw new UnsupportedOperationException();
	}
}
