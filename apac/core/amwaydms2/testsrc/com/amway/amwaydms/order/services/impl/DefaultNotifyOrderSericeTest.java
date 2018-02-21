package com.amway.amwaydms.order.services.impl;

import com.amway.amwaydms.common.mock.CommonMockService;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.OrderRequestData;

import de.hybris.platform.servicelayer.ServicelayerTest;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.amwaydms.model.OrderRequest.ActionTypeEnum;
import com.amway.amwaydms.model.OrderRequest.OrderTypeEnum;

import javax.annotation.Resource;

/**
 * Created by aiueq92 on 10/10/17.
 */
public class DefaultNotifyOrderSericeTest extends ServicelayerTest
{

    @Resource(name = "mockAmwayNotifyOrderService")
    private CommonMockService mockNotifyOrderService;

    private OrderRequestData requestData;

    @Before
    public void setUp()
    {
        requestData = new OrderRequestData();
        requestData.setActionType(ActionTypeEnum.ORDERCREATED.toString());
        requestData.setOrderType(OrderTypeEnum.FIRSTORDER.toString());
        requestData.setInvoiceNumber("123123123");
    }

    @Test
    public void shouldNotifyOrder()
    {
        final CommonResponseFieldsData response = mockNotifyOrderService.process(requestData);
        Assert.assertTrue(response.getReturnCd() == 1);
    }

}
