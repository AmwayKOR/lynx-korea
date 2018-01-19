/**
 *
 */
package com.amway.apac.core.wishlist.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




/**
 * @author avnishalaugh
 *
 */
@IntegrationTest
public class DefaultAmwayApacWishlistServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource(name = "wishlistService")
	private DefaultAmwayApacWishlistService defaultAmwayApacWishlistService;
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

	/**
	 * Wishlist uid string constant
	 */
	private static final String WISHLIST_UID = "wishlist2A";

	/**
	 * Added Date string constant
	 */
	private static final String ADDED_DATE = "27.04.2010 00:00:00";

	/**
	 * Product Code string constant
	 */
	private static final String PRODUCT_CODE = "HW1210-3422";

	/**
	 * Comment string constant
	 */
	private static final String COMMENT = "Hi";

	/**
	 * Priority string constant
	 */
	private static final String WISHLISTENTRY_PRIORITY = "HIGH";

	/**
	 * Wishlist Entries int constant
	 */
	private static final int WISHLIST_ENTRIES = 3;

	/**
	 * Wishlist Desired int constant
	 */
	private static final int WISHLIST_DESIRED = 2;
	private final Integer desired = Integer.valueOf(2);

	private List<Wishlist2Model> wishlistModelList;
	private Wishlist2EntryModel wishlist2EntryModel;
	private Wishlist2Model wishlist2Model;
	private ProductModel product;
	private Wishlist2EntryPriority wishlist2EntryPriority;

	@Before
	public void setUp() throws ImpExException
	{

		importCsv("/amwayapacfacades/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		Assert.assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));

		final CustomerModel user = (CustomerModel) userService.getUserForUID(CUSTOMER_UID);
		userService.setCurrentUser(user);


	}


	/**
	 * Test for method {@link DefaultAmwayApacWishlistService#getWishlists}.
	 */
	@Test
	public void testGetWishlists()
	{
		wishlistModelList = defaultAmwayApacWishlistService.getWishlists(SORT_FIELD, SORT_ORDER);
		Assert.assertEquals("Number of wishlists", WISHLIST_ENTRIES, wishlistModelList.iterator().next().getEntries().size());

	}

	/**
	 * Test for method {@link DefaultAmwayApacWishlistService#getWishlists}. Testing with null Sort field
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullSortField()
	{
		defaultAmwayApacWishlistService.getWishlists(null, SORT_ORDER);

	}

	/**
	 * Test for method {@link DefaultAmwayApacWishlistService#getWishlists}. Testing with null Sort order
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullSortOrder()
	{
		defaultAmwayApacWishlistService.getWishlists(SORT_FIELD, null);
	}

	/**
	 * Test for method {@link DefaultAmwayApacWishlistService#getWishlists}. Testing with null SortField and SortOrder
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullSortFieldAndSortOrder()
	{
		defaultAmwayApacWishlistService.getWishlists(null, null);
	}

	@Test
	public void testAddAndReturnWishlistEntry()
	{
		wishlist2EntryModel = new Wishlist2EntryModel();
		wishlist2Model = defaultAmwayApacWishlistService.getWishlistByUid(WISHLIST_UID);
		product = productService.getProductForCode(PRODUCT_CODE);
		wishlist2EntryPriority = Wishlist2EntryPriority.HIGH;
		wishlist2EntryModel.setComment(COMMENT);
		wishlist2EntryModel.setDesired(Integer.valueOf(WISHLIST_DESIRED));

		wishlist2EntryModel = defaultAmwayApacWishlistService.addAndReturnWishlistEntry(wishlist2Model, product, desired,
				wishlist2EntryPriority, COMMENT);

		Assert.assertEquals("Number of wishlists", 4, wishlist2Model.getEntries().size());

		Assert.assertNotNull(wishlist2EntryModel.getProduct());
		Assert.assertEquals(wishlist2EntryModel.getProduct().getCode(), PRODUCT_CODE);

		Assert.assertNotNull(wishlist2EntryModel.getPriority());
		Assert.assertEquals(wishlist2EntryModel.getPriority(), WISHLISTENTRY_PRIORITY);

		Assert.assertNotNull(wishlist2EntryModel.getComment());
		Assert.assertEquals(wishlist2EntryModel.getComment(), COMMENT);

		Assert.assertNotNull(wishlist2EntryModel.getAddedDate());
		Assert.assertEquals(wishlist2EntryModel.getAddedDate(), ADDED_DATE);
	}

	@Test
	public void testGetWishlistByUidForCurrentUser()
	{
		wishlist2Model = defaultAmwayApacWishlistService.getWishlistByUidForCurrentUser(WISHLIST_UID);
		Assert.assertNotNull(wishlist2Model);

		Assert.assertNotNull(wishlist2Model.getUid());
		Assert.assertEquals(wishlist2Model.getUid(), WISHLIST_UID);

		Assert.assertNotNull(wishlist2Model.getUser());
		Assert.assertEquals(wishlist2Model.getUser().getUid(), CUSTOMER_UID);

		Assert.assertNotNull(wishlist2Model.getEntries());
		Assert.assertEquals("Number of wishlist entries", WISHLIST_ENTRIES, wishlist2Model.getEntries().size());

	}

	/**
	 * Test for method {@link DefaultAmwayApacWishlistService#getWishlistByUidForCurrentUser}. Testing with null Uid.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullUid()
	{
		defaultAmwayApacWishlistService.getWishlistByUidForCurrentUser(null);
	}

	@Test
	public void testDeleteWishlist()
	{
		Assert.assertEquals(Boolean.TRUE, Boolean.valueOf(defaultAmwayApacWishlistService.deleteWishlist(WISHLIST_UID)));
	}

	/**
	 * Test for method {@link DefaultAmwayApacWishlistService#deleteWishlist(String)}. Testing with null Uid.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNullWishlistUid()
	{
		defaultAmwayApacWishlistService.deleteWishlist(null);
	}

}
