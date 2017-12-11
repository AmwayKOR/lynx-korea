/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.apac.auth.controllers;



/**
 */
public interface ControllerConstants
{
	// Constant names cannot be changed due to their usage in dependant extensions, thus nosonar

	/**
	 * Class with action name constants
	 */
	interface Actions
	{
		interface Cms // NOSONAR
		{
			String _Prefix = "/view/"; // NOSONAR
			String _Suffix = "Controller"; // NOSONAR

			/**
			 * Default CMS component controller
			 */
			String DefaultCMSComponent = _Prefix + "DefaultCMSComponentController"; // NOSONAR
		}
	}

	/**
	 * Class with view name constants
	 */
	interface Views
	{
		interface Cms // NOSONAR
		{
			String ComponentPrefix = "cms/"; // NOSONAR
		}

		interface Pages
		{
			interface Account // NOSONAR
			{
				String AccountLoginPage = "pages/account/accountLoginPage"; // NOSONAR
				String AccountChangePasswordPage = "pages/account/accountChangePasswordPage"; // NOSONAR
			}

			interface Password // NOSONAR
			{
				String PasswordResetChangePage = "pages/password/passwordResetChangePage"; // NOSONAR
				String PasswordResetRequest = "pages/password/passwordResetRequestPage"; // NOSONAR
				String PasswordResetRequestConfirmation = "pages/password/passwordResetRequestConfirmationPage"; // NOSONAR
			}

			interface Error // NOSONAR
			{
				String ErrorNotFoundPage = "pages/error/errorNotFoundPage"; // NOSONAR
			}

			interface Misc // NOSONAR
			{
				String MiscRobotsPage = "pages/misc/miscRobotsPage"; // NOSONAR
				String MiscSiteMapPage = "pages/misc/miscSiteMapPage"; // NOSONAR
			}

		}
	}

	interface IDPLogin
	{
		String E = "e";
		String N = "n";
		String KID = "kid";
		String TOKEN = "id_token";
		String STATE = "state";
		String SCOPE = "scope";
		String NONCE = "nonce";
		String ID_TOKEN = "id_token";
		String CLIENT_ID = "client_id";
		String JALOSESSION = "jalosession";
		String REDIRECT_URI = "redirect_uri";
		String RESPONSE_MODE = "response_mode";
		String RESPONSE_TYPE = "response_type";
		String ERROR_PAGE = "pages/error/error";
		String IDP_SESSIONS_ME_URL = "/sessions/me";
		String AMWAY_IDP_JWT_TTLMILES = "amway.idp.jwt.ttlmiles";
		String AMWAY_IDP_JWT_KEYID = "amway.idp.jwt.secret.keyid";
		String IDP_REFRESH_URL = "/sessions/me/lifecycle/refresh";
	}
}
