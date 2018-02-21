package com.amway.integration.dam.populator;

import static com.amway.integration.dam.constants.AmwayDamConstants.*;
import static com.amway.integration.dam.util.AmwayDamTransformationUtil.*;

import de.hybris.platform.converters.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamAssetTypeEnum;

import com.amway.core.annotations.AmwayBean;


/**
 * Populates data from JSON representation as {@link Map<String, Object>} to DTO with asset data.
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetDataPopulator implements Populator<Map<String, Object>, AmwayDamAssetData>
{
	private Map<String, String> amwayDamAliasMap;
	private Map<String, String> amwayDamTagNamesMap;
	private Map<String, String> amwayDamTitlesMap;
	private Map<String, String> amwayDamDescriptionMap;
	private List<String> amwayDamIsoCodes;
	private Map<String, AmwayDamAssetTypeEnum> amwayDamExceptionalTypes;

	@Override
	@SuppressWarnings("unchecked")
	public void populate(Map<String, Object> source, AmwayDamAssetData target)
	{
		if (MapUtils.isNotEmpty(source))
		{
			populateByFormatOrMimeType(source, target);

			Boolean defaultAsset = getByAlias(Boolean.class, ATTRIBUTE_DEFAULT_ASSET, source, amwayDamAliasMap);
			List<String> tags = getByAlias(List.class, ATTRIBUTE_TAGS, source, amwayDamAliasMap);

			target.setAssetId(getByAlias(String.class, ATTRIBUTE_ASSET_ID, source, amwayDamAliasMap));
			target.setAssetModified(getByAlias(String.class, ATTRIBUTE_ASSET_MODIFIED, source, amwayDamAliasMap));
			target.setKeyWords(getListByAlias(String.class, ATTRIBUTE_KEYWORDS, source, amwayDamAliasMap));
			target.setDuration(getByAlias(String.class, ATTRIBUTE_DURATION, source, amwayDamAliasMap));
			target.setVideoId(getByAlias(String.class, ATTRIBUTE_VIDEO_ID, source, amwayDamAliasMap));
			target.setDefaultAsset(Optional.ofNullable(defaultAsset).orElse(false));

			target.setRowAssetMetadata(source);
			target.setTitle(populateMapByIsoCodes(source, target.getTitle(), amwayDamTitlesMap, amwayDamIsoCodes));
			target.setDescription(populateMapByIsoCodes(source, target.getDescription(), amwayDamDescriptionMap, amwayDamIsoCodes));

			populateByTags(tags, target);
		}
	}

	private void populateByFormatOrMimeType(Map<String, Object> source, AmwayDamAssetData target)
	{
		String format = getByAlias(String.class, ATTRIBUTE_FORMAT, source, amwayDamAliasMap);
		String mimeType = format != null ? format : getByAlias(String.class, ATTRIBUTE_TYPE, source, amwayDamAliasMap);

		target.setMimeType(mimeType);
		populateTypeByMimeType(mimeType, amwayDamExceptionalTypes, target::setType);
	}

	private void populateByTags(List<String> tags, AmwayDamAssetData target)
	{
		if (tags != null)
		{
			List<String> countries = new ArrayList<>();
			List<String> languages = new ArrayList<>();
			for (String tag : tags)
			{
				populateByTagNameWithSplit(amwayDamTagNamesMap.get(TAG_NAME_MCP), tag, countries::add);
				populateByTagNameWithSplit(amwayDamTagNamesMap.get(TAG_NAME_COUNTRIES), tag, countries::add);
				populateByTagNameWithSplit(amwayDamTagNamesMap.get(TAG_NAME_LANGUAGES), tag, languages::add);
			}
			target.setCountries(countries);
			target.setLanguages(languages);
		}
	}

	@Required
	public void setAmwayDamExceptionalTypes(Map<String, AmwayDamAssetTypeEnum> amwayDamExceptionalTypes)
	{
		this.amwayDamExceptionalTypes = amwayDamExceptionalTypes;
	}

	@Required
	public void setAmwayDamIsoCodes(List<String> amwayDamIsoCodes)
	{
		this.amwayDamIsoCodes = amwayDamIsoCodes;
	}

	@Required
	public void setAmwayDamAliasMap(Map<String, String> amwayDamAliasMap)
	{
		this.amwayDamAliasMap = amwayDamAliasMap;
	}

	@Required
	public void setAmwayDamTagNamesMap(Map<String, String> amwayDamTagNamesMap)
	{
		this.amwayDamTagNamesMap = amwayDamTagNamesMap;
	}

	@Required
	public void setAmwayDamTitlesMap(Map<String, String> amwayDamTitlesMap)
	{
		this.amwayDamTitlesMap = amwayDamTitlesMap;
	}

	@Required
	public void setAmwayDamDescriptionMap(Map<String, String> amwayDamDescriptionMap)
	{
		this.amwayDamDescriptionMap = amwayDamDescriptionMap;
	}
}
