package com.amway.integration.cis.dms.accountrenewal.service.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.renewal.data.AccountRenewalRequestData;
import com.amway.integration.cis.dms.updateinformation.common.mock.CommonMockService;


public class DefaultAccountRenewalServiceTest extends ServicelayerTest
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFlI_NO = "170";


	@Resource(name = "mockAccountRenewalService")
	private CommonMockService defaultAccountRenewalService;

	private AccountRenewalRequestData requestData;


	@Before
	public void setUp()
	{
		requestData = new AccountRenewalRequestData();
		requestData.setAboNum(ABO_NO);
		requestData.setSalesPlanAff(AFFlI_NO);
		requestData.setAccountSubTypeCd("Customer");
		requestData.setRenewalDate("2016-03-02T15:17:49-03:00");
		requestData.setRenewalCd("Renewal1Years");
		requestData.setLegalConsentFlg("Y");
		requestData.setRenewalWithGroupFlg("");

	}

	@Test
	public void shouldRenewAccountTest()
	{
		final CommonResponseFieldsData responce = defaultAccountRenewalService.process(requestData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}
}
