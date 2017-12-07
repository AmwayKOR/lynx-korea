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
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.apac.auth.dto.JWTKeys;
import com.amway.apac.auth.dto.Key;
import com.amway.apac.auth.security.impl.AmwayJWTKeyMakerImpl;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/oauth2/v1/keys")
public class KeysController extends AbstractPageController
{

	@Resource(name = "jwtKeyMaker")
	AmwayJWTKeyMakerImpl jwtKeyMaker;


	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody JWTKeys getKeys(final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		final JWTKeys jwtKey = new JWTKeys();
		final List<Key> keies = new ArrayList<>();
		final Key key = new Key();

		key.setAlg("RS256");
		key.setE(jwtKeyMaker.getE());
		key.setKid(jwtKeyMaker.getKid());
		key.setKty("RSA");
		key.setN(jwtKeyMaker.getN());
		key.setUse("sig");
		keies.add(key);
		jwtKey.setKeys(keies);
		/*
		 * model.addAttribute("kid", jwtKeyMaker.getKid()); model.addAttribute("n", jwtKeyMaker.getN());
		 * model.addAttribute("e", jwtKeyMaker.getE()); response.setContentType("application/json");
		 */
		return jwtKey;
	}

	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}

	/**
	 * @param jwtKeyMaker
	 *           the jwtKeyMaker to set
	 */
	public void setJwtKeyMaker(final AmwayJWTKeyMakerImpl jwtKeyMaker)
	{
		this.jwtKeyMaker = jwtKeyMaker;
	}
}
