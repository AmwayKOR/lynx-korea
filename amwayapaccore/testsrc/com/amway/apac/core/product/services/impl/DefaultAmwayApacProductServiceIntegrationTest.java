package com.amway.apac.core.product.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.product.services.AmwayApacProductService;


/**
 * @author Ashish Sabal
 *
 */
@IntegrationTest
public class DefaultAmwayApacProductServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String OMS_CODE_1 = "220136";
	private static final String OMS_CODE_2 = "220245";
	private static final String OMS_CODE_3 = "220246";
	private static final String OMS_CODE_4 = "220301";
	private static final String OMS_CODE_5 = "220302";
	private static final String PRODUCT_CATALOG = "testProductCatalog";
	private static final String VERSION = "Online";

	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;

	@Resource(name = "productService")
	private AmwayApacProductService amwayApacProductService;

	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwayapaccore/test/testProductService.impex", "utf-8");
	}

	@Test
	public void testPaymentOptionForAliasCode()
	{

		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(PRODUCT_CATALOG, VERSION);

		final AmwayPaymentOptionModel paymentOption = amwayApacProductService.getPaymentOptionForAliasCode(OMS_CODE_1,
				catalogVersion);
		Assert.assertTrue(paymentOption.getAliasCode().equalsIgnoreCase(OMS_CODE_1));

		// ONE Payment Option is attached with approved product, so only one payment option exists
		final AmwayPaymentOptionModel paymentOption1 = amwayApacProductService.getPaymentOptionForAliasCode(OMS_CODE_3,
				catalogVersion);
		Assert.assertTrue(paymentOption1.getAliasCode().equalsIgnoreCase(OMS_CODE_3));
	}

	@Test
	public void testAllPaymentOptionForOmsCode()
	{
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(PRODUCT_CATALOG, VERSION);

		final AmwayPaymentOptionModel paymentOption = amwayApacProductService.getAllPaymentOptionForAliasCode(OMS_CODE_1,
				catalogVersion);
		Assert.assertTrue(paymentOption.getAliasCode().equalsIgnoreCase(OMS_CODE_1));

		final AmwayPaymentOptionModel paymentOption1 = amwayApacProductService.getAllPaymentOptionForAliasCode(OMS_CODE_2,
				catalogVersion);
		Assert.assertTrue(paymentOption1.getAliasCode().equalsIgnoreCase(OMS_CODE_2));
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testAllPaymentOptionForOmsCodeException1()
	{
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(PRODUCT_CATALOG, VERSION);

		final AmwayPaymentOptionModel paymentOption = amwayApacProductService.getAllPaymentOptionForAliasCode(OMS_CODE_4,
				catalogVersion);
		Assert.assertFalse(paymentOption.getAliasCode().equalsIgnoreCase(OMS_CODE_4));
	}

	@Test(expected = AmbiguousIdentifierException.class)
	public void testAllPaymentOptionForOmsCodeException2()
	{
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(PRODUCT_CATALOG, VERSION);

		final AmwayPaymentOptionModel paymentOption = amwayApacProductService.getAllPaymentOptionForAliasCode(OMS_CODE_5,
				catalogVersion);
		Assert.assertFalse(paymentOption.getAliasCode().equalsIgnoreCase(OMS_CODE_5));
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testAllPaymentOptionForOmsCodeException3()
	{
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(PRODUCT_CATALOG, VERSION);

		final AmwayPaymentOptionModel paymentOption = amwayApacProductService.getAllPaymentOptionForAliasCode(OMS_CODE_4,
				catalogVersion);
		Assert.assertFalse(paymentOption.getAliasCode().equalsIgnoreCase(OMS_CODE_5));
	}
}
