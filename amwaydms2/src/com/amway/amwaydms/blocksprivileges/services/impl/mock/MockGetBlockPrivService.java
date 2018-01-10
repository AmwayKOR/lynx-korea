package com.amway.amwaydms.blocksprivileges.services.impl.mock;

import com.amway.amwaydms.model.ErrorMessage;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.BlockPrivilege;
import com.amway.amwaydms.model.BlockPrivilegeResponse;

import java.util.ArrayList;

@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/Mocks")
public class MockGetBlockPrivService
        extends AbstractDmsService<BlockPrevResponseDataList, BlockPrevRequestData, BlockPrivilegeResponse>
        implements DmsService<BlockPrevRequestData, BlockPrevResponseDataList>
{
    private static final String ABO_NO = "3109215040";
    private static final String AFFLI_NO = "170";

    @Override
    public BlockPrevResponseDataList process(final BlockPrevRequestData requestData)
    {
        //final Object input = getInputConverter().convert(requestData);
        return extractOutput(executeEvent(requestData));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
     */
    @Override
    protected BlockPrevResponseDataList createResultObject()
    {

        return new BlockPrevResponseDataList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
     */
    @Override
    protected BlockPrevResponseDataList createDefaultResult()
    {
        final BlockPrevResponseDataList dataList = createResultObject();
        dataList.setReturnCd(-1);
        dataList.setReturnMessage("Faild to get Block Prev List");
        return dataList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
     */
    @Override
    protected BlockPrivilegeResponse executeEvent(final Object input)
    {
        final BlockPrivilegeResponse output = new BlockPrivilegeResponse();
        final BlockPrivilege privDetSrvcOutput = new BlockPrivilege();
        privDetSrvcOutput.setBlockPrivTypeId("BlockAmwayCredit");
        privDetSrvcOutput.setEffectiveDate("2016-01-23T06:38:30-02:00");
        privDetSrvcOutput.setExpirationDate("2020-01-23T06:38:30-02:00");
        privDetSrvcOutput.setBlockFlag(true);
        output.setBlockPrivilegeList(new ArrayList<BlockPrivilege>());
        output.getBlockPrivilegeList().add(privDetSrvcOutput);
        output.setErrorMessage(new ErrorMessage());
        output.getErrorMessage().setCode(1);
        output.getErrorMessage().setMessage("Success");
        return output;
    }

}
