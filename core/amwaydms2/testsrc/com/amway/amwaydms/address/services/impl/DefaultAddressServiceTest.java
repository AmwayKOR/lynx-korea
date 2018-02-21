package com.amway.amwaydms.address.services.impl;

import com.amway.core.b2b.services.AddressProcessDecision;
import com.amway.core.dms.data.AddressResultData;
import com.amway.dms.data.AddressInformationRequestData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.amwaydms.address.services.impl.mock.MockAddressService;

import javax.annotation.Resource;

/**
 * Created by aiueq92 on 9/12/17.
 */
public class DefaultAddressServiceTest extends ServicelayerTest
{
    @Resource(name = "mockAmwayAddressService")
    private MockAddressService defaultAddressService;

    private AddressInformationRequestData requestData;

    @Before
    public void setUp() throws ImpExException
    {
        importCsv("/amwaycore/test/common.csv", "windows-1252");
        requestData = new AddressInformationRequestData();
        requestData.setAboNum("3109215040");
        requestData.setPartyId("171956");
        requestData.setContactPointPurposeCd("HomeAddress");
        requestData.setPrimaryFlag("Y");
    }

    @Test
    public void shouldGetAddressInformation()
    {
        final AddressResultData<AddressProcessDecision> response = defaultAddressService.process(requestData);
        Assert.assertTrue(response.getDecision().equals(AddressProcessDecision.ACCEPT));
    }

}