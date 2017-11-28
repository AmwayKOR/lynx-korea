/**
 *
 */
package com.amway.apac.auth.security.impl;

import de.hybris.platform.util.Config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.amway.apac.auth.security.AmwayJWTTokenProvider;
import com.amway.core.model.AmwayAccountModel;
import com.amway.lynxcore.strategies.LynxCustomerNameStrategy;
import com.amway.lynxfacades.customer.LynxCustomerFacade;


/**
 *
 */
public class AmwayJWTTokenProviderImpl implements AmwayJWTTokenProvider
{

	private static final String AMWAY_IDP_JWT_SECRET_KEY = "amway.idp.jwt.secret.key";
	private static final String AMWAY_IDP_JWT_TTLMILES = "amway.idp.jwt.ttlmiles";

	private LynxCustomerNameStrategy customerNameStrategy;
	private LynxCustomerFacade lynxCustomerFacade;


	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.auth.security.AmwayJWTTokenProvider#createJWToken(com.amway.core.model.AmwayAccountModel)
	 */
	@Override
	public String createJWToken(final String amwayAccountId, final Date creationDate)
	{
		// get AmwayAccount
		final AmwayAccountModel amwayAccount = lynxCustomerFacade.getAmwayAccountById(getAmwayAccountId(amwayAccountId));

		final Long ttlMillis = Long.valueOf(Config.getParameter(AMWAY_IDP_JWT_TTLMILES));
		//The JWT signature algorithm we will be using to sign the token
		final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		final long nowMillis = creationDate.getTime();
		final Date now = new Date(nowMillis);

		//We will sign our JWT with our ApiKey secret
		final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Config.getParameter(AMWAY_IDP_JWT_SECRET_KEY));
		final Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		final Map<String, Object> claims = prepareTokenClaim(amwayAccount);

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

	/**
	 * @param amwayAccount
	 * @return
	 */
	private Map<String, Object> prepareTokenClaim(final AmwayAccountModel amwayAccount)
	{
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
	 * @param lynxCustomerFacade
	 *           the lynxCustomerFacade to set
	 */
	public void setLynxCustomerFacade(final LynxCustomerFacade lynxCustomerFacade)
	{
		this.lynxCustomerFacade = lynxCustomerFacade;
	}

}
