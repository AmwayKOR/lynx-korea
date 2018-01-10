/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.sso.web.controller;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.persistence.security.EJBPasswordEncoderNotFoundException;
import de.hybris.platform.samlsinglesignon.SSOUserService;
import de.hybris.platform.samlsinglesignon.constants.SamlsinglesignonConstants;
import de.hybris.platform.util.Config;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amway.sso.web.utils.SAMLUtil;
import com.amway.sso.web.security.AuthenticationHandler;
import com.amway.sso.web.utils.DefaultSAMLUtil;
import com.amway.sso.web.utils.SAMLUtil;



@Controller
@RequestMapping("/saml/**")
public class AmwayRedirectionController
{
	private final static Logger LOGGER = Logger.getLogger(AmwayRedirectionController.class);

	private static final String ERROR_PAGE = "/error";
	private static final String USERGROUP_PARAM_NAME = "usergroup";
	private static final String SSO_SITE_REDIRECT_URL = "sso.%s.redirect.url";
	private static final String SSO_SITE_REQUEST_ATTRIBUTE = "site";

	@Resource(name = "ssoUserService")
	private SSOUserService userService;

	private SAMLUtil samlUtil;

	@Resource(name = "amwaySAMLAuthenticationHandler")
	private AuthenticationHandler authenticationHandler;

	@RequestMapping(method = RequestMethod.GET)
	public String redirect(final HttpServletResponse response, final HttpServletRequest request)
	{

		final SAMLCredential credential = (SAMLCredential) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		final String referenceURL = resolveRedirectUrl(request);

		try
		{
			final Collection<String> roles = samlUtil.getCustomAttributes(credential, StringUtils
					.defaultIfEmpty(Config.getParameter(SamlsinglesignonConstants.SSO_USERGROUP_KEY), USERGROUP_PARAM_NAME));

			final UserModel user = userService.getOrCreateSSOUser(samlUtil.getUserId(credential), samlUtil.getUserName(credential),
					roles);

			storeTokenFromSaml(response, user);
			authenticationHandler.handle(credential, user);

			//continue to the redirection and token will be generated only in case the user has valid group
			final String redirectURL = resolveSiteRedirectBaseUrl(request);

			return SamlsinglesignonConstants.REDIRECT_PREFIX + redirectURL + referenceURL;

		}
		catch (final IllegalArgumentException e)
		{
			//the user is not belonging to any valid group
			LOGGER.error(e);
		}

		catch (final Exception e)
		{
			//something went wrong and we need to log that
			LOGGER.error(e);
		}

		return ERROR_PAGE;
	}

	private String resolveSiteRedirectBaseUrl(final HttpServletRequest request)
	{
		String ssoRedirectPropertyKey = SamlsinglesignonConstants.SSO_REDIRECT_URL;

		final String site = request.getParameter(SSO_SITE_REQUEST_ATTRIBUTE);
		if (StringUtils.isNotBlank(site))
		{
			ssoRedirectPropertyKey = String.format(SSO_SITE_REDIRECT_URL, site);
		}

		return StringUtils.defaultIfEmpty(Config.getParameter(ssoRedirectPropertyKey),
				SamlsinglesignonConstants.DEFAULT_REDIRECT_URL);
	}

	private String resolveRedirectUrl(final HttpServletRequest request)
	{
		String referenceURL = StringUtils.substringAfter(request.getServletPath(), "/saml/");

		if (!StringUtils.isEmpty(request.getQueryString()))
		{
			referenceURL += request.getQueryString().isEmpty() ? "" : "?" + request.getQueryString();
		}

		return referenceURL;
	}

	public void storeTokenFromSaml(final HttpServletResponse response, final UserModel user)
	{
		try
		{
			final String cookiePath = StringUtils.defaultIfEmpty(Config.getParameter(SamlsinglesignonConstants.SSO_COOKIE_PATH),
					SamlsinglesignonConstants.DEFAULT_COOKIE_PATH);

			final String cookieMaxAgeStr = StringUtils.defaultIfEmpty(
					Config.getParameter(SamlsinglesignonConstants.SSO_COOKIE_MAX_AGE),
					String.valueOf(SamlsinglesignonConstants.DEFAULT_COOKIE_MAX_AGE));

			int cookieMaxAge;

			if (!NumberUtils.isNumber(cookieMaxAgeStr))
			{
				cookieMaxAge = SamlsinglesignonConstants.DEFAULT_COOKIE_MAX_AGE;
			}
			else
			{
				cookieMaxAge = Integer.valueOf(cookieMaxAgeStr).intValue();
			}

			UserManager.getInstance().storeLoginTokenCookie(
					//
					StringUtils.defaultIfEmpty(Config.getParameter(SamlsinglesignonConstants.SSO_COOKIE_NAME),
							SamlsinglesignonConstants.SSO_DEFAULT_COOKIE_NAME), // cookie name
					user.getUid(), // user id
					"en", // language iso code
					null, // credentials to check later
					cookiePath, // cookie path
					StringUtils.defaultIfEmpty(Config.getParameter(SamlsinglesignonConstants.SSO_COOKIE_DOMAIN),
							SamlsinglesignonConstants.SSO_DEFAULT_COOKIE_DOMAIN), // cookie domain
					true, // secure cookie
					cookieMaxAge, // max age in seconds
					response);
		}
		catch (final EJBPasswordEncoderNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
}
