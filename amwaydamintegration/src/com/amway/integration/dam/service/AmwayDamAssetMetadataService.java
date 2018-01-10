package com.amway.integration.dam.service;

import com.amway.integration.dam.data.AmwayDamAssetData;


/**
 * Service for working with DAM asset metadata
 */
public interface AmwayDamAssetMetadataService
{
	/**
	 * Remove metadata items by id of asset
	 *
	 * @param assetId
	 * 		id of asset
	 */
	void removeMetadataByAssetId(String assetId);

	/**
	 * Create and save metadata by asset data
	 *
	 * @param assetData
	 * 		dto with asset details
	 */
	void createMetadataForAsset(AmwayDamAssetData assetData);
}
