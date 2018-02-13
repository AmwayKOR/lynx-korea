package com.amway.amwaydms.balance.services.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.amwaydms.balance.services.impl.mock.MockGetAccountBalanceService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;


public class DefaultGetBalanceAccountServiceTest extends ServicelayerTest
{

	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";
	private static final String BR = "BR";

	@Resource(name = "mockGetBalanceAccountService")
	private MockGetAccountBalanceService defaultGetBalanceAccountService;

	private CommonRequestFieldsData fieldsData;

	@Before
	public void setUp()
	{
		fieldsData = new CommonRequestFieldsData();
		fieldsData.setSalesPlanAff(AFFLI_NO);
		fieldsData.setAboNum(ABO_NO);
		fieldsData.setClientCntryCd(BR);
	}

	@Test
	public void shouldGetBalanceAccount()
	{
		final GetBalanceResponseData response = defaultGetBalanceAccountService.process(fieldsData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
