package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.OrderHistoryPopulator;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.core.model.order.OrderModel;

import com.amway.apac.core.constants.AmwayapacCoreConstants;


/**
 * Overriding OOTB implementation to populate APAC specific fields.
 *
 * @author Aaron Yong
 */
public class AmwayApacOrderHistoryPopulator extends OrderHistoryPopulator
{

	/**
	 * Overriding OOTB implementation to populate APAC specific fields.
	 */
	@Override
	public void populate(final OrderModel source, final OrderHistoryData target)
	{
		super.populate(source, target);
		target.setItemQuantity(
				Integer.valueOf(source.getEntries() == null ? AmwayapacCoreConstants.ZERO_INT : source.getEntries().size()));
	}

}
