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
package com.amway.apac.storefront.controllers.pages.checkout.steps;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateQuoteCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.ValidationResults;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.checkout.steps.AbstractCheckoutStepController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator;
import de.hybris.platform.acceleratorstorefrontcommons.util.AddressDataUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.util.Config;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.apac.storefront.forms.AmwayApacAddressForm;
import com.amway.apac.storefront.validators.AmwayApacAddressValidator;
import com.amway.facades.checkout.AmwayCheckoutFacade;


@Controller
@RequestMapping(value = "/checkout/multi/delivery-address")
public class DeliveryAddressCheckoutStepController extends AbstractCheckoutStepController
{
	private static final String DELIVERY_ADDRESS = "delivery-address";
	private static final String SHOW_SAVE_TO_ADDRESS_BOOK_ATTR = "showSaveToAddressBook";

	@Resource(name = "addressDataUtil")
	private AddressDataUtil addressDataUtil;

	@Resource(name = "amwayApacAddressValidator")
	private AmwayApacAddressValidator addressValidator;

	@Resource(name = "amwayCheckoutFacade")
	private AmwayCheckoutFacade amwayCheckoutFacade;

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@RequireHardLogIn
	@PreValidateQuoteCheckoutStep
	@PreValidateCheckoutStep(checkoutStep = DELIVERY_ADDRESS)
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{

		amwayCheckoutFacade.setDeliveryAddressIfAvailable();
		amwayCheckoutFacade.setDeliveryModeIfAvailable();

		final CartData cartData = amwayCheckoutFacade.getCheckoutCart();

		populateCommonModelAttributes(model, cartData, new AmwayApacAddressForm());

		return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@RequireHardLogIn
	public String add(final AmwayApacAddressForm addressForm, final BindingResult bindingResult, final Model model,
			@RequestParam(value = "ajax", required = false, defaultValue = "false") final Boolean ajax,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{

		//set to default country and first region as the field is not mandatory
		final String countryIso = getCmsSiteService().getCurrentSite().getDefaultCountry().getIsocode();
		addressForm.setCountryIso(countryIso);
		addressForm.setRegionIso(getI18NFacade().getRegionsForCountryIso(countryIso).iterator().next().getIsocode());

		getAddressValidator().validate(addressForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "checkout.step.one.delivery.address.error.formentry.invalid");
			if (Boolean.TRUE.equals(ajax))
			{
				return ControllerConstants.Views.Fragments.Checkout.DeliveryAddressListPage;
			}
			else
			{
				model.addAttribute("openCreateAddressForm", Boolean.TRUE);
				return getCheckoutStep().currentStep();
			}
		}

		final AddressData newAddress = addressDataUtil.convertToAddressData(addressForm);

		//Custom for APAC
		newAddress.setLine3(addressForm.getLine3());
		newAddress.setEmail(addressForm.getEmail());

		processAddressVisibilityAndDefault(addressForm, newAddress);

		// Verify the address data.
		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		if (addressRequiresReview)
		{
			//return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		final AddressData previousSelectedAddress = amwayCheckoutFacade.getCheckoutCart().getDeliveryAddress();

		if (previousSelectedAddress != null && !previousSelectedAddress.isVisibleInAddressBook())
		{ // temporary address should be removed
			getUserFacade().removeAddress(previousSelectedAddress);
		}

		// Save address
		getUserFacade().addAddress(newAddress);

		// Set the new address as the selected checkout delivery address
		amwayCheckoutFacade.setDeliveryModeIfAvailable();
		amwayCheckoutFacade.setDeliveryAddress(newAddress);

		final CartData cartData = amwayCheckoutFacade.getCheckoutCart();
		populateCommonModelAttributes(model, cartData, addressForm);

		if (Boolean.TRUE.equals(ajax))
		{

			GlobalMessages.addInfoMessage(model, "checkout.step.one.delivery.address.added");
			return ControllerConstants.Views.Fragments.Checkout.DeliveryAddressListPage;
		}

		return getCheckoutStep().currentStep();
	}

	protected void processAddressVisibilityAndDefault(final AmwayApacAddressForm addressForm, final AddressData newAddress)
	{
		if (addressForm.getSaveInAddressBook() != null)
		{
			newAddress.setVisibleInAddressBook(addressForm.getSaveInAddressBook().booleanValue());
			if (addressForm.getSaveInAddressBook().booleanValue() && getUserFacade().isAddressBookEmpty())
			{
				newAddress.setDefaultAddress(true);
			}
			else
			{
				newAddress.setDefaultAddress(addressForm.getDefaultAddress().booleanValue());
			}
		}
		else if (getCheckoutCustomerStrategy().isAnonymousCheckout())
		{
			newAddress.setDefaultAddress(true);
			newAddress.setVisibleInAddressBook(true);
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editAddressForm(@RequestParam("editAddressCode") final String editAddressCode, final Model model,
			final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		final ValidationResults validationResults = getCheckoutStep().validate(redirectAttributes);
		if (getCheckoutStep().checkIfValidationErrors(validationResults))
		{
			return getCheckoutStep().onValidation(validationResults);
		}

		AddressData addressData = null;
		if (StringUtils.isNotEmpty(editAddressCode))
		{
			addressData = amwayCheckoutFacade.getDeliveryAddressForCode(editAddressCode);
		}

		final AmwayApacAddressForm addressForm = new AmwayApacAddressForm();
		final boolean hasAddressData = addressData != null;
		if (hasAddressData)
		{
			addressDataUtil.convert(addressData, addressForm);
		}

		final CartData cartData = amwayCheckoutFacade.getCheckoutCart();
		populateCommonModelAttributes(model, cartData, addressForm);

		if (addressData != null)
		{
			model.addAttribute(SHOW_SAVE_TO_ADDRESS_BOOK_ATTR, Boolean.valueOf(!addressData.isVisibleInAddressBook()));
		}

		return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@RequireHardLogIn
	public String edit(final AmwayApacAddressForm addressForm, final BindingResult bindingResult, final Model model,
			@RequestParam(value = "ajax", required = false, defaultValue = "false") final Boolean ajax,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getAddressValidator().validate(addressForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "checkout.step.one.delivery.address.error.formentry.invalid");
			if (Boolean.TRUE.equals(ajax))
			{
				return ControllerConstants.Views.Fragments.Checkout.DeliveryAddressListPage;
			}
			else
			{
				return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
			}
		}

		final AddressData newAddress = addressDataUtil.convertToAddressData(addressForm);

		//Custom for APAC
		newAddress.setLine3(addressForm.getLine3());
		newAddress.setEmail(addressForm.getEmail());

		processAddressVisibility(addressForm, newAddress);

		newAddress.setDefaultAddress(getUserFacade().isAddressBookEmpty() || getUserFacade().getAddressBook().size() == 1
				|| Boolean.TRUE.equals(addressForm.getDefaultAddress()));

		// Verify the address data.
		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		if (addressRequiresReview)
		{
			if (StringUtils.isNotEmpty(addressForm.getAddressId()))
			{
				final AddressData addressData = amwayCheckoutFacade.getDeliveryAddressForCode(addressForm.getAddressId());
				if (addressData != null)
				{
					//model.addAttribute(SHOW_SAVE_TO_ADDRESS_BOOK_ATTR, Boolean.valueOf(!addressData.isVisibleInAddressBook()));
					//model.addAttribute("edit", Boolean.TRUE);
				}
			}

			if (Boolean.TRUE.equals(ajax))
			{
				//return ControllerConstants.Views.Fragments.Checkout.DeliveryAddressListPage;
			}
			else
			{
				return ControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
			}
		}

		getUserFacade().editAddress(newAddress);
		amwayCheckoutFacade.setDeliveryModeIfAvailable();
		amwayCheckoutFacade.setDeliveryAddress(newAddress);

		final CartData cartData = amwayCheckoutFacade.getCheckoutCart();
		populateCommonModelAttributes(model, cartData, addressForm);

		if (Boolean.TRUE.equals(ajax))
		{
			GlobalMessages.addInfoMessage(model, "checkout.step.one.delivery.address.updated");
			return ControllerConstants.Views.Fragments.Checkout.DeliveryAddressListPage;
		}

		return getCheckoutStep().currentStep();
	}

	protected void processAddressVisibility(final AmwayApacAddressForm addressForm, final AddressData newAddress)
	{

		if (addressForm.getSaveInAddressBook() == null)
		{
			newAddress.setVisibleInAddressBook(true);
		}
		else
		{
			newAddress.setVisibleInAddressBook(Boolean.TRUE.equals(addressForm.getSaveInAddressBook()));
		}
	}

	@RequestMapping(value = "/remove", method =
	{ RequestMethod.GET, RequestMethod.POST })
	@RequireHardLogIn
	public String removeAddress(@RequestParam("addressCode") final String addressCode, final RedirectAttributes redirectModel,
			@RequestParam(value = "ajax", required = false, defaultValue = "false") final Boolean ajax, final Model model)
					throws CMSItemNotFoundException
	{
		if (amwayCheckoutFacade.isRemoveAddressEnabledForCart())
		{
			final AddressData addressData = new AddressData();
			addressData.setId(addressCode);
			getUserFacade().removeAddress(addressData);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"account.confirmation.address.removed");
		}

		if (Boolean.TRUE.equals(ajax))
		{
			GlobalMessages.addInfoMessage(model, "checkout.step.one.delivery.address.removed");
			final CartData cartData = amwayCheckoutFacade.getCheckoutCart();
			populateCommonModelAttributes(model, cartData, new AmwayApacAddressForm());
			return ControllerConstants.Views.Fragments.Checkout.DeliveryAddressListPage;
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute("addressForm", new AmwayApacAddressForm());

		return getCheckoutStep().currentStep();
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	@RequireHardLogIn
	public String doSelectSuggestedAddress(final AmwayApacAddressForm addressForm, final RedirectAttributes redirectModel)
	{
		final Set<String> resolveCountryRegions = org.springframework.util.StringUtils
				.commaDelimitedListToSet(Config.getParameter("resolve.country.regions"));

		final AddressData selectedAddress = addressDataUtil.convertToAddressData(addressForm);
		final CountryData countryData = selectedAddress.getCountry();

		if (!resolveCountryRegions.contains(countryData.getIsocode()))
		{
			selectedAddress.setRegion(null);
		}

		if (addressForm.getSaveInAddressBook() != null)
		{
			selectedAddress.setVisibleInAddressBook(addressForm.getSaveInAddressBook().booleanValue());
		}

		if (Boolean.TRUE.equals(addressForm.getEditAddress()))
		{
			getUserFacade().editAddress(selectedAddress);
		}
		else
		{
			getUserFacade().addAddress(selectedAddress);
		}

		final AddressData previousSelectedAddress = amwayCheckoutFacade.getCheckoutCart().getDeliveryAddress();
		// Set the new address as the selected checkout delivery address
		amwayCheckoutFacade.setDeliveryAddress(selectedAddress);
		if (previousSelectedAddress != null && !previousSelectedAddress.isVisibleInAddressBook())
		{ // temporary address should be removed
			getUserFacade().removeAddress(previousSelectedAddress);
		}

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "checkout.multi.address.added");

		return getCheckoutStep().nextStep();
	}


	/**
	 * This method gets called when the "Use this Address" button is clicked. It sets the selected delivery address on
	 * the checkout facade - if it has changed, and reloads the page highlighting the selected delivery address.
	 *
	 * @param selectedAddressCode
	 *           - the id of the delivery address.
	 *
	 * @return - a URL to the page to load.
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/select-address", method = RequestMethod.POST)
	@RequireHardLogIn
	public String doSelectDeliveryAddress(@RequestParam("selectedAddressCode") final String selectedAddressCode, final Model model,
			@RequestParam(value = "ajax", required = false, defaultValue = "false") final Boolean ajax,
			final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		final ValidationResults validationResults = getCheckoutStep().validate(redirectAttributes);
		if (getCheckoutStep().checkIfValidationErrors(validationResults))
		{
			return getCheckoutStep().onValidation(validationResults);
		}

		if (StringUtils.isNotBlank(selectedAddressCode))
		{
			final AddressData selectedAddressData = amwayCheckoutFacade.getDeliveryAddressForCode(selectedAddressCode);
			final boolean hasSelectedAddressData = selectedAddressData != null;
			if (hasSelectedAddressData)
			{
				setDeliveryAddress(selectedAddressData);
			}
		}

		if (Boolean.TRUE.equals(ajax))
		{
			GlobalMessages.addInfoMessage(model, "checkout.step.one.delivery.default.address.changed");
			final CartData cartData = amwayCheckoutFacade.getCheckoutCart();
			populateCommonModelAttributes(model, cartData, new AmwayApacAddressForm());
			return ControllerConstants.Views.Fragments.Checkout.DeliveryAddressListPage;
		}

		return getCheckoutStep().currentStep();
	}

	/**
	 * This method gets called when the "Use Selected Delivery Method" button is clicked. It sets the selected delivery
	 * mode on the checkout facade and reloads the page highlighting the selected delivery Mode.
	 *
	 * @param selectedDeliveryMethod
	 *           - the id of the delivery mode.
	 * @return - a URL to the page to load.
	 */
	@RequestMapping(value = "/delivery-mode-select", method = RequestMethod.POST)
	@RequireHardLogIn
	public String doSelectDeliveryMode(@RequestParam("delivery_method") final String selectedDeliveryMethod)
	{
		if (StringUtils.isNotEmpty(selectedDeliveryMethod))
		{
			amwayCheckoutFacade.setDeliveryMode(selectedDeliveryMethod);
		}

		return getCheckoutStep().currentStep();
	}

	protected void setDeliveryAddress(final AddressData selectedAddressData)
	{
		final AddressData cartCheckoutDeliveryAddress = amwayCheckoutFacade.getCheckoutCart().getDeliveryAddress();
		if (isAddressIdChanged(cartCheckoutDeliveryAddress, selectedAddressData))
		{
			amwayCheckoutFacade.setDeliveryAddress(selectedAddressData);
			if (cartCheckoutDeliveryAddress != null && !cartCheckoutDeliveryAddress.isVisibleInAddressBook())
			{ // temporary address should be removed
				getUserFacade().removeAddress(cartCheckoutDeliveryAddress);
			}
		}
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String back(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().previousStep();
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String next(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().nextStep();
	}

	protected String getBreadcrumbKey()
	{
		return "checkout.multi." + getCheckoutStep().getProgressBarId() + ".breadcrumb";
	}

	protected CheckoutStep getCheckoutStep()
	{
		return getCheckoutStep(DELIVERY_ADDRESS);
	}

	protected void populateCommonModelAttributes(final Model model, final CartData cartData,
			final AmwayApacAddressForm addressForm) throws CMSItemNotFoundException
	{
		model.addAttribute("cartData", cartData);
		model.addAttribute("addressForm", addressForm);
		model.addAttribute("deliveryAddresses", getDeliveryAddresses(cartData.getDeliveryAddress()));
		model.addAttribute("noAddress", Boolean.valueOf(getCheckoutFlowFacade().hasNoDeliveryAddress()));
		model.addAttribute("addressFormEnabled", Boolean.valueOf(amwayCheckoutFacade.isNewAddressEnabledForCart()));
		model.addAttribute("removeAddressEnabled", Boolean.valueOf(amwayCheckoutFacade.isRemoveAddressEnabledForCart()));
		model.addAttribute(SHOW_SAVE_TO_ADDRESS_BOOK_ATTR, Boolean.TRUE);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, getResourceBreadcrumbBuilder().getBreadcrumbs(getBreadcrumbKey()));
		model.addAttribute("metaRobots", "noindex,nofollow");
		model.addAttribute("deliveryMethods", amwayCheckoutFacade.getSupportedDeliveryModes());

		//for success ajax response
		model.addAttribute("response", Boolean.TRUE);

		if (StringUtils.isNotBlank(addressForm.getCountryIso()))
		{
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
			model.addAttribute("country", addressForm.getCountryIso());
		}
		prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setCheckoutStepLinksForModel(model, getCheckoutStep());
	}

	@Override
	protected AddressValidator getAddressValidator()
	{
		return addressValidator;
	}

}
