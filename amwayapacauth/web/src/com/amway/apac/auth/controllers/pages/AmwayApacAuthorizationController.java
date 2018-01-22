package com.amway.apac.auth.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.apac.auth.controllers.ControllerConstants;
import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;
import com.amway.apac.auth.dto.JWTokenDto;
import com.amway.apac.auth.security.AmwayApacJWTCreator;
import com.amway.apac.auth.validation.AmwayApacIdpLoginValidationService;


/**
 * Authorization Controller. Handles login for the account flow.
 */
@Controller
@RequestMapping(value = "/oauth2/default/v1/authorize")
public class AmwayApacAuthorizationController extends AbstractLoginPageController
{
	/** The http session request cache. */
	private HttpSessionRequestCache httpSessionRequestCache;

	/** The jwt creator. */
	@Resource(name = "jwtCreator")
	private AmwayApacJWTCreator jwtCreator;

	/** The user service. */
	@Resource(name = "userService")
	private UserService userService;

	/** The Amway APAC idp login validation service. */
	@Resource(name = "idpLoginValidationService")
	private AmwayApacIdpLoginValidationService idpLoginValidationService;

	/**
	 * handle authorize request from resource servers
	 *
	 * @param loginError
	 * @param model
	 * @param request
	 * @param session
	 * @return default login page
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doAuthorize(@RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
			final HttpServletRequest request, final HttpSession session) throws CMSItemNotFoundException
	{
		// validate login request
		final Collection<String> errors = idpLoginValidationService.validationLoginRequest(request);

		if (CollectionUtils.isNotEmpty(errors))
		{
			model.addAttribute("loginError", Boolean.TRUE);
			errors.stream().forEach(error -> GlobalMessages.addErrorMessage(model, error));
			return IDPLogin.ERROR_PAGE;
		}

		// redirect to IdP homepage controller
		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		if (!userService.isAnonymousUser(customer))
		{
			return REDIRECT_PREFIX + ROOT + IDPLogin.loginSuccessUrl(request);
		}

		// values are set as hidden parameters in login form
		model.addAttribute(IDPLogin.RESPONSE_TYPE, request.getParameter(IDPLogin.RESPONSE_TYPE));
		model.addAttribute(IDPLogin.CLIENT_ID, request.getParameter(IDPLogin.CLIENT_ID));
		model.addAttribute(IDPLogin.RESPONSE_MODE, request.getParameter(IDPLogin.RESPONSE_MODE));
		model.addAttribute(IDPLogin.SCOPE, request.getParameter(IDPLogin.SCOPE));
		model.addAttribute(IDPLogin.NONCE, request.getParameter(IDPLogin.NONCE));
		model.addAttribute(IDPLogin.STATE, request.getParameter(IDPLogin.STATE));
		model.addAttribute(IDPLogin.REDIRECT_URI, request.getParameter(IDPLogin.REDIRECT_URI));

		return getDefaultLoginPage(loginError, session, model);
	}

	/**
	 * Validate & return JWT
	 *
	 * @param request
	 * @param response
	 * @param session
	 * @return JWT DTO
	 * @throws CMSItemNotFoundException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = IDPLogin.IDP_SESSIONS_ME_URL)
	public JWTokenDto doValidateSession(final HttpServletRequest request, final HttpServletResponse response,
			final HttpSession session) throws CMSItemNotFoundException, IOException
	{
		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		if (!userService.isAnonymousUser(customer))
		{
			return populateJWTDto(request, new Date(session.getCreationTime()), customer);
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	/**
	 * Refresh JWT
	 *
	 * @param request
	 * @param response
	 * @return JWT DTO
	 * @throws CMSItemNotFoundException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = IDPLogin.IDP_REFRESH_URL)
	public JWTokenDto doRefreshSession(final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException, IOException
	{
		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		if (!userService.isAnonymousUser(customer))
		{
			return populateJWTDto(request, new Date(), customer);
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	/**
	 * Invalidate user session
	 *
	 * @param response
	 * @param session
	 * @throws CMSItemNotFoundException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = IDPLogin.IDP_SESSIONS_ME_URL)
	public void doInValidateSession(final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, IOException
	{
		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		if (!userService.isAnonymousUser(customer))
		{
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			session.invalidate();
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (httpSessionRequestCache.getRequest(request, response) != null)
		{
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		return "/";
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}

	/**
	 * Populate JWT dto.
	 *
	 * @param request
	 *           the request
	 * @param date
	 *           the date
	 * @param customer
	 *           the customer
	 * @return JWT DTO
	 */
	protected JWTokenDto populateJWTDto(final HttpServletRequest request, final Date date, final CustomerModel customer)
	{
		final String idToken = jwtCreator.createJWToken(customer.getUid(), date, request);
		final String state = request.getParameter(IDPLogin.STATE);

		final JWTokenDto token = new JWTokenDto();
		token.setState(state);
		token.setIdToken(idToken);
		return token;
	}
}
