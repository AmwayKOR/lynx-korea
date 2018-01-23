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
	private static final String TEST_BUNDLE_PRODUCT_1 = "testBundleProduct1";

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
	public void testGetProductStatusForBundle()
	{
		cartService.setSessionCart(commerceCartService.getCartForCodeAndUser(CART_1, userService.getUserForUID(TEST_USER)));
		Assert.assertEquals(StockLevelStatus.INSTOCK,
				amwayApacStockService.getProductStatus(getProductByCode(TEST_BUNDLE_PRODUCT_1), getWarehouseByUid(TEST_WAREHOUSE_1)));
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
