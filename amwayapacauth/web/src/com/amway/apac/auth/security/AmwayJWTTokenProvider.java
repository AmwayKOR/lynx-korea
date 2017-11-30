/**
 *
 */
package com.amway.apac.auth.security;

import java.util.Date;
import java.util.Locale;



/**
 *
 */
public interface AmwayJWTTokenProvider
{
	/**
	 * @param userId
	 * @return
	 */
	String createJWToken(final String userId, final Date creationDate, final Locale locale);
}
