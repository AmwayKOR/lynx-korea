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
package com.amway.sso.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collection;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amway.sso.exception.SSOException;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultLynxSSOServiceTest
{

	@Mock
	private UserService userService;

	@Mock
	private UserModel user;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();


	@InjectMocks
	private final DefaultAmwaySSOService unit = new DefaultAmwaySSOService();

	@Test
	public void shouldReturnUserForUid() throws Exception
	{

		final String userid = "testUserId";
		final String expectedUserId = "testuserid";

		final String userName = "test user name";
		final Collection<String> userRoles = Collections.singletonList("test_role");

		given(userService.getUserForUID(expectedUserId)).willReturn(user);

		final UserModel actualUser = unit.getOrCreateSSOUser(userid, userName, userRoles);

		verify(userService).getUserForUID(expectedUserId);
		assertEquals(user, actualUser);
	}

	@Test
	public void shouldThrowExceptionWhenUserWasNotFound() throws Exception
	{
		final String userid = "testUserId";
		final String expectedUserId = "testuserid";
		final String expectedExceptionMessage = "can't find user for uid: " + userid;
		final String userName = "test user name";
		final Collection<String> userRoles = Collections.singletonList("test_role");
		final UserModel expectedUser = null;

		given(userService.getUserForUID(expectedUserId)).willReturn(expectedUser);
		expectedException.expect(SSOException.class);
		expectedException.expectMessage(expectedExceptionMessage);

		unit.getOrCreateSSOUser(userid, userName, userRoles);

		verify(userService).getUserForUID(userid);
	}
}
