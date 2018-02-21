package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.integration.dms.services.AddABOBlockPrivRequest;


/**
 * Populator implementation for {@link BlockPrevRequestData} as source and {@link AddABOBlockPrivRequest} as target
 * type.
 */
public class DmsProcessBlockPrevDetailsInputPopulator extends AbstractDmsPopulator
		implements Populator<BlockPrevRequestData, AddABOBlockPrivRequest>
{

	@Override
	public void populate(final BlockPrevRequestData source, final AddABOBlockPrivRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setBlockPrivTypeId(source.getBlockPrivTypeId());
		target.setEffectiveDate(formatDate(source.getEffectiveDate(), "ddMMyyyy", DMSDATEPATTERN));
		target.setExpirationDate(formatDate(source.getExpirationDate(), "ddMMyyyy", DMSDATEPATTERN));
	}

}
