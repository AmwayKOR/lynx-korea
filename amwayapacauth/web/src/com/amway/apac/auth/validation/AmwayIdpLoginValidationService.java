/**
 *
 */
package com.amway.apac.auth.validation;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;


/**
 *
 */
public interface AmwayIdpLoginValidationService
{
	/**
	 * @param request
	 */
	Collection<String> validationLoginRequest(final HttpServletRequest request);
}
