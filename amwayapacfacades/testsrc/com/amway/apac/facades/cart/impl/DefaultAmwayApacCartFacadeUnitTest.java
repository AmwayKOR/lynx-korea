package com.amway.apac.facades.cart.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;


/**
 *
 * @author Shubham Goyal
 */

@UnitTest
public class DefaultAmwayApacCartFacadeUnitTest
{

	@InjectMocks
	private final DefaultAmwayApacCartFacade defaultAmwayApacCartFacade = new DefaultAmwayApacCartFacade();

	@Mock
	private CartService cartService;

	@Mock
	private Converter<CartModel, CartData> cartConverter;

	@Mock
	private Converter<CartModel, CartData> miniCartConverter;

	private CartData cartData;

	private CartModel cartModel;


	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		cartData = new CartData();
		cartModel = new CartModel();
		final List<OrderEntryData> entries = new ArrayList<>();

		entries.add(getOrderEntryDataForSorting("AAA", 70));
		entries.add(getOrderEntryDataForSorting("BBB", 20));
		entries.add(getOrderEntryDataForSorting("CCC", 50));

		cartData.setEntries(entries);
	}

	@Test
	public void testGetSessionCartWithSortBySortCodeForAllGoodCases()
	{
		given(Boolean.valueOf(cartService.hasSessionCart())).willReturn(Boolean.TRUE);
		given(cartService.getSessionCart()).willReturn(cartModel);
		given(cartConverter.convert(cartModel)).willReturn(cartData);
		Assert.assertEquals("CCC", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.LAST_ITEM_ADDED).getEntries().get(0).getProduct().getName());
		Assert.assertEquals("AAA", defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.NAME_ASCENDING)
				.getEntries().get(0).getProduct().getName());
		Assert.assertEquals("CCC", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.NAME_DESCEDNING).getEntries().get(0).getProduct().getName());
		Assert.assertEquals("BBB", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.PRICE_ASCENDING).getEntries().get(0).getProduct().getName());
		Assert.assertEquals("AAA", defaultAmwayApacCartFacade
				.getSessionCartWithSortBySortCode(AmwayApacCartSortCode.PRICE_DESCEDNING).getEntries().get(0).getProduct().getName());
	}


	@Test
	public void testGetSessionCartWithSortBySortCodeForNullSortCode()
	{
		given(Boolean.valueOf(cartService.hasSessionCart())).willReturn(Boolean.TRUE);
		given(cartService.getSessionCart()).willReturn(cartModel);
		given(cartConverter.convert(cartModel)).willReturn(cartData);
		Assert.assertEquals("CCC",
				defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(null).getEntries().get(0).getProduct().getName());
	}

	@Test
	public void testGetSessionCartWithSortBySortCodeForEmptyCart()
	{
		given(Boolean.valueOf(cartService.hasSessionCart())).willReturn(Boolean.TRUE);
		cartData.setEntries(new ArrayList<OrderEntryData>());
		given(cartService.getSessionCart()).willReturn(cartModel);
		given(cartConverter.convert(cartModel)).willReturn(cartData);
		Assert.assertEquals(0, defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(null).getEntries().size());
	}

	@Test
	public void testGetSessionCartWithSortBySortCodeForNoSessionCart()
	{
		given(Boolean.valueOf(cartService.hasSessionCart())).willReturn(Boolean.FALSE);
		given(miniCartConverter.convert(null)).willReturn(new CartData());
		Assert.assertNull(defaultAmwayApacCartFacade.getSessionCartWithSortBySortCode(null).getEntries());
	}


	private OrderEntryData getOrderEntryDataForSorting(final String productName, final double entryTotalPrice)
	{
		final OrderEntryData entry = new OrderEntryData();
		final ProductData product = new ProductData();
		product.setName(productName);
		final PriceData price = new PriceData();
		price.setValue(BigDecimal.valueOf(entryTotalPrice));
		entry.setTotalPrice(price);
		entry.setProduct(product);

		return entry;
	}

}
