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
import de.hybris.platform.acceleratorstorefrontcommons.security.StorefrontAuthenticationSuccessHandler;
import de.hybris.platform.core.Constants;
import de.hybris.platform.util.Config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.amway.core.model.AmwayAccountModel;
import com.amway.lynxcore.strategies.LynxCustomerNameStrategy;
import com.amway.lynxfacades.customer.LynxCustomerFacade;


/**
 * Success handler initializing user settings, restoring or merging the cart and ensuring the cart is handled correctly.
 * Cart restoration is stored in the session since the request coming in is that to j_spring_security_check and will be
 * redirected.
 */
public class AmwayStorefrontAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	/**
	 * 
	 */
	private static final String AMWAY_IDP_JWT_SECRET_KEY = "amway.idp.jwt.secret.key";
	/**
	 * 
	 */
	private static final String AMWAY_IDP_JWT_TTLMILES = "amway.idp.jwt.ttlmiles";
	private LynxCustomerFacade lynxCustomerFacade;
	private LynxCustomerNameStrategy customerNameStrategy;
	private UiExperienceService uiExperienceService;
	private ClientDetailsService clientDetailsService;
	private GrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_" + Constants.USER.ADMIN_USERGROUP.toUpperCase());

	private static final Logger LOG = Logger.getLogger(StorefrontAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException
	{
		try
		{
			final String responseType = request.getParameter("response_type");
			final String redirectURL = request.getParameter("redirect_uri");
			final String clientID = request.getParameter("client_id");

			final String amwayAccountId = getAmwayAccountId((String) authentication.getPrincipal());

			final AmwayAccountModel amwayAccount = lynxCustomerFacade.getAmwayAccountById(amwayAccountId);

			if (null != amwayAccount)
			{
				final ClientDetails client = clientDetailsService.loadClientByClientId(clientID);

				if (null != client)
				{
					if ("token".equals(responseType))
					{
						final String token = createJWT(amwayAccount);
						request.setAttribute("JWT", token);

						if (null == redirectURL)
						{
							throw new InvalidRequestException("Cannot approve request when no redirect URI is provided.");
						}
					}
				}
			}
		}
		catch (final Exception exp)
		{
			LOG.error(exp.getMessage());
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * @param principal
	 * @return
	 */
	private String getAmwayAccountId(final String amwayAccountId)
	{
		if (amwayAccountId.contains("-"))
		{
			return amwayAccountId.substring((amwayAccountId.indexOf("-") + 1), (amwayAccountId.lastIndexOf("-")));
		}
		return amwayAccountId;
	}

	private String createJWT(final AmwayAccountModel amwayAccount)
	{
		final Long ttlMillis = Long.valueOf(Config.getParameter(AMWAY_IDP_JWT_TTLMILES));
		//The JWT signature algorithm we will be using to sign the token
		final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		final long nowMillis = System.currentTimeMillis();
		final Date now = new Date(nowMillis);

		//We will sign our JWT with our ApiKey secret
		final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Config.getParameter(AMWAY_IDP_JWT_SECRET_KEY));
		final Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		final Map<String, Object> claims = new HashMap<>();
		claims.put("name", amwayAccount.getName());
		claims.put("locale", amwayAccount.getAccountPreferences().getPreferedLanguage().getIsocode());
		claims.put("preferred_username", amwayAccount.getPrimaryParty().getCustomerID());

		final String[] names = customerNameStrategy.splitName(amwayAccount.getPrimaryParty().getName());

		if (null != names && names.length > 0)
		{
			claims.put("given_name", names[0]);
		}

		if (null != names && names.length == 2)
		{
			claims.put("family_name", names[1]);
		}

		claims.put("auth_time", Long.valueOf(new Date().getTime()));

		//Let's set the JWT Claims
		final JwtBuilder builder = Jwts.builder().setId(amwayAccount.getCode())
				.setIssuedAt(now)
				.setSubject("435473587345863475989090u")
				.setIssuer("/oauth2/default/v1/authorize")
				.addClaims(claims)
				.signWith(signatureAlgorithm, signingKey);

		//if it has been specified, let's add the expiration
		if (ttlMillis >= 0)
		{
			final long expMillis = nowMillis + ttlMillis;
			final Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
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
		String targetUrl = request.getParameter("redirect_uri");
		final String token = (String) request.getAttribute("JWT");

		targetUrl = targetUrl + "?access_token=" + token;
		return targetUrl;
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
	 * @param lynxCustomerFacade
	 *           the lynxCustomerFacade to set
	 */
	public void setLynxCustomerFacade(final LynxCustomerFacade lynxCustomerFacade)
	{
		this.lynxCustomerFacade = lynxCustomerFacade;
	}

	/**
	 * @param customerNameStrategy
	 *           the customerNameStrategy to set
	 */
	public void setCustomerNameStrategy(final LynxCustomerNameStrategy customerNameStrategy)
	{
		this.customerNameStrategy = customerNameStrategy;
	}

}
