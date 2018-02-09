package com.amway.apac.auth.security.impl;

import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.AMWAY_IDP_JWT_KEYID;
import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.AMWAY_IDP_JWT_TTLMILES;
import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.CLIENT_ID;
import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.ISSUER;
import static com.amway.apac.auth.controllers.ControllerConstants.IDPLogin.NONCE;

import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.auth.controllers.ControllerConstants.JWT;
import com.amway.apac.auth.security.AmwayApacJWTCreator;
import com.amway.apac.auth.security.AmwayApacJWTKeyMaker;
import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.core.model.AmwayAccountModel;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;


/**
 * Default implementation of {@link AmwayApacJWTCreator}
 */
public class DefaultAmwayApacJWTCreator implements AmwayApacJWTCreator
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacJWTCreator.class);

	/** The user service. */
	private UserService userService;

	/** The Amway APAC account service. */
	private AmwayApacAccountService accountService;

	/** The Amway APAC jwt key maker. */
	private AmwayApacJWTKeyMaker jwtKeyMaker;

	/** The customer name strategy. */
	private CustomerNameStrategy customerNameStrategy;

	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String createJWToken(final String userId, final Date creationDate, final HttpServletRequest request)
	{
		try
		{
			final CustomerModel customer = getUserService().getUserForUID(userId, CustomerModel.class);
			if (null != customer)
			{
				// 	get AmwayAccount
				final AmwayAccountModel amwayAccount = getAccountService().getAmwayAccount(customer);
				// Create RSA-signer with the private key
				final JWSSigner signer = new RSASSASigner(getJwtKeyMaker().getPrivateKey());
				final SignedJWT signedJWT = new SignedJWT(
						new JWSHeader(JWSAlgorithm.RS256, null, null, null, null, null, null, null, null, null,
								Config.getParameter(AMWAY_IDP_JWT_KEYID), null, null),
						prepareJWTClaimsSet(amwayAccount, creationDate, request));
				// Compute the RSA signature
				signedJWT.sign(signer);
				final String jwt = signedJWT.serialize();
				if (LOGGER.isDebugEnabled())
				{
					LOGGER.debug("TOKEN :: " + jwt);
				}

				return jwt;
			}
		}
		catch (final JOSEException exp)
		{
			LOGGER.error(exp.getMessage(), exp);
		}

		return null;
	}

	/**
	 * Prepare JWT claims set.
	 *
	 * @param amwayAccount
	 *           the amway account
	 * @param creationDate
	 *           the creation date
	 * @param request
	 *           the request
	 * @return - JWTClaimsSet
	 */
	protected JWTClaimsSet prepareJWTClaimsSet(final AmwayAccountModel amwayAccount, final Date creationDate,
			final HttpServletRequest request)
	{
		final String[] names = getCustomerNameStrategy().splitName(amwayAccount.getPrimaryParty().getName());
		final Long ttlMillis = Long.valueOf(Config.getParameter(AMWAY_IDP_JWT_TTLMILES));
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(creationDate);
		calendar.add(Calendar.MILLISECOND, ttlMillis.intValue());

		// Add 2 minutes in AuthTime and Updated Time
		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date afterAddingTwoMins=new Date(t + (2 * ONE_MINUTE_IN_MILLIS));

		// Prepare & return JWT with claims set
		return new JWTClaimsSet.Builder().audience(request.getParameter(CLIENT_ID)).subject(JWT.ALICE).issueTime(new Date())
				.notBeforeTime(new Date()).issuer(Config.getParameter(ISSUER)).claim(JWT.NAME, amwayAccount.getName())
				.claim(JWT.LOCALE, request.getLocale().getLanguage() + "-" + request.getLocale().getCountry())
				.claim(JWT.PREFERRED_USERNAME, amwayAccount.getPrimaryParty().getCustomerID())
				.claim(JWT.AUTH_TIME, Long.valueOf(afterAddingTwoMins.getTime()))
				.claim(JWT.PARTY_ID, amwayAccount.getPrimaryParty().getCustomerID()) 
				.claim(JWT.ZONEINFO, Calendar.getInstance(request.getLocale()).getTimeZone().getID())
				.claim(JWT.UPDATED_AT, Long.valueOf(afterAddingTwoMins.getTime()))
				.claim(JWT.NONCE, StringUtils.replace(request.getParameter(NONCE), " ", "+"))
				.claim(JWT.GIVEN_NAME, (null != names && names.length > 0) ? names[0] : StringUtils.EMPTY)
				.claim(JWT.HYBRIS_LOGIN_CODE, amwayAccount.getCode())
				.claim(JWT.FAMILY_NAME, (names.length == 2) ? names[1] : StringUtils.EMPTY).expirationTime(calendar.getTime())
				.build();
	}

	/**
	 * Gets the user service.
	 *
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * Sets the user service.
	 *
	 * @param userService
	 *           the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * Gets the account service.
	 *
	 * @return the accountService
	 */
	public AmwayApacAccountService getAccountService()
	{
		return accountService;
	}

	/**
	 * Sets the account service.
	 *
	 * @param accountService
	 *           the accountService to set
	 */
	@Required
	public void setAccountService(final AmwayApacAccountService accountService)
	{
		this.accountService = accountService;
	}

	/**
	 * Gets the jwt key maker.
	 *
	 * @return the jwtKeyMaker
	 */
	public AmwayApacJWTKeyMaker getJwtKeyMaker()
	{
		return jwtKeyMaker;
	}

	/**
	 * Sets the jwt key maker.
	 *
	 * @param jwtKeyMaker
	 *           the jwtKeyMaker to set
	 */
	@Required
	public void setJwtKeyMaker(final AmwayApacJWTKeyMaker jwtKeyMaker)
	{
		this.jwtKeyMaker = jwtKeyMaker;
	}

	/**
	 * Gets the customer name strategy.
	 *
	 * @return the customerNameStrategy
	 */
	public CustomerNameStrategy getCustomerNameStrategy()
	{
		return customerNameStrategy;
	}

	/**
	 * Sets the customer name strategy.
	 *
	 * @param customerNameStrategy
	 *           the customerNameStrategy to set
	 */
	@Required
	public void setCustomerNameStrategy(final CustomerNameStrategy customerNameStrategy)
	{
		this.customerNameStrategy = customerNameStrategy;
	}
}
