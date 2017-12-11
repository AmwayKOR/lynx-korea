/**
 *
 */
package com.amway.apac.auth.validation.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.security.oauth2.provider.endpoint.RedirectResolver;

import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;
import com.amway.apac.auth.validation.AmwayIdpLoginValidationService;


/**
 *
 */
public class AmwayIdpLoginValidationServiceImpl implements AmwayIdpLoginValidationService
{
	private static final Logger LOG = Logger.getLogger(AmwayIdpLoginValidationServiceImpl.class);
	private ClientDetailsService clientDetailsService;
	private final RedirectResolver redirectResolver;

	public AmwayIdpLoginValidationServiceImpl()
	{
		redirectResolver = new DefaultRedirectResolver();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.auth.validation.AmwayIdpLoginValidationService#validationLoginRequest(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public Collection<String> validationLoginRequest(final HttpServletRequest request)
	{
		final Set<String> errors = new HashSet<>();

		// validate response Type it should be token
		final String responseType = request.getParameter(IDPLogin.RESPONSE_TYPE);
		if (!IDPLogin.TOKEN.equals(responseType))
		{
			errors.add("Invalid Response type : Valid response type is 'token'");
		}

		//validate client id
		final String clientId = request.getParameter(IDPLogin.CLIENT_ID);
		final ClientDetails client = validateClientId(clientId);
		if (null == client)
		{
			errors.add("Invalid Client Id : No client exists");
		}

		// validate redirect url
		final String redirectUrl = request.getParameter(IDPLogin.REDIRECT_URI);
		if (validateRedirectUrl(client, redirectUrl))
		{
			errors.add("Invalid redirect url : redirect url is invalid");
		}
		return errors;
	}

	/**
	 * @param clientId
	 * @param redirectUrl
	 */
	private boolean validateRedirectUrl(final ClientDetails client, final String redirectUrl)
	{
		boolean result = false;
		try
		{
			final String resolvedRedirect = redirectResolver.resolveRedirect(redirectUrl, client);
			if (!(StringUtils.isNotEmpty(resolvedRedirect)))
			{
				throw new RedirectMismatchException("A redirectUri must be either supplied or preconfigured in the ClientDetails");
			}
		}
		catch (final Exception exp)
		{
			result = true;
			LOG.error(exp.getMessage(), exp);
		}
		return result;
	}

	/**
	 * @param request
	 * @return
	 */
	private ClientDetails validateClientId(final String clientId)
	{
		ClientDetails client = null;
		try
		{
			client = clientDetailsService.loadClientByClientId(clientId);
		}
		catch (final NoSuchClientException exp)
		{
			LOG.error(exp.getMessage(), exp);
		}
		return client;
	}

	/**
	 * @param clientDetailsService
	 *           the clientDetailsService to set
	 */
	public void setClientDetailsService(final ClientDetailsService clientDetailsService)
	{
		this.clientDetailsService = clientDetailsService;
	}
}
