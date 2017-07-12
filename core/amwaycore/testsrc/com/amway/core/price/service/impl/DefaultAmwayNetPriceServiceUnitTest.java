/**
 *
 */
package com.amway.core.price.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



@IntegrationTest
public class DefaultAmwayNetPriceServiceUnitTest extends ServicelayerTest
{
	@Resource
	private DefaultAmwayNetPriceService netPriceService;
	@Resource
	private ProductService productService;
	@Resource
	private CustomerAccountService customerAccountService;
	@Resource
	private BaseStoreService baseStoreService;
	private BaseStoreModel store;
	private ProductModel productModel;
	private OrderModel order1, order2;
	private static final String PRODUCT_CODE = "Promotional_Kit_Plus";
	private static final String ORDER_CODE1 = "kit_order1";
	private static final String STORE_UID = "defaultstore";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/kitProductTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/orderTestDataForKitProduct.csv", "windows-1252");
		productModel = productService.getProductForCode(PRODUCT_CODE);
		store = baseStoreService.getBaseStoreForUid(STORE_UID);
		order1 = customerAccountService.getOrderForCode(ORDER_CODE1, store);
		order2 = customerAccountService.getOrderForCode(ORDER_CODE1, store);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.price.service.impl.DefaultAmwayNetPriceService#getPriceInformationsForProduct(de.hybris.platform.core.model.product.ProductModel)}
	 * .
	 */
	@Test
	public void testGetPriceInformationsForProductProductModel()
	{
		final List<PriceInformation> priceList = netPriceService.getPriceInformationsForProduct(productModel);
		Assert.assertNotNull(priceList);
		Assert.assertTrue(CollectionUtils.size(priceList) == 1);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.price.service.impl.DefaultAmwayNetPriceService#findABOBasePrice(de.hybris.platform.core.model.order.AbstractOrderEntryModel)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testFindABOBasePriceForNormalProduct() throws CalculationException
	{
		Assert.assertNotNull(netPriceService.findABOBasePrice(order2.getEntries().iterator().next()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.price.service.impl.DefaultAmwayNetPriceService#findABOBasePrice(de.hybris.platform.core.model.order.AbstractOrderEntryModel)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testFindABOBasePriceForKitProduct() throws CalculationException
	{
		Assert.assertNotNull(netPriceService.findABOBasePrice(order1.getEntries().iterator().next()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.price.service.impl.DefaultAmwayNetPriceService#findRetailBasePrice(de.hybris.platform.core.model.order.AbstractOrderEntryModel)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testFindRetailBasePriceForNormalProduct() throws CalculationException
	{
		Assert.assertNotNull(netPriceService.findRetailBasePrice(order2.getEntries().iterator().next()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.price.service.impl.DefaultAmwayNetPriceService#findRetailBasePrice(de.hybris.platform.core.model.order.AbstractOrderEntryModel)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testFindRetailBasePriceForKitProduct() throws CalculationException
	{
		Assert.assertNotNull(netPriceService.findRetailBasePrice(order1.getEntries().iterator().next()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.price.service.impl.DefaultAmwayNetPriceService#getBasePrice(de.hybris.platform.core.model.order.AbstractOrderEntryModel)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testGetBasePriceForKitProduct() throws CalculationException
	{
		Assert.assertNotNull(netPriceService.getBasePrice(order1.getEntries().iterator().next()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.price.service.impl.DefaultAmwayNetPriceService#getBasePrice(de.hybris.platform.core.model.order.AbstractOrderEntryModel)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testGetBasePriceForNormalProduct() throws CalculationException
	{
		Assert.assertNotNull(netPriceService.getBasePrice(order2.getEntries().iterator().next()));
	}
}
