package com.amway.integration.dam.dao;

import java.util.List;
import java.util.Map;

import com.amway.integration.dam.model.AmwayDamAssetFormatModel;


/**
 * DAO for working with {@link AmwayDamAssetFormatModel}
 */
public interface AmwayDamAssetFormatDao
{
	/**
	 * Find list of the {@link AmwayDamAssetFormatModel} by type where size is not defined
	 *
	 * @param assetType
	 * 		type of asset
	 * @return list of {@link AmwayDamAssetFormatModel}
	 */
	List<AmwayDamAssetFormatModel> findByAssetTypeWithoutSize(String assetType);

	/**
	 * Find list of the {@link AmwayDamAssetFormatModel} by type, width and height
	 *
	 * @param parameters
	 * 		map of parameters type, width and height
	 * @return list of {@link AmwayDamAssetFormatModel}
	 */
	List<AmwayDamAssetFormatModel> findByAssetTypeWidthIntervalAndHeightInterval(Map<String, Object> parameters);
}
