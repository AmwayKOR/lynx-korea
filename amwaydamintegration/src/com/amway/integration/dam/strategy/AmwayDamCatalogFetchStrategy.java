package com.amway.integration.dam.strategy;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import com.amway.integration.dam.data.AmwayDamCatalogFetchingData;


/**
 * Strategy of fetching appropriate catalog
 */
public interface AmwayDamCatalogFetchStrategy
{
	/**
	 * Checking that this strategy can be used
	 *
	 * @param value
	 * 	  checking value
	 * @return true if strategy can be used and false otherwise
	 */
	boolean isApplicable(String value);

	/**
	 * Fetch catalog version
	 *
	 * @param catalogFetchingData
	 * 	  information of fetched catalog version
	 * @return catalog version
	 */
	CatalogVersionModel fetchCatalogVersion(AmwayDamCatalogFetchingData catalogFetchingData);
}
