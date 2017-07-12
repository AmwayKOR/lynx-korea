package com.amway.facades.customer.impl;

import com.amway.core.facades.customer.AmwayCustomerFacade;
import com.amway.core.model.AmwayAccountModel;
import com.amway.facades.data.AmwayAccountDataList;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Created by aiueq92 on 4/11/16.
 */
@IntegrationTest
public class DefaultCustomerFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private AmwayCustomerFacade amwayCustomerFacade;

	private static String NO_ACCOUNTS = "none";
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

		AmwayAccountDataList emptyAccountList = amwayCustomerFacade.lookupAccountsByName(NO_ACCOUNTS);
		assertTrue("No accounts should be returned", emptyAccountList.getAccounts().isEmpty());

		emptyAccountList = amwayCustomerFacade.lookupAccountsById(NO_ACCOUNTS);
		assertTrue("No accounts should be returned", emptyAccountList.getAccounts().isEmpty());
	}

	@Test
	public void amwayAccountServiceTestWithResults()
	{

		AmwayAccountDataList nAccounts = amwayCustomerFacade.lookupAccountsByName(ACCOUNT_NAME);
		assertTrue(nAccounts.getAccounts().size() > 0);

		nAccounts = amwayCustomerFacade.lookupAccountsById(ACCOUNT_CODE);
		assertTrue(nAccounts.getAccounts().size() > 0);
	}


}

