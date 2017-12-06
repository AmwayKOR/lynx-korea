/**
 *
 */
package com.amway.apac.auth.security.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

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
	private X509Certificate certificate;

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

			final RSAKey rsaKey = new RSAKey.Builder(publicKey)
					.privateKey(privateKey)
					.keyUse(KeyUse.SIGNATURE)
					.algorithm(JWSAlgorithm.RS256)
					.keyID(kid)
					.build();

			//final Gson gson = new GsonBuilder().setPrettyPrinting().create();

			//printKey(true, rsaKey, gson);

			setN(rsaKey.getModulus().toString());
			setE(rsaKey.getPublicExponent().toString());

			setKid("hybris-idp-key1");

			setPrivateKey(privateKey);
			setPublicKey(publicKey);

			// generate X509 certificate
			//	setCertificate(generateCertificate("amwaymalaysia.local", kp, 1000, "RS256"));

		}
		catch (final GeneralSecurityException e)
		{
			// YTODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create a self-signed X.509 Certificate
	 *
	 * @param dn
	 *           the X.509 Distinguished Name, eg "CN=Test, L=London, C=GB"
	 * @param pair
	 *           the KeyPair
	 * @param days
	 *           how many days from now the Certificate is valid for
	 * @param algorithm
	 *           the signing algorithm, eg "SHA1withRSA"
	 */
	X509Certificate generateCertificate(final String dn, final KeyPair pair, final int days, final String algorithm)
			throws GeneralSecurityException, IOException
	{
		final PrivateKey privkey = pair.getPrivate();
		final X509CertInfo info = new X509CertInfo();
		final Date from = new Date();
		final Date to = new Date(from.getTime() + days * 86400000l);
		final CertificateValidity interval = new CertificateValidity(from, to);
		final BigInteger sn = new BigInteger(64, new SecureRandom());
		final X500Name owner = new X500Name(dn);

		info.set(X509CertInfo.VALIDITY, interval);
		info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(sn));
		info.set(X509CertInfo.SUBJECT, new CertificateSubjectName(owner));
		info.set(X509CertInfo.ISSUER, new CertificateIssuerName(owner));
		info.set(X509CertInfo.KEY, new CertificateX509Key(pair.getPublic()));
		info.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
		AlgorithmId algo = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);
		info.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algo));

		// Sign the cert to identify the algorithm that's used.
		X509CertImpl cert = new X509CertImpl(info);
		cert.sign(privkey, algorithm);

		// Update the algorith, and resign.
		algo = (AlgorithmId) cert.get(X509CertImpl.SIG_ALG);
		info.set(CertificateAlgorithmId.NAME + "." + CertificateAlgorithmId.ALGORITHM, algo);
		cert = new X509CertImpl(info);
		cert.sign(privkey, algorithm);
		return cert;
	}

	/*
	 * private void printKey(final boolean keySet, final JWK jwk, final Gson gson) { if (keySet) { final JWKSet jwkSet =
	 * new JWKSet(jwk); final JsonElement json = new JsonParser().parse(jwkSet.toJSONObject(false).toJSONString());
	 * System.out.println(gson.toJson(json)); } else { final JsonElement json = new
	 * JsonParser().parse(jwk.toJSONString()); System.out.println(gson.toJson(json)); } }
	 */

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

	/**
	 * @return the certificate
	 */
	public X509Certificate getCertificate()
	{
		return certificate;
	}

	/**
	 * @param certificate
	 *           the certificate to set
	 */
	public void setCertificate(final X509Certificate certificate)
	{
		this.certificate = certificate;
	}
}
