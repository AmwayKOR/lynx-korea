package com.amway.integration.dam.validate.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_UNKNOWN_ASSET_TYPE;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamAssetDefinition;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.service.AmwayDamAssetDefinitionService;
import com.amway.integration.dam.util.AmwayDamValidationUtil;
import com.amway.integration.dam.validate.AmwayDamValidator;
import com.amway.core.annotations.AmwayBean;

/**
 * Validator for checking that type of asset is able to be handled
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetTypeValidatorImpl implements AmwayDamValidator
{
	@Autowired
	private AmwayDamAssetDefinitionService amwayDamAssetDefinitionService;

	@Override
	public void validate(AmwayDamProcessingData processingData)
	{
		AmwayDamAssetData assetData = processingData.getAssetData();
		AmwayDamAssetDefinition assetDefinition = amwayDamAssetDefinitionService.getAssetDefinition(assetData);
		AmwayDamValidationUtil.validateNotNull(assetDefinition, ERROR_UNKNOWN_ASSET_TYPE, assetData.getAssetId(), assetData.getPath());
	}

}
