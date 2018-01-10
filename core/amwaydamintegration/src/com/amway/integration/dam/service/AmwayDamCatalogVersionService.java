package com.amway.integration.dam.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamCatalogTypeEnum;


/**
 * Service for working with {@link CatalogVersionModel} for DAM
 */
public interface AmwayDamCatalogVersionService
{
	/**
	 * Collect list of {@link CatalogVersionModel} for defined information about asset
	 *
	 * @param assetData
	 * 		asset data that contains list of countries
	 * @return list of {@link CatalogVersionModel}
	 */
	List<CatalogVersionModel> getCatalogVersionsForAssetData(AmwayDamAssetData assetData);

	/**
	 * Check catalog version instance by specified catalog type
	 *
	 * @param catalogVersion
	 * 		{@link CatalogVersionModel} to check by type
	 * @param catalogType
	 * 		type of catalog
	 * @return true if catalog version instance of specified type
	 */
	boolean isCatalogVersionValidByCatalogType(CatalogVersionModel catalogVersion, AmwayDamCatalogTypeEnum catalogType);
}
