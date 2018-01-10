package com.amway.amwaydms.blocksprivileges.services.impl;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import de.hybris.platform.servicelayer.ServicelayerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import com.amway.amwaydms.blocksprivileges.services.impl.mock.MockGetBlockPrivService;

/**
 * Created by aiueq92 on 10/10/17.
 */
public class DefaultBlockPrivServiceTest extends ServicelayerTest {
    private static final String ABO_NO = "3109215040";
    private static final String AFFLI_NO = "170";

    @Resource(name = "mockGetBlockPrevService")
    private MockGetBlockPrivService defaultGetBlockPrevService;
    private BlockPrevRequestData requestData;

    @Before
    public void setUp() {
        requestData = new BlockPrevRequestData();
        requestData.setAboNum(ABO_NO);
        requestData.setSalesPlanAff(AFFLI_NO);
        requestData.setBlockPrivTypeId("BlockAmwayCredit");
        requestData.setEffectiveDate("2016-01-23T06:38:30-02:00");
        requestData.setExpirationDate("2020-01-23T06:38:30-02:00");
    }

    @Test
    public void shouldGetBlockPrev() {
        final BlockPrevResponseDataList response = defaultGetBlockPrevService.process(requestData);
        Assert.assertTrue(response.getReturnCd() == 1);
    }
}
