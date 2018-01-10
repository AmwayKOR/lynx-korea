package com.amway.integration.dam.dao;

import java.util.List;

import com.amway.integration.dam.model.AmwayDamAssetMetadataModel;


/**
 * DAO for working with {@link AmwayDamAssetMetadataModel}
 */
public interface AmwayDamAssetMetadataDao
{
	/**
	 * <p>Get list of the {@link AmwayDamAssetMetadataModel} by asset id.</p>
	 *
	 * @param assetId
	 * 	  id of the {@link AmwayDamAssetMetadataModel}
	 * @return list of {@link AmwayDamAssetMetadataModel}
	 */
	List<AmwayDamAssetMetadataModel> findByAssetId(String assetId);
}
