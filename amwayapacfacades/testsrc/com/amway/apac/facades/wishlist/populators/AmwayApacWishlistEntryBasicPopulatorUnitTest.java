/**
 *
 */
package com.amway.apac.facades.wishlist.populators;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.amway.facades.product.data.WishlistEntryData;


/**
 * Unit test for {@link AmwayApacWishlistEntryBasicPopulator}
 *
 * @author avnishalaugh
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class AmwayApacWishlistEntryBasicPopulatorUnitTest
{
	/**
	 * Product name string constant
	 */
	private static final String PRODUCT_NAME = "productName";

	/**
	 * Product code string constant
	 */
	private static final String PRODUCT_CODE = "productCode";

	/**
	 * Product code string constant
	 */
	private static final Date ADDED_DATE = new Date();

	/**
	 * The populator for test cases
	 */
	@InjectMocks
	private final AmwayApacWishlistEntryBasicPopulator amwayApacWishlistEntryBasicPopulator = new AmwayApacWishlistEntryBasicPopulator();

	/**
	 * Mock for the converter used by the populator
	 */
	@Mock
	private Converter<ProductModel, ProductData> productConverter;

	/**
	 * Product model mock
	 */
	@Mock
	private ProductModel productModel;

	/**
	 * WishlistEntry model mock
	 */
	@Mock
	private Wishlist2EntryModel wishlistEntryModel;

	/**
	 * Product data mock
	 */
	@Mock
	private ProductData productData;

	/**
	 * WishlistEntry Data mock
	 */
	@Mock
	private WishlistEntryData wishlistEntryData;

	@Before
	public void prepare()
	{
		//MockitoAnnotations.initMocks(this);

		productModel = Mockito.mock(ProductModel.class);

		wishlistEntryModel = Mockito.mock(Wishlist2EntryModel.class);
		BDDMockito.when(wishlistEntryModel.getAddedDate()).thenReturn(ADDED_DATE);
		BDDMockito.when(wishlistEntryModel.getProduct()).thenReturn(productModel);

		productData = new ProductData();
		productData.setCode(PRODUCT_CODE);
		productData.setName(PRODUCT_NAME);

		wishlistEntryData = new WishlistEntryData();

		BDDMockito.when(productConverter.convert(productModel)).thenReturn(productData);

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
