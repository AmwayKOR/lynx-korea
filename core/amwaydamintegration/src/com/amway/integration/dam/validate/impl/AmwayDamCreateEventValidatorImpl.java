package com.amway.integration.dam.validate.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_CREATE_REMOVED;
import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_DUPLICATE_ASSET;

import java.util.List;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.util.AmwayDamValidationUtil;
import com.amway.integration.dam.validate.AmwayDamValidator;
import com.amway.core.annotations.AmwayBean;

/**
 * Validating {@link AmwayDamProcessingData} for events of type CREATE
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamCreateEventValidatorImpl implements AmwayDamValidator
{
	@Override
	public void validate(AmwayDamProcessingData processingData)
	{
		List<AmwayDamAssetModel> storedDamAssets = processingData.getExistedAssets();
		AmwayDamAssetData assetData = processingData.getAssetData();
		String[] errorParams = { assetData.getAssetId(), processingData.getAssetPath() };
		boolean existRemoved = storedDamAssets.stream().anyMatch(AmwayDamAssetModel::getRemoved);
		AmwayDamValidationUtil.validate(!existRemoved, ERROR_CREATE_REMOVED, errorParams);
		AmwayDamValidationUtil.validate(storedDamAssets.isEmpty(), ERROR_DUPLICATE_ASSET, errorParams);
	}
}
