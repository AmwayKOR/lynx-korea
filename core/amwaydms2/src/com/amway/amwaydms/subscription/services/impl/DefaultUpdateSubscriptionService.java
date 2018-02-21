package com.amway.amwaydms.subscription.services.impl;

import com.amway.amwaydms.client.DmsClient;
import com.amway.amwaydms.model.CommonResponse;
import com.amway.amwaydms.model.SubscriptionRequest;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.SubscriptionOptionRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.hybris.charon.Charon;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 *
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultUpdateSubscriptionService")
public class DefaultUpdateSubscriptionService
        extends AbstractDmsService<CommonResponseFieldsData, SubscriptionOptionRequestData, CommonResponse>
        implements DmsService<SubscriptionOptionRequestData, CommonResponseFieldsData>
{
    private static Logger LOG = Logger.getLogger(DefaultUpdateSubscriptionService.class);

    @Override
    protected CommonResponse executeEvent(final Object input)
    {
        SubscriptionOptionRequestData request = (SubscriptionOptionRequestData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildCronClientConfig(request)).url(getUrlPath()).build();

        final CommonResponse response = client.updateSubscriptions(request.getSalesPlanAff(),request.getAboNum(),request.getPartyId(),
                (SubscriptionRequest) getInputConverter().convert(request));
        Assert.notNull(response, "add subscription");
        return response;
    }


    @Override
    protected CommonResponseFieldsData createDefaultResult()
    {
        final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
        commonResponseFieldsData.setReturnMessage("Invalid fields entry add subscription failed");
        commonResponseFieldsData.setReturnCd(-1);

        return commonResponseFieldsData;
    }


    @Override
    protected CommonResponseFieldsData createResultObject()
    {
        return new CommonResponseFieldsData();
    }


}
