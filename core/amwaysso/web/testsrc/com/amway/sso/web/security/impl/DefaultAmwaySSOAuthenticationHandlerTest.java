/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.sso.web.security.impl;

import static org.mockito.Mockito.verifyZeroInteractions;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.UserModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.saml.SAMLCredential;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultAmwaySSOAuthenticationHandlerTest
{
	@Mock
	private SAMLCredential samlCredential;

	@Mock
	private UserModel user;

	@InjectMocks
	private final DefaultAmwaySSOAuthenticationHandler unit = new DefaultAmwaySSOAuthenticationHandler();

	@Test
	public void doNothingForAtuhenticationHandling() throws Exception
	{

		unit.handle(samlCredential, user);

		verifyZeroInteractions(samlCredential, user);
	}
}
