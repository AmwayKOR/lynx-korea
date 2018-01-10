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
package com.amway.amwaydms.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;
import com.amway.amwaydms.constants.Amwaydms2Constants;

@SuppressWarnings("PMD")
public class Amwaydms2Manager extends GeneratedAmwaydms2Manager
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger( Amwaydms2Manager.class.getName() );
	
	public static final Amwaydms2Manager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (Amwaydms2Manager) em.getExtension(Amwaydms2Constants.EXTENSIONNAME);
	}
	
}
