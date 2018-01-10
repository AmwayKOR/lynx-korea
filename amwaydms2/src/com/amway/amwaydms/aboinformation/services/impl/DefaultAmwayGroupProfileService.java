package com.amway.amwaydms.aboinformation.services.impl;

import com.amway.amwaydms.client.DmsClient;
import com.amway.amwaydms.model.AccountResponse;
import com.amway.core.annotations.AmwayBean;
import com.hybris.charon.Charon;
import org.apache.log4j.Logger;

import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.amwaydms.services.impl.AbstractDmsService;


/**
 *
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultAmwayGroupProfileService")
public class DefaultAmwayGroupProfileService
        extends AbstractDmsService<AmwayProfileResponseData, AmwayProfileRequestData, AccountResponse  >
        implements DmsService<AmwayProfileRequestData, AmwayProfileResponseData>
{
    private static final Logger LOG = Logger.getLogger(DefaultAmwayGroupProfileService.class);

    @Override
    protected AccountResponse executeEvent(final Object input)
    {

        AmwayProfileRequestData request = (AmwayProfileRequestData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildProxyClientConfig(request)).url(getUrlPath()).build();

        AccountResponse response = client.getAmwayGroupProfile(request.getSalesPlanAff(),
                request.getAboNum(),request.getAccntBalType());

        return response;

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
        amwayProfileResponseData.setReturnMessage("Failed to get amway group profile information");
        return amwayProfileResponseData;
    }
}
