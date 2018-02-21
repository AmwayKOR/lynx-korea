package com.amway.integration.cis.dms.bankaccountservice.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.dms.data.BankAccountDetails;
import com.amway.core.dms.data.BankAccountRequestData;
import com.amway.integration.cis.dms.bankaccountservice.mock.impl.MockBankAccountService;


@UnitTest
public class DefaultBankAccountServiceTest extends ServicelayerTest
{

	@Resource(name = "mockBankAccountService")
	private MockBankAccountService defaultBankAccountService;

	private BankAccountRequestData detailRequest;

	@Before
	public void setUp()
	{
		detailRequest = new BankAccountRequestData();
		detailRequest.setAboNum("3109215040");
		detailRequest.setSalesPlanAff("170");
		detailRequest.setPartyId("171956");
		detailRequest.setBankAcctUseCode("BonusPayments");
	}

	@Test
	public void shouldGetBankAccountServiceTest()
	{
		final BankAccountDetails responce = defaultBankAccountService.process(detailRequest);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}

}
