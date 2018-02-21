package com.amway.core.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;

import java.util.List;

import com.amway.core.constants.AmwaycoreConstants;


/**
 * System setup for amwaycore
 */
@SystemSetup(extension = AmwaycoreConstants.EXTENSIONNAME)
public class AmwaycoreSystemSetup extends AbstractSystemSetup
{
	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 *
	 * @param context the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		importImpexFile(context, "/amwaycore/import/coredata/essential-data.impex");
	}

	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		// YTODO Auto-generated method stub
		return null;
	}
}
