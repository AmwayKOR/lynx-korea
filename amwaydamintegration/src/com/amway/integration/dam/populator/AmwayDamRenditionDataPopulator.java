package com.amway.integration.dam.populator;


import static com.amway.integration.dam.constants.AmwayDamConstants.*;
import static com.amway.integration.dam.util.AmwayDamTransformationUtil.getByAlias;
import static com.amway.integration.dam.util.AmwayDamTransformationUtil.populateTypeByMimeType;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;

import de.hybris.platform.converters.Populator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamAssetTypeEnum;
import com.amway.integration.dam.data.AmwayDamRenditionData;

import com.amway.core.annotations.AmwayBean;


/**
 * Populates data from JSON representation as {@link Map<String, Object>} to DTO with renditions data.
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamRenditionDataPopulator implements Populator<Map<String, Object>, List<AmwayDamRenditionData>>
{
	private Map<String, String> amwayDamAliasMap;
	private Map<String, AmwayDamAssetTypeEnum> amwayDamExceptionalTypes;

	@Override
	public void populate(Map<String, Object> source, List<AmwayDamRenditionData> target)
	{
		//@formatter:off
		Map<String, Map> rowRenditions = source.entrySet().stream()
				.filter(entry -> entry.getValue() instanceof Map)
				.collect(toMap(Entry::getKey, this::getContentAsValue));
		rowRenditions.entrySet().forEach(entry -> fillAmwayRenditionData(entry, target));
		//@formatter:on
	}

	@SuppressWarnings("unchecked")
	private void fillAmwayRenditionData(Entry<String, Map> entry, List<AmwayDamRenditionData> target)
	{
		AmwayDamRenditionData rendition = new AmwayDamRenditionData();
		rendition.setRenditionId(entry.getKey());
		rendition.setHeight(getByAlias(Integer.class, ATTRIBUTE_HEIGHT, entry.getValue(), amwayDamAliasMap));
		rendition.setWidth(getByAlias(Integer.class, ATTRIBUTE_WIGHT, entry.getValue(), amwayDamAliasMap));
		rendition.setMimeType(getByAlias(String.class, ATTRIBUTE_FORMAT, entry.getValue(), amwayDamAliasMap));
		rendition.setFiltrationProperties(getByAlias(List.class, ATTRIBUTE_TYPE, entry.getValue(), amwayDamAliasMap));
		populateTypeByMimeType(rendition.getMimeType(), amwayDamExceptionalTypes, rendition::setType);

		target.add(rendition);
	}

	@SuppressWarnings("unchecked")
	private Map getContentAsValue(Entry<String, Object> entry)
	{
		Map contentMap = getByAlias(Map.class, ATTRIBUTE_CONTENT, (Map) entry.getValue(), amwayDamAliasMap);
		return (contentMap != null) ? contentMap : emptyMap();
	}

	@Required
	public void setAmwayDamAliasMap(Map<String, String> amwayDamAliasMap)
	{
		this.amwayDamAliasMap = amwayDamAliasMap;
	}

	@Required
	public void setAmwayDamExceptionalTypes(Map<String, AmwayDamAssetTypeEnum> amwayDamExceptionalTypes)
	{
		this.amwayDamExceptionalTypes = amwayDamExceptionalTypes;
	}
}
