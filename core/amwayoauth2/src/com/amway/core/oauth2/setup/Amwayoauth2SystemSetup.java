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
package com.amway.core.oauth2.setup;

import static com.amway.core.oauth2.constants.Amwayoauth2Constants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.amway.core.oauth2.constants.Amwayoauth2Constants;
import com.amway.core.oauth2.service.Amwayoauth2Service;


@SystemSetup(extension = Amwayoauth2Constants.EXTENSIONNAME)
public class Amwayoauth2SystemSetup
{
	private final Amwayoauth2Service amwayoauth2Service;

	public Amwayoauth2SystemSetup(final Amwayoauth2Service amwayoauth2Service)
	{
		this.amwayoauth2Service = amwayoauth2Service;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		amwayoauth2Service.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return Amwayoauth2SystemSetup.class.getResourceAsStream("/amwayoauth2/sap-hybris-platform.png");
	}
}
