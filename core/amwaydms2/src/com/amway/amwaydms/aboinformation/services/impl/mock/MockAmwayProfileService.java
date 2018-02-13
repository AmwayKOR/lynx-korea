package com.amway.amwaydms.aboinformation.services.impl.mock;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.*;

import java.util.ArrayList;

/**
 * Created by aiueq92 on 9/9/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/Mocks")
public class MockAmwayProfileService  extends AbstractDmsService<AmwayProfileResponseData, AmwayProfileRequestData, AccountResponse>
        implements DmsService<AmwayProfileRequestData, AmwayProfileResponseData>
    {

        @Override
        public AmwayProfileResponseData process(final AmwayProfileRequestData requestData)
        {
            return extractOutput(executeEvent(requestData));
        }

        @Override
        protected AmwayProfileResponseData createResultObject()
        {
            return new AmwayProfileResponseData();
        }

        @Override
        protected AmwayProfileResponseData createDefaultResult()
        {
            final AmwayProfileResponseData amwayProfileResponseData = createResultObject();
            amwayProfileResponseData.setReturnCd(-1);
            amwayProfileResponseData.setReturnMessage("Failed to get amway profile information");
            return amwayProfileResponseData;
        }



        @Override
        protected AccountResponse executeEvent(final Object input)
        {
            final AccountResponse response = new AccountResponse();
            Account account = new Account();
            account.setAccountBalanceList(new ArrayList());
            account.setAccountMst(new AccountMaster());
            account.setBankAccountDetailList(new ArrayList());
            account.setBlockPrivilegeList(new ArrayList());
            account.setMissingInfoList(new ArrayList());
            account.setPartyList(new ArrayList());
            response.setAccount(account );

            return response;


        }
    }
