package com.amway.apac.core.stock.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.order.CartService;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.stock.services.AmwayApacStockService;


/**
 * Test class for Integration test cases for {@link DefaultAmwayApacStockService}
 *
 * @author Ashish Sabal
 *
 */
@IntegrationTest
public class DefaultAmwayApacStockServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_PRODUCT_1 = "testProduct1";
	private static final String TEST_WAREHOUSE_1 = "testWarehouse1";
	private static final String TEST_CATALOG_ID = "testProductCatalog";
	private static final String TEST_CATALOG_VERSION = "Online";
	private static final String CART_1 = "testCart1";
	private static final String TEST_USER = "amwaytestuser";

	private static final String TEST_PRODUCT_11_BUNDLE = "testProduct11Bundle";
	private static final String TEST_PRODUCT_12_BUNDLE = "testProduct12Bundle";
	private static final String TEST_PRODUCT_13_BUNDLE = "testProduct13Bundle";
	private static final String TEST_PRODUCT_21_BUNDLE = "testProduct21Bundle";
	private static final String TEST_PRODUCT_23_BUNDLE = "testProduct23Bundle";
	private static final String TEST_PRODUCT_31_BUNDLE = "testProduct31Bundle";
	private static final String TEST_PRODUCT_33_BUNDLE = "testProduct33Bundle";
	private static final String TEST_PRODUCT_41_BUNDLE = "testProduct41Bundle";
	private static final String TEST_PRODUCT_43_BUNDLE = "testProduct43Bundle";

	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "stockService")
	private AmwayApacStockService amwayApacStockService;

	@Resource(name = "cartService")
	private CartService cartService;

	@Resource(name = "commerceCartService")
	private CommerceCartService commerceCartService;

	@Resource(name = "warehouseService")
	private WarehouseService warehouseService;

	@Resource(name = "userService")
	private UserService userService;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testStockService.impex", "UTF-8");
		catalogVersionService.setSessionCatalogVersion(TEST_CATALOG_ID, TEST_CATALOG_VERSION);
	}

	@Test
	public void testGetProductStatusForProduct()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.INSTOCK,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_1), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}


	@Test
	public void testGetProductStatusForWarehouseListForProduct()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.INSTOCK, amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_1),
				Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateAvailableAmountForNullProduct()
	{
		amwayApacStockService.updateAvailableAmount(null, getWarehouseByUid(TEST_WAREHOUSE_1), 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateAvailableAmountForNullWarehouse()
	{
		amwayApacStockService.updateAvailableAmount(getProductByCode(TEST_PRODUCT_1), null, 10);
	}


	/**
	 * Stock level test for [All Major Products] & [All Backorder]
	 */
	@Test
	public void testGetProductStatusForBundleTest1()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.BACKORDER,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_11_BUNDLE),
						getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [All Major Products] & [All Backorder]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest1()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.BACKORDER,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_11_BUNDLE),
						Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [All Minor Products] & [All Backorder]
	 */
	@Test
	public void testGetProductStatusForBundleTest2()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.BACKORDER,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_12_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [All Minor Products] & [All Backorder]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest2()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.BACKORDER, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_12_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [Mixed Products] & [All Major Backorder]
	 */
	@Test
	public void testGetProductStatusForBundleTest3()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.BACKORDER,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_13_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [Mixed Products] & [All Major Backorder]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest3()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.BACKORDER, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_13_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [All Major Products] & [One TNA]
	 */
	@Test
	public void testGetProductStatusForBundleTest4()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.TEMPORARYNOTAVAILABLE,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_21_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [All Major Products] & [One TNA]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest4()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.TEMPORARYNOTAVAILABLE, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_21_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [Mixed Products] & [One Major TNA]
	 */
	@Test
	public void testGetProductStatusForBundleTest5()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.TEMPORARYNOTAVAILABLE,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_23_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [Mixed Products] & [One Major TNA]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest5()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.TEMPORARYNOTAVAILABLE, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_23_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [All Major Products] & [One NYA and No TNA]
	 */
	@Test
	public void testGetProductStatusForBundleTest6()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOTYETAVAILABLE,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_31_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [All Major Products] & [One NYA and No TNA]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest6()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOTYETAVAILABLE, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_31_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [Mixed Products] & [One major NYA and No TNA]
	 */
	@Test
	public void testGetProductStatusForBundleTest7()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOTYETAVAILABLE,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_33_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [Mixed Products] & [One major NYA and No TNA]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest7()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOTYETAVAILABLE, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_33_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [All Major Products] & [One NLA and no TNA or NYA]
	 */
	@Test
	public void testGetProductStatusForBundleTest8()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOLONGERAVAILABLE,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_41_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [All Major Products] & [One NLA and no TNA or NYA]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest8()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOLONGERAVAILABLE, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_41_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}

	/**
	 * Stock level test for [Mixed Products] & [One major NLA and No TNA or NYA]
	 */
	@Test
	public void testGetProductStatusForBundleTest9()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOLONGERAVAILABLE,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_PRODUCT_43_BUNDLE), getWarehouseByUid(TEST_WAREHOUSE_1)));
	}

	/**
	 * Stock level test for [Mixed Products] & [One major NLA and No TNA or NYA]
	 */
	@Test
	public void testGetProductStatusForWarehouseListForBundleTest9()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.NOLONGERAVAILABLE, amwayApacStockService
				.getProductStatus(getProductByCode(TEST_PRODUCT_43_BUNDLE), Arrays.asList(getWarehouseByUid(TEST_WAREHOUSE_1))));
	}


	/**
	 * Gets product by code
	 *
	 * @param productCode
	 * @return Product
	 */
	private ProductModel getProductByCode(final String productCode)
	{
		return productService.getProductForCode(productCode);
	}

	/**
	 *
	 * Gets warehouse by UID
	 *
	 * @param uid
	 * @return base store
	 */
	private WarehouseModel getWarehouseByUid(final String uid)
	{
		return warehouseService.getWarehouseForCode(uid);
	}
}
