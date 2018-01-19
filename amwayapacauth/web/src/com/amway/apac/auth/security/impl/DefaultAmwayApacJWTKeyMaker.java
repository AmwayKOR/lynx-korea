package com.amway.apac.auth.security.impl;

import de.hybris.platform.util.Config;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;
import com.amway.apac.auth.controllers.ControllerConstants.IdpKey;
import com.amway.apac.auth.security.AmwayApacJWTKeyMaker;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;


/**
 * generate key pair from certificate on server start up
 */
public class DefaultAmwayApacJWTKeyMaker implements AmwayApacJWTKeyMaker
{

	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacJWTKeyMaker.class);

	/** The modulus for the public key and the private keys. */
	private String n;

	/** The public key exponent. */
	private String e;

	/** The key id. */
	private String kid;

	/** The private key which is used to signed the document. */
	private RSAPrivateKey privateKey;

	/** The public key which is used to verify the document at received end. */
	private RSAPublicKey publicKey;

	/**
	 * read the certificate on server start up and store private, public key in spring session
	 */
	@Override
	public void init()
	{
		try
		{
			final KeyPair kp = generateKeyPairFromCertificate();
			if (null != kp)
			{
				setPrivateKey((RSAPrivateKey) kp.getPrivate());
				setPublicKey((RSAPublicKey) kp.getPublic());
				setKid(Config.getParameter(IDPLogin.AMWAY_IDP_JWT_KEYID));
				final RSAKey rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).keyUse(KeyUse.SIGNATURE)
						.algorithm(JWSAlgorithm.RS256).keyID(kid).build();
				setN(rsaKey.getModulus().toString());
				setE(rsaKey.getPublicExponent().toString());
			}
		}
		catch (final Exception exp)
		{
			LOGGER.error(exp.getMessage(), exp);
		}
	}

	/**
	 * Generate KeyPair from certificate
	 *
	 * @return KeyPair
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws IOException
	 * @throws CertificateException
	 */
	protected KeyPair generateKeyPairFromCertificate()
			throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException
	{
		final ClassLoader classLoader = this.getClass().getClassLoader();
		// Getting resource(File) from class loader
		final File configFile = new File(classLoader.getResource(IdpKey.KEYSTORE_FILENAME).getFile());
		final FileInputStream is = new FileInputStream(configFile);
		final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(is, IdpKey.KEYSTORE_PASSWORD.toCharArray());
		LOGGER.debug("Alias " + IdpKey.KEYSTORE_ALIAS + " Found ::" + keystore.containsAlias(IdpKey.KEYSTORE_ALIAS));
		final Key key = keystore.getKey(IdpKey.KEYSTORE_ALIAS, IdpKey.KEYSTORE_PASSWORD.toCharArray());

		if (key instanceof PrivateKey)
		{
			// Get certificate of public key
			final Certificate cert = keystore.getCertificate(IdpKey.KEYSTORE_ALIAS);
			// Get public key
			final PublicKey certPublicKey = cert.getPublicKey();
			// Return a key pair
			return new KeyPair(certPublicKey, (PrivateKey) key);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	 * {@inheritDoc}
	 */
	@Override
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
	 * {@inheritDoc}
	 */
	@Override
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
	 * {@inheritDoc}
	 */
	@Override
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
