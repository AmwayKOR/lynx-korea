/**
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 *
 */
package com.amway.core.v2.filter;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


//Amway Core import
import javax.annotation.Resource;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;


/**
 * Filter that puts user from the requested url into the session.
 */
public class AmwayUserMatchingFilter extends AbstractUrlMatchingFilter
{
	public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
	public static final String ROLE_CUSTOMERGROUP = "ROLE_CUSTOMERGROUP";
	public static final String ROLE_CUSTOMERMANAGERGROUP = "ROLE_CUSTOMERMANAGERGROUP";
	public static final String ROLE_TRUSTED_CLIENT = "ROLE_TRUSTED_CLIENT";
	private static final String CURRENT_USER = "current";
	private static final String ANONYMOUS_USER = "anonymous";
	private String regexp;
	private UserService userService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException
	{
		final Authentication auth = getAuth();

		//Amway Core method
		String userID = setAmwayUserId(getValue(request, regexp));

		if (userID == null)
		{
			if (hasRole(ROLE_CUSTOMERGROUP, auth) || hasRole(ROLE_CUSTOMERMANAGERGROUP, auth))
			{
				setCurrentUser((String) auth.getPrincipal());
			}
			else
			{
				// fallback to anonymous
				setCurrentUser(userService.getAnonymousUser());
			}
		}
		else if (userID.equals(ANONYMOUS_USER))
		{
			setCurrentUser(userService.getAnonymousUser());
		}
		else if (hasRole(ROLE_TRUSTED_CLIENT, auth) || hasRole(ROLE_CUSTOMERMANAGERGROUP, auth))
		{
			setCurrentUser(userID);
		}
		else if (hasRole(ROLE_CUSTOMERGROUP, auth))
		{
			if (userID.equals(CURRENT_USER) || userID.equals(auth.getPrincipal()))
			{
				setCurrentUser((String) auth.getPrincipal());
			}
			else
			{
				throw new AccessDeniedException("Access is denied");
			}
		}
		else
		{
			// could not match any authorized role
			throw new AccessDeniedException("Access is denied");
		}

		filterChain.doFilter(request, response);
	}

	protected Authentication getAuth()
	{
		return SecurityContextHolder.getContext().getAuthentication();
	}

	protected String getRegexp()
	{
		return regexp;
	}

	@Required
	public void setRegexp(final String regexp)
	{
		this.regexp = regexp;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected boolean hasRole(final String role, final Authentication auth)
	{
		for (final GrantedAuthority ga : auth.getAuthorities())
		{
			if (ga.getAuthority().equals(role))
			{
				return true;
			}
		}
		return false;
	}

	protected void setCurrentUser(final String uid)
	{
		final UserModel userModel = userService.getUserForUID(uid);
		userService.setCurrentUser(userModel);

		//Amway Core method
		setAmwayCurrentUser(uid, userModel);
	}

	protected void setCurrentUser(final UserModel user)
	{
		userService.setCurrentUser(user);
	}



	//Amway Core methods and resources
	@Resource(name = "amwayAccountCommerceService")
	private AmwayAccountCommerceService accountCommerceService;


	protected void setAmwayCurrentUser(final String uid, UserModel userModel) {

		AmwayAccountModel currentAccount = accountCommerceService.getCurrentAccount();
		//this is a scenario not in pos where the amway account is not a part of the request
		if (currentAccount == null) {
			accountCommerceService.setCurrentAccount(userModel);
			currentAccount = accountCommerceService.getCurrentAccount();
		}

		if (currentAccount!=null && currentAccount.getParties()!=null &&!currentAccount.getParties().contains(userModel))
			throw new AccessDeniedException("Invalid user (party) associated to the b2bunit (account)");

	}

	protected String setAmwayUserId(String userID) {

		String retUserID = userID;
		if(userID==null)
		{
			//this is a scenario where the user id has been set correctly based on abo number
			final AmwayAccountModel currentAccount = accountCommerceService.getCurrentAccount();
			retUserID = currentAccount != null ? currentAccount.getPrimaryParty().getUid() : null;
		}
		return retUserID;
	}

}

