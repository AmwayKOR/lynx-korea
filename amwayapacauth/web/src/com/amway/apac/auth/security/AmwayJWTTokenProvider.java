/**
 *
 */
package com.amway.apac.auth.security;

import java.util.Date;



/**
 *
 */
public interface AmwayJWTTokenProvider
{
	/**
	 * @param amwayAccount
	 * @return
	 */
	String createJWToken(final String amwayAccount, final Date creationDate);
}
