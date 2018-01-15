/**
 *
 */
package com.amway.apac.core.shopfinder.evaluators;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import com.amway.apac.core.model.AmwayPOSRestrictionModel;


/**
 * Class to evaluate POS Restriction
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacPOSRestrictionEvaluator implements CMSRestrictionEvaluator<AmwayPOSRestrictionModel>
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator#evaluate(de.hybris.platform.cms2.
	 * model.restrictions.AbstractRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)
	 */
	@Override
	public boolean evaluate(final AmwayPOSRestrictionModel model, final RestrictionData context)
	{
		boolean allowed = false;

		final Object pos = context.getValue("pos");
		final PointOfServiceModel currentShop = (pos instanceof PointOfServiceModel) ? (PointOfServiceModel) pos : null;
		if (null != currentShop)
		{
			allowed = model.getShops().stream().anyMatch(shop -> shop.getName().equalsIgnoreCase(currentShop.getName()));
		}

		return allowed;
	}
}
