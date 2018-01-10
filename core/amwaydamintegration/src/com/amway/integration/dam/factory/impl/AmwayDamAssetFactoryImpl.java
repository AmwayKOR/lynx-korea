package com.amway.integration.dam.factory.impl;

import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamAssetDefinition;
import com.amway.integration.dam.factory.AmwayDamAssetFactory;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.service.AmwayDamAssetDefinitionService;
import com.amway.core.annotations.AmwayBean;

/**
 * Factory for creating instances of {@link AmwayDamAssetModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetFactoryImpl implements AmwayDamAssetFactory
{
	@Autowired
	private ModelService modelService;
	@Autowired
	private AmwayDamAssetDefinitionService amwayDamAssetDefinitionService;

	@Override
	public AmwayDamAssetModel create(AmwayDamAssetData assetData)
	{
		AmwayDamAssetDefinition assetDefinition = amwayDamAssetDefinitionService.getAssetDefinition(assetData);
		return modelService.create(assetDefinition.getItemType());
	}

}
