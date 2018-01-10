package com.amway.integration.dam.dao;

import java.util.List;

import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * Dao for working with {@link AmwayDamAssetModel}
 */
public interface AmwayDamAssetDao
{
	/**
	 * Get list of {@link AmwayDamAssetModel} by original path for all inactive catalogs
	 *
	 * @param path
	 * 		path of the asset
	 * @return list of {@link AmwayDamAssetModel}
	 */
	List<AmwayDamAssetModel> findAssetsForInactiveCatalogsByPath(String path);

	/**
	 * Get list of {@link AmwayDamAssetModel} by id for all inactive catalogs
	 *
	 * @param assetId
	 * 		id of the asset
	 * @return list of {@link AmwayDamAssetModel}
	 */
	List<AmwayDamAssetModel> findAssetsForInactiveCatalogsByAssetId(String assetId);
}
