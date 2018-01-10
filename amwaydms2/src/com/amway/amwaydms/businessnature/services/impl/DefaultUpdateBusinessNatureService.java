package com.amway.amwaydms.businessnature.services.impl;

import com.amway.amwaydms.client.DmsClient;
import com.amway.amwaydms.model.BusinessNatureChangeRequest;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;
import com.amway.dms.data.UpdateBusinessNatureInputRequestData;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.CommonResponse;
import com.hybris.charon.Charon;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 *
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultUpdateBusinessNatureService")
public class DefaultUpdateBusinessNatureService
        extends AbstractDmsService<CommonResponseFieldsData, UpdateBusinessNatureInputRequestData, CommonResponse>
        implements DmsService<UpdateBusinessNatureInputRequestData, CommonResponseFieldsData>
{
    private static Logger LOG = Logger.getLogger(DefaultUpdateBusinessNatureService.class);

    @Override
    protected CommonResponse executeEvent(final Object input)
    {
        UpdateBusinessNatureInputRequestData request = (UpdateBusinessNatureInputRequestData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildCronClientConfig(request)).url(getUrlPath()).build();

        final CommonResponse response = client.updateBusinessNature(request.getSalesPlanAff(), request.getAboNum(),
                (BusinessNatureChangeRequest) getInputConverter().convert(request));
        Assert.notNull(response, "Update business nature failure");
        return response;
    }

    @Override
    protected CommonResponseFieldsData createDefaultResult()
    {
        final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
        commonResponseFieldsData.setReturnMessage("Invalid feilds entry business nature change");
        commonResponseFieldsData.setReturnCd(-1);

        return commonResponseFieldsData;
    }

    @Override
    protected CommonResponseFieldsData createResultObject()
    {
        return new CommonResponseFieldsData();
    }

}
