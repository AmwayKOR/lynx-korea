package com.amway.core.facades;

import org.apache.log4j.Logger;


import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.util.RedeployUtilities;
import de.hybris.platform.util.Utilities;


/**
 * Demonstration of how to write a standalone application that can be run directly from within eclipse or from the
 * commandline.<br>
 * To run this from commandline, just use the following command:<br>
 * <code>
 * java -jar bootstrap/bin/ybootstrap.jar "new com.amway.core.facades.AmwayfacadesStandalone().run();"
 * </code> From eclipse, just run as Java Application. Note that you maybe need to add all other projects like
 * ext-commerce, ext-pim to the Launch configuration classpath.
 */
public class AmwayfacadesStandalone
{
	private static final Logger LOG = Logger.getLogger(AmwayfacadesStandalone.class);

	/**
	 * Main class to be able to run it directly as a java program.
	 *
	 * @param args the arguments from commandline
	 */
	public static void main(final String[] args)
	{
		new AmwayfacadesStandalone().run();
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		Registry.activateStandaloneMode();
		Registry.activateMasterTenant();

		final JaloSession jaloSession = JaloSession.getCurrentSession();
		LOG.info("Session ID: " + jaloSession.getSessionID()); //NOPMD
		LOG.info("User: " + jaloSession.getUser()); //NOPMD
		Utilities.printAppInfo();

		RedeployUtilities.shutdown();
	}
}
