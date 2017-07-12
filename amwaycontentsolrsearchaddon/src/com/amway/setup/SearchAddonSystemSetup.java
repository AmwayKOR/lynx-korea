/**
 *
 */
package com.amway.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;

import java.util.List;

import com.amway.constants.AmwaycontentsolrsearchaddonConstants;


@SystemSetup(extension = AmwaycontentsolrsearchaddonConstants.EXTENSIONNAME)
public class SearchAddonSystemSetup extends AbstractSystemSetup
{
	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		importImpexFile(context, "/amwaycontentsolrsearchaddon/import/coredata/stores/amway/solr_content.impex");
	}


}
