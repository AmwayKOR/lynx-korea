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
package com.amway.apac.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.amway.apac.fulfilmentprocess.constants.AmwayapacFulfilmentProcessConstants;

@SuppressWarnings("PMD")
public class AmwayapacFulfilmentProcessManager extends GeneratedAmwayapacFulfilmentProcessManager
{
	public static final AmwayapacFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwayapacFulfilmentProcessManager) em.getExtension(AmwayapacFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
