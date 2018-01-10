package com.amway.integration.dam.service;

import java.util.Collection;
import java.util.List;

import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * DAM service for working with {@link AmwayDamAssetItemModel}
 */
public interface AmwayDamAssetItemService
{
	/**
	 * Download files from server to hybris for defined assets
	 *
	 * @param assets
	 * 		list of {@link AmwayDamAssetModel}
	 */
	void downloadFilesForAssets(Collection<AmwayDamAssetModel> assets);

	/**
	 * Remove from hybris files related to defined assets
	 *
	 * @param assetItems
	 * 		list of asset items
	 */
	void removeAssetItemsWithFiles(Collection<? extends AmwayDamAssetItemModel> assetItems);

	/**
	 * Get asset items for defined asset
	 *
	 * @param asset
	 * 		instance of the asset
	 * @return list of asset items
	 */
	List<AmwayDamAssetItemModel> getAssetItemsForAsset(AmwayDamAssetModel asset);
}
