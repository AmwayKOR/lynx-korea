package com.amway.integration.dam.strategy;

import com.amway.integration.dam.data.AmwayDamAssetData;


/**
 * Generating qualifier for {@link com.amway.integration.dam.model.AmwayDamAssetModel}
 */
public interface AmwayDamAssetQualifierStrategy
{
	/**
	 * Generate qualifier for {@link com.amway.integration.dam.model.AmwayDamAssetModel}
	 *
	 * @param assetData
	 * 	  information of asset
	 * @return qualifier value
	 */
	String generateQualifier(AmwayDamAssetData assetData);
}
