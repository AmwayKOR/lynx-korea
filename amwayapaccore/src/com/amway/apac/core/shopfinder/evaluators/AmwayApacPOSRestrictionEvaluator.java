package com.amway.apac.core.shopfinder.evaluators;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import com.amway.apac.core.model.AmwayPOSRestrictionModel;


/**
 * Class to evaluate POS Restriction.
 *
 * @author Ashish Sabal
 */
public class AmwayApacPOSRestrictionEvaluator implements CMSRestrictionEvaluator<AmwayPOSRestrictionModel>
{
	/** The Constant POS. */
	private static final String POS = "pos";

	/**
	 * Checks if current shop in context exist in restriction model shop list.
	 *
	 * @param model
	 *           the model
	 * @param context
	 *           the context
	 * @return true, if successful
	 */
	@Override
	public boolean evaluate(final AmwayPOSRestrictionModel model, final RestrictionData context)
	{
		boolean allowed = false;

		final Object pos = context.getValue(POS);
		final PointOfServiceModel currentShop = (pos instanceof PointOfServiceModel) ? (PointOfServiceModel) pos : null;
		if (null != currentShop)
		{
			allowed = model.getShops().stream().anyMatch(shop -> shop.getName().equalsIgnoreCase(currentShop.getName()));
		}

		return allowed;
	}
}
