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
package com.amway.amwayperformance.setup;

import static com.amway.amwayperformance.constants.AmwayperformanceConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.amway.amwayperformance.constants.AmwayperformanceConstants;
import com.amway.amwayperformance.service.AmwayperformanceService;


@SystemSetup(extension = AmwayperformanceConstants.EXTENSIONNAME)
public class AmwayperformanceSystemSetup
{
	private final AmwayperformanceService amwayperformanceService;

	public AmwayperformanceSystemSetup(final AmwayperformanceService amwayperformanceService)
	{
		this.amwayperformanceService = amwayperformanceService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		amwayperformanceService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return AmwayperformanceSystemSetup.class.getResourceAsStream("/amwayperformance/sap-hybris-platform.png");
	}
}
