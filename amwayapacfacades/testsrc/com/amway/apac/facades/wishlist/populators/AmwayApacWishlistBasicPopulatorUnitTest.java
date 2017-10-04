package com.amway.apac.facades.wishlist.populators;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.facades.product.data.WishlistData;


/**
 * Unit test for {@link AmwayApacWishlistBasicPopulator}
 *
 * @author Parvesh Goyal
 */
@UnitTest
public class AmwayApacWishlistBasicPopulatorUnitTest
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
	@InjectMocks
	private final AmwayApacWishlistBasicPopulator amwayApacWishlistBasicPopulator = new AmwayApacWishlistBasicPopulator();

	/**
	 * Mock for the converter used by the populator
	 */
	@Mock
	private Converter<UserModel, CustomerData> amwayApacUserBasicConverter;

	/**
	 * User model mock
	 */
	private UserModel userModel;

	/**
	 * Wishlist model mock
	 */
	private Wishlist2Model wishlistModel;

	/**
	 * Customer data mock
	 */
	private CustomerData customerData;

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
		MockitoAnnotations.initMocks(this);

		userModel = Mockito.mock(UserModel.class);

		wishlistModel = Mockito.mock(Wishlist2Model.class);
		BDDMockito.when(wishlistModel.getUid()).thenReturn(WISHLIST_UID);
		BDDMockito.when(wishlistModel.getName()).thenReturn(WISHLIST_NAME);
		BDDMockito.when(wishlistModel.getDescription()).thenReturn(WISHLIST_DESCRIPTION);
		BDDMockito.when(wishlistModel.getDefault()).thenReturn(Boolean.TRUE);
		BDDMockito.when(wishlistModel.getFavorite()).thenReturn(Boolean.TRUE);
		BDDMockito.when(wishlistModel.getUser()).thenReturn(userModel);

		customerData = new CustomerData();
		customerData.setUid(CUSTOMER_UID);
		customerData.setName(CUSTOMER_NAME);

		wishlistData = new WishlistData();

		BDDMockito.when(amwayApacUserBasicConverter.convert(userModel)).thenReturn(customerData);

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
		Assert.assertEquals(wishlistData.getIsDefault(), Boolean.TRUE.toString());

		Assert.assertTrue(wishlistData.isIsFavorite());

		Assert.assertNotNull(wishlistData.getUser());

		Assert.assertNotNull(wishlistData.getUser().getName());
		Assert.assertEquals(wishlistData.getUser().getName(), CUSTOMER_NAME);

		Assert.assertNotNull(wishlistData.getUser().getUid());
		Assert.assertEquals(wishlistData.getUser().getUid(), CUSTOMER_UID);

	}

}
