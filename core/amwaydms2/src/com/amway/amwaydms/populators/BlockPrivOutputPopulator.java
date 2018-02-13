package com.amway.amwaydms.populators;

import com.amway.amwaydms.model.BlockPrivilege;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.dms.data.BlockPrevResponseData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;
import de.hybris.platform.converters.Populator;
import com.amway.amwaydms.model.BlockPrivilegeResponse;

/**
 * Created by aiueq92 on 10/10/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class BlockPrivOutputPopulator  extends AbstractDmsPopulator
        implements Populator<BlockPrivilegeResponse, BlockPrevResponseDataList>
{
    @Override
    public void populate(final BlockPrivilegeResponse source, final BlockPrevResponseDataList target)
            throws ConversionException
    {
        final List<BlockPrivilege> blockPrevDetailsList = source.getBlockPrivilegeList();
        final List<BlockPrevResponseData> blockPrevResponseList = new ArrayList<>();
        for (final BlockPrivilege blockPrevDetails : blockPrevDetailsList)
        {
            final BlockPrevResponseData blockPrevResponseData = new BlockPrevResponseData();
            blockPrevResponseData.setBlockPrivTypeId(blockPrevDetails.getBlockPrivTypeId());
            blockPrevResponseData.setEffectiveDate(blockPrevDetails.getEffectiveDate());
            blockPrevResponseData.setExpirationDate(blockPrevDetails.getExpirationDate());
            blockPrevResponseData.setIsBlockFlag(blockPrevDetails.getBlockFlag().toString());
            blockPrevResponseList.add(blockPrevResponseData);
        }
        target.setBlockPrevResponseData(blockPrevResponseList);

        if (source.getErrorMessage() == null) {
            target.setReturnCd(1);
        } else {
            target.setReturnCd(source.getErrorMessage().getCode());
            target.setReturnMessage(source.getErrorMessage().getMessage());
        }
    }

}
