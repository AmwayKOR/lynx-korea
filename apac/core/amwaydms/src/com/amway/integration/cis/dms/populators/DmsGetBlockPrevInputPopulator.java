package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.integration.dms.services.GetBlockPrivDetInput;


/**
 * Populator implementation for {@link BlockPrevRequestData} as source and {@link GetBlockPrivDetInput} as target type.
 */
public class DmsGetBlockPrevInputPopulator extends AbstractDmsPopulator
		implements Populator<BlockPrevRequestData, GetBlockPrivDetInput>
{
	@Override
	public void populate(final BlockPrevRequestData source, final GetBlockPrivDetInput target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setBlockPrivTypeId(source.getBlockPrivTypeId());
		target.setEffectiveDate(formatDate(source.getEffectiveDate(), "ddMMyyyy", DMSDATEPATTERN));
		target.setIsBlockFlag(source.getIsBlockFlag());
	}

}
