package com.amway.integration.dam.strategy.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.strategy.AmwayDamAssetItemFileNameStrategy;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of strategy for building file name for {@link AmwayDamAssetItemModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetItemFileNameStrategyImpl implements AmwayDamAssetItemFileNameStrategy
{
	private static final String ASSET_ITEM_PARAM = "assetItem";

	@Override
	public String getFileName(AmwayDamAssetItemModel assetItem)
	{
		validateParameterNotNullStandardMessage(ASSET_ITEM_PARAM, assetItem);
		return assetItem.getRealFileName();
	}
}
