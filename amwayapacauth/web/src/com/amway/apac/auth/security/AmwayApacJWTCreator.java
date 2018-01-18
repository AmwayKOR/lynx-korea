/**
 *
 */
package com.amway.apac.auth.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * Create JWT
 */
public interface AmwayApacJWTCreator
{
	/**
	 * Create Json Web Token
	 *
	 * @param userId
	 *           - Uid or AmwayAccount code
	 * @param creationDate
	 *           - token issue date
	 * @param request
	 *           - to get locale attributes
	 * @return Json Web Token
	 */
	String createJWToken(final String userId, final Date creationDate, final HttpServletRequest request);
}
