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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.apac.auth.controllers.ControllerConstants.IDPLogin;
import com.amway.apac.auth.dto.JWTKeysDto;
import com.amway.apac.auth.dto.KeyDto;
import com.amway.apac.auth.security.impl.DefaultAmwayApacJWTKeyMaker;


/**
 * Keys Controller, Magic calls keys controller to fetch the keys for JWT Signature verification
 */
@Controller
@RequestMapping("/oauth2/v1/keys")
public class AmwayApacKeysController extends AbstractPageController
{
	@Resource(name = "jwtKeyMaker")
	DefaultAmwayApacJWTKeyMaker jwtKeyMaker;

	/**
	 * @return jwtKeysDto
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public JWTKeysDto getKeys() throws CMSItemNotFoundException
	{
		final JWTKeysDto jwtKey = new JWTKeysDto();
		final List<KeyDto> keies = new ArrayList<>();
		final KeyDto key = new KeyDto();

		key.setAlg(IDPLogin.SECURITY_ALGORITHM);
		key.setE(jwtKeyMaker.getE());
		key.setKid(jwtKeyMaker.getKid());
		key.setKty(IDPLogin.KEY_TYPE);
		key.setN(jwtKeyMaker.getN());
		key.setUse(IDPLogin.KEY_USE);
		keies.add(key);
		jwtKey.setKeys(keies);
		return jwtKey;
	}

	/**
	 * @param jwtKeyMaker
	 *           the jwtKeyMaker to set
	 */
	public void setJwtKeyMaker(final DefaultAmwayApacJWTKeyMaker jwtKeyMaker)
	{
		this.jwtKeyMaker = jwtKeyMaker;
	}
}
