/**
 *
 */
package com.amway.apac.auth.security.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

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

	private static final String AMWAY_IDP_JWT_SECRET_KEY = "amway.idp.jwt.secret.key";
	private static final String AMWAY_IDP_JWT_TTLMILES = "amway.idp.jwt.ttlmiles";

	private LynxCustomerNameStrategy customerNameStrategy;
	private LynxAccountService accountService;
	private UserService userService;
	private AmwayJWTKeyMakerImpl jwtKeyMaker;


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
				final Long ttlMillis = Long.valueOf(Config.getParameter(AMWAY_IDP_JWT_TTLMILES));
				//The JWT signature algorithm we will be using to sign the token
				final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

				final long nowMillis = creationDate.getTime();
				final Date now = new Date(nowMillis);

				//final Map<String, Object> claims = prepareTokenClaim(amwayAccount, request);

				// Create RSA-signer with the private key
				final JWSSigner signer = new RSASSASigner(jwtKeyMaker.getPrivateKey());

				SignedJWT signedJWT = new SignedJWT(
						new JWSHeader(JWSAlgorithm.RS256, null, null, null, null, null, null, null, null, null, "hybris-idp-key1",
								null, null),
						prepareJWTClaimsSet(amwayAccount, request));

				// Compute the RSA signature
				signedJWT.sign(signer);

				final String s = signedJWT.serialize();

				System.out.println("TOKEN :: " + s);

				// On the consumer side, parse the JWS and verify its RSA signature
				signedJWT = SignedJWT.parse(s);

				final JWSVerifier verifier = new RSASSAVerifier(jwtKeyMaker.getPublicKey());
				System.out.println("TOKEN VERIFIED :: " + signedJWT.verify(verifier));


				/*
				 * //Let's set the JWT Claims final JwtBuilder builder = Jwts.builder().setId(amwayAccount.getCode())
				 * .setHeaderParam("kid", jwtKeyMaker.getKid()) .setAudience("hteeuj8ujx6bkuznxe39dttp") .setIssuedAt(now)
				 * .setSubject("435473587345863475989090u") .setIssuer(request.getRequestURI()) .addClaims(claims)
				 * //.signWith(signatureAlgorithm, Config.getParameter(AMWAY_IDP_JWT_SECRET_KEY).getBytes("UTF-8"));
				 * .signWith(signatureAlgorithm, jwtKeyMaker.getPrivateKey());
				 */



				//if it has been specified, let's add the expiration
				/*
				 * if (ttlMillis >= 0) { final long expMillis = nowMillis + ttlMillis; final Date exp = new Date(expMillis);
				 * builder.setExpiration(exp); }
				 */
				//Builds the JWT and serializes it to a compact, URL-safe string
				//final String token = builder.compact();
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
	 * @return
	 */
	private Map<String, Object> prepareTokenClaim(final AmwayAccountModel amwayAccount, final HttpServletRequest request)
	{
		final Map<String, Object> claims = new HashMap<>();
		claims.put("name", amwayAccount.getName());
		claims.put("locale", request.getLocale().getLanguage() + "-" + request.getLocale().getCountry());
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
		claims.put("zoneinfo", Calendar.getInstance(request.getLocale()).getTimeZone().getID());
		claims.put("updated at", Long.valueOf(new Date().getTime()));
		claims.put("nonce", request.getParameter("nonce"));
		return claims;
	}

	/**
	 * @param amwayAccount
	 * @return
	 */
	private JWTClaimsSet prepareJWTClaimsSet(final AmwayAccountModel amwayAccount, final HttpServletRequest request)
	{
		final String[] names = customerNameStrategy.splitName(amwayAccount.getPrimaryParty().getName());
		String given_name = null;
		String family_name = null;
		if (null != names && names.length > 0)
		{
			given_name = names[0];

		}

		if (null != names && names.length == 2)
		{
			family_name = names[1];
		}

		// Prepare JWT with claims set
		final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.audience("hteeuj8ujx6bkuznxe39dttp")
				.subject("alice")
				.issueTime(new Date())
				.notBeforeTime(new Date())
				.issuer(request.getRequestURL().toString())
				.claim("name", amwayAccount.getName())
				.claim("locale", request.getLocale().getLanguage() + "-" + request.getLocale().getCountry())
				.claim("preferred username", amwayAccount.getPrimaryParty().getCustomerID())
				.claim("auth_time", Long.valueOf(new Date().getTime()))
				.claim("partyId", "47929860")
				.claim("zoneinfo", Calendar.getInstance(request.getLocale()).getTimeZone().getID())
				.claim("updated at", Long.valueOf(new Date().getTime()))
				.claim("nonce", request.getParameter("nonce"))
				.claim("given_name", given_name)
				.claim("family_name", family_name)
				.expirationTime(new Date(new Date().getTime() + 60 * 1000))
				.build();
		return claimsSet;
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
	public void setJwtKeyMaker(final AmwayJWTKeyMakerImpl jwtKeyMaker)
	{
		this.jwtKeyMaker = jwtKeyMaker;
	}

}
