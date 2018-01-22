package com.amway.apac.auth.validation.impl;

import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.CLIENT_ID;
import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.REDIRECT_URI;
import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.RESPONSE_TYPE;
import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.TOKEN;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.security.oauth2.provider.endpoint.RedirectResolver;

import com.amway.apac.auth.validation.AmwayApacIdpLoginValidationService;


/**
 * Default implementation of {@link AmwayApacIdpLoginValidationService}
 */
public class DefaultAmwayApacIdpLoginValidationService implements AmwayApacIdpLoginValidationService
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacIdpLoginValidationService.class);

	/** The client details service. */
	private ClientDetailsService clientDetailsService;

	/** The redirect resolver. */
	private final RedirectResolver redirectResolver;

	/**
	 * Instantiates a new default amway apac idp login validation service.
	 */
	public DefaultAmwayApacIdpLoginValidationService()
	{
		redirectResolver = new DefaultRedirectResolver();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> validationLoginRequest(final HttpServletRequest request)
	{
		final Set<String> errors = new HashSet<>();

		// validate response Type it should be token
		final String responseType = request.getParameter(RESPONSE_TYPE);
		if (!TOKEN.equals(responseType))
		{
			errors.add("Invalid Response type : Valid response type is 'id_token'");
		}

		//validate client id
		final String clientId = request.getParameter(CLIENT_ID);
		final ClientDetails client = validateClientId(clientId);
		if (null == client)
		{
			errors.add(new StringBuilder(100).append("Invalid Client Id : No client exists with client id : ").append(clientId)
					.toString());
		}

		// validate redirect url
		final String redirectUrl = request.getParameter(REDIRECT_URI);
		if (validateRedirectUrl(client, redirectUrl))
		{
			errors.add("Invalid redirect url : redirect url is invalid");
		}
		return errors;
	}

	/**
	 * Validate redirect url.
	 *
	 * @param client
	 * @param redirectUrl
	 * @return true, if successful
	 */
	protected boolean validateRedirectUrl(final ClientDetails client, final String redirectUrl)
	{
		boolean result = false;
		try
		{
			final String resolvedRedirect = getRedirectResolver().resolveRedirect(redirectUrl, client);
			if (!(StringUtils.isNotEmpty(resolvedRedirect)))
			{
				throw new RedirectMismatchException("A redirectUri must be either supplied or preconfigured in the ClientDetails");
			}
		}
		catch (final Exception exp)
		{
			result = true;
			LOGGER.error(exp.getMessage(), exp);
		}
		return result;
	}

	/**
	 * Validate client id.
	 *
	 * @param clientId
	 * @return client details
	 */
	protected ClientDetails validateClientId(final String clientId)
	{
		ClientDetails client = null;
		try
		{
			client = getClientDetailsService().loadClientByClientId(clientId);
		}
		catch (final NoSuchClientException exp)
		{
			LOGGER.error(exp.getMessage(), exp);
		}
		return client;
	}

	/**
	 * Sets the client details service.
	 *
	 * @param clientDetailsService
	 *           the clientDetailsService to set
	 */
	@Required
	public void setClientDetailsService(final ClientDetailsService clientDetailsService)
	{
		this.clientDetailsService = clientDetailsService;
	}

	/**
	 * Gets the client details service.
	 *
	 * @return the clientDetailsService
	 */
	public ClientDetailsService getClientDetailsService()
	{
		return clientDetailsService;
	}

	/**
	 * Gets the redirect resolver.
	 *
	 * @return the redirectResolver
	 */
	public RedirectResolver getRedirectResolver()
	{
		return redirectResolver;
	}
}
