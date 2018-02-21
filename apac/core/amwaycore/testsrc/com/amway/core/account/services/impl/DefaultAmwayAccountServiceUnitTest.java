package com.amway.core.account.services.impl;

import com.amway.core.account.dao.AmwayAccountDao;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.account.service.impl.DefaultAmwayAccountService;
import com.amway.core.model.AmwayAccountModel;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.List;

import javax.annotation.Resource;


import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;


/**
 * Created by aiueq92 on 4/11/16.
 */

@UnitTest
public class DefaultAmwayAccountServiceUnitTest
{

	@Mock
	private AmwayAccountDao amwayAccountDao;
	@InjectMocks
	private DefaultAmwayAccountService amwayAccountService = new DefaultAmwayAccountService();

	private static String NO_ACCOUNTS = "none";
	private static String ALL_ACCOUNTS = "n";
	private static String ACCOUNT_ID = "8888";

	final List<AmwayAccountModel> noaccounts = new java.util.ArrayList();
	final List<AmwayAccountModel> accounts = new java.util.ArrayList();

	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);
		accounts.add((new AmwayAccountModel()));
	}

	@Test
	public void amwayAccountServiceTestEmpty()
	{

		given(amwayAccountDao.lookupAccountsByName(NO_ACCOUNTS)).willReturn(noaccounts);
		List<AmwayAccountModel> emptyAccountList = amwayAccountService.lookupAccountsByName(NO_ACCOUNTS);
		assertTrue("No accounts should be returned", emptyAccountList.isEmpty());

		given(amwayAccountDao.lookupAccountsById(NO_ACCOUNTS)).willReturn(noaccounts);
		emptyAccountList = amwayAccountService.lookupAccountsById(NO_ACCOUNTS);
		assertTrue("No accounts should be returned", emptyAccountList.isEmpty());
	}

	@Test
	public void amwayAccountServiceTestWithResults()
	{

		given(amwayAccountDao.lookupAccountsByName(ALL_ACCOUNTS)).willReturn(accounts);
		List<AmwayAccountModel> nAccounts = amwayAccountService.lookupAccountsByName(ALL_ACCOUNTS);
		int size = nAccounts.size();
		assertTrue(nAccounts.size() > 0);

		given(amwayAccountDao.lookupAccountsById(ACCOUNT_ID)).willReturn(accounts);
		nAccounts = amwayAccountService.lookupAccountsById(ACCOUNT_ID);
		size = nAccounts.size();
		assertTrue(nAccounts.size() > 0);
	}



}
