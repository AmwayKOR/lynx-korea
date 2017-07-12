package com.amway.integration.cis.dms.jalo;

import com.amway.integration.cis.dms.constants.AmwayDMSConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;


@SuppressWarnings("PMD")
public class AmwayDMSManager extends GeneratedAmwayDMSManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AmwayDMSManager.class.getName());

	public static final AmwayDMSManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwayDMSManager) em.getExtension(AmwayDMSConstants.EXTENSIONNAME);
	}

}
