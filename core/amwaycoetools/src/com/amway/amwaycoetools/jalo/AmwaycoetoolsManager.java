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
package com.amway.amwaycoetools.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;
import com.amway.amwaycoetools.constants.AmwaycoetoolsConstants;

@SuppressWarnings("PMD")
public class AmwaycoetoolsManager extends GeneratedAmwaycoetoolsManager
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger( AmwaycoetoolsManager.class.getName() );
	
	public static final AmwaycoetoolsManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwaycoetoolsManager) em.getExtension(AmwaycoetoolsConstants.EXTENSIONNAME);
	}
	
}
