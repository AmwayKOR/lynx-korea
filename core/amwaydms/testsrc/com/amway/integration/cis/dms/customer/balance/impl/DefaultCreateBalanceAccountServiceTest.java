/**
 *
 */
package com.amway.integration.cis.dms.customer.balance.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.CreateBalanceRequestData;
import com.amway.integration.cis.dms.customer.balance.mock.impl.MockCreateAccountBalanceService;


public class DefaultCreateBalanceAccountServiceTest extends ServicelayerTest
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";

	@Resource(name = "mockCreateBalanceAccountService")
	private MockCreateAccountBalanceService defaultCreateBalanceAccountService;
	private CreateBalanceRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new CreateBalanceRequestData();

		requestData.setBalanceAmount("-816.650");
		requestData.setBalanceTypeCd("Credit");
		requestData.setCurrencyCd("BRL");
		requestData.setTxSourceCd("HB");
		requestData.setSourcRefNum("700337282");
		requestData.setTxTypeCd("Ord");
		requestData.setSalesPlanAff(AFFLI_NO);
		requestData.setAboNum(ABO_NO);
	}

	@Test
	public void shouldCreateBalanceAccount()
	{
		final CommonResponseFieldsData response = defaultCreateBalanceAccountService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
