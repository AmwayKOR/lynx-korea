package com.amway.apac.facades.cart.impl;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.order.CartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacCartFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private DefaultAmwayApacCartFacade defaultAmwayApacCartFacade;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private UserService userService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private CartService cartService;
	@Resource
	private CommerceCartService commerceCartService;
	@Resource
	private ProductService productService;

	@Before
	public void setUp() throws ImpExException, CommerceCartModificationException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));

		final UserModel user = userService.getUserForUID("ahertz");
		userService.setCurrentUser(user);
		final CartModel currentCart = user.getCarts().iterator().next();
		cartService.setSessionCart(currentCart);

		final ProductModel lastProductToBeAdded = productService.getProductForCode("HW1210-3425promo");
		cartService.addNewEntry(currentCart, lastProductToBeAdded, 1, productService.getOrderableUnit(lastProductToBeAdded));
	}

	@Test
	public void testGetSessionCartWithSortBySortCodeForAllGoodCases()
	{
		Assert.assertEquals("EEE", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.LAST_ITEM_ADDED).getEntries().get(0).getProduct().getName());
		Assert.assertEquals("AAA", defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.NAME_ASCENDING)
				.getEntries().get(0).getProduct().getName());
		Assert.assertEquals("EEE", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.NAME_DESCEDNING).getEntries().get(0).getProduct().getName());
		Assert.assertEquals("EEE", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.PRICE_ASCENDING).getEntries().get(0).getProduct().getName());
		Assert.assertEquals("AAA", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.PRICE_DESCEDNING).getEntries().get(0).getProduct().getName());
	}


	@Test
	public void testGetSessionCartWithSortBySortCodeForNullSortCode()
	{
		Assert.assertEquals("EEE",
				defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(null).getEntries().get(0).getProduct().getName());
	}

	@Test
	public void testGetSessionCartWithSortBySortCodeForEmptyCart()
	{
		final CartModel currentCart = cartService.getSessionCart();
		currentCart.setEntries(new ArrayList<AbstractOrderEntryModel>());
		cartService.setSessionCart(currentCart);
		Assert.assertEquals(0, defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(null).getEntries().size());
	}

	@Test
	public void testGetSessionCartWithSortBySortCodeForNoSessionCart()
	{
		cartService.setSessionCart(null);
		Assert.assertNull(defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(null).getEntries());
	}

}
