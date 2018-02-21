package com.amway.facades;


import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commercefacades.catalog.impl.DefaultCatalogFacade;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;
import java.util.Collection;


/**
 * Extension of {@link de.hybris.platform.commercefacades.catalog.impl.DefaultCatalogFacade}
 *
 * @author Viktar_Yarmalovich on 6/23/2015.
 */
public class AmwayCatalogFacade extends DefaultCatalogFacade
{

	@Resource(name = "catalogVersionService")
	CatalogVersionService catalogVersionService;
	@Resource(name = "sessionService")
	SessionService sessionService;
	@Resource(name = "userService")
	protected UserService userService;

	/**
	 * Returns CatalogVersionModel object for particular catalog version ID
	 *
	 * @param catalogVersionId - valid version ID of catalog (Imported, Staged, Online)
	 * @return List of product codes
	 */
	public CatalogVersionModel getCatalogVersion(final String catalogVersionId)
	{
		Collection<CatalogVersionModel> catalogs = catalogVersionService.getSessionCatalogVersions();
		if (catalogs.isEmpty())
			return null;

		final String catalogId = catalogs.iterator().next().getCatalog().getId();

		return getCatalogVersion(catalogId, catalogVersionId);
	}

	/**
	 * Returns CatalogVersionModel object for particular catalog version ID
	 *
	 * @param catalogId
	 * @param catalogVersionId - valid version ID of catalog (Imported, Staged, Online)
	 * @return List of product codes
	 */
	public CatalogVersionModel getCatalogVersion(final String catalogId, final String catalogVersionId)
	{
		return sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public CatalogVersionModel execute()
			{
				final UserModel admin = userService.getAdminUser();
				userService.setCurrentUser(admin);
				return catalogVersionService.getCatalogVersion(catalogId, catalogVersionId);
			}
		});
	}
}
