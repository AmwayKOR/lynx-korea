package com.amway.amwayordermanagementwebservices.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;
import com.amway.amwayordermanagementwebservices.constants.AmwayordermanagementwebservicesConstants;

@SuppressWarnings("PMD")
public class AmwayordermanagementwebservicesManager extends GeneratedAmwayordermanagementwebservicesManager
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger( AmwayordermanagementwebservicesManager.class.getName() );
	
	public static final AmwayordermanagementwebservicesManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwayordermanagementwebservicesManager) em.getExtension(AmwayordermanagementwebservicesConstants.EXTENSIONNAME);
	}
	
}
