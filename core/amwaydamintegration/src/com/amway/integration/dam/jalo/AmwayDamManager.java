package com.amway.integration.dam.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.integration.dam.constants.AmwayDamConstants;


@SuppressWarnings("PMD")
public class AmwayDamManager extends GeneratedAmwayDamManager
{
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AmwayDamManager.class.getName());

	public static final AmwayDamManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AmwayDamManager) em.getExtension(AmwayDamConstants.EXTENSIONNAME);
	}
}
