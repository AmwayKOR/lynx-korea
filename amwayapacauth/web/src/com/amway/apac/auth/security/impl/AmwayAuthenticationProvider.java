/**
 *
 */
package com.amway.apac.auth.security.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.amway.apac.auth.security.AcceleratorAuthenticationProvider;
import com.amway.core.enums.AmwayAccountStatus;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;
import com.amway.lynxcore.account.LynxAccountService;
import com.amway.lynxcore.constants.LynxCoreConstants;
import com.amway.lynxcore.services.LynxExternalAuthenticationService;
import com.amway.lynxcore.services.LynxUserService;


/**
 * Authentication provider which extends the default accelerator authentication functionality to add the addition checks
 * on amway account status
 *
 * @author Nitin Jain
 */
public class AmwayAuthenticationProvider extends AcceleratorAuthenticationProvider
{
	private static Logger LOG = Logger.getLogger(AmwayAuthenticationProvider.class);

	private LynxUserService lynxUserService;

	private LynxAccountService lynxAccountService;

	private LynxExternalAuthenticationService lynxExternalAuthenticationService;

	private SessionService sessionService;


	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
		final UserModel userModel;
		try
		{
			userModel = lynxUserService.getUserForUIDAndAmwayAccount(StringUtils.lowerCase(username));
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.warn("Brute force attack attempt for non existing user name: " + username, e);
			throw new BadCredentialsException(messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		UsernamePasswordAuthenticationToken newUPA = null;
		if (authentication instanceof UsernamePasswordAuthenticationToken)
		{
			final UsernamePasswordAuthenticationToken oldUPA = (UsernamePasswordAuthenticationToken) authentication;
			newUPA = new UsernamePasswordAuthenticationToken(userModel.getUid(), oldUPA.getCredentials());
			newUPA.setDetails(oldUPA.getDetails());
		}
		if (lynxExternalAuthenticationService != null)
		{
			if (userModel != null && userModel instanceof CustomerModel && StringUtils.isEmpty(userModel.getEncodedPassword()))
			{
				LOG.info("Password for user found empty. Authenticating using external system.");
				authenticate(newUPA != null ? newUPA : authentication, userModel);
			}
		}

		return super.authenticate(newUPA != null ? newUPA : authentication);
	}

	/**
	 * Authenticate from external system.
	 *
	 * @param authentication
	 *           the authentication
	 * @param userModel
	 *           the user model
	 */
	private void authenticate(final Authentication authentication, final UserModel userModel)
	{
		final AmwayAccountModel account = lynxAccountService.getAmwayAccount((CustomerModel) userModel);
		final boolean authenticateSuccess = lynxExternalAuthenticationService.authenticate(authentication, account);
		if (authenticateSuccess)
		{
			getUserService().setPassword(userModel, authentication.getCredentials().toString(), userModel.getPasswordEncoding());
			getModelService().save(userModel);
			getModelService().refresh(userModel);
		}
	}

	@Override
	protected void additionalAuthenticationChecks(final UserDetails details, final AbstractAuthenticationToken authentication)
			throws AuthenticationException
	{
		final CustomerModel customer = (CustomerModel) lynxUserService.getUserForUID(details.getUsername());
		final AmwayAccountModel account = lynxAccountService.getAmwayAccount(customer);
		final Optional<AmwayAccountModel> optionalAccount = Optional.ofNullable(account);

		if (optionalAccount.isPresent())
		{
			final AmwayAccountModel amwayAccount = optionalAccount.get();
			final AmwayBusinessRestrictionModel restriction = lynxAccountService.getMOPRestriction(amwayAccount);
			if (AmwayAccountStatus.INACTIVE.equals(account.getStatus())
					|| (restriction != null && restriction.getRestrictionId().equals(LynxCoreConstants.RESTRICTED_ID_ACCOUNT_SUSPEND)))
			{
				getSessionService().setAttribute(LynxCoreConstants.ACCOUNT_SUSPENDED, Boolean.TRUE);
				throw new BadCredentialsException("Account is not active or suspended as per restriction 7 ");
			}
			// Check if RENEWAL_PENDING and LOGIC for checking status 8 and D in isABOShipExpired
			if (AmwayAccountStatus.RENEWAL_PENDING.equals(account.getStatus()) && lynxAccountService.isABOShipExpired(account))
			{
				throw new BadCredentialsException("Account Expired ");
			}
		}
		else
		{
			throw new AuthenticationCredentialsNotFoundException("Account is not found");
		}

		super.additionalAuthenticationChecks(details, authentication);
	}


	/**
	 * @param lynxExternalAuthenticationService
	 */
	public void setLynxExternalAuthenticationService(final LynxExternalAuthenticationService lynxExternalAuthenticationService)
	{
		this.lynxExternalAuthenticationService = lynxExternalAuthenticationService;
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
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @param lynxUserService
	 *           the lynxUserService to set
	 */
	public void setLynxUserService(final LynxUserService lynxUserService)
	{
		this.lynxUserService = lynxUserService;
	}

	/**
	 * @param lynxAccountService
	 *           the lynxAccountService to set
	 */
	public void setLynxAccountService(final LynxAccountService lynxAccountService)
	{
		this.lynxAccountService = lynxAccountService;
	}

}
