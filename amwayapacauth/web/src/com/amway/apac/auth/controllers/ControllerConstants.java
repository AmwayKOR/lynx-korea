package com.amway.apac.auth.controllers;

import javax.servlet.http.HttpServletRequest;



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

	interface IDPLogin // NOSONAR
	{
		String E = "e";
		String N = "n";
		String KID = "kid";
		String TOKEN = "id_token";
		String STATE = "state";
		String SCOPE = "scope";
		String NONCE = "nonce";
		String LOGIN = "login";
		String PROMPT = "prompt";
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
		String ISSUER = "amway.idp.jwt.issuer";
		String KEY_TYPE = "RSA";
		String KEY_USE = "sig";
		String SECURITY_ALGORITHM = "RS256";
		String STR_AMP = "&";
		String STR_EQUALS = "=";
		String STR_QUESTIONMARK = "?";
		String PAGES_ACCOUNT_IDP_PAGE = "pages/account/accountIDPPage";
		String LOGIN_ERROR = "loginError";
		String BASE_URL = "/";

		/**
		 * Create and return login Success url.
		 *
		 * @param request
		 *           the request
		 * @return Login success url
		 */
		static String loginSuccessUrl(final HttpServletRequest request)
		{
			return IDPLogin.STR_QUESTIONMARK + IDPLogin.STATE + IDPLogin.STR_EQUALS + request.getParameter(IDPLogin.STATE)
					+ IDPLogin.STR_AMP + IDPLogin.NONCE + IDPLogin.STR_EQUALS + request.getParameter(IDPLogin.NONCE)
					+ IDPLogin.STR_AMP + IDPLogin.CLIENT_ID + IDPLogin.STR_EQUALS + request.getParameter(IDPLogin.CLIENT_ID)
					+ IDPLogin.STR_AMP + IDPLogin.REDIRECT_URI + IDPLogin.STR_EQUALS + request.getParameter(IDPLogin.REDIRECT_URI);
		}
	}

	interface IdpKey //NOSONAR
	{
		String KEYSTORE_ALIAS = "amwayqaidp";
		String KEYSTORE_PASSWORD = "hybrismsb";
		String KEYSTORE_FILENAME = "keystore.jks";
	}

	interface JWT //NOSONAR
	{
		String NAME = "name";
		String FAMILY_NAME = "family_name";
		String GIVEN_NAME = "given_name";
		String NONCE = "nonce";
		String UPDATED_AT = "updated at";
		String ZONEINFO = "zoneinfo";
		String PARTY_ID = "partyId";
		String AUTH_TIME = "auth_time";
		String PREFERRED_USERNAME = "preferred username";
		String LOCALE = "locale";
		String ALICE = "alice";
		String HYBRIS_LOGIN_CODE = "hybrisLoginCode";
	}
}
