package com.amway.core.events.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;

import java.util.List;

import com.amway.core.events.constants.AmwayeventsConstants;


/**
 * System setup for amwaycore
 */
@SystemSetup(extension = AmwayeventsConstants.EXTENSIONNAME)
public class AmwayeventsSystemSetup extends AbstractSystemSetup
{
	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 *
	 * @param context
	 * 		the context provides the selected parameters and values
	 */
	@SystemSetup(type = SystemSetup.Type.ESSENTIAL, process = SystemSetup.Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		
	}

	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		return null;
	}
}
