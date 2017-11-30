/**
 *
 */
package com.amway.apac.auth.security.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amway.apac.auth.security.AmwayJWTTokenProvider;
import com.amway.core.model.AmwayAccountModel;
import com.amway.lynxcore.account.LynxAccountService;
import com.amway.lynxcore.strategies.LynxCustomerNameStrategy;


/**
 *
 */
public class AmwayJWTTokenProviderImpl implements AmwayJWTTokenProvider
{

	private static final Logger LOG = Logger.getLogger(AmwayJWTTokenProviderImpl.class);

	private static final String AMWAY_IDP_JWT_SECRET_KEY = "amway.idp.jwt.secret.key";
	private static final String AMWAY_IDP_JWT_TTLMILES = "amway.idp.jwt.ttlmiles";

	private LynxCustomerNameStrategy customerNameStrategy;
	private LynxAccountService accountService;
	private UserService userService;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.apac.auth.security.AmwayJWTTokenProvider#createJWToken(com.amway.core.model.AmwayAccountModel)
	 */
	@Override
	public String createJWToken(final String userId, final Date creationDate, final Locale locale)
	{
		try
		{
			final CustomerModel customer = userService.getUserForUID(userId, CustomerModel.class);

			if (null != customer)
			{
				// 	get AmwayAccount
				final AmwayAccountModel amwayAccount = accountService.getAmwayAccount(customer);
				final Long ttlMillis = Long.valueOf(Config.getParameter(AMWAY_IDP_JWT_TTLMILES));
				//The JWT signature algorithm we will be using to sign the token
				final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

				final long nowMillis = creationDate.getTime();
				final Date now = new Date(nowMillis);

				//We will sign our JWT with our ApiKey secret
				//final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Config.getParameter(AMWAY_IDP_JWT_SECRET_KEY));
				//final Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

				final Map<String, Object> claims = prepareTokenClaim(amwayAccount, locale);

				//Let's set the JWT Claims
				final JwtBuilder builder = Jwts.builder().setId(amwayAccount.getCode())
						.setIssuedAt(now)
						.setSubject("435473587345863475989090u")
						.setIssuer("/oauth2/default/v1/authorize")
						.addClaims(claims)
						.signWith(signatureAlgorithm, Config.getParameter(AMWAY_IDP_JWT_SECRET_KEY).getBytes("UTF-8"));

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
		}
		catch (final Exception exp)
		{
			LOG.equals(exp.getMessage());
		}
		return null;
	}

	/**
	 * @param amwayAccount
	 * @return
	 */
	private Map<String, Object> prepareTokenClaim(final AmwayAccountModel amwayAccount, final Locale locale)
	{
		final Map<String, Object> claims = new HashMap<>();
		claims.put("name", amwayAccount.getName());
		claims.put("locale", locale.getLanguage() + "-" + locale.getCountry());
		claims.put("preferred username", amwayAccount.getPrimaryParty().getCustomerID());
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
		claims.put("partyId", "47929860");
		claims.put("zoneinfo", Calendar.getInstance(locale).getTimeZone().getID());
		claims.put("updated at", Long.valueOf(new Date().getTime()));
		return claims;
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

}
