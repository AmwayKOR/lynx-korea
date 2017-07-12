package com.amway.integration.cis.los.order.service;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.http.client.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.los.data.BonusStatementRequestData;
import com.amway.core.los.data.BonusStatementResultData;
import com.amway.integration.cis.los.bonus.statement.mock.service.MockLosBonusStatementService;


@UnitTest
public class DefaultLosBonusStatementServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String YYYY_MM = "yyyyMM";

	@Resource(name = "mockLosBonusStatementService")
	private MockLosBonusStatementService defaultLosBonusStatementService;

	private BonusStatementRequestData resultData;

	@Before
	public void setUp()
	{
		final Date currentDate = Calendar.getInstance().getTime();
		resultData = new BonusStatementRequestData();
		resultData.setBonusPeriod(DateUtils.formatDate(currentDate, YYYY_MM));
		resultData.setRequestingAbo(ABO_NO);
	}

	@Test
	public void shouldGetLosBonusStatementTest()
	{
		final BonusStatementResultData responce = defaultLosBonusStatementService.process(resultData);
		Assert.assertTrue(responce.getReturnCode().intValue() == 1);
	}

}
