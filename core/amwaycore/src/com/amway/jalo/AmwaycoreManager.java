package com.amway.jalo;

import com.amway.core.constants.AmwaycoreConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;


@SuppressWarnings("PMD")
public class AmwaycoreManager extends GeneratedAmwaycoreManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AmwaycoreManager.class.getName());

	public static final AmwaycoreManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwaycoreManager) em.getExtension(AmwaycoreConstants.EXTENSIONNAME);
	}

}
