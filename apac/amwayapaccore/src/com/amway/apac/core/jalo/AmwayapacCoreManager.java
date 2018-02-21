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
package com.amway.apac.core.jalo;

import de.hybris.platform.core.systemsetup.CoreSystemSetup;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;

import com.amway.apac.core.constants.AmwayapacCoreConstants;


/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 *
 */
@SuppressWarnings("PMD")
public class AmwayapacCoreManager extends GeneratedAmwayapacCoreManager
{
	public static final AmwayapacCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwayapacCoreManager) em.getExtension(AmwayapacCoreConstants.EXTENSIONNAME);
	}
}
