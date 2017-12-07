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
package com.amway.apac.auth.handler;

import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.core.Constants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.security.oauth2.provider.endpoint.RedirectResolver;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.amway.apac.auth.security.AmwayJWTTokenProvider;


/**
 * Success handler initializing user settings, restoring or merging the cart and ensuring the cart is handled correctly.
 * Cart restoration is stored in the session since the request coming in is that to j_spring_security_check and will be
 * redirected.
 */
public class AmwayStorefrontAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	private UiExperienceService uiExperienceService;
	private ClientDetailsService clientDetailsService;
	private AmwayJWTTokenProvider jwtTokenProvider;
	private RedirectResolver redirectResolver;
	private GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_" + Constants.USER.ADMIN_USERGROUP.toUpperCase());

	private static final Logger LOG = Logger.getLogger(AmwayStorefrontAuthenticationSuccessHandler.class);

	public AmwayStorefrontAuthenticationSuccessHandler()
	{
		redirectResolver = new DefaultRedirectResolver();
	}

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException
	{
		try
		{
			final String responseType = request.getParameter("response_type");
			final String redirectURL = request.getParameter("redirect_uri");
			final String clientID = request.getParameter("client_id");

			final ClientDetails client = clientDetailsService.loadClientByClientId(clientID);
			final String resolvedRedirect = this.redirectResolver.resolveRedirect(redirectURL, client);
			if (!(StringUtils.isNotEmpty(resolvedRedirect)))
			{
				throw new RedirectMismatchException("A redirectUri must be either supplied or preconfigured in the ClientDetails");
			}
		}
		catch (final Exception exp)
		{
			LOG.error(exp.getMessage(), exp);
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	protected void invalidateSession(final HttpServletRequest request, final HttpServletResponse response) throws IOException
	{
		SecurityContextHolder.getContext().setAuthentication(null);
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	}

	protected boolean isAdminAuthority(final Authentication authentication)
	{
		return CollectionUtils.isNotEmpty(authentication.getAuthorities())
				&& authentication.getAuthorities().contains(adminAuthority);
	}

	/*
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler#
	 * isAlwaysUseDefaultTargetUrl()
	 */
	@Override
	protected boolean isAlwaysUseDefaultTargetUrl()
	{
		return true;
	}

	@Override
	protected String determineTargetUrl(final HttpServletRequest request, final HttpServletResponse response)
	{
		final String responseType = request.getParameter("response_type");
		final String responseMode = request.getParameter("response_mode");
		final String scope = request.getParameter("scope");
		final String state = request.getParameter("state");
		final String redirectUrl = request.getParameter("redirect_uri");
		final String clientId = request.getParameter("client_id");
		final String nonce = request.getParameter("nonce");

		final String redirect = this.getDefaultTargetUrl() + "?response_Type=" + responseType + "&response_Mode=" + responseMode
				+ "&scope=" + scope
				+ "&state=" + state + "&nonce=" + nonce + "&client_id=" + clientId + "&redirect_uri=" + redirectUrl;

		return redirect;
	}

	protected UiExperienceService getUiExperienceService()
	{
		return uiExperienceService;
	}

	@Required
	public void setUiExperienceService(final UiExperienceService uiExperienceService)
	{
		this.uiExperienceService = uiExperienceService;
	}

	/**
	 * @param adminGroup
	 *           the adminGroup to set
	 */
	public void setAdminGroup(final String adminGroup)
	{
		if (StringUtils.isBlank(adminGroup))
		{
			adminAuthority = null;
		}
		else
		{
			adminAuthority = new SimpleGrantedAuthority(adminGroup);
		}
	}

	protected GrantedAuthority getAdminAuthority()
	{
		return adminAuthority;
	}

	/**
	 * @param clientDetailsService
	 *           the clientDetailsService to set
	 */
	public void setClientDetailsService(final ClientDetailsService clientDetailsService)
	{
		this.clientDetailsService = clientDetailsService;
	}

	/**
	 * @param jwtTokenProvider
	 *           the jwtTokenProvider to set
	 */
	public void setJwtTokenProvider(final AmwayJWTTokenProvider jwtTokenProvider)
	{
		this.jwtTokenProvider = jwtTokenProvider;
	}

	/**
	 * @param redirectResolver
	 *           the redirectResolver to set
	 */
	public void setRedirectResolver(final RedirectResolver redirectResolver)
	{
		this.redirectResolver = redirectResolver;
	}
}
