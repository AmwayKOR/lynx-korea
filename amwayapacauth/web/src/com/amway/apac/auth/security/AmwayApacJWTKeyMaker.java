package com.amway.apac.auth.security;

import java.security.PrivateKey;


/**
 * JWT key maker interface
 */
public interface AmwayApacJWTKeyMaker
{
	/**
	 * read the certificate on server start up and store private, public key in spring session
	 */
	void init();

	/**
	 * Gets the key exponent.
	 *
	 * @return the key exponent
	 */
	String getE();

	/**
	 * Gets the key id.
	 *
	 * @return the key id
	 */
	String getKid();

	/**
	 * Gets the modulus.
	 *
	 * @return The modulus
	 */
	String getN();

	/**
	 * Gets the private key.
	 *
	 * @return the private key
	 */
	PrivateKey getPrivateKey();
}
