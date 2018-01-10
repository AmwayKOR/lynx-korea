package com.amway.integration.dam.populator;

import static com.amway.integration.dam.constants.AmwayDamConstants.ASSET_KEYWORD_DELIMITER;
import static com.amway.integration.dam.util.AmwayDamTransformationUtil.populateLocalizedProperty;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.i18n.LanguageResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamExtendedAssetData;
import com.amway.integration.dam.data.AmwayDamExtendedRenditionData;
import com.amway.integration.dam.enums.AmwayDamAssetTypeEnum;
import com.amway.integration.dam.factory.AmwayDamAssetItemFactory;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.model.AmwayDamAssetVideoModel;
import com.amway.core.annotations.AmwayBean;

/**
 * Populating data from {@link AmwayDamExtendedAssetData} to {@link AmwayDamAssetModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetReversePopulator implements Populator<AmwayDamExtendedAssetData, AmwayDamAssetModel>
{
	@Autowired
	private EnumerationService enumerationService;
	@Autowired
	private LanguageResolver languageResolver;
	@Autowired
	private Converter<AmwayDamExtendedRenditionData, AmwayDamAssetItemModel> amwayDamAssetItemReverseConverter;
	@Autowired
	private AmwayDamAssetItemFactory amwayDamAssetItemFactory;

	private Map<String, String> languageToIsoCodeMap;

	@Override
	public void populate(AmwayDamExtendedAssetData source, AmwayDamAssetModel target)
	{
		AmwayDamAssetData assetData = source.getAssetData();
		CatalogVersionModel catalogVersion = source.getCatalogVersion();
		Collection<LanguageModel> catalogLanguages = catalogVersion.getLanguages();

		target.setAssetId(assetData.getAssetId());
		target.setOriginal(assetData.getPath());
		target.setKeywords(StringUtils.join(assetData.getKeyWords(), ASSET_KEYWORD_DELIMITER));
		target.setDefaultAsset(assetData.getDefaultAsset());
		target.setQualifier(source.getAssetQualifier());
		target.setRemoved(Boolean.FALSE);
		target.setCatalogVersion(catalogVersion);

		AmwayDamAssetTypeEnum assetType = enumerationService.getEnumerationValue(AmwayDamAssetTypeEnum._TYPECODE,
				assetData.getType().name());
		target.setType(assetType);

		populatePropertyForLanguages(assetData.getDescription(), target::setDescription, catalogLanguages);
		populatePropertyForLanguages(assetData.getTitle(), target::setTitle, catalogLanguages);
		populatePropertyForLanguages(assetData.getTitle(), target::setName, catalogLanguages);

		List<LanguageModel> languages = getLanguages(source);
		target.setLanguages(languages);

		List<MediaModel> mediaModels = createRenditions(source);
		target.setMedias(mediaModels);

		// TODO: to refactor
		if (target instanceof AmwayDamAssetVideoModel)
		{
			populateAssetVideo(assetData, (AmwayDamAssetVideoModel) target);
		}
	}

	private void populateAssetVideo(AmwayDamAssetData assetData, AmwayDamAssetVideoModel target)
	{
		target.setDuration(assetData.getDuration());
		target.setVideoId(assetData.getVideoId());
	}

	private void populatePropertyForLanguages(Map<String, String> source, BiConsumer<String, Locale> consumer, Collection<LanguageModel> catalogLanguages)
	{
		Map<String, String> filteredProperty = filterLocalizedValuesByCatalogs(source, catalogLanguages);
		populateLocalizedProperty(filteredProperty, consumer);
	}

	private List<LanguageModel> getLanguages(AmwayDamExtendedAssetData extendedAssetData)
	{
		AmwayDamAssetData assetData = extendedAssetData.getAssetData();
		CatalogVersionModel catalogVersion = extendedAssetData.getCatalogVersion();
		Collection<LanguageModel> catalogLanguages = catalogVersion.getLanguages();
		//@formatter:off
		return assetData.getLanguages().stream()
			  .map(languageToIsoCodeMap::get)
			  .filter(assetIsoCode -> isLanguageIsoCodeValid(assetIsoCode, catalogLanguages))
			  .map(languageResolver::getLanguage)
			  .filter(Objects::nonNull)
			  .collect(Collectors.toList());
		//@formatter:on
	}

	private List<MediaModel> createRenditions(AmwayDamExtendedAssetData extendedAssetData)
	{
		//@formatter:off
		return extendedAssetData.getExtendedRenditionDataList().stream()
			  .map(this::createRendition)
			  .collect(Collectors.toList());
		//@formatter:on
	}

	private AmwayDamAssetItemModel createRendition(AmwayDamExtendedRenditionData extendedRenditionData)
	{
		AmwayDamAssetItemModel assetItem = amwayDamAssetItemFactory.create();
		return amwayDamAssetItemReverseConverter.convert(extendedRenditionData, assetItem);
	}

	private boolean isLanguageIsoCodeValid(String languageIsoCode, Collection<LanguageModel> catalogLanguages)
	{
		if (CollectionUtils.isNotEmpty(catalogLanguages))
		{
			//@formatter:off
			return catalogLanguages.stream()
				  .anyMatch(catalogLanguage -> StringUtils.equalsIgnoreCase(catalogLanguage.getIsocode(), languageIsoCode));
			//@formatter:on
		}

		return false;
	}

	private <V> Map<String, V> filterLocalizedValuesByCatalogs(Map<String, V> mapToFilter, Collection<LanguageModel> catalogLanguages)
	{
		//@formatter:off
		return mapToFilter.entrySet().stream()
			  .filter(entry -> entry.getValue() != null)
			  .filter(entry -> isLanguageIsoCodeValid(entry.getKey(), catalogLanguages))
			  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		//@formatter:on
	}

	@Required
	public void setLanguageToIsoCodeMap(Map<String, String> languageToIsoCodeMap)
	{
		this.languageToIsoCodeMap = languageToIsoCodeMap;
	}
}
