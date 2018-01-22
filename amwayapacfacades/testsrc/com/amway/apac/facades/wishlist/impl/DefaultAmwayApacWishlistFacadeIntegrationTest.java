/**
 *
 */
package com.amway.apac.facades.wishlist.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;
import com.amway.apac.facades.wishlist.AmwayApacWishlistFacade;
import com.amway.apac.facades.wishlist.data.AmwayApacWishListModification;
import com.amway.facades.product.data.WishlistData;
import com.amway.facades.product.data.WishlistEntryData;
import com.amway.facades.wishlist.modification.status.AmwayApacWishlistModificationStatus;


/**
 * @author avnishalaugh
 *
 */
@IntegrationTest
public class DefaultAmwayApacWishlistFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource(name = "wishlistFacade")
	private AmwayApacWishlistFacade defaultAmwayApacWishlistFacade;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private UserService userService;
	@Resource
	private ProductService productService;
	@Resource(name = "modelService")
	private ModelService modelService;
	@Resource
	private CatalogVersionService catalogVersionService;

	private Wishlist2Model wishlist2model;

	private AmwayApacCartSortCode sortBy;

	private List<WishlistData> wishlistDataList;

	private WishlistData wishlistData;

	private AmwayApacWishListModification amwayApacWishListModification = new AmwayApacWishListModification();

	private AmwayApacWishlistModificationStatus amwayApacWishlistModificationStatus;
	/**
	 * Wishlist uid string constant
	 */
	private static final String WISHLIST_UID = "wishlist2A";

	/**
	 * Wishlist Name string constant
	 */
	private static final String WISHLIST_NAME = "AA";

	/**
	 * Product Code string constant
	 */
	private static final String PRODUCT_CODE = "HW1210-3422";

	/**
	 * Customer uid string constant
	 */
	private static final String CUSTOMER_UID = "ahertz";

	/**
	 * SortField string constant
	 */
	private static final String SORT_FIELD = "Name";

	/**
	 * SortOrder string constant
	 */
	private static final String SORT_ORDER = "DESC";

	@Before
	public void setUp() throws ImpExException
	{
		wishlistData = new WishlistData();

		importCsv("/amwayapacfacades/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		Assert.assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));

		final CustomerModel user = (CustomerModel) userService.getUserForUID(CUSTOMER_UID);
		userService.setCurrentUser(user);
	}

	@Test
	public void testGetShoppingListDetailsSortBySortCode()
	{
		wishlistData = defaultAmwayApacWishlistFacade.getWishlistByUid(WISHLIST_UID);
		Assert.assertEquals("CCC",
				defaultAmwayApacWishlistFacade
						.getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode.LAST_ITEM_ADDED, wishlistData).getEntries().get(0)
						.getProduct().getName());
		Assert.assertEquals("AAA",
				defaultAmwayApacWishlistFacade
						.getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode.NAME_ASCENDING, wishlistData).getEntries().get(0)
						.getProduct().getName());
		Assert.assertEquals("CCC",
				defaultAmwayApacWishlistFacade
						.getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode.NAME_DESCEDNING, wishlistData).getEntries().get(0)
						.getProduct().getName());
		Assert.assertEquals("CCC",
				defaultAmwayApacWishlistFacade
						.getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode.PRICE_ASCENDING, wishlistData).getEntries().get(0)
						.getProduct().getName());
		Assert.assertEquals("BBB",
				defaultAmwayApacWishlistFacade
						.getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode.PRICE_DESCEDNING, wishlistData).getEntries().get(0)
						.getProduct().getName());
	}

	/**
	 * Test for method
	 * {@link AmwayApacWishlistFacade#getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode, WishlistData)}. Testing
	 * with null AmwayApacCartSortCode
	 */
	@Test
	public void testGetShoppingListDetailsWithSortBySortCodeForNullSortCode()
	{
		wishlistData = defaultAmwayApacWishlistFacade.getWishlistByUid(WISHLIST_UID);
		Assert.assertEquals("CCC", defaultAmwayApacWishlistFacade.getShoppingListDetailsSortBySortCode(null, wishlistData)
				.getEntries().get(0).getProduct().getName());
	}

	/**
	 * Test for method
	 * {@link AmwayApacWishlistFacade#getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode, WishlistData)}. Testing
	 * with null WishlistData
	 */
	@Test
	public void testGetShoppingListDetailsWithNullWishlistdata()
	{
		Assert.assertEquals(null, defaultAmwayApacWishlistFacade.getShoppingListDetailsSortBySortCode(sortBy, null));
	}

	/**
	 * Test for method
	 * {@link AmwayApacWishlistFacade#getShoppingListDetailsSortBySortCode(AmwayApacCartSortCode, WishlistData)}. Testing
	 * with null WishlistData and null WishlistUid
	 */
	@Test
	public void testGetShoppingListDetailsWithEmptyWishlist()
	{
		wishlistData = defaultAmwayApacWishlistFacade.getWishlistByUid(WISHLIST_UID);
		wishlistData.setEntries(new ArrayList<WishlistEntryData>());
		final AmwayApacCartSortCode sortBy = AmwayApacCartSortCode.LAST_ITEM_ADDED;
		Assert.assertEquals(0,
				defaultAmwayApacWishlistFacade.getShoppingListDetailsSortBySortCode(sortBy, wishlistData).getEntries().size());
	}

	@Test
	public void testGetAllWishlistsWithBasicData()
	{

		wishlistDataList = defaultAmwayApacWishlistFacade.getAllWishlistsWithBasicData(SORT_FIELD, SORT_ORDER);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#getAllWishlistsWithBasicData}. Testing with null Sort field
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullSortField()
	{
		defaultAmwayApacWishlistFacade.getAllWishlistsWithBasicData(null, SORT_ORDER);

	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#getAllWishlistsWithBasicData}. Testing with null Sort order
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullSortOrder()
	{
		defaultAmwayApacWishlistFacade.getAllWishlistsWithBasicData(SORT_FIELD, null);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#getAllWishlistsWithBasicData}. Testing with null Sort field and
	 * Sort order
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullSortFieldAndSortOrder()
	{
		defaultAmwayApacWishlistFacade.getAllWishlistsWithBasicData(null, null);
	}

	@Test
	public void testGetWishlistByUidForCurrentUser()
	{
		wishlistData = defaultAmwayApacWishlistFacade.getWishlistByUidForCurrentUser(WISHLIST_UID);

		Assert.assertNotNull(wishlistData.getUid());
		Assert.assertEquals(wishlistData.getUid(), WISHLIST_UID);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#getWishlistByUidForCurrentUser}. Testing with null UID
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetEmptyWishlistUid()
	{
		defaultAmwayApacWishlistFacade.getWishlistByUidForCurrentUser(null);
	}

	@Test
	public void testAddProductToWishlist()
	{
		amwayApacWishListModification = defaultAmwayApacWishlistFacade.addProductToWishlist(PRODUCT_CODE, WISHLIST_UID);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#addProductToWishlist}. Testing with null product Code
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullProductCode()
	{
		defaultAmwayApacWishlistFacade.addProductToWishlist(null, WISHLIST_UID);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#addProductToWishlist}. Testing with null WishlistUid
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullWishlistUid()
	{
		defaultAmwayApacWishlistFacade.addProductToWishlist(PRODUCT_CODE, null);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#addProductToWishlist}. Testing with null product Code and
	 * WishlistUid
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullWishlistUidAndProductCode()
	{
		defaultAmwayApacWishlistFacade.addProductToWishlist(null, null);
	}

	@Test
	public void testupdateWishlistNameAndReturnStatus()
	{
		amwayApacWishlistModificationStatus = defaultAmwayApacWishlistFacade.updateWishlistNameAndReturnStatus(WISHLIST_UID,
				WISHLIST_NAME);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#updateWishlistNameAndReturnStatus}. Testing with null WishlistUid
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullUid()
	{
		defaultAmwayApacWishlistFacade.updateWishlistNameAndReturnStatus(null, WISHLIST_NAME);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#updateWishlistNameAndReturnStatus}. Testing with null WishlistName
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullWishlistName()
	{
		defaultAmwayApacWishlistFacade.updateWishlistNameAndReturnStatus(WISHLIST_UID, null);
	}

	/**
	 * Test for method {@link AmwayApacWishlistFacade#updateWishlistNameAndReturnStatus}. Testing with null WishlistName
	 * and WishlistUid
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullWishlistUidAndWishlistName()
	{
		defaultAmwayApacWishlistFacade.updateWishlistNameAndReturnStatus(null, null);
	}
}
