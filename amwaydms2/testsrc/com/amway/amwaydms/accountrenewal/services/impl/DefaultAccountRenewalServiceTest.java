package com.amway.amwaydms.accountrenewal.services.impl;

import com.amway.amwaydms.aboinformation.services.impl.mock.MockAmwayProfileService;
import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import de.hybris.platform.servicelayer.ServicelayerTest;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by aiueq92 on 10/4/17.
 */
public class DefaultAccountRenewalServiceTest extends ServicelayerTest {

    @Resource(name = "mockAmwayAccountRenewalService")
    private MockAmwayProfileService defaultAmwayProfileService;

    private final AmwayProfileRequestData requestData = new AmwayProfileRequestData();

    @Before
    public void setup()
    {
        requestData.setAboNum("7000020315");
        requestData.setDeltailLevelCd("100");
        requestData.setSalesPlanAff("170");
    }

    @Test
    public void shouldGetRenewedServiceTest()
    {
        final AmwayProfileResponseData response = defaultAmwayProfileService.process(requestData);
        Assert.assertTrue(response.getReturnCd() == 1);
    }


}
