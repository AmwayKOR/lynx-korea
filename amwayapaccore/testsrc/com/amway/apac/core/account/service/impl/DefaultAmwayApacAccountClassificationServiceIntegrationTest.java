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

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.account.services.AmwayApacAccountClassificationService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacAccountClassificationServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private AmwayApacAccountClassificationService amwayApacAccountClassificationService;
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
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));
	}

	@Test
	public void testCheckUserClassification()
	{
		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				AccountClassificationEnum.PLATINUM_AND_ABOVE.toString());
		Assert.assertEquals(Boolean.TRUE, Boolean.valueOf(
				amwayApacAccountClassificationService.checkUserClassification(AccountClassificationEnum.PLATINUM_AND_ABOVE)));
		Assert.assertEquals(Boolean.FALSE, Boolean
				.valueOf(amwayApacAccountClassificationService.checkUserClassification(AccountClassificationEnum.DIAMOND_AND_ABOVE)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckUserClassificationForNullReferenceClassification()
	{
		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				AccountClassificationEnum.PLATINUM_AND_ABOVE.toString());
		amwayApacAccountClassificationService.checkUserClassification(null);
	}

	@Test
	public void testCheckUserClassificationForNullSessionClassificationCode()
	{
		Assert.assertEquals(Boolean.FALSE,
				Boolean.valueOf(amwayApacAccountClassificationService.checkUserClassification(AccountClassificationEnum.NORMAL_ABO)));
	}

}
