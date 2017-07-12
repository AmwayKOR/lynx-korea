package com.amway.core.account.services.impl;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.model.AmwayAccountModel;

import de.hybris.bootstrap.annotations.IntegrationTest;

import java.util.List;

import javax.annotation.Resource;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;

import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;


/**
 * Created by aiueq92 on 4/11/16.
 */

@IntegrationTest
public class DefaultAmwayAccountServiceIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private AmwayAccountService amwayAccountService;

	private static String NO_ACCOUNTS = "none";

	private static String CUSTOMER_NAME = "D";

	private final static String ACCOUNT_CODE = "3108051595";
	private final static String ACCOUNT_NAME = "bv enterprises com e repres ltda ii";

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
	}

	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void amwayAccountServiceTestEmpty()
	{

		List<AmwayAccountModel> emptyAccountList = amwayAccountService.lookupAccountsByName(NO_ACCOUNTS);
		assertTrue("No accounts should be returned", emptyAccountList.isEmpty());

		emptyAccountList = amwayAccountService.lookupAccountsById(NO_ACCOUNTS);
		assertTrue("No accounts should be returned", emptyAccountList.isEmpty());

	}

	@Test
	public void amwayAccountServiceTestWithResults()
	{

		List<AmwayAccountModel> nAccounts = amwayAccountService.lookupAccountsByName(ACCOUNT_NAME);
		assertTrue(nAccounts.size() > 0);


		nAccounts = amwayAccountService.lookupAccountsById(ACCOUNT_CODE);
		assertTrue(nAccounts.size() > 0);
	}

	@Test
	public void amwayCustomerServiceTestWithResults()
	{

		List<CustomerModel> nCustomers = amwayAccountService.lookupAccountsCustomersByPartyName("V");
		assertTrue(nCustomers.size() > 0);

		nCustomers = amwayAccountService.lookupAccountsCustomersByPartyId("340028");
		assertTrue(nCustomers.size() > 0);

		nCustomers = amwayAccountService.lookupAccountsCustomersByUid("amway");
		assertTrue(nCustomers.size() > 0);

		nCustomers = amwayAccountService.lookupAccountsCustomersByEmail("test");
		assertTrue(nCustomers.size() > 0);
	}


}
