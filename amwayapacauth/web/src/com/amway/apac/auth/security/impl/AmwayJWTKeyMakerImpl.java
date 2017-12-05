/**
 *
 */
package com.amway.apac.auth.security.impl;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.amway.apac.auth.security.AmwayJWTKeyMaker;


/**
 *
 */
public class AmwayJWTKeyMakerImpl implements AmwayJWTKeyMaker
{

	private BigInteger n;
	private BigInteger e;
	private String kid;
	private RSAPrivateKey privateKey;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.apac.auth.security.AmwayJWTKeyMaker#generateKeys()
	 */
	@Override
	public void generateKeys()
	{
		// YTODO Auto-generated method stub

	}

	public void init()
	{
		KeyPairGenerator keyGenerator;
		try
		{
			keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(2048);
			final KeyPair kp = keyGenerator.genKeyPair();
			final RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
			final RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

			setE(publicKey.getPublicExponent());
			setN(publicKey.getModulus());

			setKid("secret");

			setPrivateKey(privateKey);
		}
		catch (final NoSuchAlgorithmException e)
		{
			// YTODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the n
	 */
	public BigInteger getN()
	{
		return n;
	}

	/**
	 * @param n
	 *           the n to set
	 */
	public void setN(final BigInteger n)
	{
		this.n = n;
	}

	/**
	 * @return the e
	 */
	public BigInteger getE()
	{
		return e;
	}

	/**
	 * @param e
	 *           the e to set
	 */
	public void setE(final BigInteger e)
	{
		this.e = e;
	}

	/**
	 * @return the kid
	 */
	public String getKid()
	{
		return kid;
	}

	/**
	 * @param kid
	 *           the kid to set
	 */
	public void setKid(final String kid)
	{
		this.kid = kid;
	}

	/**
	 * @return the privateKey
	 */
	public RSAPrivateKey getPrivateKey()
	{
		return privateKey;
	}

	/**
	 * @param privateKey
	 *           the privateKey to set
	 */
	public void setPrivateKey(final RSAPrivateKey privateKey)
	{
		this.privateKey = privateKey;
	}
}
