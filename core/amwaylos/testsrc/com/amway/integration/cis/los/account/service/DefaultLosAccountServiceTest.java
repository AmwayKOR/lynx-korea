/**
 *
 */
package com.amway.integration.cis.los.account.service;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.http.client.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.los.service.LosService;


public class DefaultLosAccountServiceTest extends ServicelayerTransactionalTest
{
	private LosAccountRequestData losAccountRequestData;

	@Resource(name = "mockLosAccountService")
	private LosService<LosAccountRequestData, LosAccountResponseData> losAccountService;

	private static final String ABO_NO = "3109215040";
	private static final String YYYY_MM = "yyyyMM";
	private static final String DEPTH = "2";
	private static final String HYPHEN = "-";
	private static final String AFFlI_NO = "170";


	@Before
	public void setUp()
	{
		final Date currentDate = Calendar.getInstance().getTime();
		losAccountRequestData = new LosAccountRequestData();
		losAccountRequestData.setAbo(new StringBuffer(AFFlI_NO).append(HYPHEN).append(ABO_NO).toString());
		losAccountRequestData.setBonusPeriod(DateUtils.formatDate(currentDate, YYYY_MM));
		losAccountRequestData.setDepth(DEPTH);
		losAccountRequestData.setRequestingAbo(new StringBuffer(AFFlI_NO).append(HYPHEN).append(ABO_NO).toString());
	}


	@Test
	public void shouldGetLosAccountTest()
	{
		final LosAccountResponseData response = losAccountService.process(losAccountRequestData);
		Assert.assertEquals(1, response.getReturnCode());
	}

}
