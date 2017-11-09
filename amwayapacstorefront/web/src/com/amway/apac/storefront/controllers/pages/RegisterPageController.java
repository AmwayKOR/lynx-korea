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

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractRegisterPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Resource(name = "termValidator")
	private AmwayApacTermValidator termValidator;

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

	@RequestMapping(value = "/simple-terms", method = RequestMethod.GET)
	public String simpleTerms(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("termForm", new AmwayApacTermForm());
		return setCMSPage(model, SIMPLE_TERM_PAGE);
	}

	@RequestMapping(value = "/multiple-terms", method = RequestMethod.GET)
	public String multipleTerms(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("termForm", new AmwayApacTermForm());
		return setCMSPage(model, MULTIPLE_TERM_PAGE);
	}

	/**
	 * Sample url mapping to test validation. Remove/change to function call on confirmed flow templates:
	 * accountRegisterTermsPage.jsp
	 *
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/validate-terms", method = RequestMethod.POST)
	public String validateTerms(final AmwayApacTermForm termForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{

		getTermValidator().validate(termForm, bindingResult);
		model.addAttribute("termForm", termForm);

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			return setCMSPage(model, MULTIPLE_TERM_PAGE);
		}
		else
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, "account.terms.success", null);
			return setCMSPage(model, MULTIPLE_TERM_PAGE);
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
}
