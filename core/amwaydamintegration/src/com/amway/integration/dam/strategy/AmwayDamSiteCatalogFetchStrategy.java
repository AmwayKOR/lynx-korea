package com.amway.integration.dam.strategy;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;

import com.amway.integration.dam.data.AmwayDamCatalogFetchingData;


/**
 * Site-depended strategy for fetching catalog version
 */
public interface AmwayDamSiteCatalogFetchStrategy
{
	/**
	 * Fetch catalog version by site and information of catalog version
	 *
	 * @param catalogFetchingData
	 * 	  information of catalog version
	 * @param site
	 * 	  site
	 * @return catalog version
	 */
	CatalogVersionModel fetchCatalogVersionBySite(AmwayDamCatalogFetchingData catalogFetchingData, CMSSiteModel site);
}
