package com.amway.integration.dam.factory;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * Factory for creating instances of {@link AmwayDamAssetModel}
 */
public interface AmwayDamAssetFactory
{
	/**
	 * Create instance of {@link AmwayDamAssetModel}
	 *
	 * @param damAssetData
	 * 	  information about asset
	 * @return instance of {@link AmwayDamAssetModel}
	 */
	AmwayDamAssetModel create(AmwayDamAssetData damAssetData);
}
