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
package com.amway.sso.service.user;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.samlsinglesignon.DefaultSSOService;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.sso.exception.SSOException;


public class DefaultAmwaySSOService extends DefaultSSOService
{

	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmwaySSOService.class);

	@Override
	public UserModel getOrCreateSSOUser(final String id, final String name, final Collection<String> roles)
	{
		LOG.debug("Find user for uid: {}", id);

		// NOTE: ID should be converted to lower case, because hybris stores user's id always in lower case, but in SSO it could be in different cases
		final String userId = id.toLowerCase();

		final UserModel user = lookupExisting(userId, null);

		if (Objects.isNull(user))
		{
			throw new SSOException("can't find user for uid: " + id);
		}

		return user;
	}
}
