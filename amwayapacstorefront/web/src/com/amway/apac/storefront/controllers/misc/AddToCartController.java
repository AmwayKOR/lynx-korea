/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.apac.storefront.controllers.misc;

import static com.amway.apac.storefront.controllers.ControllerConstants.ModelParameters.QUANTITY_ATTR;

import de.hybris.platform.acceleratorfacades.product.data.ProductWrapperData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.AddToCartForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.AddToCartOrderForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.AddToEntryGroupForm;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.converters.populator.GroupCartModificationListPopulator;
import de.hybris.platform.commercefacades.order.data.AddToCartParams;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for Add to Cart functionality which is not specific to a certain page.
 */
@Controller
public class AddToCartController extends AbstractController
{
	private static final String TYPE_MISMATCH_ERROR_CODE = "typeMismatch";
	private static final String ERROR_MSG_TYPE = "errorMsg";
	private static final String QUANTITY_INVALID_BINDING_MESSAGE_KEY = "basket.error.quantity.invalid.binding";
	private static final String SHOWN_PRODUCT_COUNT = "amwayapacstorefront.storefront.minicart.shownProductCount";

	private static final Logger LOG = Logger.getLogger(AddToCartController.class);

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "productVariantFacade")
	private ProductFacade productFacade;

	@Resource(name = "groupCartModificationListPopulator")
	private GroupCartModificationListPopulator groupCartModificationListPopulator;

	@RequestMapping(value = "/cart/add", method = RequestMethod.POST)
	public String addToCart(@RequestParam("productCodePost") final String code, final Model model, @Valid final AddToCartForm form,
			final BindingResult bindingErrors)
	{
		if (bindingErrors.hasErrors())
		{
			return getViewWithBindingErrorMessages(model, bindingErrors);
		}

		final long qty = form.getQty();

		if (qty <= 0)
		{
			GlobalMessages.addErrorMessage(model, ControllerConstants.ErrorMessageKeys.AddToCart.INVALID_QUANTITY);
			model.addAttribute(QUANTITY_ATTR, Long.valueOf(0L));
		}
		else
		{
			try
			{
				final CartModificationData cartModification = cartFacade.addToCart(code, qty);
				model.addAttribute(QUANTITY_ATTR, Long.valueOf(cartModification.getQuantityAdded()));
				model.addAttribute("entry", cartModification.getEntry());
				model.addAttribute("cartCode", cartModification.getCartCode());
				model.addAttribute("isQuote", cartFacade.getSessionCart().getQuoteData() != null ? Boolean.TRUE : Boolean.FALSE);

				if (cartModification.getQuantityAdded() == 0L)
				{
					GlobalMessages.addErrorMessage(model,
							"basket.information.quantity.noItemsAdded." + cartModification.getStatusCode());
				}
				else if (cartModification.getQuantityAdded() < qty)
				{
					GlobalMessages.addErrorMessage(model,
							"basket.information.quantity.reducedNumberOfItemsAdded." + cartModification.getStatusCode());
				}
			}
			catch (final CommerceCartModificationException ex)
			{
				logDebugException(ex);
				GlobalMessages.addErrorMessage(model, ControllerConstants.ErrorMessageKeys.AddToCart.BASKET_ERROR);
				model.addAttribute(QUANTITY_ATTR, Long.valueOf(0L));
			}
		}

		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}

	protected String getViewWithBindingErrorMessages(final Model model, final BindingResult bindingErrors)
	{
		for (final ObjectError error : bindingErrors.getAllErrors())
		{
			if (isTypeMismatchError(error))
			{
				GlobalMessages.addErrorMessage(model, QUANTITY_INVALID_BINDING_MESSAGE_KEY);
			}
			else
			{
				GlobalMessages.addErrorMessage(model, error.getDefaultMessage());
			}
		}
		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}

	protected boolean isTypeMismatchError(final ObjectError error)
	{
		return error.getCode().equals(TYPE_MISMATCH_ERROR_CODE);
	}

	@RequestMapping(value = "/cart/addGrid", method = RequestMethod.POST)
	public final String addGridToCart(final AddToCartOrderForm form, final Model model)
	{
		final List<CartModificationData> modificationDataList = new ArrayList<>();

		final List<OrderEntryData> filteredEntries = filterUnselectedOrderEntries(form.getCartEntries());

		if (CollectionUtils.isEmpty(filteredEntries))
		{
			GlobalMessages.addErrorMessage(model, "shopping.list.grid.addtocart.none.selected");
		}
		else
		{
			for (final OrderEntryData cartEntry : filteredEntries)
			{
				if (!isValidProductEntry(cartEntry))
				{
					LOG.error("Error processing entry");
				}
				else if (!isValidQuantity(cartEntry))
				{
					GlobalMessages.addErrorMessage(model, "basket.error.quantity.invalid");
				}
				else
				{
					final String errorMsg = addEntryToCart(modificationDataList, cartEntry, true);
					if (StringUtils.isNotEmpty(errorMsg))
					{
						GlobalMessages.addErrorMessage(model, errorMsg);
					}
				}
			}
		}

		if (CollectionUtils.isNotEmpty(modificationDataList))
		{
			groupCartModificationListPopulator.populate(null, modificationDataList);

			model.addAttribute("modifications", modificationDataList);
		}

		model.addAttribute("numberShowing", Integer.valueOf(Config.getInt(SHOWN_PRODUCT_COUNT, 3)));

		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}

	/**
	 * Filters out the entries which are not selected.
	 *
	 * @param cartEntries
	 *           all entries
	 * @return list containing only the selected entries, empty list if none is selected
	 */
	protected List<OrderEntryData> filterUnselectedOrderEntries(final List<OrderEntryData> cartEntries)
	{
		final List<OrderEntryData> filteredEntries = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(cartEntries))
		{
			for (final OrderEntryData orderEntry : cartEntries)
			{
				if (BooleanUtils.isTrue(orderEntry.getSelected()))
				{
					filteredEntries.add(orderEntry);
				}
			}
		}
		return filteredEntries;
	}

	@RequestMapping(value = "/cart/addQuickOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public final String addQuickOrderToCart(@RequestBody final AddToCartOrderForm form, final Model model)
	{
		final List<CartModificationData> modificationDataList = new ArrayList();
		final List<ProductWrapperData> productWrapperDataList = new ArrayList();
		final int maxQuickOrderEntries = Config.getInt("amwayapacstorefront.quick.order.rows.max", 25);
		final int sizeOfCartEntries = CollectionUtils.size(form.getCartEntries());
		form.getCartEntries().stream().limit(Math.min(sizeOfCartEntries, maxQuickOrderEntries)).forEach(cartEntry -> {
			String errorMsg = StringUtils.EMPTY;
			final String sku = !isValidProductEntry(cartEntry) ? StringUtils.EMPTY : cartEntry.getProduct().getCode();
			if (StringUtils.isEmpty(sku))
			{
				errorMsg = "text.quickOrder.product.code.invalid";
			}
			else if (!isValidQuantity(cartEntry))
			{
				errorMsg = "text.quickOrder.product.quantity.invalid";
			}
			else
			{
				errorMsg = addEntryToCart(modificationDataList, cartEntry, false);
			}

			if (StringUtils.isNotEmpty(errorMsg))
			{
				productWrapperDataList.add(createProductWrapperData(sku, errorMsg));
			}
		});

		if (CollectionUtils.isNotEmpty(productWrapperDataList))
		{
			model.addAttribute("quickOrderErrorData", productWrapperDataList);
			model.addAttribute("quickOrderErrorMsg", "basket.quick.order.error");
		}

		if (CollectionUtils.isNotEmpty(modificationDataList))
		{
			model.addAttribute("modifications", modificationDataList);
		}

		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}

	@RequestMapping(value = "/entrygroups/cart/addToEntryGroup", method =
	{ RequestMethod.POST, RequestMethod.GET })
	public String addEntryGroupToCart(final Model model, @Valid final AddToEntryGroupForm form, final BindingResult bindingErrors)
	{
		if (bindingErrors.hasErrors())
		{
			return getViewWithBindingErrorMessages(model, bindingErrors);
		}
		final long qty = 1;
		try
		{
			final AddToCartParams addToCartParams = new AddToCartParams();
			addToCartParams.setEntryGroupNumbers(new HashSet(Collections.singletonList(form.getEntryGroupNumber())));
			addToCartParams.setProductCode(form.getProductCode());
			addToCartParams.setQuantity(qty);
			addToCartParams.setStoreId(null);
			final CartModificationData cartModification = cartFacade.addToCart(addToCartParams);
			model.addAttribute(QUANTITY_ATTR, Long.valueOf(cartModification.getQuantityAdded()));
			model.addAttribute("entry", cartModification.getEntry());
			model.addAttribute("cartCode", cartModification.getCartCode());

			if (cartModification.getQuantityAdded() == 0L)
			{
				model.addAttribute(ERROR_MSG_TYPE, "basket.information.quantity.noItemsAdded." + cartModification.getStatusCode());
			}
			else if (cartModification.getQuantityAdded() < qty)
			{
				model.addAttribute(ERROR_MSG_TYPE,
						"basket.information.quantity.reducedNumberOfItemsAdded." + cartModification.getStatusCode());
			}
		}
		catch (final CommerceCartModificationException ex)
		{
			logDebugException(ex);
			model.addAttribute(ERROR_MSG_TYPE, "basket.error.occurred");
			model.addAttribute(QUANTITY_ATTR, Long.valueOf(0L));
		}
		model.addAttribute("product",
				productFacade.getProductForCodeAndOptions(form.getProductCode(), Arrays.asList(ProductOption.BASIC)));

		return REDIRECT_PREFIX + "/cart";
	}

	protected ProductWrapperData createProductWrapperData(final String sku, final String errorMsg)
	{
		final ProductWrapperData productWrapperData = new ProductWrapperData();
		final ProductData productData = new ProductData();
		productData.setCode(sku);
		productWrapperData.setProductData(productData);
		productWrapperData.setErrorMsg(errorMsg);
		return productWrapperData;
	}

	protected void logDebugException(final Exception ex)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(ex);
		}
	}

	protected String addEntryToCart(final List<CartModificationData> modificationDataList, final OrderEntryData cartEntry,
			final boolean isReducedQtyError)
	{
		String errorMsg = StringUtils.EMPTY;
		try
		{
			final long qty = cartEntry.getQuantity().longValue();
			final CartModificationData cartModificationData = cartFacade.addToCart(cartEntry.getProductCode(), qty);
			if (cartModificationData.getQuantityAdded() == 0L)
			{
				errorMsg = "basket.information.quantity.noItemsAdded." + cartModificationData.getStatusCode();
			}
			else if (cartModificationData.getQuantityAdded() < qty && isReducedQtyError)
			{
				errorMsg = "basket.information.quantity.reducedNumberOfItemsAdded." + cartModificationData.getStatusCode();
			}

			modificationDataList.add(cartModificationData);

		}
		catch (final CommerceCartModificationException ex)
		{
			errorMsg = "basket.error.occurred";
			logDebugException(ex);
		}
		return errorMsg;
	}

	protected boolean isValidProductEntry(final OrderEntryData cartEntry)
	{
		return StringUtils.isNotBlank(cartEntry.getProductCode());
	}

	protected boolean isValidQuantity(final OrderEntryData cartEntry)
	{
		return cartEntry.getQuantity() != null && cartEntry.getQuantity().longValue() >= 1L;
	}
}