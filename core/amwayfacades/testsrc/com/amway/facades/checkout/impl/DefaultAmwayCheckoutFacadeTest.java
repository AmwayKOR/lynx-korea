package com.amway.facades.checkout.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.data.GetBalanceResponseData;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.checkout.services.AmwayCommerceCheckoutService;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.facades.order.data.PaymentModeData;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.CardType;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;


@UnitTest
public class DefaultAmwayCheckoutFacadeTest
{

	@InjectMocks
	DefaultAmwayCheckoutFacade defaultAmwayCheckoutFacade = new DefaultAmwayCheckoutFacade();

	@Mock
	private Converter<PaymentModeModel, PaymentModeData> paymentModeConverter;
	@Mock
	private AmwayPaymentModeService paymentModeService;
	@Mock
	private AmwayCommerceCheckoutService amwayCommerceCheckoutService;
	@Mock
	private PaymentService paymentService;
	@Mock
	private ModelService modelService;
	@Mock
	private CartFacade cartFacade;
	@Mock
	private AbstractPopulatingConverter<AddressModel, AddressData> addressConverter;

	@Mock
	private CartService cartService;
	private CommerceCheckoutParameter commerceCheckoutParameter;
	CartModel cartModel;

	private CartData cartData;

	private CustomerModel customerModel;

	private AddressData addressData;

	private AddressModel addressModel, addressModel1;

	@Mock
	private AmwayAccountBalanceService amwayAccountBalanceService;

	@Mock
	private DeliveryService deliveryService;

	@Mock
	private CheckoutCustomerStrategy checkoutCustomerStrategy;

	Map<String, List<AmwayPaymentModeData>> paymentModeMap;

	List<AmwayPaymentModeData> paymentModes;

	AmwayPaymentModeData amwayPaymentModeData1, amwayPaymentModeData2, amwayPaymentModeData3;



	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		defaultAmwayCheckoutFacade.setAddressConverter(addressConverter);
		defaultAmwayCheckoutFacade.setCartFacade(cartFacade);
		defaultAmwayCheckoutFacade.setCartService(cartService);
		defaultAmwayCheckoutFacade.setCommerceCheckoutService(amwayCommerceCheckoutService);
		defaultAmwayCheckoutFacade.setPaymentModeService(paymentModeService);
		defaultAmwayCheckoutFacade.setDeliveryService(deliveryService);
		defaultAmwayCheckoutFacade.setCheckoutCustomerStrategy(checkoutCustomerStrategy);
		defaultAmwayCheckoutFacade.setModelService(modelService);
		defaultAmwayCheckoutFacade.setCartFacade(cartFacade);
		defaultAmwayCheckoutFacade.setAmwayAccountBalanceService(amwayAccountBalanceService);

		cartData = new CartData();
		cartModel = new CartModel();
		addressModel = Mockito.mock(AddressModel.class);
		addressData = new AddressData();
		commerceCheckoutParameter = new CommerceCheckoutParameter();
		commerceCheckoutParameter.setEnableHooks(true);
		final CountryData countryData = new CountryData();
		countryData.setIsocode("PL");
		addressData.setContactPointName("abc");
		addressData.setId("123456");
		addressData.setTown("Koluszki");
		addressData.setCountry(countryData);

		customerModel = new CustomerModel();
		customerModel.setDefaultShipmentAddress(addressModel);
		cartModel.setUser(customerModel);

		cartModel.setDeliveryAddress(addressModel);
		cartData.setDeliveryAddress(addressData);

		final List<CardType> cardTypes = new ArrayList<CardType>();
		final CardType cardType = new CardType(CreditCardType.MASTER.getCode(), CreditCardType.MASTER,
				CreditCardType.MASTER.getCode());
		cardTypes.add(cardType);

		paymentModes = new ArrayList<>();

		amwayPaymentModeData1 = Mockito.mock(AmwayPaymentModeData.class);
		amwayPaymentModeData2 = Mockito.mock(AmwayPaymentModeData.class);
		amwayPaymentModeData3 = Mockito.mock(AmwayPaymentModeData.class);
		paymentModes.add(amwayPaymentModeData1);
		paymentModes.add(amwayPaymentModeData2);
		paymentModes.add(amwayPaymentModeData3);

		paymentModeMap = new HashMap<>();
		paymentModeMap.put("1", paymentModes);

		given(modelService.create(Mockito.any(Class.class))).willReturn(new AddressModel());

		given(checkoutCustomerStrategy.getCurrentUserForCheckout()).willReturn(customerModel);
		given(addressModel.getPk()).willReturn(PK.parse("123456"));
		given(Boolean.valueOf(cartFacade.hasSessionCart())).willReturn(Boolean.TRUE);
		given(addressConverter.convert(addressModel)).willReturn(addressData);

		given(modelService.clone(addressModel)).willReturn(new AddressModel());

	}

	@Test
	public void testSetDeliveryAddressForAppWithCartAsNull()
	{
		AddressData address = new AddressData();
		Assert.assertFalse(defaultAmwayCheckoutFacade.setDeliveryAddressForApp(address));

	}

	@Test
	public void testSetDeliveryAddressForApp()
	{
		given(cartService.getSessionCart()).willReturn(cartModel);
		given(deliveryService.getSupportedDeliveryAddressesForOrder(cartModel, false)).willReturn(Arrays.asList(addressModel));
		Assert.assertTrue(defaultAmwayCheckoutFacade.setDeliveryAddressForApp(addressData));

	}

	
	@Test
	public void testNullGetSupportedPaymentModes()
	{
		CartModel cartModel = new CartModel();
		AmwayProfileResponseData profileData = new AmwayProfileResponseData();
		GetBalanceResponseData balanceResponseData = new GetBalanceResponseData();
		given(cartService.getSessionCart()).willReturn(cartModel);
		given(paymentModeService.getSupportedPaymentModesCombination(cartModel, profileData, true)).willReturn(paymentModeMap);
		given(amwayAccountBalanceService.getAccountBalance(cartModel)).willReturn(balanceResponseData);
		Map<String, List<AmwayPaymentModeData>> paymentModeMap = defaultAmwayCheckoutFacade.getSupportedPaymentModesCombinations();
		Assert.assertNotNull(paymentModeMap);
	}

}
