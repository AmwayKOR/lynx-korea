package com.amway.apac.core.account.service.impl;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.account.services.impl.DefaultAmwayApacAccountService;
import com.amway.core.model.AmwayAccountModel;


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


	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapacfacades/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));
	}

	@Test
	public void testGetAmwayAccount()
	{
		final List<AmwayAccountModel> accountList = defaultAmwayApacAccountService.getAmwayAccount("ahertz", "100");
		if (CollectionUtils.isNotEmpty(accountList))
		{
			final AmwayAccountModel account = accountList.iterator().next();
			Assert.assertEquals("Test User", account.getName());
		}
	}


}
