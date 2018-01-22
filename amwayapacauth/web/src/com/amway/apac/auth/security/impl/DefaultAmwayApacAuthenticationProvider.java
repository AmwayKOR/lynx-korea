package com.amway.apac.auth.security.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.amway.apac.auth.security.AcceleratorAuthenticationProvider;
import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.user.services.AmwayApacUserService;
import com.amway.core.enums.AmwayAccountStatus;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;


/**
 * Authentication provider which extends the default accelerator authentication functionality to add the addition checks
 * on amway account status
 *
 * @author Shubham Maheshwari
 */
public class DefaultAmwayApacAuthenticationProvider extends AcceleratorAuthenticationProvider
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacAuthenticationProvider.class);

	/** The Constant credentials error string. */
	private static final String BAD_CREDENTIALS = "Bad credentials";

	/** The Constant NONE_PROVIDED. */
	private static final String NONE_PROVIDED = "NONE_PROVIDED";

	/** The session service. */
	private SessionService sessionService;

	/** The amway apac user service. */
	private AmwayApacUserService amwayApacUserService;

	/** The amway apac account service. */
	private AmwayApacAccountService amwayApacAccountService;


	/**
	 * Checks authentication for provided authentication data
	 *
	 * @param authentication
	 * @return authentication
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		final String username = (authentication.getPrincipal() == null) ? NONE_PROVIDED : authentication.getName();
		final UserModel userModel;
		try
		{
			userModel = getAmwayApacUserService().getUserForUIDAndAmwayAccount(StringUtils.lowerCase(username));
		}
		catch (final UnknownIdentifierException e)
		{
			LOGGER.warn(new StringBuilder(200).append("Brute force attack attempt for non existing user name: ").append(username)
					.toString(), e);
			throw new BadCredentialsException(messages.getMessage("CoreAuthenticationProvider.badCredentials", BAD_CREDENTIALS));
		}
		UsernamePasswordAuthenticationToken newUPA = null;
		if (authentication instanceof UsernamePasswordAuthenticationToken)
		{
			final UsernamePasswordAuthenticationToken oldUPA = (UsernamePasswordAuthenticationToken) authentication;
			newUPA = new UsernamePasswordAuthenticationToken(userModel.getUid(), oldUPA.getCredentials());
			newUPA.setDetails(oldUPA.getDetails());
		}
		return super.authenticate(newUPA != null ? newUPA : authentication);
	}

	/**
	 * Additional authentication for user details provided
	 *
	 * @param details
	 *           User details
	 * @param authentication
	 * @throws AuthenticationException
	 */
	@Override
	protected void additionalAuthenticationChecks(final UserDetails details, final AbstractAuthenticationToken authentication)
			throws AuthenticationException
	{
		final CustomerModel customer = (CustomerModel) getAmwayApacUserService().getUserForUID(details.getUsername());
		final AmwayAccountModel account = getAmwayApacAccountService().getAmwayAccount(customer);
		final Optional<AmwayAccountModel> optionalAccount = Optional.ofNullable(account);

		if (optionalAccount.isPresent())
		{
			final AmwayAccountModel amwayAccount = optionalAccount.get();
			final AmwayBusinessRestrictionModel restriction = getAmwayApacAccountService().getMOPRestriction(amwayAccount);
			if (AmwayAccountStatus.INACTIVE.equals(account.getStatus()) || (restriction != null
					&& restriction.getRestrictionId().equals(AmwayapacCoreConstants.RESTRICTED_ID_ACCOUNT_SUSPEND)))
			{
				getSessionService().setAttribute(AmwayapacCoreConstants.ACCOUNT_SUSPENDED, Boolean.TRUE);
				throw new BadCredentialsException("Account is not active or suspended as per restriction 7 ");
			}
		}
		else
		{
			throw new AuthenticationCredentialsNotFoundException("Account is not found");
		}
		super.additionalAuthenticationChecks(details, authentication);
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the amwayApacUserService
	 */
	public AmwayApacUserService getAmwayApacUserService()
	{
		return amwayApacUserService;
	}

	/**
	 * @param amwayApacUserService
	 *           the amwayApacUserService to set
	 */
	@Required
	public void setAmwayApacUserService(final AmwayApacUserService amwayApacUserService)
	{
		this.amwayApacUserService = amwayApacUserService;
	}

	/**
	 * @return the amwayApacAccountService
	 */
	public AmwayApacAccountService getAmwayApacAccountService()
	{
		return amwayApacAccountService;
	}

	/**
	 * @param amwayApacAccountService
	 *           the amwayApacAccountService to set
	 */
	@Required
	public void setAmwayApacAccountService(final AmwayApacAccountService amwayApacAccountService)
	{
		this.amwayApacAccountService = amwayApacAccountService;
	}
}
