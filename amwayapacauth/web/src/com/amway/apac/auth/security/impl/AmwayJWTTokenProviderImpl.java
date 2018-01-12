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
import com.amway.apac.auth.security.AmwayJWTTokenProvider;
import com.amway.core.model.AmwayAccountModel;
import com.amway.lynxcore.account.LynxAccountService;
import com.amway.lynxcore.strategies.LynxCustomerNameStrategy;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;


/**
 *
 */
public class AmwayJWTTokenProviderImpl implements AmwayJWTTokenProvider
{
	private static final Logger LOG = Logger.getLogger(AmwayJWTTokenProviderImpl.class);

	private LynxCustomerNameStrategy customerNameStrategy;
	private LynxAccountService accountService;
	private AmwayJWTKeyMaker jwtKeyMaker;
	private UserService userService;


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
				SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256, null, null, null, null, null, null, null, null,
						null, Config.getParameter(IDPLogin.AMWAY_IDP_JWT_KEYID), null, null), prepareJWTClaimsSet(amwayAccount,
						creationDate, request));
				// Compute the RSA signature
				signedJWT.sign(signer);
				final String s = signedJWT.serialize();
				LOG.info("TOKEN :: " + s);
				// On the consumer side, parse the JWS and verify its RSA signature
				signedJWT = SignedJWT.parse(s);
				final JWSVerifier verifier = new RSASSAVerifier(jwtKeyMaker.getPublicKey());
				LOG.info("TOKEN VERIFIED :: " + signedJWT.verify(verifier));
				return s;
			}
		}
		catch (final Exception exp)
		{
			LOG.equals(exp.getMessage());
		}
		return null;
	}

	/**
	 * @param amwayAccount
	 * @return JWT Claim set
	 */
	private JWTClaimsSet prepareJWTClaimsSet(final AmwayAccountModel amwayAccount, final Date creationDate,
			final HttpServletRequest request)
	{
		final String[] names = customerNameStrategy.splitName(amwayAccount.getPrimaryParty().getName());
		final Long ttlMillis = Long.valueOf(Config.getParameter(IDPLogin.AMWAY_IDP_JWT_TTLMILES));
		//The JWT signature algorithm we will be using to sign the token
		final long nowMillis = creationDate.getTime();
		String givenName = null;
		String familyName = null;
		if (null != names && names.length > 0)
		{
			givenName = names[0];
		}

		if (null != names && names.length == 2)
		{
			familyName = names[1];
		}
		// Prepare JWT with claims set

		return new JWTClaimsSet.Builder().audience(request.getParameter(IDPLogin.CLIENT_ID)).subject("alice")
				.issueTime(new Date())
				.notBeforeTime(new Date()).issuer(Config.getParameter(IDPLogin.ISSUER)).claim("name", amwayAccount.getName())
				.claim("locale", request.getLocale().getLanguage() + "-" + request.getLocale().getCountry())
				.claim("preferred username", amwayAccount.getPrimaryParty().getCustomerID())
				.claim("auth_time", Long.valueOf(new Date().getTime())).claim("partyId", "47929860")
				.claim("zoneinfo", Calendar.getInstance(request.getLocale()).getTimeZone().getID())
				.claim("updated at", Long.valueOf(new Date().getTime()))
				.claim("nonce", StringUtils.replace(request.getParameter(IDPLogin.NONCE), " ", "+")).claim("given_name", givenName)
				.claim("family_name", familyName).expirationTime(new Date(nowMillis + ttlMillis.longValue())).build();
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
	public void setJwtKeyMaker(final AmwayJWTKeyMaker jwtKeyMaker)
	{
		this.jwtKeyMaker = jwtKeyMaker;
	}

}
