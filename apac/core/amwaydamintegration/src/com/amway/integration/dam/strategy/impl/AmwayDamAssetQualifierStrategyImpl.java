package com.amway.integration.dam.strategy.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.INTERNAL_DELIMITER;
import static com.amway.integration.dam.constants.AmwayDamConstants.PATH_DELIMITER;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;

import java.util.UUID;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.strategy.AmwayDamAssetQualifierStrategy;


/**
 * Implementation of generating qualifier for {@link com.amway.integration.dam.model.AmwayDamAssetModel}
 */
public class AmwayDamAssetQualifierStrategyImpl implements AmwayDamAssetQualifierStrategy
{
	@Override
	public String generateQualifier(AmwayDamAssetData assetData)
	{
		//@formatter:off
		return new StringBuilder()
			  .append(assetData.getType())
			  .append(INTERNAL_DELIMITER)
			  .append(substringAfterLast(assetData.getPath(), PATH_DELIMITER))
			  .append(INTERNAL_DELIMITER)
			  .append(UUID.randomUUID().toString())
			  .toString();
		//@formatter:on
	}
}
