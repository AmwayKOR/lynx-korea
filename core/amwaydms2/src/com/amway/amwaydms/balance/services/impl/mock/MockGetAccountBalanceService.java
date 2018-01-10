package com.amway.amwaydms.balance.services.impl.mock;

import com.amway.amwaydms.model.ErrorMessage;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.dms.service.DmsService;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.AccountBalanceResponse;
import com.amway.amwaydms.model.AccountBalance;

import java.util.ArrayList;


/**
 * Mock Service for to get account balance information.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/Mocks")
public class MockGetAccountBalanceService
        extends AbstractDmsService<GetBalanceResponseData, CommonRequestFieldsData, AccountBalanceResponse>
        implements DmsService<CommonRequestFieldsData, GetBalanceResponseData>
{
    @Override
    public GetBalanceResponseData process(final CommonRequestFieldsData requestData)
    {
        final Object input = getInputConverter().convert(requestData);
        return extractOutput(executeEvent(input));
    }


    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
     */
    @Override
    protected GetBalanceResponseData createResultObject()
    {
        // YTODO Auto-generated method stub
        return new GetBalanceResponseData();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
     */
    @Override
    protected GetBalanceResponseData createDefaultResult()
    {
        final GetBalanceResponseData response = createResultObject();
        response.setReturnCd(-1);
        response.setReturnMessage("Fail to get customer balance");
        return response;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
     */
    @Override
    protected AccountBalanceResponse executeEvent(final Object input)
    {
        final AccountBalanceResponse response = new AccountBalanceResponse();
        final AccountBalance balance = new AccountBalance();
        balance.setBalanceAmount("123");
        balance.setBalanceTypeCd("Monetary");
        balance.setCurrencyCd("USD");
        balance.setInstrumentId("0");
        response.setAccntBalList(new ArrayList<AccountBalance>());
        response.getAccntBalList().add(balance);
        response.setErrorMessage(new ErrorMessage());
        response.getErrorMessage().setCode(1);
        response.getErrorMessage().setMessage("get customer balance successfully");
        return response;
    }

}

