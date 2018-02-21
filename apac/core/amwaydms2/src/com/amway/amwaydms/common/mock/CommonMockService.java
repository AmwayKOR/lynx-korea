package com.amway.amwaydms.common.mock;

import com.amway.amwaydms.model.ErrorMessage;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.CommonResponse;

/**
 * Common Mock response for some services.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/Mocks")
public class CommonMockService extends AbstractDmsService<CommonResponseFieldsData, Object, Object>
        implements DmsService<Object, CommonResponseFieldsData>
{

    @Override
    public CommonResponseFieldsData process(final Object requestData)
    {
        return extractOutput(executeEvent(requestData));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
     */
    @Override
    protected CommonResponseFieldsData createResultObject()
    {
        return new CommonResponseFieldsData();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
     */
    @Override
    protected CommonResponseFieldsData createDefaultResult()
    {
        final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
        commonResponseFieldsData.setReturnCd(-1);
        return commonResponseFieldsData;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
     */
    @Override
    protected Object executeEvent(final Object input)
    {
        final CommonResponse response = new CommonResponse();
        response.setErrorMessage(new ErrorMessage());
        response.getErrorMessage().setCode(1);
        response.getErrorMessage().setMessage("success");
        return response;
    }
}