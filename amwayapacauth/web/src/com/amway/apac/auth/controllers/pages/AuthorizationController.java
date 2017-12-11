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
package com.amway.apac.auth.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.apac.auth.controllers.ControllerConstants;
import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;
import com.amway.apac.auth.security.AmwayJWTTokenProvider;
import com.amway.apac.auth.validation.AmwayIdpLoginValidationService;


/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@RequestMapping(value = "/oauth2/default/v1/authorize")
public class AuthorizationController extends AbstractLoginPageController
{
	private HttpSessionRequestCache httpSessionRequestCache;

	@Resource(name = "jWTokenProvider")
	private AmwayJWTTokenProvider jwtTokenProvider;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "idpLoginValidationService")
	private AmwayIdpLoginValidationService idpLoginValidationService;


	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
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
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}


	@Resource(name = "httpSessionRequestCache")
	public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache)
	{
		this.httpSessionRequestCache = accHttpSessionRequestCache;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doAuthorize(@RequestHeader(value = "referer", required = false) final String referer,
			@RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException
	{
		if (!loginError)
		{
			storeReferer(referer, request, response);
		}

		final Collection<String> errors = idpLoginValidationService.validationLoginRequest(request);

		if (CollectionUtils.isNotEmpty(errors))
		{
			model.addAttribute("loginError", Boolean.valueOf(true));
			errors.stream().forEach(error -> GlobalMessages.addErrorMessage(model, error));
			return IDPLogin.ERROR_PAGE;
		}

		model.addAttribute(IDPLogin.RESPONSE_TYPE, request.getParameter(IDPLogin.RESPONSE_TYPE));
		model.addAttribute(IDPLogin.CLIENT_ID, request.getParameter(IDPLogin.CLIENT_ID));
		model.addAttribute(IDPLogin.RESPONSE_MODE, request.getParameter(IDPLogin.RESPONSE_MODE));
		model.addAttribute(IDPLogin.SCOPE, request.getParameter(IDPLogin.SCOPE));
		model.addAttribute(IDPLogin.NONCE, request.getParameter(IDPLogin.NONCE));
		model.addAttribute(IDPLogin.STATE, request.getParameter(IDPLogin.STATE));
		model.addAttribute(IDPLogin.REDIRECT_URI, request.getParameter(IDPLogin.REDIRECT_URI));

		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		if (!userService.isAnonymousUser(customer))
		{
			return REDIRECT_PREFIX + ROOT;
		}
		return getDefaultLoginPage(loginError, session, model);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = IDPLogin.IDP_SESSIONS_ME_URL)
	public String doValidateSession(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, IOException
	{
		final JaloSession jaloSession = (JaloSession) session.getAttribute(IDPLogin.JALOSESSION);
		final String userId = jaloSession.getUser().getUid();
		if (!"anonymous".equals(userId))
		{
			return jwtTokenProvider.createJWToken(userId, new Date(session.getCreationTime()), request);
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = IDPLogin.IDP_REFRESH_URL)
	public String doRefreshSession(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, IOException
	{
		final JaloSession jaloSession = (JaloSession) session.getAttribute(IDPLogin.JALOSESSION);
		final String userId = jaloSession.getUser().getUid();
		if (!"anonymous".equals(userId))
		{

			return jwtTokenProvider.createJWToken(userId, new Date(), request);
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = IDPLogin.IDP_SESSIONS_ME_URL)
	public String doInValidateSession(final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, IOException
	{
		final JaloSession jaloSession = (JaloSession) session.getAttribute(IDPLogin.JALOSESSION);
		final String userId = jaloSession.getUser().getUid();
		if (!"anonymous".equals(userId))
		{
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			session.invalidate();
			return null;
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	protected void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response)
	{
		if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login")
				&& StringUtils.contains(referer, request.getServerName()))
		{
			httpSessionRequestCache.saveRequest(request, response);
		}
	}

	/**
	 * @param jwtTokenProvider
	 *           the jwtTokenProvider to set
	 */
	public void setJwtTokenProvider(final AmwayJWTTokenProvider jwtTokenProvider)
	{
		this.jwtTokenProvider = jwtTokenProvider;
	}
}
