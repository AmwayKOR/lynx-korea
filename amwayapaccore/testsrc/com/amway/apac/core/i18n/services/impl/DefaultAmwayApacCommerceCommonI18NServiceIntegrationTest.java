package com.amway.apac.core.i18n.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacCommerceCommonI18NServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private DefaultAmwayApacCommerceCommonI18NService defaultAmwayApacCommerceCommonI18NService;
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

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		Assert.assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));
	}

	@Test
	public void testGetCountryForCode()
	{
		final CountryModel country = defaultAmwayApacCommerceCommonI18NService.getCountryForCode("100");
		Assert.assertEquals("US", country.getIsocode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCountryForCodeForNullCountryCode()
	{
		defaultAmwayApacCommerceCommonI18NService.getCountryForCode(null);
	}

}
