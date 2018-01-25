package com.amway.apac.core.user.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacUserServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private DefaultAmwayApacUserService defaultAmwayApacUserService;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private UserService userService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private SessionService sessionService;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetUserForUIDForCurrentAffiliateForNullId()
	{
		defaultAmwayApacUserService.getUserForUIDForCurrentAffiliate(null);
	}

	@Test
	public void testGetUserForUIDForCurrentAffiliate()
	{
		final UserModel user = defaultAmwayApacUserService.getUserForUIDForCurrentAffiliate("ahertz");
		Assert.assertEquals("Anja Hertz", user.getName());
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testGetUserForUIDForCurrentAffiliateFonNonExistingUser()
	{
		defaultAmwayApacUserService.getUserForUIDForCurrentAffiliate("nonExistingUser");
	}
}
