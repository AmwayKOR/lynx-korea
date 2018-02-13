package com.amway.integration.dam.service;

import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.model.AmwayDamAssetFormatModel;


/**
 * Service for working with {@link AmwayDamRenditionData}
 */
public interface AmwayDamMediaFormatService
{
	/**
	 * Get {@link AmwayDamAssetFormatModel}
	 *
	 * @param renditionData
	 * 	  information about rendition
	 * @return asset format
	 */
	AmwayDamAssetFormatModel getAssetFormat(AmwayDamRenditionData renditionData);
}
