package com.amway.amwayinventory.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.amway.amwayinventory.constants.AmwayInventoryConstants;

public class AmwayInventoryManager extends GeneratedAmwayInventoryManager
{
	
	public static final AmwayInventoryManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwayInventoryManager) em.getExtension(AmwayInventoryConstants.EXTENSIONNAME);
	}
	
}