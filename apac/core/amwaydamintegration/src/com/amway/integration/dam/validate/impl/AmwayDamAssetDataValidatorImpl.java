package com.amway.integration.dam.validate.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_ASSET_DATA_NOT_EXIST;
import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_COUNTRY_NOT_EXIST;
import static com.amway.integration.dam.util.AmwayDamValidationUtil.validateNotNull;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.util.AmwayDamValidationUtil;
import com.amway.integration.dam.validate.AmwayDamValidator;
import com.amway.core.annotations.AmwayBean;

/**
 * Validator for {@link  AmwayDamAssetData}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetDataValidatorImpl implements AmwayDamValidator
{
	@Override
	public void validate(AmwayDamProcessingData processingData)
	{
		AmwayDamAssetData assetData = processingData.getAssetData();

		validateNotNull(assetData, ERROR_ASSET_DATA_NOT_EXIST, processingData.getAssetPath(),
			  processingData.getOperationType().getCode());
		AmwayDamValidationUtil.validate(isNotEmpty(assetData.getCountries()), ERROR_COUNTRY_NOT_EXIST, assetData.getAssetId(),
			  assetData.getPath());
	}
}
