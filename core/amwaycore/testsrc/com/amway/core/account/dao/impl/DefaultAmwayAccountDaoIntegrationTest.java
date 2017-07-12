/**
 *
 */
package com.amway.core.account.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.util.BaseCommerceBaseTest;
import de.hybris.platform.impex.jalo.ImpExException;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.model.AmwayAccountModel;


@IntegrationTest
public class DefaultAmwayAccountDaoIntegrationTest extends BaseCommerceBaseTest
{
	@Resource
	private DefaultAmwayAccountDao defaultAmwayAccountDao;
	private final static String ACCOUNT_CODE = "3108051595";
	private final static String ACCOUNT_NAME = "bv enterprises com e repres ltda ii";

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
	}

	@Test
	public void testGetAccount()
	{

		final List<AmwayAccountModel> accountList = defaultAmwayAccountDao.getAccount(ACCOUNT_CODE);
		Assert.assertTrue(CollectionUtils.isNotEmpty(accountList));
		Assert.assertEquals("Should be same", 1, CollectionUtils.size(accountList));
	}


	@Test
	public void testGetAccountsForUidOrName()
	{
		// test for name
		List<AmwayAccountModel> accountList = defaultAmwayAccountDao.getAccountsForUidOrName(ACCOUNT_NAME);
		Assert.assertTrue(CollectionUtils.isNotEmpty(accountList));
		Assert.assertTrue(CollectionUtils.size(accountList) >= 1);
		// test for uid
		accountList = defaultAmwayAccountDao.getAccountsForUidOrName(ACCOUNT_CODE);
		Assert.assertTrue(CollectionUtils.isNotEmpty(accountList));
		Assert.assertEquals("Should be same", 1, CollectionUtils.size(accountList));
	}

	@Test
	public void testAccountLookupByName()
	{
		// test for name
		List<AmwayAccountModel> accountList = defaultAmwayAccountDao.lookupAccountsByName(ACCOUNT_NAME);
		Assert.assertTrue(CollectionUtils.isNotEmpty(accountList));
		Assert.assertTrue(CollectionUtils.size(accountList) >= 1);

	}

	@Test
	public void testAccountLookupById()
	{
		// test for name
		List<AmwayAccountModel> accountList = defaultAmwayAccountDao.lookupAccountsById(ACCOUNT_CODE);
		Assert.assertTrue(CollectionUtils.isNotEmpty(accountList));
		Assert.assertTrue(CollectionUtils.size(accountList) >= 1);

	}

}
