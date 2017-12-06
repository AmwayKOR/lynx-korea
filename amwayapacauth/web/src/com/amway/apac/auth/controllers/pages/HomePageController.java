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
package com.amway.apac.auth.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.auth.security.AmwayJWTTokenProvider;
import com.amway.apac.auth.security.impl.AmwayJWTKeyMakerImpl;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/")
public class HomePageController extends AbstractPageController
{

	@Resource(name = "jWTokenProvider")
	private AmwayJWTTokenProvider jwtTokenProvider;

	@Resource(name = "jwtKeyMaker")
	AmwayJWTKeyMakerImpl jwtKeyMaker;

	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = "logout", defaultValue = "false") final boolean logout, final Model model,
			final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (logout)
		{
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER,
					"account.confirmation.signout.title");
			return REDIRECT_PREFIX + ROOT;
		}

		/*
		 * storeCmsPageInModel(model, getContentPageForLabelOrId(null)); setUpMetaDataForContentPage(model,
		 * getContentPageForLabelOrId(null)); updatePageTitle(model, getContentPageForLabelOrId(null));
		 *
		 * return getViewForPage(model);
		 */

		final String state = request.getParameter("state");
		final String redirectUrl = request.getParameter("redirect_uri");
		final String clientId = request.getParameter("client_id");

		final String token = jwtTokenProvider.createJWToken(getCustomerFacade().getCurrentCustomerUid(), new Date(),
				request);


		model.addAttribute("id_token", token);
		model.addAttribute("state", state);
		model.addAttribute("redirect_Url", redirectUrl);
		model.addAttribute("client_Id", clientId);

		model.addAttribute("kid", jwtKeyMaker.getKid());
		model.addAttribute("n", jwtKeyMaker.getN());
		model.addAttribute("e", jwtKeyMaker.getE());

		return "pages/account/accountIDPPage";
	}

	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}
}
