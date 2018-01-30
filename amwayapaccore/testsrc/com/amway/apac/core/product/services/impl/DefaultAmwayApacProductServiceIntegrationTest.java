package com.amway.apac.core.product.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.model.AmwayUserPromotionCountModel;
import com.amway.apac.core.product.daos.AmwayApacProductDao;
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
	private static final String PRODUCT_WITHOUT_PRELAUNCH = "HW1210-3425";
	private static final String PRODUCT_BEFORE_PRELAUNCH = "HW1210-3422";
	private static final String PRODUCT_IN_PRELAUNCH = "HW1210-3423";
	private static final String PRODUCT_AFTER_PRELAUNCH = "HW1210-3424";

	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;

	@Resource(name = "productService")
	private AmwayApacProductService amwayApacProductService;

	@Resource
	private CustomerAccountService customerAccountService;

	@Resource
	private BaseStoreService baseStoreService;

	@Resource(name = "productDao")
	private AmwayApacProductDao amwayApacProductDao;

	private OrderModel orderModel;
	private Map<String, Integer> productCountMap;

	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwayapaccore/test/testProductService.impex", "utf-8");
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		orderModel = customerAccountService.getOrderForCode("ahertzOrder", baseStoreService.getBaseStoreForUid("testStore"));
		productCountMap = new HashMap<>();
		productCountMap.put(PRODUCT_IN_PRELAUNCH, Integer.valueOf(1));
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

	@Test(expected = IllegalArgumentException.class)
	public void testUpdatePreLaunchProductCountForNullProductCountMap()
	{
		amwayApacProductService.updatePreLaunchProductCount(null, orderModel);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdatePreLaunchProductCountForNullOrder()
	{
		amwayApacProductService.updatePreLaunchProductCount(productCountMap, null);
	}

	@Test
	public void testUpdatePreLaunchProductCountForExistingPromotionCount()
	{
		orderModel = customerAccountService.getOrderForCode("abrodeOrder", baseStoreService.getBaseStoreForUid("testStore"));
		final int countBeforeUpdate = amwayApacProductService.getUsedQuantityForPrelaunch("abrode", PRODUCT_IN_PRELAUNCH,
				baseStoreService.getBaseStoreForUid("testStore"));
		amwayApacProductService.updatePreLaunchProductCount(productCountMap, orderModel);
		final int countAfterUpdate = amwayApacProductService.getUsedQuantityForPrelaunch("abrode", PRODUCT_IN_PRELAUNCH,
				baseStoreService.getBaseStoreForUid("testStore"));
		Assert.assertEquals(countBeforeUpdate + 1, countAfterUpdate);
	}

	@Test
	public void testUpdatePreLaunchProductCountForNonExistingPromotionCount()
	{
		final List<AmwayUserPromotionCountModel> userSavedCountListBeforeUpdate = amwayApacProductDao
				.getPromotionRuleCountByUserAndProduct("ahertz", Arrays.asList(PRODUCT_IN_PRELAUNCH),
						AmwayapacCoreConstants.PRE_LAUNCH_PROMOTION, baseStoreService.getBaseStoreForUid("testStore"));
		Assert.assertEquals(0, userSavedCountListBeforeUpdate.size());
		amwayApacProductService.updatePreLaunchProductCount(productCountMap, orderModel);
		final List<AmwayUserPromotionCountModel> userSavedCountListAfterUpdate = amwayApacProductDao
				.getPromotionRuleCountByUserAndProduct("ahertz", Arrays.asList(PRODUCT_IN_PRELAUNCH),
						AmwayapacCoreConstants.PRE_LAUNCH_PROMOTION, baseStoreService.getBaseStoreForUid("testStore"));
		Assert.assertEquals(1, userSavedCountListAfterUpdate.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetUsedQuantityForPreLaunchForNullUserId()
	{
		amwayApacProductService.getUsedQuantityForPrelaunch(null, PRODUCT_IN_PRELAUNCH,
				baseStoreService.getBaseStoreForUid("testStore"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetUsedQuantityForPreLaunchForNullPromotionCode()
	{
		amwayApacProductService.getUsedQuantityForPrelaunch("abrode", null, baseStoreService.getBaseStoreForUid("testStore"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetUsedQuantityForPreLaunchForNullBasestore()
	{
		amwayApacProductService.getUsedQuantityForPrelaunch("abrode", PRODUCT_IN_PRELAUNCH, null);
	}

	@Test
	public void testGetUsedQuantityForPreLaunchForExistingCount()
	{
		Assert.assertTrue(amwayApacProductService.getUsedQuantityForPrelaunch("abrode", PRODUCT_IN_PRELAUNCH,
				baseStoreService.getBaseStoreForUid("testStore")) > 0);
	}

	@Test
	public void testGetUsedQuantityForPreLaunchForNonExistingCount()
	{
		Assert.assertTrue(amwayApacProductService.getUsedQuantityForPrelaunch("ahertz", PRODUCT_IN_PRELAUNCH,
				baseStoreService.getBaseStoreForUid("testStore")) == 0);
	}


}
