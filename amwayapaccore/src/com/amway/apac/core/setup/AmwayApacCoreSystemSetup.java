package com.amway.apac.core.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * System setup class for the amwayapaccore project.
 *
 * @author Parvesh Goyal
 */
public class AmwayApacCoreSystemSetup extends AbstractSystemSetup
{
	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		importImpexFile(context, "/amwayapaccore/import/common/essential-data.impex");
		importImpexFile(context, "/amwayapaccore/import/common/countries.impex");
		importImpexFile(context, "/amwayapaccore/import/common/delivery-modes.impex");
		importImpexFile(context, "/amwayapaccore/import/common/user-groups.impex");
		importImpexFile(context, "/amwayapaccore/import/common/themes.impex");

	}

	/**
	 * Generates the Dropdown and Multi-select boxes for the project data import. No update parameter needed as of now,
	 * so returning this as empty list.
	 * 
	 * @return list of parameters needed at update/init
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		return new ArrayList<SystemSetupParameter>();
	}

	/**
	 * This method will be called during the system initialization.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		// If any impexes are needed to be run during initialization only, put them here.
	}


}

