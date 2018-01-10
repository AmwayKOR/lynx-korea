package com.amway.integration.dam.service.impl;

import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.dao.AmwayDamAssetDao;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.service.AmwayDamAssetItemService;
import com.amway.integration.dam.service.AmwayDamAssetService;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of service for working with DAM assets
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetServiceImpl implements AmwayDamAssetService
{
	@Autowired
	private AmwayDamAssetItemService amwayDamAssetItemService;
	@Autowired
	private AmwayDamAssetDao amwayDamAssetDao;
	@Autowired
	private ModelService modelService;

	@Override
	public List<AmwayDamAssetModel> getAssetsForInactiveCatalogsByAssetId(String assetId)
	{
		return amwayDamAssetDao.findAssetsForInactiveCatalogsByAssetId(assetId);
	}

	@Override
	public void removeDamAssetWithItems(AmwayDamAssetModel asset)
	{
		List<AmwayDamAssetItemModel> assetItems = amwayDamAssetItemService.getAssetItemsForAsset(asset);
		amwayDamAssetItemService.removeAssetItemsWithFiles(assetItems);
		modelService.remove(asset);
	}

	@Override
	public void saveDamAssets(Collection<AmwayDamAssetModel> assets)
	{
		modelService.saveAll(assets);
	}
}
