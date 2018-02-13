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

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;

import java.util.List;

import com.amway.apac.coupon.constants.AmwayapaccouponConstants;


@SystemSetup(extension = AmwayapaccouponConstants.EXTENSIONNAME)
public class AmwayapaccouponSystemSetup extends AbstractSystemSetup
{
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		//importImpexFile(context, "/impex/essentialdata-amwaycoupon-validation.impex");
	}

	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		// YTODO Auto-generated method stub
		return null;
	}
}
