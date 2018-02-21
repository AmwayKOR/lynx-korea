package com.amway.integration.cis.los.account.detail.service;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.http.client.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.integration.cis.los.account.detail.service.mock.impl.MockLosAccountDetailService;


@UnitTest
public class DefaultLosAccountDetailServiceTest extends ServicelayerTransactionalTest
{
	private static final String AFFlI_NO = "170";
	private static final String BONUS_PERIOD_COUNT = "13";
	private static final String ABO_NO = "7001002605";
	private static final String YYYY_MM = "yyyyMM";
	private static final String HYPHEN = "-";

	@Resource(name = "mockLosAccountDetailService")
	private MockLosAccountDetailService defaultLosAccountDetailService;

	private LosAccountRequestData losAccountRequestData;


	@Before
	public void setUp()
	{
		final Date currentDate = Calendar.getInstance().getTime();
		losAccountRequestData = new LosAccountRequestData();
		losAccountRequestData.setAbo(new StringBuffer(AFFlI_NO).append(HYPHEN).append(ABO_NO).toString());
		losAccountRequestData.setBonusPeriod(DateUtils.formatDate(currentDate, YYYY_MM));
		losAccountRequestData.setBonusPeriodCount(BONUS_PERIOD_COUNT);
		losAccountRequestData.setGetVolume(Boolean.TRUE);
		losAccountRequestData.setGetExtAttributes(Boolean.TRUE);
		losAccountRequestData.setGetSponsorStats(Boolean.TRUE);
		losAccountRequestData.setRequestingAbo(new StringBuffer(AFFlI_NO).append(HYPHEN).append(ABO_NO).toString());
	}

	@Test
	public void shouldGetLosAccountDetailsTest()
	{
		final LosAccountDetailResponseData responce = defaultLosAccountDetailService.process(losAccountRequestData);
		Assert.assertEquals(responce.getReturnCode(), 1);
	}
}
