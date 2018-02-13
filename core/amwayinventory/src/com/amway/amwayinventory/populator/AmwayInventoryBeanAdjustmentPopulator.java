package com.amway.amwayinventory.populator;

import de.hybris.platform.converters.Populator;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryDeltaAdjustmentData;


/**
 * Populates data from {@link AmwayInventoryDeltaAdjustmentData} into {@link AmwayInventoryBean} before stock processing.
 */
public class AmwayInventoryBeanAdjustmentPopulator implements Populator<AmwayInventoryDeltaAdjustmentData, AmwayInventoryBean>
{
	@Override
	public void populate(AmwayInventoryDeltaAdjustmentData source, AmwayInventoryBean target)
	{
		target.setAvailable(source.getAdjustment());
		target.setBaseItemNumber(source.getBaseItemNumber());
		target.setItemNumber(source.getItemNumber());
	}
}
