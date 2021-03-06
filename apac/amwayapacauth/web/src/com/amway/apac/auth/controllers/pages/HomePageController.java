package com.amway.apac.auth.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;
import com.amway.apac.auth.security.AmwayApacJWTCreator;
import com.amway.apac.auth.security.AmwayApacJWTKeyMaker;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/")
@RequireHardLogIn
public class HomePageController extends AbstractPageController
{

	/** The Amway APAC jwt creator. */
	@Resource(name = "jwtCreator")
	private AmwayApacJWTCreator jwtCreator;

	/** The Amway APAC jwt key maker. */
	@Resource(name = "jwtKeyMaker")
	private AmwayApacJWTKeyMaker jwtKeyMaker;

	/**
	 * @param logout
	 * @param model
	 * @param request
	 * @param redirectModel
	 * @return IDP page
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = "logout", defaultValue = "false") final boolean logout, final Model model,
			final HttpServletRequest request, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (logout)
		{
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, "account.confirmation.signout.title");
			return REDIRECT_PREFIX + ROOT;
		}

		final String redirectUrl = request.getParameter(IDPLogin.REDIRECT_URI);

		final String token = jwtCreator.createJWToken(getCustomerFacade().getCurrentCustomerUid(), new Date(), request);

		model.addAttribute(IDPLogin.ID_TOKEN, token);
		model.addAttribute(IDPLogin.REDIRECT_URI, redirectUrl);
		model.addAttribute(IDPLogin.STATE, request.getParameter(IDPLogin.STATE));
		model.addAttribute(IDPLogin.CLIENT_ID, request.getParameter(IDPLogin.CLIENT_ID));

		model.addAttribute(IDPLogin.KID, jwtKeyMaker.getKid());
		model.addAttribute(IDPLogin.N, jwtKeyMaker.getN());
		model.addAttribute(IDPLogin.E, jwtKeyMaker.getE());

		return IDPLogin.PAGES_ACCOUNT_IDP_PAGE;
	}

	/**
	 * Updates the page title.
	 *
	 * @param model
	 * @param cmsPage
	 */
	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}
}
