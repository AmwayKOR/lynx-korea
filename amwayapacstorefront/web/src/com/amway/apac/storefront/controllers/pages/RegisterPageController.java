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
package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractRegisterPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.apac.storefront.forms.AmwayApacTermForm;
import com.amway.apac.storefront.forms.AmwayApacTermValidator;


/**
 * Register Controller for mobile. Handles login and register for the account flow.
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterPageController extends AbstractRegisterPageController
{
	private HttpSessionRequestCache httpSessionRequestCache;

	private static final String FORM_GLOBAL_ERROR = "form.global.error";

	// CMS Pages
	private static final String SIMPLE_TERM_PAGE = "simple-terms";
	private static final String MULTIPLE_TERM_PAGE = "multiple-terms";
	private static final String REDIRECT_TO_MULTIPLE_TERMS = REDIRECT_PREFIX + "/register/multiple-terms";

	@Resource(name = "termValidator")
	private AmwayApacTermValidator termValidator;

	@Resource(name = "registrationBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder registrationBreadcrumbBuilder;

	protected AmwayApacTermValidator getTermValidator()
	{
		return termValidator;
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("register");
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (httpSessionRequestCache.getRequest(request, response) != null)
		{
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		return "/";
	}

	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountRegisterPage;
	}

	@Resource(name = "httpSessionRequestCache")
	public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache)
	{
		this.httpSessionRequestCache = accHttpSessionRequestCache;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doRegister(final Model model) throws CMSItemNotFoundException
	{
		return getDefaultRegistrationPage(model);
	}

	@RequestMapping(value = "/newcustomer", method = RequestMethod.POST)
	public String doRegister(final RegisterForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel)
					throws CMSItemNotFoundException
	{
		getRegistrationValidator().validate(form, bindingResult);
		return processRegisterUserRequest(null, form, bindingResult, model, request, response, redirectModel);
	}

	/**
	 * Simple Registration Terms page loading controller.
	 *
	 * @param model
	 *           view model
	 * @return view
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/simple-terms", method = RequestMethod.GET)
	public String simpleTerms(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute(ControllerConstants.ModelParameters.TERM_FORM_STRING, new AmwayApacTermForm());
		model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR, getRegistrationBreadcrumbBuilder()
				.getBreadcrumbs((ControllerConstants.GeneralConstants.REGISTER_SIMPLE_TERMS_PAGE_BREADCRUMB_KEY)));
		setCMSPage(model, SIMPLE_TERM_PAGE);
		return ControllerConstants.Views.Pages.Registration.RegistrationSimpleTerms;
	}

	/**
	 * Simple Terms Form validating controller.
	 *
	 * @param termForm
	 *           Simple Terms Form
	 * @param bindingResult
	 *           Binding Results
	 * @param model
	 *           View Model
	 * @return view
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/simple-terms", method = RequestMethod.POST)
	public String validateSimpleTerms(final AmwayApacTermForm termForm, final BindingResult bindingResult, final Model model)
			throws CMSItemNotFoundException
	{

		getTermValidator().validate(termForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			model.addAttribute(ControllerConstants.ModelParameters.TERM_FORM_STRING, termForm);
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR, getRegistrationBreadcrumbBuilder()
					.getBreadcrumbs((ControllerConstants.GeneralConstants.REGISTER_SIMPLE_TERMS_PAGE_BREADCRUMB_KEY)));
			setCMSPage(model, SIMPLE_TERM_PAGE);
			return ControllerConstants.Views.Pages.Registration.RegistrationSimpleTerms;
		}
		else
		{
			return REDIRECT_TO_MULTIPLE_TERMS;
		}
	}

	/**
	 * Multiple Registration Terms page loading controller.
	 *
	 * @param model
	 *           view model
	 * @return view
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/multiple-terms", method = RequestMethod.GET)
	public String multipleTerms(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute(ControllerConstants.ModelParameters.TERM_FORM_STRING, new AmwayApacTermForm());
		model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR, getRegistrationBreadcrumbBuilder()
				.getBreadcrumbs((ControllerConstants.GeneralConstants.REGISTER_MULTIPLE_TERMS_PAGE_BREADCRUMB_KEY)));
		return setCMSPage(model, MULTIPLE_TERM_PAGE);
	}

	/**
	 * Multiple Terms Form validating controller.
	 *
	 * @param termForm
	 *           Multiple Terms Registration form
	 * @param bindingResult
	 *           Binding Results
	 * @param model
	 *           View Model
	 * @return vie
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/multiple-terms", method = RequestMethod.POST)
	public String validateMultipleTerms(final AmwayApacTermForm termForm, final BindingResult bindingResult, final Model model)
			throws CMSItemNotFoundException
	{

		getTermValidator().validate(termForm, bindingResult);
		model.addAttribute(ControllerConstants.ModelParameters.TERM_FORM_STRING, termForm);
		if ((bindingResult.hasErrors()) || (BooleanUtils.isNotTrue(termForm.getVerified())))
		{
			model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR, getRegistrationBreadcrumbBuilder()
					.getBreadcrumbs((ControllerConstants.GeneralConstants.REGISTER_MULTIPLE_TERMS_PAGE_BREADCRUMB_KEY)));
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			return setCMSPage(model, MULTIPLE_TERM_PAGE);
		}
		else
		{
			return REDIRECT_TO_MULTIPLE_TERMS;
		}
	}

	protected String setCMSPage(final Model model, final String cmsPage) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(cmsPage));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(cmsPage));
		storeContentPageTitleInModel(model, getPageTitle(getContentPageForLabelOrId(cmsPage).getTitle()));
		return getViewForPage(model);
	}

	protected String getPageTitle(final String string)
	{
		return getPageTitleResolver().resolveHomePageTitle(string);
	}

	/**
	 * @return the registrationBreadcrumbBuilder
	 */
	public ResourceBreadcrumbBuilder getRegistrationBreadcrumbBuilder()
	{
		return registrationBreadcrumbBuilder;
	}

}
