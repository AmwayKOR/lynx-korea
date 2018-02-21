/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.sso.web.security.impl;

import de.hybris.platform.core.model.user.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.SAMLCredential;

import com.amway.sso.web.security.AuthenticationHandler;


public class DefaultAmwaySSOAuthenticationHandler implements AuthenticationHandler
{

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwaySSOAuthenticationHandler.class);

	@Override
	public void handle(final SAMLCredential samlCredential, final UserModel user)
	{
		LOGGER.debug("SSO default behavior. Do nothing.");
	}
}
