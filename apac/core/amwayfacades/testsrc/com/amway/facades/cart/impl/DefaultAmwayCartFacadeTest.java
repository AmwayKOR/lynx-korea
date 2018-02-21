package com.amway.facades.cart.impl;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hybris.platform.commercefacades.order.data.CartRestorationData;
import de.hybris.platform.commercefacades.order.impl.DefaultCartFacade;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartRestoration;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.enums.AmwayCartType;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;


@UnitTest
public class DefaultAmwayCartFacadeTest
{
	@InjectMocks
	private DefaultAmwayCartFacade defaultAmwayCartFacade = new DefaultAmwayCartFacade();

	@Mock
	private CartService cartService;

	@Mock
	private CommerceCartService commerceCartService;

	@Mock
	private BaseSiteService baseSiteService;

	@Mock
	private UserService userService;

	@Mock
	private Converter<CartModel, CartData> cartConverter;

	@Mock
	private Converter<CommerceCartRestoration, CartRestorationData> cartRestorationConverter;

	private CustomerModel customer;

	private CMSSiteModel site;

	private CartModel cart1;

	private CartData cartData;

	private CartRestorationData restoreData;

	private CommerceCartRestoration modification;

	private List<OrderEntryData> orderEntries = new ArrayList<>();

	private CommerceCartParameter parameter;

	private CommerceCartRestoration cartResData;



	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		customer = new CustomerModel();
		site = Mockito.mock(CMSSiteModel.class);
		cart1 = Mockito.mock(CartModel.class);
		parameter = Mockito.mock(CommerceCartParameter.class);
		cartResData = Mockito.mock(CommerceCartRestoration.class);
		modification = Mockito.mock(CommerceCartRestoration.class);
		restoreData = Mockito.mock(CartRestorationData.class);

		given(cart1.getType()).willReturn(AmwayCartType.WEBRECURRING);

		defaultAmwayCartFacade.setCartRestorationConverter(cartRestorationConverter);

		cartData = Mockito.mock(CartData.class);

		parameter.setEnableHooks(true);
		parameter.setCart(cart1);

		given(baseSiteService.getCurrentBaseSite()).willReturn(site);
		given(userService.getCurrentUser()).willReturn(customer);
		given(cartData.getEntries()).willReturn(orderEntries);
		given(cartService.hasSessionCart()).willReturn(Boolean.TRUE);
		given(cartService.getSessionCart()).willReturn(cart1);
		given(cartConverter.convert(cart1)).willReturn(cartData);
		given(cartRestorationConverter.convert(modification)).willReturn(restoreData);

		given(commerceCartService.getCartsForSiteAndUser(site, customer)).willReturn(Arrays.asList(cart1));
		given(commerceCartService.restoreCart(parameter)).willReturn(cartResData);
	}

	@Test
	public void testRestoreSavedCartForWebCart() throws CommerceCartRestorationException
	{
		Assert.assertNull(defaultAmwayCartFacade.restoreSavedCart(""));
	}
}
