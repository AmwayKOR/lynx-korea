package com.amway.integration.dam.service;

import java.util.Collection;
import java.util.List;

import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * Service for working with DAM assets
 */
public interface AmwayDamAssetService
{
	/**
	 * Get list of DAM assets by Id
	 *
	 * @param assetId
	 * 		id of asset
	 * @return list of DAM assets
	 */
	List<AmwayDamAssetModel> getAssetsForInactiveCatalogsByAssetId(String assetId);

	/**
	 * Remove DAM asset with items and data
	 *
	 * @param asset
	 * 		instance of {@link AmwayDamAssetModel}
	 */
	void removeDamAssetWithItems(AmwayDamAssetModel asset);

	/**
	 * Save DAM assets after creation or update
	 *
	 * @param assets
	 * 		collection of {@link AmwayDamAssetModel}
	 */
	void saveDamAssets(Collection<AmwayDamAssetModel> assets);
}
