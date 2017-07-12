/**
 *
 */
package com.amway.integration.cis.dms.bankaccountservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.ProcessBankAccountRequestData;
import com.amway.integration.cis.dms.bankaccountservice.mock.impl.MockDefaultUpdateBankAccountService;


public class DefaultUpdateBankAccountServiceTest extends ServicelayerTest
{
	private static final String PARTY_ID = "171956";
	private static final String BONUS_PAYMENTS = "BonusPayments";
	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";

	@Resource(name = "mockDefaultUpdateBankAccountService")
	private MockDefaultUpdateBankAccountService defaultUpdateBankAccountService;

	private ProcessBankAccountRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new ProcessBankAccountRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFlI_NO);
		requestData.setPartyId(PARTY_ID);
		requestData.setIssuingBankId("965");
		requestData.setBankBranchCode("8765");
		requestData.setIssuingBankId("630138");
		requestData.setBankAcctNum("876543456789877");
		requestData.setBankAcctTypeCd("Checking");
		requestData.setBankAcctHolderName("SANTANA GEAZI RODRIQUES");
		requestData.setCurrencyCd("BRL");
		requestData.setAccountUseCodes(Collections.singletonList("BonusPayments"));
	}

	@Test
	public void shouldUpdateBankAccountServiceTest()
	{
		final CommonResponseFieldsData responce = defaultUpdateBankAccountService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}
}
