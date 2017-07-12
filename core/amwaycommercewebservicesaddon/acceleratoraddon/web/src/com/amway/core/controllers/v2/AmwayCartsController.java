/**
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 *
 */
package com.amway.core.v2.controller;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.promotion.CommercePromotionRestrictionFacade;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceDataList;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commercewebservicescommons.dto.store.PointOfServiceListWsDTO;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.mapping.FieldSetLevelHelper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amway.core.cart.impl.CommerceWebServicesCartFacade;
import com.amway.core.dto.DeleteEntriesWsDTO;
import com.amway.core.request.support.impl.PaymentProviderRequestSupportedStrategy;
import com.amway.core.stock.CommerceStockFacade;
import com.amway.facades.cart.AmwayCartFacade;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.DeliveryModesData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.PromotionResultData;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.commercefacades.promotion.CommercePromotionRestrictionFacade;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceDataList;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.commercewebservicescommons.errors.exceptions.CartEntryException;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.order.CommerceCartMergingException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.commerceservices.promotion.CommercePromotionRestrictionException;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commercewebservicescommons.dto.order.*;

//Amway Core import
import com.amway.facades.cart.AmwayCartFacade;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;
import com.amway.core.dto.DeleteEntriesWsDTO;

import de.hybris.platform.webservicescommons.mapping.FieldSetLevelHelper;
import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercewebservicescommons.dto.store.PointOfServiceListWsDTO;
import de.hybris.platform.commercefacades.order.data.*;
import de.hybris.platform.commercewebservicescommons.dto.voucher.VoucherWsDTO;


/**
 *
 * @pathparam userId User identifier or one of the literals below :
 *            <ul>
 *            <li>'current' for currently authenticated user</li>
 *            <li>'anonymous' for anonymous user</li>
 *            </ul>
 * @pathparam cartId Cart identifier
 *            <ul>
 *            <li>cart code for logged in user</li>
 *            <li>cart guid for anonymous user</li>
 *            <li>'current' for the last modified cart</li>
 *            </ul>
 * @pathparam entryNumber Entry number. Zero-based numbering.
 * @pathparam promotionId Promotion identifier (code)
 * @pathparam voucherId Voucher identifier (code)
 * @security Anonymous user may access cart by its guid. Customer may access only own cart by its id. Trusted client or
 *           customer manager may impersonate as any user and access cart on their behalf.
 */
@Controller
@RequestMapping(value = "/{baseSiteId}/users/{userId}/carts")
@CacheControl(directive = CacheControlDirective.NO_CACHE)
public class AmwayCartsController extends BaseCommerceController
{
	private static final Logger LOG = Logger.getLogger(AmwayCartsController.class);
	private static final long DEFAULT_PRODUCT_QUANTITY = 1;
	@Autowired
	AcceleratorCheckoutFacade acceleratorCheckoutFacade;



	/**
	 * Web service handler for getting consolidated pickup options<br>
	 * Request Method = <code>GET</code>
	 *
	 * @return {@link PointOfServiceListWsDTO} as response body
	 */
	@RequestMapping(value = "/{cartId}/consolidate", method = RequestMethod.GET)
	@ResponseBody
	public PointOfServiceListWsDTO getConsolidatedPickupOptions(
			@RequestParam(required = false, defaultValue = FieldSetLevelHelper.DEFAULT_LEVEL) final String fields)
	{
		final PointOfServiceDataList pointOfServices = new PointOfServiceDataList();
		pointOfServices.setPointOfServices(acceleratorCheckoutFacade.getConsolidatedPickupOptions());
		return getDataMapper().map(pointOfServices, PointOfServiceListWsDTO.class, fields);
	}

	/**
	 * Web service handler for consolidating pickup locations<br>
	 * Request Method = <code>POST</code>
	 *
	 * @param storeName
	 *           - name of store where items will be picked
	 * @param pickupDate
	 * 			 - date and time when the items will be picked
	 * @return {@link CartModificationListWsDTO} unsuccessfull modifications if any as response body
	 */
	@RequestMapping(value = "/{cartId}/consolidate", method = RequestMethod.POST)
	@ResponseBody
	public CartModificationListWsDTO consolidatePickupLocations(@RequestParam(required = true) final String storeName,
			@RequestParam(required = true) final String pickupDate,
			@RequestParam(required = false, defaultValue = FieldSetLevelHelper.DEFAULT_LEVEL) final String fields)
			throws CommerceCartModificationException
	{
		final CartModificationDataList modifications = new CartModificationDataList();
		modifications.setCartModificationList(acceleratorCheckoutFacade.consolidateCheckoutCart(storeName));
		amwayCartFacade.addPickupDateTime(pickupDate);
		final CartModificationListWsDTO result = getDataMapper().map(modifications, CartModificationListWsDTO.class, fields);
		return result;
	}

	/**
	 * Method returns all delivery modes supported for the current cart depending on the delivery methods which are assigned to the current fulfilling warehouse
	 * and the delivery methods which are available for all the products in the shopping cart.
	 *
	 * @queryparam fields Response configuration (list of fields, which should be returned in response)
	 * @return All supported delivery modes
	 */
	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/{cartId}/deliverymodes/available", method = RequestMethod.GET)
	@ResponseBody
	public DeliveryModeListWsDTO getAvailableDeliveryModes(
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getAvailableDeliveryModes");
		}
		final DeliveryModeListWsDTO dto = new DeliveryModeListWsDTO();
		final List<DeliveryModeData> availableDeliveryModes = new ArrayList<>();
		CartData cart = getSessionCart();
		for(OrderEntryData cartEntry : cart.getEntries())
		{
			List<DeliveryModeData> productDeliveryModes = cartEntry.getProduct().getDeliveryModes();
			if (!productDeliveryModes.isEmpty())
			{
				for (DeliveryModeData productDeliveryMode : productDeliveryModes)
				{
					if (!availableDeliveryModes.contains(productDeliveryMode))
					{
						for (DeliveryModeData supportedDeliveryMode : getCheckoutFacade().getSupportedDeliveryModes())
						{
							if (supportedDeliveryMode.getCode().equals(productDeliveryMode.getCode()))
							{
								availableDeliveryModes.add(supportedDeliveryMode);
							}
						}
					}
				}
			}
		}
		dto.setDeliveryModes(getDataMapper().mapAsList(availableDeliveryModes, DeliveryModeWsDTO.class, fields));
		return dto;
	}


	@Resource(name = "amwayCartFacade")
	private AmwayCartFacade amwayCartFacade;

	@Resource(name = "cartService")
	private CartService cartService;
	@Resource(name = "modelService")
	private ModelService modelService;

	@RequestMapping(value = "/{cartId}/carttype", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void setCartType(@RequestParam(required = true) final String type)
	{
		amwayCartFacade.setCartType(type);
	}

	/**
	 * Assigns an email to the cart. This step is required to make a guest checkout.
	 *
	 * @param entries
	 *         Request body parameter (DTO in json format) which array of cart entry numbers to remove
	 * @throws CommerceCartModificationException
	 */
	@RequestMapping(value = "/{cartId}/entries", method = RequestMethod.DELETE, consumes =
			{ MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CartWsDTO purgeCart(@RequestBody(required = false) final DeleteEntriesWsDTO entries)
			throws CommerceCartModificationException
	{
		if (getCartFacade() instanceof CommerceWebServicesCartFacade && entries != null)
		{
			final CartData cart = getSessionCart();
			final List<Integer> deleteEntries = entries.getEntries();

			for (int i = 0; i < deleteEntries.size(); i++)
			{
				final int entryNumber = deleteEntries.get(i).intValue();
				getCartEntryForNumber(cart, entryNumber);
				getCartFacade().updateCartEntry(entryNumber, 0);
				normalizeDeleteEntries(deleteEntries);
			}
		}

		return getDataMapper().map(getSessionCart(), CartWsDTO.class, DEFAULT_FIELD_SET);
	}

	/**
	 * Iterate over the list of entries and 'normalize' after cart update, i.e. decrement every entry
	 *
	 * @param entries
	 */
	private static void normalizeDeleteEntries(final List<Integer> entries) {
		for (int i = 0; i < entries.size(); i++) {
			int entry = entries.get(i);
			entries.set(i, --entry);
		}
	}

	protected static OrderEntryData getCartEntryForNumber(final CartData cart, final long number) throws CartEntryException
	{
		final List<OrderEntryData> entries = cart.getEntries();
		if (entries != null && !entries.isEmpty())
		{
			final Integer requestedEntryNumber = Integer.valueOf((int) number);
			for (final OrderEntryData entry : entries)
			{
				if (entry != null && requestedEntryNumber.equals(entry.getEntryNumber()))
				{
					return entry;
				}
			}
		}
		throw new CartEntryException("Entry not found", CartEntryException.NOT_FOUND, String.valueOf(number));
	}

	/**
	 * Remove all entries from the active shopping cart
	 *
	 * @throws CommerceCartModificationException
	 */
	@RequestMapping(value = "/{cartId}/entries", method = RequestMethod.DELETE)
	@ResponseBody
	public CartWsDTO purgeCart() throws CommerceCartModificationException
	{
		if (getCartFacade() instanceof CommerceWebServicesCartFacade)
		{
			final CartData cart = getSessionCart();
			final List<OrderEntryData> deleteEntries = cart.getEntries();

			for (int i = 0; i < deleteEntries.size(); i++)
			{
				getCartEntryForNumber(cart, i);
				//full empty so always delete 0 entry during iteration
				getCartFacade().updateCartEntry(0, 0);
			}
		}
		return getDataMapper().map(getSessionCart(), CartWsDTO.class, DEFAULT_FIELD_SET);
	}

}

