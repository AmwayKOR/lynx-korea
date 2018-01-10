package com.amway.amwaydms.blocksprivileges.services.impl;

import com.amway.amwaydms.client.DmsClient;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.BlockPrivilegeResponse;
import com.hybris.charon.Charon;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/**
 * Service for to get the block prev details.
 *
 * port to amwaydms2
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultGetBlockPrevService")
public class DefaultBlocksPrivilegesService
        extends AbstractDmsService<BlockPrevResponseDataList, BlockPrevRequestData, BlockPrivilegeResponse>
        implements DmsService<BlockPrevRequestData, BlockPrevResponseDataList>
{
    private static final Logger LOG = Logger.getLogger(DefaultBlocksPrivilegesService.class);

    @Override
    protected BlockPrivilegeResponse executeEvent(final Object input)
    {
        BlockPrevRequestData request = (BlockPrevRequestData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildProxyClientConfig(request)).url(getUrlPath()).build();

        final BlockPrivilegeResponse blockPrivileges = client.getBlocksPrivileges(request.getSalesPlanAff(),request.getAboNum());
        Assert.notNull(blockPrivileges, "Process Block Prev Service failure");
        return blockPrivileges;
    }

    @Override
    protected BlockPrevResponseDataList createResultObject()
    {
        return new BlockPrevResponseDataList();
    }

    @Override
    protected BlockPrevResponseDataList createDefaultResult()
    {
        final BlockPrevResponseDataList getBalanceResult = createResultObject();
        getBalanceResult.setReturnCd(-1);
        getBalanceResult.setReturnMessage("Failed to get block prev details");
        return getBalanceResult;
    }


}

