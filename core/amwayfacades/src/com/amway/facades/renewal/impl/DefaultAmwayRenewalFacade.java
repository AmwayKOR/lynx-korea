package com.amway.facades.renewal.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.customer.service.AmwayCustomerAccountService;
import com.amway.core.exceptions.AmwayServiceException;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.renewal.service.AmwayRenewalService;
import com.amway.core.user.services.AmwayUserService;
import com.amway.facades.renewal.AmwayRenewalFacade;
import com.amway.facades.renewal.data.AmwayAutoRenewalRequestData;
import com.amway.facades.renewal.data.AmwayAutoRenewalResponseData;
import com.amway.facades.renewal.data.AmwayRenewalAddressData;

@AmwayBean(ext="amwayfacades",docs={"https://jira.amway.com:8444/display/HC/Auto-Renewal+Order",
												"https://jira.amway.com:8444/display/HC/Auto-Renewal+OCC+Endpoint"})
public class DefaultAmwayRenewalFacade implements AmwayRenewalFacade
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayRenewalFacade.class);

	private static final String FAILURE = "Failure";
	private static final String SUCCESS = "Success";
	private static final String PAYMENT_NOT_FOUND_EXCEPTION = "Payment info not found";
	private static final String AUTORENEWAL_ORDER_PLACEMENT_EXCCEPTION = "Error occurred during creating and placing order for autorenewal request";

	private CartService cartService;
	private AmwayRenewalService amwayRenewalService;
	private AmwayUserService amwayUserService;
	private AmwayCustomerAccountService amwayCustomerAccountService;
	private AmwayAccountService amwayAccountService;
	private Converter<AmwayRenewalAddressData, AddressModel> amwayReverseRenewalAddressConverter;
	private CommerceCheckoutService commerceCheckoutService;

	@Override
	public AmwayAutoRenewalResponseData placeAutoRenewalOrder(String aboNum, String partyId,
			AmwayAutoRenewalRequestData autoRenewalRequest)
	{
		LOGGER.info("Start creating an registration auto renewal order.");

		final AmwayAutoRenewalResponseData response = new AmwayAutoRenewalResponseData();
		response.setStatus(FAILURE);

		try
		{
			prepareAutoRenewalCart(autoRenewalRequest, partyId, aboNum);
			CartModel cart = cartService.getSessionCart();
			String orderCode = amwayRenewalService.placeRenewal(cart, SalesApplication.AUTO_RENEW);
			if (Objects.nonNull(orderCode))
			{
				response.setStatus(SUCCESS);
				response.setOrderNumber(orderCode);
			}
		}
		catch (Exception exception)
		{
			LOGGER.error(AUTORENEWAL_ORDER_PLACEMENT_EXCCEPTION, exception);
		}

		return response;
	}

	private void prepareAutoRenewalCart(AmwayAutoRenewalRequestData autoRenewalRequest, String partyId, String aboNum)
	{
		CustomerModel user = amwayUserService.getCustomerByPartyId(partyId);
		AmwayAccountModel account = amwayAccountService.findAccount(aboNum);
		AddressModel address = createDeliveryAddressFromRenewalRequest(user, autoRenewalRequest.getAddress());

		prepareDefaultSessionConfig(user);

		CartModel cart = cartService.getSessionCart();
		cart.setDeliveryAddress(address);
		cart.setAccount(account);

		addServiceFeesToAutoRenewalCart(account);
		cartService.setSessionCart(cart);

		applyRenewalFeePriceAdjustments(cart, autoRenewalRequest.getRegistrationDate());
		setCartPaymentDetails(user, cart, autoRenewalRequest.getPaymentAlias(), SalesApplication.AUTO_RENEW);
	}

	protected void prepareDefaultSessionConfig(UserModel user)
	{
		amwayUserService.setCurrentUser(user);
		//Affiliates may want to set default warehouse or other session config info here
	}

	/**
	 * Adds the renewal service fee to the autorenewal cart. This implementation is left empty for now. It is intended to be
	 * overridden by regional implementations.
	 *
	 * @param account
	 *           ABO account model
	 */
	@Override
	public void addServiceFeesToAutoRenewalCart(AmwayAccountModel account)
	{
		// Intentionally empty
	}

	private boolean setCartPaymentDetails(final CustomerModel user, final CartModel cart, final String paymentAlias, final SalesApplication salesApp)
	{
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		final CreditCardPaymentInfoModel ccPaymentInfo = amwayCustomerAccountService
				.findCreditCardPaymentInfoByCustomerAndCode(user, paymentAlias)
				.orElseThrow(() -> new AmwayServiceException(PAYMENT_NOT_FOUND_EXCEPTION));

		parameter.setEnableHooks(true);
		parameter.setCart(cart);
		parameter.setPaymentInfo(ccPaymentInfo);
		parameter.setSalesApplication(salesApp);

		return commerceCheckoutService.setPaymentInfo(parameter);
	}

	/**
	 * Creates a hybris address from the address provided in the autorenewal request from MAGIC.
	 *
	 * @param user
	 *           ABO user model
	 * @param autoRenewalAddress
	 *           the address data provided by the autorenewal request.
	 * @return AddressModel representation of address
	 */
	private AddressModel createDeliveryAddressFromRenewalRequest(UserModel user, AmwayRenewalAddressData autoRenewalAddress)
	{
		AddressModel result = amwayReverseRenewalAddressConverter.convert(autoRenewalAddress);
		result.setOwner(user);
		return result;
	}

	/**
	 * Applies any adjustments to the renewal fee based upon the ABO's registration date. This might be for prorations,
	 * grace periods, etc
	 * <p>
	 * The implementation is empty for Core, and is intended to be overridden for regional implementations.
	 *
	 * @param cart
	 *           cart used to build and place the autorenewal order
	 * @param registrationDate
	 *           the date the ABO was registered.
	 */
	@Override
	public void applyRenewalFeePriceAdjustments(CartModel cart, String registrationDate)
	{
		// Intentionally empty
	}

	public CartService getCartService()
	{
		return cartService;
	}

	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}

	public AmwayRenewalService getAmwayRenewalService()
	{
		return amwayRenewalService;
	}

	public void setAmwayRenewalService(AmwayRenewalService amwayRenewalService)
	{
		this.amwayRenewalService = amwayRenewalService;
	}

	public AmwayUserService getAmwayUserService()
	{
		return amwayUserService;
	}

	public void setAmwayUserService(AmwayUserService amwayUserService)
	{
		this.amwayUserService = amwayUserService;
	}

	public AmwayCustomerAccountService getAmwayCustomerAccountService()
	{
		return amwayCustomerAccountService;
	}

	public void setAmwayCustomerAccountService(AmwayCustomerAccountService amwayCustomerAccountService)
	{
		this.amwayCustomerAccountService = amwayCustomerAccountService;
	}

	public AmwayAccountService getAmwayAccountService()
	{
		return amwayAccountService;
	}

	public void setAmwayAccountService(AmwayAccountService amwayAccountService)
	{
		this.amwayAccountService = amwayAccountService;
	}

	public Converter<AmwayRenewalAddressData, AddressModel> getAmwayReverseRenewalAddressConverter()
	{
		return amwayReverseRenewalAddressConverter;
	}

	public void setAmwayReverseRenewalAddressConverter(
			Converter<AmwayRenewalAddressData, AddressModel> amwayReverseRenewalAddressConverter)
	{
		this.amwayReverseRenewalAddressConverter = amwayReverseRenewalAddressConverter;
	}

	public CommerceCheckoutService getCommerceCheckoutService()
	{
		return commerceCheckoutService;
	}

	public void setCommerceCheckoutService(CommerceCheckoutService commerceCheckoutService)
	{
		this.commerceCheckoutService = commerceCheckoutService;
	}
}
