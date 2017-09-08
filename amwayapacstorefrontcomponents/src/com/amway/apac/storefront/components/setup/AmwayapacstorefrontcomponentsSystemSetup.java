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
package com.amway.apac.storefront.components.setup;

import static com.amway.apac.storefront.components.constants.AmwayapacstorefrontcomponentsConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.amway.apac.storefront.components.constants.AmwayapacstorefrontcomponentsConstants;
import com.amway.apac.storefront.components.service.AmwayapacstorefrontcomponentsService;


@SystemSetup(extension = AmwayapacstorefrontcomponentsConstants.EXTENSIONNAME)
public class AmwayapacstorefrontcomponentsSystemSetup
{
	private final AmwayapacstorefrontcomponentsService amwayapacstorefrontcomponentsService;

	public AmwayapacstorefrontcomponentsSystemSetup(final AmwayapacstorefrontcomponentsService amwayapacstorefrontcomponentsService)
	{
		this.amwayapacstorefrontcomponentsService = amwayapacstorefrontcomponentsService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		amwayapacstorefrontcomponentsService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return AmwayapacstorefrontcomponentsSystemSetup.class.getResourceAsStream("/amwayapacstorefrontcomponents/sap-hybris-platform.png");
	}
}
