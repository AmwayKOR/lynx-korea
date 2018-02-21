package com.amway.integration.dam.service.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.dao.AmwayDamAssetMetadataDao;
import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.model.AmwayDamAssetMetadataModel;
import com.amway.integration.dam.service.AmwayDamAssetMetadataService;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetMetadataServiceImpl implements AmwayDamAssetMetadataService
{
	@Autowired
	private ModelService modelService;
	@Autowired
	private AmwayDamAssetMetadataDao amwayDamAssetMetadataDao;
	@Autowired
	private Converter<AmwayDamAssetData, List<AmwayDamAssetMetadataModel>> amwayDamAssetMetadataReverseConverter;

	@Override
	public void removeMetadataByAssetId(String assetId)
	{
		List<AmwayDamAssetMetadataModel> storedAssetMetadata = amwayDamAssetMetadataDao.findByAssetId(assetId);
		modelService.removeAll(storedAssetMetadata);
	}

	@Override
	public void createMetadataForAsset(AmwayDamAssetData assetData)
	{
		List<AmwayDamAssetMetadataModel> newAssetMetadata = new ArrayList<>();
		amwayDamAssetMetadataReverseConverter.convert(assetData, newAssetMetadata);
		modelService.saveAll(newAssetMetadata);
	}
}
