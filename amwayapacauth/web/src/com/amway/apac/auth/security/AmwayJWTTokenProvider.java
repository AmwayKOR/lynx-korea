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
	 * @param userId
	 * @return
	 */
	String createJWToken(final String userId, final Date creationDate);
}
