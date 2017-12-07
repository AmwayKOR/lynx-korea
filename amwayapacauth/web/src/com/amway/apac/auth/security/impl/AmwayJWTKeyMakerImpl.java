/**
 *
 */
package com.amway.apac.auth.security.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.amway.apac.auth.security.AmwayJWTKeyMaker;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;


/**
 *
 */
public class AmwayJWTKeyMakerImpl implements AmwayJWTKeyMaker
{
	private String n;
	private String e;
	private String kid;
	private RSAPrivateKey privateKey;
	private RSAPublicKey publicKey;

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
		try
		{
			final KeyPair kp = generateKeyPairFromCertificate();
			if (null != kp)
			{
				final RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
				final RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

				final RSAKey rsaKey = new RSAKey.Builder(publicKey)
						.privateKey(privateKey)
						.keyUse(KeyUse.SIGNATURE)
						.algorithm(JWSAlgorithm.RS256)
						.keyID(kid)
						.build();

				setN(rsaKey.getModulus().toString());
				setE(rsaKey.getPublicExponent().toString());

				setKid("hybris-idp-key1");

				setPrivateKey(privateKey);
				setPublicKey(publicKey);
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws IOException
	 * @throws CertificateException
	 */
	private KeyPair generateKeyPairFromCertificate() throws UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException
	{
		final ClassLoader classLoader = this.getClass().getClassLoader();
		// Getting resource(File) from class loader
		final File configFile = new File(classLoader.getResource("keystore.jks").getFile());
		final FileInputStream is = new FileInputStream(configFile);
		final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(is, "hybrismsb".toCharArray());
		final String alias = "amwayqaidp";
		System.out.println("Alias " + alias + " Found ::" + keystore.containsAlias(alias));
		final Key key = keystore.getKey(alias, "hybrismsb".toCharArray());

		if (key instanceof PrivateKey)
		{
			// Get certificate of public key
			final Certificate cert = keystore.getCertificate(alias);
			// Get public key
			final PublicKey publicKey = cert.getPublicKey();
			// Return a key pair
			return new KeyPair(publicKey, (PrivateKey) key);
		}
		return null;
	}

	/**
	 * @return the n
	 */
	public String getN()
	{
		return n;
	}

	/**
	 * @param n
	 *           the n to set
	 */
	public void setN(final String n)
	{
		this.n = n;
	}

	/**
	 * @return the e
	 */
	public String getE()
	{
		return e;
	}

	/**
	 * @param e
	 *           the e to set
	 */
	public void setE(final String e)
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

	/**
	 * @return the publicKey
	 */
	public RSAPublicKey getPublicKey()
	{
		return publicKey;
	}

	/**
	 * @param publicKey
	 *           the publicKey to set
	 */
	public void setPublicKey(final RSAPublicKey publicKey)
	{
		this.publicKey = publicKey;
	}
}
