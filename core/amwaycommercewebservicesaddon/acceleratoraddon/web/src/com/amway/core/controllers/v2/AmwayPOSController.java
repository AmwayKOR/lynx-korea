/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.core.v2.controllers;


import com.amway.core.exceptions.*;
import de.hybris.platform.commercefacades.order.data.*;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commercewebservicescommons.dto.order.OrderHistoryListWsDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import com.amway.core.facades.customer.AmwayCustomerFacade;
import com.amway.core.model.AmwayBatchModel;
import com.amway.core.pos.service.AmwayPOSService;
import com.amway.facades.data.AmwayAccountDataList;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.commercewebservicescommons.dto.order.CartModificationListWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.CartWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.OrderWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.AmwayAccountWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.pos.AmwayBatchWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.pos.AmwayBatchListWsDTO;
import de.hybris.platform.commercewebservicescommons.errors.exceptions.CartException;
import de.hybris.platform.commercewebservicescommons.errors.exceptions.ProductLowStockException;
import de.hybris.platform.commercewebservicescommons.errors.exceptions.StockSystemException;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import de.hybris.platform.webservicescommons.mapping.FieldSetLevelHelper;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.ordercancel.OrderCancelException;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.cart.data.CartEntryInfoData;
import com.amway.core.cart.data.CartInfoData;
import com.amway.core.cart.data.PlaceOrderCartInfoData;
import com.amway.core.commerceservices.payment.service.PosPaymentService;
import com.amway.core.commercewebservices.dto.cart.AmwayCartWsDTO;
import com.amway.core.commercewebservices.dto.order.AmwayPaymentModeDataWsDTO;
import com.amway.core.commercewebservices.dto.order.AmwayPaymentModeWsDTO;
import com.amway.core.commercewebservices.dto.order.PaymentModeListWSDTO;
import com.amway.core.customer.service.AmwayCustomerAccountService;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.facades.order.AmwayOrderFacade;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.order.services.AmwayOrderProrationService;
import com.amway.core.stock.CommerceStockFacade;
import com.amway.core.validator.PointOfSaleValidator;
import com.amway.facades.checkout.AmwayCheckoutFacade;
import com.amway.lynx.dto.message.MessageDTO;
import com.amway.facades.dto.AmwayAccountDataListWsDTO;
import com.amway.core.v2.controller.BaseCommerceController;
import com.amway.core.validator.PaymentModeValidator;



/**
 *  All the POS interactions are a part of this controller except
 * for voucher.
 */
@Controller
@RequestMapping(value = "/{baseSiteId}")
public class AmwayPOSController extends BaseCommerceController
{

	private static final Logger LOG = Logger.getLogger(AmwayPOSController.class);

	private static final String DELIVERYMODE_PICKUP = "pickup-pos";

	//TODO avoid using following services, instead use facades
	@Resource(name = "commerceCartService")
	private CommerceCartService commerceCartService;
	@Resource(name = "cartService")
	private CartService cartService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	@Resource(name = "amwayAccountService")
	private AmwayAccountService amwayAccountService;
	@Resource(name = "posPaymentService")
	private PosPaymentService posPaymentService;
	@Resource(name = "amwayOrderProrationService")
	private AmwayOrderProrationService amwayOrderProrationService;

	@Resource(name = "amwayPOSService")
	private AmwayPOSService amwayPOSService;

	@Resource(name = "amwayCustomerFacade")
	private AmwayCustomerFacade amwayCustomerFacade;

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@Resource(name = "pointOfSaleValidator")
	private PointOfSaleValidator pointOfSaleValidator;

	@Resource(name = "commerceStockFacade")
	private CommerceStockFacade commerceStockFacade;

	@Resource(name = "defaultAmwayCheckoutFacade")
	private AmwayCheckoutFacade amwayCheckoutFacade;
	@Resource(name = "amwayCustomerAccountService")
	private AmwayCustomerAccountService amwayCustomerAccountService;
	@Resource(name = "amwayOrderFacade")
	private AmwayOrderFacade amwayOrderFacade;


	@Resource(name = "paymentModeValidator")
	private PaymentModeValidator paymentModeValidator;



	/**
	 * Returns the Account info, refreshes profile from DMS and also provides default customer associated with the account.
	 *
	 * @return Account info
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/account/{accountUid}/customer/{user_id}"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayAccountWsDTO loadAccountCustomer(@PathVariable final String accountUid, @PathVariable final String user_id,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		AmwayAccountWsDTO accountWsDTO = getDataMapper().map(amwayCustomerFacade.loadAccountCustomerForPOS(accountUid, user_id), AmwayAccountWsDTO.class, fields);
		return accountWsDTO;
	}

	/**
	 * Returns the Account list based on account name search.
	 *
	 * @return Account list by name
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/accounts/name"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayAccountDataListWsDTO lookupAccountsByName(@RequestParam  final String searchKey,
			@RequestParam(defaultValue = FieldSetLevelHelper.FULL_LEVEL) final String fields)
	{
		AmwayAccountDataList accounts = amwayCustomerFacade.lookupAccountsByName(searchKey);
		AmwayAccountDataListWsDTO accountsListWsDTO = getDataMapper().map(accounts, AmwayAccountDataListWsDTO.class, fields);
		return accountsListWsDTO;

	}

	/**
	 * Returns the Account list based on account id search.
	 *
	 * @return Account list by name
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/accounts/Uid/{searchKey}"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayAccountDataListWsDTO lookupAccountsByUid(@PathVariable final String searchKey,
			@RequestParam(defaultValue = FieldSetLevelHelper.FULL_LEVEL) final String fields)
	{
		AmwayAccountDataList accounts = amwayCustomerFacade.lookupAccountsById(searchKey);
		AmwayAccountDataListWsDTO accountsListWsDTO = getDataMapper().map(accounts, AmwayAccountDataListWsDTO.class, fields);
		return accountsListWsDTO;

	}


	/**
	 * Returns the Account list based on party name search.
	 *
	 * @return Account list order by party name
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/customers/name"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayAccountDataListWsDTO lookupAccountsCustomersByPartyName(@RequestParam  final String searchKey,
			@RequestParam(defaultValue = FieldSetLevelHelper.FULL_LEVEL) final String fields)
	{
		AmwayAccountDataList accounts = amwayCustomerFacade.lookupAccountsCustomersByPartyName(searchKey);
		AmwayAccountDataListWsDTO accountsListWsDTO = getDataMapper().map(accounts, AmwayAccountDataListWsDTO.class, fields);
		return accountsListWsDTO;

	}

	/**
	 * Returns the Account list based on party email search.
	 *
	 * @return Account list order by party name
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/customers/email"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayAccountDataListWsDTO lookupAccountsCustomersByEmail(@RequestParam final String searchKey,
			@RequestParam(defaultValue = FieldSetLevelHelper.FULL_LEVEL) final String fields)
	{
		AmwayAccountDataList accounts = amwayCustomerFacade.lookupAccountsCustomersByEmail(searchKey);
		AmwayAccountDataListWsDTO accountsListWsDTO = getDataMapper().map(accounts, AmwayAccountDataListWsDTO.class, fields);
		return accountsListWsDTO;

	}

	/**
	 * Returns the Account list based on party name search.
	 *
	 * @return Account list order by party id
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/customers/partyid/{searchKey}"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayAccountDataListWsDTO lookupAccountsCustomersByPartyId(@PathVariable final String searchKey,
			@RequestParam(defaultValue = FieldSetLevelHelper.FULL_LEVEL) final String fields)
	{
		AmwayAccountDataList accounts = amwayCustomerFacade.lookupAccountsCustomersByPartyId(searchKey);
		AmwayAccountDataListWsDTO accountsListWsDTO = getDataMapper().map(accounts, AmwayAccountDataListWsDTO.class, fields);
		return accountsListWsDTO;

	}

	/**
	 * Returns the Account list based on party name search.
	 *
	 * @return Account list order by party id
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/customers/Uid/{searchKey}"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayAccountDataListWsDTO lookupAccountsCustomersByUid(@PathVariable final String searchKey,
			@RequestParam(defaultValue = FieldSetLevelHelper.FULL_LEVEL) final String fields)
	{
		AmwayAccountDataList accounts = amwayCustomerFacade.lookupAccountsCustomersByUid(searchKey);
		AmwayAccountDataListWsDTO accountsListWsDTO = getDataMapper().map(accounts, AmwayAccountDataListWsDTO.class, fields);
		return accountsListWsDTO;

	}

	/**
	 * @param accountUid
	 * @param user_id
	 * @param fields
	 * @return the pos cart associated to the default customer for the Account.
	 * @throws CommerceCartModificationException
	 * @throws WebserviceValidationException
	 * @throws ProductLowStockException
	 * @throws StockSystemException
	 */
	//@Secured(
	//{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP","ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/account/{accountUid}/users/{user_id}/getcart"}, method = RequestMethod.GET)
	@ResponseBody
	public AmwayCartWsDTO getCart(@PathVariable final String accountUid, @PathVariable final String user_id,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
			throws CommerceCartModificationException, WebserviceValidationException, ProductLowStockException,
			StockSystemException
	{
		if (getUserFacade().isAnonymousUser())
		{
			throw new AccessDeniedException("Access is denied");
		}
		//STEP 1: retrieve/create the cart for the user
		//clear the contents of the cart
		final CartModel sessionCartModel = cartService.getSessionCart();
		//STEP 2: set the cart type as POS, next step will save this value
		sessionCartModel.setType(AmwayCartType.POS);
		//STEP 3: set the cart delivery mode as pickup
		if (!getCheckoutFacade().setDeliveryMode(DELIVERYMODE_PICKUP))
		{
			throw new CommerceCartModificationException("delivery mode pickup could not be set");
		}

		final CartWsDTO cartWs = getDataMapper().map(getSessionCart(), CartWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);

		//TODO: To use getDataMapper()
		final AmwayCartWsDTO amwayCartWs = new AmwayCartWsDTO();
		amwayCartWs.setCartinfo(cartWs);

		return amwayCartWs;
	}


	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = {"/pos/account/{accountUid}/users/{user_id}/getSupportedPaymentModes/{cartId}"}, method = RequestMethod.GET)
	@ResponseBody
	public PaymentModeListWSDTO getSupportedPaymentModesCombinations(@PathVariable final String accountUid,
			@PathVariable final String user_id, @PathVariable final String cartId,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getSupportedPaymentModes");
		}

		loadCart(cartId);
		final PaymentModeListWSDTO dto = new PaymentModeListWSDTO();
		final Map<String, List<AmwayPaymentModeData>> supportedPaymentModesCombinations = amwayCheckoutFacade
				.getSupportedPaymentModesCombinations();
		final List<AmwayPaymentModeWsDTO> supportedPaymentModes = new ArrayList<AmwayPaymentModeWsDTO>();

		//Yes a lot of code but related to DTO so it stays here..
		for (final Entry<String, List<AmwayPaymentModeData>> mode : supportedPaymentModesCombinations.entrySet())
		{
			final AmwayPaymentModeWsDTO modeWs = new AmwayPaymentModeWsDTO();
			supportedPaymentModes.add(modeWs);
			final List<AmwayPaymentModeDataWsDTO> modes = new ArrayList<AmwayPaymentModeDataWsDTO>();
			modeWs.setCode(mode.getKey());
			modeWs.setPaymentModes(modes);
			for (final AmwayPaymentModeData data : mode.getValue())
			{
				final AmwayPaymentModeDataWsDTO modeDataWs = new AmwayPaymentModeDataWsDTO();
				modeDataWs.setAmount(data.getAmount());
				modeDataWs.setCode(data.getCode());
				modeDataWs.setName(data.getName());
				modeDataWs.setRepeatableCount(data.getRepeatableCount());
				modes.add(modeDataWs);
			}

		}
		dto.setPaymentModes(supportedPaymentModes);
		return dto;
	}

	/**
	 * @param cartInfoData
	 * @param fields
	 * @return the Order details that was placed using the cart code and the payment info
	 * @throws AmwayPaymentAuthorizationException
	 * @throws WebserviceValidationException
	 * @throws AmwayEndpointValidationException
	 * @throws NoCheckoutCartException
	 * @throws InsufficientPaymentAmountException
	 * @throws BusinessException
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_CLIENT", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = {"/pos/account/{accountUid}/users/{userId}/placeorder"}, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public OrderWsDTO placeOrder(@RequestBody final PlaceOrderCartInfoData cartInfoData,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws AmwayPaymentAuthorizationException,
			WebserviceValidationException, AmwayEndpointValidationException, NoCheckoutCartException, InsufficientPaymentAmountException, BusinessException
	{
		loadCart(cartInfoData.getId());
		final CartModel sessionCart = cartService.getSessionCart();
		try
		{
			getCartFacade().validateCartData();
			sessionCart.setEmployee(userService.getUserForUID(cartInfoData.getSalesRepresentative()));

			// POS shift
			String terminal = cartInfoData.getTerminal();
			if (terminal==null || terminal.isEmpty()) {
				//missing POS terminal; throw exception
				throw new AmwayEndpointValidationException("missing POS terminal identifier");
			}
			String pointOfService = cartInfoData.getPointOfService();
			if (pointOfService==null || pointOfService.isEmpty()) {
				//missing POS identifier; throw exception
				throw new AmwayEndpointValidationException("missing POS identifier");
			}
			if(terminal != null && pointOfService != null) {
				AmwayBatchModel batchModel = amwayPOSService.getOpenBatch(pointOfService, terminal);
				sessionCart.setBatch(batchModel);
			}

		}
		catch (final Exception exc)
		{
			LOG.error("Exception during place order " + exc.getMessage(), exc);
			throw new AmwayEndpointValidationException(exc.getMessage());
		}
		validatePaymentModeForCart(cartInfoData);

		//not required to authorize, because this is being handled by microsega at POS
		try
		{
			if (!posPaymentService.validateOrderAmount(sessionCart, cartInfoData) || sessionCart.getTotalPrice().doubleValue() == 0)
			{
				throw new InsufficientPaymentAmountException("payment amount does not match the order total");
			}
			posPaymentService.capturePayment(sessionCart, cartInfoData);
			if (sessionCart.getPaymentInfo() == null && CollectionUtils.isEmpty(sessionCart.getPaymentInfos()))
			{
				throw new InsufficientPaymentAmountException(
						"no payments available for this cart. Cannot place order. Please enter valid payment types");
			}
		}
		catch (final IllegalArgumentException e)
		{
			LOG.error(e.getMessage(), e);
			throw new AmwayPaymentAuthorizationException(e.getMessage());
		}

		validateCartForPlaceOrder();

		//placeorder
		final OrderData orderData = amwayCheckoutFacade.placeOrder(SalesApplication.POS);
		return getDataMapper().map(orderFacade.getOrderDetailsForCode(orderData.getCode()), OrderWsDTO.class,
				FieldSetLevelHelper.DEFAULT_LEVEL);
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = {"/pos/account/{accountUid}/users/{user_id}/cancel-order/{orderCode}"}, method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public MessageDTO cancelOrder(@PathVariable(value = "accountUid") final String accountUid,
			@PathVariable(value = "user_id") final String user_id, @PathVariable(value = "orderCode") final String orderCode,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields) throws OrderCancelException
	{
		final MessageDTO message = new MessageDTO();
		final boolean isCanceled = amwayOrderFacade.cancelOrder(orderCode, AmwayCartType.POS);
		if (isCanceled)
		{
			message.setCode("200");
			message.setReturnMessage("Order Canceled Successfully");
		}
		else
		{
			message.setCode("-1");
			message.setReturnMessage("Some thing wrong while cancellation");
		}
		return message;
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = {"/pos/account/{accountUid}/users/{user_id}/return-order/{orderCode}"}, method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public MessageDTO returnOrder(@PathVariable(value = "accountUid") final String accountUid,
			@PathVariable(value = "user_id") final String user_id, @PathVariable(value = "orderCode") final String orderCode,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields) throws OrderCancelException
	{
		final MessageDTO message = new MessageDTO();
		final boolean isInitiated = amwayOrderFacade.sendOrderReturnNotification(orderCode, AmwayCartType.POS);
		if (isInitiated)
		{
			message.setCode("200");
			message.setReturnMessage("Return process initiated Successfully");
		}
		else
		{
			message.setCode("-1");
			message.setReturnMessage("Order with code " + orderCode + " is not returnable");
		}
		return message;
	}

	@Secured(
			{ "ROLE_POSEMPLOYEEGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/pos/batches/users/{user_id}", method = RequestMethod.POST)
	@ResponseBody
	public AmwayBatchWsDTO createBatch(@PathVariable final String baseSiteId,
			@PathVariable final String user_id,
			@RequestParam(required = true) final String pickupStore,
			@RequestParam(required = true) final String terminal,
			@RequestParam(required = true) final String startingBalance)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("creating batch: " + logParam("terminal", terminal) + ", " + logParam("startingBalance", startingBalance) + ", "
					+ logParam("baseSiteId", baseSiteId));
		}

		AmwayBatchModel batchModel = amwayPOSService.createBatch(baseSiteId, pickupStore, terminal, user_id, startingBalance);
		final AmwayBatchWsDTO batchWsDTO = getDataMapper().map(batchModel, AmwayBatchWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);
		batchWsDTO.setCashier(batchModel.getCashier().getUid());

		return batchWsDTO;
	}

	@Secured(
			{ "ROLE_POSEMPLOYEEGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/pos/batches/batch/{batch_id}", method = RequestMethod.POST)
	@ResponseBody
	public AmwayBatchWsDTO updateBatch(@PathVariable final String baseSiteId,
			@PathVariable final String batch_id,
			@RequestParam(required = true) final String terminal,
			@RequestParam(required = true) final String endingBalance)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("updating batch: " + logParam("batch_id", batch_id) + ", " + logParam("endingBalance", endingBalance) + ", "
					+ logParam("baseSiteId", baseSiteId));
		}

		AmwayBatchModel batchModel = amwayPOSService.updateBatch(batch_id, endingBalance);
		final AmwayBatchWsDTO batchWsDTO = getDataMapper().map(batchModel, AmwayBatchWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);
		batchWsDTO.setCashier(batchModel.getCashier().getUid());

		return batchWsDTO;
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/pos/batches/batch/{batch_id}", method = RequestMethod.GET)
	@ResponseBody
	public AmwayBatchWsDTO getBatch(@PathVariable final String baseSiteId,
			@PathVariable final String batch_id)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("retrieving batch: " + logParam("batch_id", batch_id)  + logParam("baseSiteId", baseSiteId));
		}

		AmwayBatchModel batchModel = amwayPOSService.getBatch(batch_id);
		final AmwayBatchWsDTO batchWsDTO = getDataMapper().map(batchModel, AmwayBatchWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);
		batchWsDTO.setCashier(batchModel.getCashier().getUid());
		return batchWsDTO;
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/pos/batches", method = RequestMethod.GET)
	@ResponseBody
	public AmwayBatchListWsDTO getBatches(@PathVariable final String baseSiteId,
			@RequestParam(required = true) final String pickupStore,
			@RequestParam(required = true) final String terminal,
			@RequestParam(required = true) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) final Date startDate,
			@RequestParam(required = true) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) final Date endDate)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("retrieving batches: " + logParam("startDate", startDate.toString())+ ", " + logParam("endDate", endDate.toString())+ ", "+
					logParam("terminal", terminal) + ", " + logParam("pickupStore", pickupStore));
		}

		List<AmwayBatchModel> batches = amwayPOSService.getBatches(pickupStore, terminal, startDate, endDate);
		final AmwayBatchListWsDTO dto = new AmwayBatchListWsDTO();
		final List<AmwayBatchWsDTO> batchesDto = new ArrayList<AmwayBatchWsDTO>();

		for (final AmwayBatchModel batchModel: batches)
		{
			final AmwayBatchWsDTO batchWsDTO = getDataMapper().map(batchModel, AmwayBatchWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);
			batchWsDTO.setCashier(batchModel.getCashier().getUid());
			batchesDto.add(batchWsDTO);
		}
		dto.setBatches(batchesDto);

		return dto;
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/pos/openbatch", method = RequestMethod.GET)
	@ResponseBody
	public AmwayBatchWsDTO getOpenBatch(@PathVariable final String baseSiteId,
			@RequestParam(required = true) final String pickupStore,
			@RequestParam(required = true) final String terminal)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("retrieving open batch: " + logParam("terminal", terminal) + ", " + logParam("pickupStore", pickupStore));
		}

		AmwayBatchModel batchModel = amwayPOSService.getOpenBatch(pickupStore, terminal);
		final AmwayBatchWsDTO batchWsDTO = getDataMapper().map(batchModel, AmwayBatchWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);
		batchWsDTO.setCashier(batchModel.getCashier().getUid());

		return batchWsDTO;
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/pos/batches/users/{user_id}", method = RequestMethod.GET)
	@ResponseBody
	public AmwayBatchListWsDTO getEmployeeBatches(@PathVariable final String baseSiteId,
			@PathVariable final String user_id,
			@RequestParam(required = true) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) final Date startDate,
			@RequestParam(required = true) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) final Date endDate)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("retrieving batches: " + logParam("startDate", startDate.toString())+ ", " + logParam("endDate", endDate.toString())+ ", "+
					logParam("employee", user_id));
		}

		List<AmwayBatchModel> batches = amwayPOSService.getBatches(user_id, startDate, endDate);

		final AmwayBatchListWsDTO dto = new AmwayBatchListWsDTO();
		final List<AmwayBatchWsDTO> batchesDto = new ArrayList<AmwayBatchWsDTO>();

		for (final AmwayBatchModel batchModel: batches)
		{
			final AmwayBatchWsDTO batchWsDTO = getDataMapper().map(batchModel, AmwayBatchWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);
			batchWsDTO.setCashier(batchModel.getCashier().getUid());
			batchesDto.add(batchWsDTO);
		}
		dto.setBatches(batchesDto);

		return dto;
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/pos/orders/batch/{batch_id}", method = RequestMethod.GET)
	@ResponseBody
	public OrderHistoryListWsDTO getOrdersForBatch(
			@PathVariable final String baseSiteId,
			@PathVariable final String batch_id,
			@RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@RequestParam(required = false) final String sort, @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(currentPage);
		pageableData.setPageSize(pageSize);
		pageableData.setSort(sort);

		final OrderHistoriesData orderHistoriesData;
		orderHistoriesData = createOrderHistoriesData(amwayOrderFacade.getPagedOrderHistoryForBatch(pageableData, batch_id));
		final OrderHistoryListWsDTO dto = getDataMapper().map(orderHistoriesData, OrderHistoryListWsDTO.class, fields);
		return dto;
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/pos/orders", method = RequestMethod.GET)
	@ResponseBody
	public OrderHistoryListWsDTO getOrdersForDateRange(
			@PathVariable final String baseSiteId,
			@RequestParam(required = true) final String pickupStore,
			@RequestParam(required = true) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) final Date startDate,
			@RequestParam(required = true) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) final Date endDate,
			@RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@RequestParam(required = false) final String sort, @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(currentPage);
		pageableData.setPageSize(pageSize);
		pageableData.setSort(sort);

		final OrderHistoriesData orderHistoriesData;
		orderHistoriesData = createOrderHistoriesData(amwayOrderFacade.getPagedOrderHistoryForPOS(pageableData, pickupStore, startDate, endDate));
		final OrderHistoryListWsDTO dto = getDataMapper().map(orderHistoriesData, OrderHistoryListWsDTO.class, fields);
		return dto;
	}

	private OrderHistoriesData createOrderHistoriesData(final SearchPageData<OrderHistoryData> result)
	{
		final OrderHistoriesData orderHistoriesData = new OrderHistoriesData();

		orderHistoriesData.setOrders(result.getResults());
		orderHistoriesData.setSorts(result.getSorts());
		orderHistoriesData.setPagination(result.getPagination());

		return orderHistoriesData;
	}

	protected void loadCart(final String cartId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		final BaseSiteModel baseSite = baseSiteService.getCurrentBaseSite();
		final CartModel cart;

		if (getUserFacade().isAnonymousUser())
		{
			cart = commerceCartService.getCartForGuidAndSite(cartId, baseSite);
			if (cart != null && CustomerModel.class.isAssignableFrom(cart.getUser().getClass()))
			{
				final CustomerModel cartOwner = (CustomerModel) cart.getUser();
				if (!userService.isAnonymousUser(cartOwner) && !CustomerType.GUEST.equals(cartOwner.getType()))
				{
					// 'access denied' presented as 'not found' for security reasons
					throw new CartException("Cart not found.", CartException.NOT_FOUND, cartId);
				}
			}
		}
		else
		{
			cart = commerceCartService.getCartForCodeAndUser(cartId, currentUser);
		}
		if (cart == null)
		{
			throw new CartException("Cart not found.", CartException.NOT_FOUND, cartId);
		}

		cartService.setSessionCart(cart);
	}

	protected void validatePaymentModeForCart(final PlaceOrderCartInfoData cartInfoData)
			throws NoCheckoutCartException, InvalidCartException, WebserviceValidationException
	{
		final CartData cartData = getSessionCart();

		final Errors errors = new BeanPropertyBindingResult(cartData, "sessionCart");
		paymentModeValidator.validate(cartInfoData, errors);
		if (errors.hasErrors())
		{
			throw new WebserviceValidationException(errors);
		}
	}

	/**
	 * @param entry
	 * @return
	 */
	private String getProductCode(final CartEntryInfoData entry)
	{
		final String code = entry.getCode();
		final String barCode = entry.getBarcode();

		if (StringUtils.isNotEmpty(barCode))
		{
			final String t = barCode.trim();
			if (t.length() == 0)
			{
				//throw new Exception("Item is empty.");
			}
			final StringBuffer prefix = new StringBuffer();
			final StringBuffer base = new StringBuffer();
			final StringBuffer suffix = new StringBuffer();
			final StringBuffer revision = new StringBuffer();
			final char[] ch = t.toCharArray();

			final boolean startsWithDigit = isDigit(ch[0]);
			boolean numericItem = startsWithDigit;


			for (final char c : ch)
			{
				if (numericItem)
				{
					if (isDigit(c))
					{
						base.append(c);
					}
					else
					{
						numericItem = false;
						if (base.length() == 0)
						{
							prefix.append(c);
						}
						else
						{
							suffix.append(c);
						}
					}
				}
				else
				{
					if (isDigit(c))
					{
						if (suffix.length() == 0)
						{
							base.append(c);
						}
						else
						{
							suffix.append(c);
						}
					}
					else if (base.length() == 0)
					{
						prefix.append(c);
					}
					else
					{
						suffix.append(c);
					}
				}
			}
			String[] rtn = null;
			if (base.length() == 0)
			{
				return code;
			}
			if (base.length() == 1)
			{
				rtn = new String[]
						{ prefix.toString(), "000" + base.toString(), suffix.toString() };
			}
			if (base.length() == 2)
			{
				rtn = new String[]
						{ prefix.toString(), "00" + base.toString(), suffix.toString() };
			}
			if (base.length() == 3)
			{
				rtn = new String[]
						{ prefix.toString(), "0" + base.toString(), suffix.toString() };
			}

			rtn = new String[]
					{ prefix.toString(), base.toString(), suffix.toString() };

			return rtn[0] + rtn[1];
		}

		return code;
	}

	private boolean isDigit(final char c)
	{
		return Character.isDigit(c);
	}



	/**
	 * Deprecated... shameful amount of code in a contoller!!!!
	 *
	 * @param cartInfoData
	 * @param baseSiteId
	 * @param accountUid
	 * @param user_id
	 * @param fields
	 * @return the cart info along with the status of the products that were added or not.
	 * @throws CommerceCartModificationException
	 * @throws WebserviceValidationException
	 * @throws ProductLowStockException
	 * @throws StockSystemException
	 * @throws InvalidCartException
	 */
	@Deprecated
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = {"/pos/account/{accountUid}/users/{user_id}/buildcart"}, method = RequestMethod.POST)
	@ResponseBody
	public AmwayCartWsDTO buildCart(@RequestBody final CartInfoData cartInfoData, @PathVariable final String baseSiteId,
			@PathVariable final String accountUid, @PathVariable final String user_id,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
			throws CommerceCartModificationException, WebserviceValidationException, ProductLowStockException,
			StockSystemException, InvalidCartException
	{
		if (getUserFacade().isAnonymousUser())
		{
			throw new AccessDeniedException("Access is denied");
		}
		LOG.info("loading cart to build :" + cartInfoData.getId());
		loadCart(cartInfoData.getId());
		//STEP 1: retrieve/create the cart for the user
		//clear the contents of the cart
		try
		{
			final CommerceCartParameter parameter = new CommerceCartParameter();
			parameter.setEnableHooks(true);
			parameter.setCart(cartService.getSessionCart());
			commerceCartService.removeAllEntries(parameter);
		}
		catch (final Exception comExc)
		{
			//ignoring this error because order threshold free gift throws this error
			LOG.error(comExc.getMessage(), comExc);
		}
		loadCart(cartInfoData.getId());
		//TODO: set delivery address as POS address based on warehouse code
		try
		{
			super.setCartDeliveryModeInternal("pickup-pos");
		}
		catch (final UnsupportedDeliveryModeException unSupDeliExc)
		{
			throw new InvalidCartException(unSupDeliExc);
		}

		//STEP 2: validate the store location sent is a valid POS
		final String pointofSale = cartInfoData.getPickupStore();
		final Errors errors = new BeanPropertyBindingResult(pointofSale, "pickupStore");
		if (StringUtils.isEmpty(pointofSale))
		{
			errors.reject("pointOfService.notExists");
			throw new WebserviceValidationException(errors);
		}
		pointOfSaleValidator.validate(pointofSale, errors);
		if (errors.hasErrors())
		{
			throw new WebserviceValidationException(errors);
		}

		//No validation of stock required because the customer already has products in the basket at the store
		//validateIfProductIsInStockInPOS(baseSiteId, code, pickupStore, null);

		//STEP 3: add products to the cart
		final List<CartModificationData> modificationList = new ArrayList<CartModificationData>();
		final CartModificationDataList modificationDataList = new CartModificationDataList();
		modificationDataList.setCartModificationList(modificationList);
		for (final CartEntryInfoData entry : cartInfoData.getEntries().getCartEntryInfos())
		{
			final String productCode = getProductCode(entry);
			final CartModificationData cartModificationData = getCartFacade().addToCart(productCode, entry.getQuantity().longValue(),
					pointofSale);
			modificationList.add(cartModificationData);
		}
		commerceCartService.calculateCart(cartService.getSessionCart());
		//this is important because the cart is not being saved with entries if this step is not performed
		getCartFacade().validateCartData();
		amwayOrderProrationService.prorate(cartService.getSessionCart());
		final CartWsDTO cartWs = getDataMapper().map(getSessionCart(), CartWsDTO.class, FieldSetLevelHelper.FULL_LEVEL);
		final CartModificationListWsDTO cartmodificationWs = getDataMapper().map(modificationDataList, CartModificationListWsDTO.class,
				FieldSetLevelHelper.BASIC_LEVEL);
		//TODO: To use getDataMapper()
		final AmwayCartWsDTO amwayCartWs = new AmwayCartWsDTO();
		amwayCartWs.setCartinfo(cartWs);
		amwayCartWs.setModifications(cartmodificationWs);
		return amwayCartWs;
	}

}

