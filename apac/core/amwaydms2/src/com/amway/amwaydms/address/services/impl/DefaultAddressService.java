package com.amway.amwaydms.address.services.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.commercefacades.user.data.AddressData;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.dms.data.AddressResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.dms.data.AddressInformationRequestData;
import com.amway.amwaydms.services.impl.AbstractDmsService;
import com.amway.amwaydms.model.AddressResponse;

import com.amway.amwaydms.client.DmsClient;
import com.hybris.charon.Charon;

/**
 *
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/dms2+DefaultAddressInformationService")
public class DefaultAddressService extends
        AbstractDmsService<AddressResultData<AddressProcessDecision>, AddressInformationRequestData, AddressResponse>
        implements DmsService<AddressInformationRequestData, AddressResultData<AddressProcessDecision>>
{
    private static final Logger LOG = Logger.getLogger(DefaultAddressService.class);

    @Override
    protected AddressResponse executeEvent(final Object input)
    {
        AddressInformationRequestData request = (AddressInformationRequestData) input;
        DmsClient client = Charon.from(DmsClient.class).config(this.buildProxyClientConfig(request)).url(getUrlPath()).build();

        final AddressResponse response = client.getAddresses(request.getSalesPlanAff(),
                request.getAboNum(),request.getPartyId());
        Assert.notNull(response, "Failed to get addresses from Magic");
        return response;
    }

    @Override
    protected AddressResultData<AddressProcessDecision> createDefaultResult()
    {
        final AddressResultData<AddressProcessDecision> addressInformationResult = createResultObject();
        addressInformationResult.setDecision(AddressProcessDecision.UNKNOWN);
        addressInformationResult.setAddressList(new ArrayList<AddressData>());
        addressInformationResult.setReturnCd(-1);
        addressInformationResult.setReturnMessage("Failed to get address data");

        return addressInformationResult;
    }

    @Override
    protected AddressResultData<AddressProcessDecision> createResultObject()
    {
        return new AddressResultData<AddressProcessDecision>();
    }

}

