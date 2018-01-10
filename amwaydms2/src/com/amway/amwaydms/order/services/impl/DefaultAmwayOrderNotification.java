package com.amway.amwaydms.order.services.impl;

import com.amway.amwaydms.client.DmsClient;
import com.amway.amwaydms.model.OrderRequest;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.OrderRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.AccountBalanceRequest;
import com.amway.amwaydms.model.CommonResponse;
import com.hybris.charon.Charon;
import org.springframework.util.Assert;

import com.amway.core.dms.data.CreateBalanceRequestData;

/**
 *
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultAmwayOrderNotification")
public class DefaultAmwayOrderNotification
        extends AbstractDmsService<CommonResponseFieldsData, OrderRequestData, CommonResponse>
        implements DmsService<OrderRequestData, CommonResponseFieldsData>
{

    @Override
    protected CommonResponse executeEvent(final Object input)
    {
        OrderRequestData request = (OrderRequestData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildCronClientConfig(request)).url(getUrlPath()).build();

        final CommonResponse response = client.notfiyOrder(request.getSalesPlanAff(),
                request.getAboNum(),request.getPartyId(),(OrderRequest) getInputConverter().convert(request));
        Assert.notNull(response, "Notify Order");
        return response;

    }

    @Override
    protected CommonResponseFieldsData createDefaultResult()
    {
        final CommonResponseFieldsData createBalanceResult = createResultObject();
        createBalanceResult.setReturnCd(-1);
        createBalanceResult.setReturnMessage("Failed to complete notify order");
        return createBalanceResult;
    }

    @Override
    protected CommonResponseFieldsData createResultObject()
    {
        return new CommonResponseFieldsData();
    }
}
