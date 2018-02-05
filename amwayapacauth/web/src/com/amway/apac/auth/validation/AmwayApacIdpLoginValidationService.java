package com.amway.apac.auth.validation;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;



/**
 * The Interface IDP login validation service.
 */
public interface AmwayApacIdpLoginValidationService
{
	/**
	 * Validates the HTTP login request
	 *
	 * @param request
	 * @return Collection of validation errors
	 */
	Collection<String> validationLoginRequest(final HttpServletRequest request);
}
