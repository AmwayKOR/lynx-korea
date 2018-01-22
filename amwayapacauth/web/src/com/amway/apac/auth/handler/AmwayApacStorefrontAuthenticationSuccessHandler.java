package com.amway.apac.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;


/**
 * Success handler initializing user settings, restoring or merging the cart and ensuring the cart is handled correctly.
 * Cart restoration is stored in the session since the request coming in is that to j_spring_security_check and will be
 * redirected.
 */
public class AmwayApacStorefrontAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	@Override
	protected String determineTargetUrl(final HttpServletRequest request, final HttpServletResponse response)
	{
		return this.getDefaultTargetUrl() + IDPLogin.loginSuccessUrl(request);
	}
}
