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
package com.amway.apac.coupon.setup;

import static com.amway.apac.coupon.constants.AmwayapaccouponConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.amway.apac.coupon.constants.AmwayapaccouponConstants;
import com.amway.apac.coupon.service.AmwayapaccouponService;


@SystemSetup(extension = AmwayapaccouponConstants.EXTENSIONNAME)
public class AmwayapaccouponSystemSetup
{
	private final AmwayapaccouponService amwayapaccouponService;

	public AmwayapaccouponSystemSetup(final AmwayapaccouponService amwayapaccouponService)
	{
		this.amwayapaccouponService = amwayapaccouponService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		amwayapaccouponService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return AmwayapaccouponSystemSetup.class.getResourceAsStream("/amwayapaccoupon/sap-hybris-platform.png");
	}
}
