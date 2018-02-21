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
package com.amway.core.amwayapigateway.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;
import com.amway.core.amwayapigateway.constants.AmwayapigatewayConstants;

@SuppressWarnings("PMD")
public class AmwayapigatewayManager extends GeneratedAmwayapigatewayManager
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger( AmwayapigatewayManager.class.getName() );
	
	public static final AmwayapigatewayManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwayapigatewayManager) em.getExtension(AmwayapigatewayConstants.EXTENSIONNAME);
	}
	
}
