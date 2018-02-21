package com.amway.integration.dam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamAssetDefinition;
import com.amway.integration.dam.data.AmwayDamAssetTypeEnum;
import com.amway.integration.dam.service.AmwayDamAssetDefinitionService;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of service for working with {@link AmwayDamAssetDefinition}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetDefinitionServiceImpl implements AmwayDamAssetDefinitionService
{
	private List<AmwayDamAssetDefinition> assetDefinitions;

	@Override
	public AmwayDamAssetDefinition getAssetDefinition(AmwayDamAssetData assetData)
	{
		AmwayDamAssetTypeEnum type = assetData.getType();
		//@formatter:off
		return assetDefinitions.stream()
			  .filter(assetDefinition -> type.equals(assetDefinition.getType()))
			  .findFirst()
			  .orElse(null);
		//@formatter:on
	}

	@Required
	public void setAssetDefinitions(List<AmwayDamAssetDefinition> assetDefinitions)
	{
		this.assetDefinitions = assetDefinitions;
	}
}
