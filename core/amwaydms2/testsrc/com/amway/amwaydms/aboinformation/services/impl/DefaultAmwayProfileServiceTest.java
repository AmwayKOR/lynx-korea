package com.amway.amwaydms.aboinformation.services.impl;

import com.amway.amwaydms.aboinformation.services.impl.mock.MockAmwayProfileService;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


import com.amway.core.dms.data.AmwayProfileResponseData;


import junit.framework.Assert;

import com.amway.core.data.AmwayProfileRequestData;

/**
 * @author admin
 */
public class DefaultAmwayProfileServiceTest extends ServicelayerTest {

    @Resource(name = "mockAmwayProfileService")
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
    public void shouldGetAmwayProfileServiceTest()
    {
        final AmwayProfileResponseData response = defaultAmwayProfileService.process(requestData);
        Assert.assertTrue(response.getReturnCd() == 1);
    }



}