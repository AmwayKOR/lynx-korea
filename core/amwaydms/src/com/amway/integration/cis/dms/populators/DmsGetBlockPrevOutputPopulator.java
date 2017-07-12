/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.BlockPrevResponseData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.integration.dms.services.BlockPrivDetSrvcOutput;
import com.amway.integration.dms.services.BlockPrivilegeServiceOutput;


/**
 * Populator implementation for {@link BlockPrivilegeServiceOutput} as source and {@link BlockPrevResponseDataList} as
 * target type.
 */
public class DmsGetBlockPrevOutputPopulator extends AbstractDmsPopulator
		implements Populator<BlockPrivilegeServiceOutput, BlockPrevResponseDataList>
{
	@Override
	public void populate(final BlockPrivilegeServiceOutput source, final BlockPrevResponseDataList target)
			throws ConversionException
	{
		final List<BlockPrivDetSrvcOutput> blockPrevDetailsList = source.getObject();
		final List<BlockPrevResponseData> blockPrevResponseList = new ArrayList<>();
		for (final BlockPrivDetSrvcOutput blockPrevDetails : blockPrevDetailsList)
		{
			final BlockPrevResponseData blockPrevResponseData = new BlockPrevResponseData();
			blockPrevResponseData.setAboNum(blockPrevDetails.getAboNum());
			blockPrevResponseData.setBlockPrivTypeId(blockPrevDetails.getBlockPrivTypeId());
			blockPrevResponseData.setEffectiveDate(blockPrevDetails.getEffectiveDate());
			blockPrevResponseData.setExpirationDate(blockPrevDetails.getExpirationDate());
			blockPrevResponseData.setIsBlockFlag(blockPrevDetails.getIsBlockFlag());
			blockPrevResponseData.setSalesPlanAff(blockPrevDetails.getSalesPlanAff());
			blockPrevResponseList.add(blockPrevResponseData);
		}
		target.setBlockPrevResponseData(blockPrevResponseList);
	}

}
