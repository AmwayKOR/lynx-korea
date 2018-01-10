package com.amway.integration.dam.service.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.service.AmwayAbstractDamService;
import com.amway.integration.dam.service.AmwayDamService;
import com.amway.core.annotations.AmwayBean;

/**
 * Executes request to DAM rest api, retrieves raw information about renditions and put it into converter.
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamRenditionInfoServiceImpl
		extends AmwayAbstractDamService<List<AmwayDamRenditionData>, String, Map<String, Object>>
		implements AmwayDamService<String, List<AmwayDamRenditionData>>
{
	@Autowired
	private Converter<Map<String, Object>, List<AmwayDamRenditionData>> amwayDamRenditionDataConverter;

	@Value("${amway.dam.assetrendition.urlPath}")
	private String urlPath;

	@Override
	protected List<AmwayDamRenditionData> extractOutput(Map<String, Object> response)
	{
		List<AmwayDamRenditionData> renditions = new ArrayList<>();
		amwayDamRenditionDataConverter.convert(response, renditions);
		return renditions;
	}

	@Override
	protected Map<String, Object> executeEvent(String path)
	{
		return getAmwayDamClient().executeRestRequest(path, urlPath);
	}
}
