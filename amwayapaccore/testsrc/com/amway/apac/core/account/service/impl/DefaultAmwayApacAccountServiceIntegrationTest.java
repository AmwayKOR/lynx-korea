package com.amway.apac.core.account.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.account.services.impl.DefaultAmwayApacAccountService;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacAccountServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private DefaultAmwayApacAccountService defaultAmwayApacAccountService;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private UserService userService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private SessionService sessionService;

	private CustomerModel customer;
	private AmwayAccountModel accountModel;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);
		customer = (CustomerModel) userService.getUserForUID("ahertz");
		accountModel = defaultAmwayApacAccountService.getAmwayAccount("ahertz", "100");
	}

	@Test
	public void testGetAmwayAccount()
	{
		final AmwayAccountModel account = defaultAmwayApacAccountService.getAmwayAccount("ahertz", "100");
		Assert.assertEquals("Test User", account.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAmwayAccountForNullAboId()
	{
		defaultAmwayApacAccountService.getAmwayAccount(null, "100");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAmwayAccountForNullAffiliateCode()
	{
		defaultAmwayApacAccountService.getAmwayAccount("ahertz", null);
	}

	@Test
	public void testGetClassificationForAccount()
	{
		final AmwayAccountModel account = defaultAmwayApacAccountService.getAmwayAccount("ahertz", "100");
		Assert.assertEquals(AccountClassificationEnum.PLATINUM_AND_ABOVE,
				defaultAmwayApacAccountService.getClassificationForAccount(account));
	}

	@Test
	public void testGetClassificationForAccountForNullAccount()
	{
		Assert.assertEquals(AccountClassificationEnum.NORMAL_ABO, defaultAmwayApacAccountService.getClassificationForAccount(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAmwayAccountForNullCustomer()
	{
		defaultAmwayApacAccountService.getAmwayAccount(null);
	}


	@Test
	public void testGetAmwayAccountForCustomer()
	{
		final AmwayAccountModel account = defaultAmwayApacAccountService.getAmwayAccount(customer);
		Assert.assertNotNull(account);
		Assert.assertEquals("Test User", account.getName());
	}

	@Test
	public void testGetAmwayAccountForCustomerWithNoAccount()
	{
		customer = (CustomerModel) userService.getUserForUID("dejol");
		final AmwayAccountModel account = defaultAmwayApacAccountService.getAmwayAccount(customer);
		Assert.assertNull(account);
	}



	@Test(expected = IllegalArgumentException.class)
	public void testGetMOPRestrictionForNullAccount()
	{
		defaultAmwayApacAccountService.getMOPRestriction(null);
	}

	@Test
	public void testGetMOPRestrictionForAccountWithRestriction()
	{
		final AmwayBusinessRestrictionModel restriction = defaultAmwayApacAccountService.getMOPRestriction(accountModel);
		Assert.assertNotNull(restriction);
		Assert.assertEquals("testrestriction", restriction.getCode());
	}

	@Test
	public void testGetMOPRestrictionForAccountWithoutRestriction()
	{
		accountModel = defaultAmwayApacAccountService.getAmwayAccount("abrode", "100");
		final AmwayBusinessRestrictionModel restriction = defaultAmwayApacAccountService.getMOPRestriction(accountModel);
		Assert.assertNull(restriction);
	}

}