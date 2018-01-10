package com.amway.integration.dam.factory.impl;

import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.factory.AmwayDamAssetItemFactory;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.core.annotations.AmwayBean;

/**
 * Factory for creating instances of {@link AmwayDamAssetItemModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetItemFactoryImpl implements AmwayDamAssetItemFactory
{
	@Autowired
	private ModelService modelService;

	@Override
	public AmwayDamAssetItemModel create()
	{
		return modelService.create(AmwayDamAssetItemModel.class);
	}
}
