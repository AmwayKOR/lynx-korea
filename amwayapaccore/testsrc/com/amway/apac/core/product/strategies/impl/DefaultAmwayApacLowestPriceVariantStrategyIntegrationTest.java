package com.amway.apac.core.product.strategies.impl;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.account.services.impl.DefaultAmwayApacAccountService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.core.model.AmwayApacVariantProductModel;
import com.amway.core.constants.AmwaycoreConstants.SessionVariables;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacLowestPriceVariantStrategyIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private DefaultAmwayApacLowestPriceVariantStrategy defaultAmwayApacLowestPriceVariantStrategy;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private DefaultAmwayApacAccountService defaultAmwayApacAccountService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private SessionService sessionService;
	@Resource
	ProductService productService;
	@Resource
	ModelService modelService;

	private static final String PRODUCT_WITHOUT_PRELAUNCH = "HW1210-3425";
	private static final String PRODUCT_BEFORE_PRELAUNCH = "HW1210-3422";
	private static final String PRODUCT_IN_PRELAUNCH = "HW1210-3423";
	private static final String PRODUCT_AFTER_PRELAUNCH = "HW1210-3424";


	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));
		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				AccountClassificationEnum.PLATINUM_AND_ABOVE.toString());
		sessionService.setAttribute(SessionVariables.ACCOUNT,
				defaultAmwayApacAccountService.getAmwayAccount("ahertz", "100"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPrimaryVariantForNullBaseProduct()
	{
		defaultAmwayApacLowestPriceVariantStrategy.getPrimaryVariant(null);
	}

	@Test
	public void testGetPrimaryVariant()
	{
		final AmwayApacVariantProductModel lowestPriceVariant = defaultAmwayApacLowestPriceVariantStrategy
				.getPrimaryVariant(productService.getProductForCode("HW1210-3421"));
		Assert.assertEquals("XXXB", lowestPriceVariant.getName());
	}


	@Test
	public void testGetPrimaryVariantForProductWithNoVariants()
	{
		final AmwayApacVariantProductModel lowestPriceVariant = defaultAmwayApacLowestPriceVariantStrategy
				.getPrimaryVariant(productService.getProductForCode("HW1210-3422"));
		Assert.assertNull(lowestPriceVariant);
	}


}
