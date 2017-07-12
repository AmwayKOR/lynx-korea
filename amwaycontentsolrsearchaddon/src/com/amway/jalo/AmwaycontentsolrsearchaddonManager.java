package com.amway.jalo;

import com.amway.constants.AmwaycontentsolrsearchaddonConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;


@SuppressWarnings("PMD")
public class AmwaycontentsolrsearchaddonManager extends GeneratedAmwaycontentsolrsearchaddonManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AmwaycontentsolrsearchaddonManager.class.getName());

	public static final AmwaycontentsolrsearchaddonManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwaycontentsolrsearchaddonManager) em.getExtension(AmwaycontentsolrsearchaddonConstants.EXTENSIONNAME);
	}

}
