package com.amway.integration.dam.validate.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_ASSET_ALREADY_REMOVED;
import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_ASSET_ITEM_NOT_EXIST;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.List;

import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.util.AmwayDamValidationUtil;
import com.amway.integration.dam.validate.AmwayDamValidator;
import com.amway.core.annotations.AmwayBean;

/**
 * Validating {@link AmwayDamProcessingData} for events of type REMOVE
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamRemoveEventValidatorImpl implements AmwayDamValidator
{
	@Override
	public void validate(AmwayDamProcessingData processingData)
	{
		List<AmwayDamAssetModel> assets = processingData.getAssetsForRemove();
		AmwayDamValidationUtil.validate(isNotEmpty(assets), ERROR_ASSET_ITEM_NOT_EXIST, processingData.getAssetPath());

		//@formatter:off
		boolean isAllAssetsRemoved = assets.stream()
			  .allMatch(AmwayDamAssetModel::getRemoved);

		String assetId = assets.stream()
				.findFirst()
				.map(AmwayDamAssetModel::getAssetId)
				.orElse(null);
		//@formatter:on

		AmwayDamValidationUtil.validate(!isAllAssetsRemoved, ERROR_ASSET_ALREADY_REMOVED, assetId, processingData.getAssetPath());
	}
}
