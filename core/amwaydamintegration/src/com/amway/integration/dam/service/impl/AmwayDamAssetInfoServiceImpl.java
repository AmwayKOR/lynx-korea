package com.amway.integration.dam.service.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.service.AmwayAbstractDamService;
import com.amway.integration.dam.service.AmwayDamService;
import com.amway.core.annotations.AmwayBean;

/**
 * Executes request to DAM rest api, retrieves raw information about asset data and put it into converter.
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetInfoServiceImpl extends AmwayAbstractDamService<AmwayDamAssetData, String, Map<String, Object>>
		implements AmwayDamService<String, AmwayDamAssetData>
{
	@Autowired
	private Converter<Map<String, Object>, AmwayDamAssetData> amwayDamAssetDataConverter;

	@Value("${amway.dam.assetinformation.urlPath}")
	private String urlPath;

	@Override
	protected AmwayDamAssetData extractOutput(Map<String, Object> response)
	{
		return amwayDamAssetDataConverter.convert(response);
	}

	@Override
	protected Map<String, Object> executeEvent(String path)
	{
		return getAmwayDamClient().executeRestRequest(path, urlPath);
	}
}
