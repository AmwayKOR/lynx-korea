package com.amway.apac.facades.wishlist.populators;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.facades.product.data.WishlistData;


/**
 * Integration test for {@link AmwayApacWishlistBasicPopulator}
 *
 * @author Parvesh Goyal
 */
@IntegrationTest
public class AmwayApacWishlistBasicPopulatorIntegrationTest extends ServicelayerTest
{
	/**
	 * Customer uid string constant
	 */
	private static final String CUSTOMER_UID = "customerUid";

	/**
	 * Customer name string constant
	 */
	private static final String CUSTOMER_NAME = "customerName";

	/**
	 * Wishlist uid string constant
	 */
	private static final String WISHLIST_UID = "wishlistUid";

	/**
	 * Wishlist description string constant
	 */
	private static final String WISHLIST_DESCRIPTION = "wishlistDescription";

	/**
	 * Wishlist name string constant
	 */
	private static final String WISHLIST_NAME = "wishlistName";

	/**
	 * The populator for test cases
	 */
	@Resource(name = "amwayApacWishlistBasicPopulator")
	private AmwayApacWishlistBasicPopulator amwayApacWishlistBasicPopulator;

	@Resource(name = "modelService")
	private ModelService modelService;

	/**
	 * Wishlist model mock
	 */
	private Wishlist2Model wishlistModel;

	/**
	 * Wishlist Data mock
	 */
	private WishlistData wishlistData;

	/**
	 * Prepares the data for the test cases
	 */
	@Before
	public void prepare()
	{
		final UserModel userModel = modelService.create(UserModel.class);
		userModel.setUid(CUSTOMER_UID);
		userModel.setName(CUSTOMER_NAME);

		wishlistModel = modelService.create(Wishlist2Model.class);
		wishlistModel.setUid(WISHLIST_UID);
		wishlistModel.setName(WISHLIST_NAME);
		wishlistModel.setDescription(WISHLIST_DESCRIPTION);
		wishlistModel.setDefault(Boolean.TRUE);
		wishlistModel.setFavorite(Boolean.TRUE);
		wishlistModel.setUser(userModel);

		wishlistData = new WishlistData();
	}


	/**
	 * Test for method {@link AmwayApacWishlistBasicPopulator#populate(Wishlist2Model, WishlistData)}. Testing with null
	 * source
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullSource()
	{
		amwayApacWishlistBasicPopulator.populate(null, wishlistData);
	}

	/**
	 * Test for method {@link AmwayApacWishlistBasicPopulator#populate(Wishlist2Model, WishlistData)}. Testing with null
	 * target
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullTarget()
	{
		amwayApacWishlistBasicPopulator.populate(wishlistModel, null);
	}

	/**
	 * Test for method {@link AmwayApacWishlistBasicPopulator#populate(Wishlist2Model, WishlistData)}. Testing with all
	 * the values present.
	 */
	@Test
	public void testPopulate()
	{

		amwayApacWishlistBasicPopulator.populate(wishlistModel, wishlistData);

		Assert.assertNotNull(wishlistData.getUid());
		Assert.assertEquals(wishlistData.getUid(), WISHLIST_UID);

		Assert.assertNotNull(wishlistData.getName());
		Assert.assertEquals(wishlistData.getName(), WISHLIST_NAME);

		Assert.assertNotNull(wishlistData.getDescription());
		Assert.assertEquals(wishlistData.getDescription(), WISHLIST_DESCRIPTION);

		Assert.assertNotNull(wishlistData.getIsDefault());
		Assert.assertEquals(wishlistData.getIsDefault().toLowerCase(), Boolean.TRUE.toString().toLowerCase());

		Assert.assertTrue(wishlistData.isIsFavorite());

		Assert.assertNotNull(wishlistData.getUser());

		Assert.assertNotNull(wishlistData.getUser().getName());
		Assert.assertEquals(wishlistData.getUser().getName(), CUSTOMER_NAME);

		Assert.assertNotNull(wishlistData.getUser().getUid());
		Assert.assertEquals(wishlistData.getUser().getUid(), CUSTOMER_UID);

	}

}
