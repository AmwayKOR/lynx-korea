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
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.jalo.JaloSession;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.auth.controllers.ControllerConstants;
import com.amway.apac.auth.security.AmwayJWTTokenProvider;


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
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session,
			final RedirectAttributes redirectModel)
			throws CMSItemNotFoundException
	{
		if (!loginError)
		{
			storeReferer(referer, request, response);
		}

		final String responseType = request.getParameter("response_type");
		final String responseMode = request.getParameter("response_mode");
		final String scope = request.getParameter("scope");
		final String state = request.getParameter("state");
		final String redirectUrl = request.getParameter("redirect_uri");
		final String clientId = request.getParameter("client_id");
		final String nonce = request.getParameter("nonce");

		model.addAttribute("response_type", responseType);
		model.addAttribute("client_id", clientId);
		model.addAttribute("responseMode", responseMode);
		model.addAttribute("scope", scope);
		model.addAttribute("nonce", nonce);
		model.addAttribute("state", state);

		if (StringUtils.isNotBlank(redirectUrl))
		{
			model.addAttribute("redirect_uri", redirectUrl);
		}
		else
		{
			model.addAttribute("redirect_uri", referer);
		}

		return getDefaultLoginPage(loginError, session, model);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/sessions/me")
	public String doValidateSession(@RequestHeader(value = "referer", required = false) final String referer,
			final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, IOException
	{
		final JaloSession jaloSession = (JaloSession) session.getAttribute("jalosession");
		final String userId = jaloSession.getUser().getUid();
		if (!"anonymous".equals(userId))
		{
			return jwtTokenProvider.createJWToken(userId, new Date(session.getCreationTime()), request);
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/sessions/me/lifecycle/refresh")
	public String doRefreshSession(@RequestHeader(value = "referer", required = false) final String referer,
			final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, IOException
	{
		final JaloSession jaloSession = (JaloSession) session.getAttribute("jalosession");
		final String userId = jaloSession.getUser().getUid();
		if (!"anonymous".equals(userId))
		{

			return jwtTokenProvider.createJWToken(userId, new Date(), request);
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "/sessions/me")
	public String doInValidateSession(@RequestHeader(value = "referer", required = false) final String referer,
			final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, IOException
	{
		final JaloSession jaloSession = (JaloSession) session.getAttribute("jalosession");
		final String userId = jaloSession.getUser().getUid();
		if (!"anonymous".equals(userId))
		{
			response.setStatus(response.SC_NO_CONTENT);
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
