package com.amway.apac.facades.wishlist.populators;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;

import java.util.Collections;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.facades.product.data.WishlistEntryData;


/**
 * Integration test for {@link AmwayApacWishlistBasicPopulator}
 *
 * @author avnishalaugh
 *
 */
@IntegrationTest
public class AmwayApacWishlistEntryBasicPopulatorIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private UserService userService;
	/**
	 * Product name string constant
	 */
	private static final String PRODUCT_NAME = "testEN";

	/**
	 * Product code string constant
	 */
	private static final String PRODUCT_CODE = "HW1210-3422";

	/**
	 * Product code string constant
	 */
	private static final Date ADDED_DATE = new Date();

	/**
	 * The populator for test cases
	 */
	@Resource(name = "amwayApacWishlistEntryBasicPopulator")
	private AmwayApacWishlistEntryBasicPopulator amwayApacWishlistEntryBasicPopulator;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "modelService")
	private ModelService modelService;

	/**
	 * WishlistEntry model mock
	 */
	private Wishlist2EntryModel wishlistEntryModel;

	/**
	 * WishlistEntry Data mock
	 */
	private WishlistEntryData wishlistEntryData;

	@Before
	public void prepare() throws ImpExException
	{
		wishlistEntryData = new WishlistEntryData();

		importCsv("/amwayapacfacades/test/testCommerceProduct.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));

		final ProductModel product = productService.getProductForCode(PRODUCT_CODE);
		wishlistEntryModel = modelService.create(Wishlist2EntryModel.class);
		wishlistEntryModel.setProduct(product);
		wishlistEntryModel.setAddedDate(ADDED_DATE);

	}

	/**
	 * Test for method {@link AmwayApacWishlistEntryBasicPopulator#populate(Wishlist2EntryModel, WishlistEntryData)}.
	 * Testing with null source
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullSource()
	{
		amwayApacWishlistEntryBasicPopulator.populate(null, wishlistEntryData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullTarget()
	{
		amwayApacWishlistEntryBasicPopulator.populate(wishlistEntryModel, null);
	}

	@Test
	public void testPopulate()
	{

		amwayApacWishlistEntryBasicPopulator.populate(wishlistEntryModel, wishlistEntryData);

		Assert.assertNotNull(wishlistEntryData.getAddedDate());
		Assert.assertEquals(wishlistEntryData.getAddedDate(), ADDED_DATE);

		Assert.assertNotNull(wishlistEntryData.getProduct());

		Assert.assertNotNull(wishlistEntryData.getProduct().getName());
		Assert.assertEquals(wishlistEntryData.getProduct().getName(), PRODUCT_NAME);

		Assert.assertNotNull(wishlistEntryData.getProduct().getCode());
		Assert.assertEquals(wishlistEntryData.getProduct().getCode(), PRODUCT_CODE);

	}

}
