package com.amway.integration.dam.service;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamAssetDefinition;


/**
 * Service for working with {@link AmwayDamAssetDefinition}
 */
public interface AmwayDamAssetDefinitionService
{
	/**
	 * Get specification of asset
	 *
	 * @param assetData
	 * 	  information about asset
	 * @return definition of asset
	 */
	AmwayDamAssetDefinition getAssetDefinition(AmwayDamAssetData assetData);
}
