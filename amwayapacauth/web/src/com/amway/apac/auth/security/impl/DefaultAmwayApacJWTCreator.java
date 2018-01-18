/**
 *
 */
package com.amway.apac.auth.security.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;
import com.amway.apac.auth.controllers.ControllerConstants.JWT;
import com.amway.apac.auth.security.AmwayApacJWTCreator;
import com.amway.core.model.AmwayAccountModel;
import com.amway.lynxcore.account.LynxAccountService;
import com.amway.lynxcore.strategies.LynxCustomerNameStrategy;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;


/**
 * Creates JWT
 */
public class DefaultAmwayApacJWTCreator implements AmwayApacJWTCreator
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacJWTCreator.class);

	private UserService userService;
	private LynxAccountService accountService;
	private DefaultAmwayApacJWTKeyMaker jwtKeyMaker;
	private LynxCustomerNameStrategy customerNameStrategy;


	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.auth.security.AmwayJWTTokenProvider#createJWToken(com.amway.core.model.AmwayAccountModel)
	 */
	@Override
	public String createJWToken(final String userId, final Date creationDate, final HttpServletRequest request)
	{
		try
		{
			final CustomerModel customer = userService.getUserForUID(userId, CustomerModel.class);
			if (null != customer)
			{
				// 	get AmwayAccount
				final AmwayAccountModel amwayAccount = accountService.getAmwayAccount(customer);
				// Create RSA-signer with the private key
				final JWSSigner signer = new RSASSASigner(jwtKeyMaker.getPrivateKey());
				final SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256, null, null, null, null, null, null, null,
						null, null, Config.getParameter(IDPLogin.AMWAY_IDP_JWT_KEYID), null, null), prepareJWTClaimsSet(amwayAccount,
						creationDate, request));
				// Compute the RSA signature
				signedJWT.sign(signer);
				final String jwt = signedJWT.serialize();
				LOG.debug("TOKEN :: " + jwt);
				return jwt;
			}
		}
		catch (final JOSEException exp)
		{
			LOG.error(exp.getMessage(), exp);
		}

		return null;
	}

	/**
	 * @param amwayAccount
	 * @param creationDate
	 * @param request
	 * @return - JWTClaimsSet
	 */
	private JWTClaimsSet prepareJWTClaimsSet(final AmwayAccountModel amwayAccount, final Date creationDate,
			final HttpServletRequest request)
	{
		final String[] names = customerNameStrategy.splitName(amwayAccount.getPrimaryParty().getName());
		final Long ttlMillis = Long.valueOf(Config.getParameter(IDPLogin.AMWAY_IDP_JWT_TTLMILES));
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(creationDate);
		calendar.add(Calendar.MILLISECOND, ttlMillis.intValue());

		// Prepare & return JWT with claims set
		return new JWTClaimsSet.Builder().audience(request.getParameter(IDPLogin.CLIENT_ID)).subject(JWT.ALICE)
				.issueTime(new Date()).notBeforeTime(new Date()).issuer(Config.getParameter(IDPLogin.ISSUER))
				.claim(JWT.NAME, amwayAccount.getName())
				.claim(JWT.LOCALE, request.getLocale().getLanguage() + "-" + request.getLocale().getCountry())
				.claim(JWT.PREFERRED_USERNAME, amwayAccount.getPrimaryParty().getCustomerID())
				.claim(JWT.AUTH_TIME, Long.valueOf(new Date().getTime()))
				.claim(JWT.PARTY_ID, amwayAccount.getPrimaryParty().getCustomerID())
				.claim(JWT.ZONEINFO, Calendar.getInstance(request.getLocale()).getTimeZone().getID())
				.claim(JWT.UPDATED_AT, Long.valueOf(new Date().getTime()))
				.claim(JWT.NONCE, StringUtils.replace(request.getParameter(IDPLogin.NONCE), " ", "+"))
				.claim(JWT.GIVEN_NAME, (null != names && names.length > 0) ? names[0] : StringUtils.EMPTY)
				.claim(JWT.HYBRIS_LOGIN_CODE, amwayAccount.getCode())
				.claim(JWT.FAMILY_NAME, (names.length == 2) ? names[1] : StringUtils.EMPTY).expirationTime(calendar.getTime())
				.build();
	}

	/**
	 * @param customerNameStrategy
	 *           the customerNameStrategy to set
	 */
	public void setCustomerNameStrategy(final LynxCustomerNameStrategy customerNameStrategy)
	{
		this.customerNameStrategy = customerNameStrategy;
	}

	/**
	 * @param accountService
	 *           the accountService to set
	 */
	public void setAccountService(final LynxAccountService accountService)
	{
		this.accountService = accountService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @param jwtKeyMaker
	 *           the jwtKeyMaker to set
	 */
	public void setJwtKeyMaker(final DefaultAmwayApacJWTKeyMaker jwtKeyMaker)
	{
		this.jwtKeyMaker = jwtKeyMaker;
	}
}
