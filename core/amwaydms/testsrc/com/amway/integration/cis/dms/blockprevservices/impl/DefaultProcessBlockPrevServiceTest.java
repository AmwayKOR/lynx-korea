/**
 *
 */
package com.amway.integration.cis.dms.blockprevservices.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.integration.cis.dms.blockprevservices.mock.impl.MockProcessBlockPrevService;


@UnitTest
public class DefaultProcessBlockPrevServiceTest extends ServicelayerTest
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";

	@Resource(name = "mockProcessBlockPrevService")
	private MockProcessBlockPrevService defaultProcessBlockPrevService;

	private BlockPrevRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new BlockPrevRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFLI_NO);
		requestData.setBlockPrivTypeId("BlockAmwayCredit");
		requestData.setEffectiveDate("2016-01-23T06:38:30-02:00");
		requestData.setExpirationDate("2020-01-23T06:38:30-02:00");
	}

	@Test
	public void shouldProcessBlockPrev()
	{
		final BlockPrevResponseDataList response = defaultProcessBlockPrevService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
