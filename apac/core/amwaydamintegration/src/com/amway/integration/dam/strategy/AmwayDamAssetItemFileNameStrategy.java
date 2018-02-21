package com.amway.integration.dam.strategy;

import com.amway.integration.dam.model.AmwayDamAssetItemModel;


/**
 * Generate real file name for {@link AmwayDamAssetItemModel}
 */
public interface AmwayDamAssetItemFileNameStrategy
{
	/**
	 * Generate file name for storing media
	 *
	 * @param assetItem
	 * 		rendition
	 * @return file name for storing media
	 */
	String getFileName(AmwayDamAssetItemModel assetItem);
}
