/**
 *
 */
package com.amway.apac.auth.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;



/**
 *
 */
public interface AmwayJWTTokenProvider
{
	/**
	 * @param userId
	 * @return
	 */
	String createJWToken(final String userId, final Date creationDate, final HttpServletRequest request);
}
