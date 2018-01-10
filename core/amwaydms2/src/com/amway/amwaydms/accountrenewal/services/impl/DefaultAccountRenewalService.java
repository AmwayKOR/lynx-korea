package com.amway.amwaydms.accountrenewal.services.impl;

import com.amway.amwaydms.client.DmsClient;
import com.amway.amwaydms.model.AccountResponse;
import com.amway.core.annotations.AmwayBean;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.renewal.data.AccountRenewalRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.hybris.charon.Charon;
import org.apache.log4j.Logger;

/**
 *
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultAccountRenewalService")
public class DefaultAccountRenewalService
        extends AbstractDmsService<AmwayProfileResponseData, AccountRenewalRequestData, AccountResponse >
        implements DmsService<AccountRenewalRequestData, AmwayProfileResponseData> {

    private static final Logger LOG = Logger.getLogger(DefaultAccountRenewalService.class);

    @Override
    protected AccountResponse executeEvent(final Object input) {

        AccountRenewalRequestData request = (AccountRenewalRequestData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildCronClientConfig(request)).url(getUrlPath()).build();

        final AccountResponse response = client.renew(request.getSalesPlanAff(),
                request.getAboNum(), request.getRenewalDate(), request.getRenewalCd(),
                request.getLegalConsentFlg(), request.getInvoiceNumber(), request.getRenewalWithGroupFlg());
        return response;

    }

    @Override
    protected AmwayProfileResponseData createResultObject() {
        return new AmwayProfileResponseData();
    }

    @Override
    protected AmwayProfileResponseData createDefaultResult() {
        final AmwayProfileResponseData commonResponseFieldsData = createResultObject();
        commonResponseFieldsData.setReturnMessage("Failed to renew the account");
        commonResponseFieldsData.setReturnCd(-1);

        return commonResponseFieldsData;
    }

}