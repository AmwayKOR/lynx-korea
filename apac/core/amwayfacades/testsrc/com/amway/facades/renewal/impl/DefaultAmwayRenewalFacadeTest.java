package com.amway.facades.renewal.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.customer.service.AmwayCustomerAccountService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.renewal.service.AmwayRenewalService;
import com.amway.core.user.services.AmwayUserService;
import com.amway.facades.renewal.data.AmwayAutoRenewalRequestData;
import com.amway.facades.renewal.data.AmwayAutoRenewalResponseData;
import com.amway.facades.renewal.data.AmwayRenewalAddressData;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultAmwayRenewalFacadeTest
{
	public static final String ABO_NUMBER = "123456";
	public static final String PARTY_ID = "256998";
	private static final String FAILURE = "Failure";
	private static final String SUCCESS = "Success";
	private static final String PAYMENT_ALIAS = "105806485aa010abf1c6";
	private static final String ORDER_CODE = "123456";

	@Mock
	private AmwayUserService amwayUserService;
	@Mock
	private Converter<AmwayRenewalAddressData, AddressModel> amwayReverseRenewalAddressConverter;
	@Mock
	private CartService cartService;
	@Mock
	private AmwayCustomerAccountService amwayCustomerAccountService;
	@Mock
	private AmwayRenewalService amwayRenewalService;
	@Mock
	private AmwayAccountService amwayAccountService;
	@Mock
	private CommerceCheckoutService commerceCheckoutService;

	@Mock
	private AmwayAutoRenewalRequestData autoRenewalRequestData;
	@Mock
	private CustomerModel customer;
	@Mock
	private AmwayRenewalAddressData renewalAddressData;
	@Mock
	private AddressModel addressModel;
	@Mock
	private CreditCardPaymentInfoModel creditCardPaymentInfoModel;
	@Mock
	private AmwayAccountModel account;
	@Mock
	private CartModel cartModel;

	@InjectMocks
	@Spy
	private DefaultAmwayRenewalFacade testedInstance;

	@Before
	public void setUp() throws Exception
	{
		when(amwayUserService.getCustomerByPartyId(PARTY_ID)).thenReturn(customer);
		when(amwayAccountService.findAccount(ABO_NUMBER)).thenReturn(account);
		when(autoRenewalRequestData.getAddress()).thenReturn(renewalAddressData);
		when(amwayReverseRenewalAddressConverter.convert(renewalAddressData)).thenReturn(addressModel);
		when(cartService.getSessionCart()).thenReturn(cartModel);
		when(autoRenewalRequestData.getPaymentAlias()).thenReturn(PAYMENT_ALIAS);
		when(amwayCustomerAccountService.findCreditCardPaymentInfoByCustomerAndCode(customer, PAYMENT_ALIAS))
				.thenReturn(Optional.of(creditCardPaymentInfoModel));
		when(amwayRenewalService.placeRenewal(cartModel, SalesApplication.AUTO_RENEW)).thenReturn(ORDER_CODE);
		when(commerceCheckoutService.setPaymentInfo(any())).thenReturn(true);
	}

	@Test
	public void shouldPlaceAutorenewalOrderWithSuccess()
	{
		//when
		AmwayAutoRenewalResponseData result = testedInstance.placeAutoRenewalOrder(ABO_NUMBER, PARTY_ID, autoRenewalRequestData);

		//then
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getStatus(), SUCCESS);
		Assert.assertEquals(result.getOrderNumber(), ORDER_CODE);
	}

	@Test
	public void shouldReturnFailure() throws Exception
	{
		//given
		when(amwayRenewalService.placeRenewal(cartModel, SalesApplication.AUTO_RENEW)).thenReturn(null);

		//when
		AmwayAutoRenewalResponseData result = testedInstance.placeAutoRenewalOrder(ABO_NUMBER, PARTY_ID, autoRenewalRequestData);

		//then
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getStatus(), FAILURE);
		Assert.assertEquals(result.getOrderNumber(), null);
	}


}
