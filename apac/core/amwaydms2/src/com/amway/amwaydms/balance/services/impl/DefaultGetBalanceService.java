package com.amway.amwaydms.balance.services.impl;

import com.amway.amwaydms.client.DmsClient;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.CreateBalanceRequestData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.AccountBalanceResponse;
import com.hybris.charon.Charon;
import com.hybris.commons.client.RestResponse;
import org.springframework.util.Assert;

/**
 *
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultGetBalanceAccountService")
public class DefaultGetBalanceService
extends AbstractDmsService<GetBalanceResponseData, CommonRequestFieldsData, AccountBalanceResponse>
        implements DmsService<CommonRequestFieldsData, GetBalanceResponseData>
{

    @Override
    protected AccountBalanceResponse executeEvent(final Object input)
    {
        CommonRequestFieldsData request = (CommonRequestFieldsData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildProxyClientConfig(request)).url(getUrlPath()).build();

        final AccountBalanceResponse accountBalance = client.getAccountBalance(request.getSalesPlanAff(),request.getAboNum());
        Assert.notNull(accountBalance, "Get Account Balance failure");
        return accountBalance;

    }

    @Override
    protected GetBalanceResponseData createDefaultResult()
    {
        final GetBalanceResponseData getBalanceResult = createResultObject();
        getBalanceResult.setReturnCd(-1);
        getBalanceResult.setReturnMessage("Failed to get account Balance information");
        return getBalanceResult;
    }

    @Override
    protected GetBalanceResponseData createResultObject()
    {
        return new GetBalanceResponseData();
    }
}

