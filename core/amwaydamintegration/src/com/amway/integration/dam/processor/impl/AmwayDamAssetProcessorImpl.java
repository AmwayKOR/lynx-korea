package com.amway.integration.dam.processor.impl;

import static com.amway.integration.dam.enums.AmwayDamOperation.UPDATE;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamExtendedAssetData;
import com.amway.integration.dam.data.AmwayDamExtendedRenditionData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.factory.AmwayDamAssetFactory;
import com.amway.integration.dam.model.AmwayDamAssetFormatModel;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.processor.AmwayDamAssetProcessor;
import com.amway.integration.dam.service.AmwayDamAssetItemService;
import com.amway.integration.dam.service.AmwayDamAssetMetadataService;
import com.amway.integration.dam.service.AmwayDamAssetService;
import com.amway.integration.dam.service.AmwayDamMediaFormatService;
import com.amway.integration.dam.strategy.AmwayDamAssetItemCodeStrategy;
import com.amway.integration.dam.strategy.AmwayDamAssetQualifierStrategy;
import com.amway.integration.dam.strategy.AmwayDamRenditionFilterStrategy;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of processing {@link AmwayDamProcessingData}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetProcessorImpl implements AmwayDamAssetProcessor
{
	@Autowired
	private AmwayDamAssetFactory amwayDamAssetFactory;

	@Autowired
	private AmwayDamAssetService amwayDamAssetService;
	@Autowired
	private AmwayDamAssetMetadataService amwayDamAssetMetadataService;
	@Autowired
	private AmwayDamAssetItemService amwayDamAssetItemService;
	@Autowired
	private AmwayDamMediaFormatService amwayDamMediaFormatService;

	@Autowired
	private Converter<AmwayDamExtendedAssetData, AmwayDamAssetModel> amwayDamAssetReverseConverter;

	@Autowired
	private AmwayDamAssetQualifierStrategy amwayDamAssetQualifierStrategy;
	@Autowired
	private AmwayDamAssetItemCodeStrategy amwayDamAssetItemCodeStrategy;
	@Autowired
	private AmwayDamRenditionFilterStrategy amwayDamRenditionFilterStrategy;

	@Override
	public void processCreate(AmwayDamProcessingData processingData)
	{
		List<CatalogVersionModel> catalogsForCreateAssets = processingData.getCatalogsForCreateAssets();
		if (isNotEmpty(catalogsForCreateAssets))
		{
			AmwayDamAssetData assetData = processingData.getAssetData();
			//@formatter:off
			Map<CatalogVersionModel, AmwayDamAssetModel> catalogToAssetMap = catalogsForCreateAssets.stream()
				  .collect(toMap(identity(), catalog -> amwayDamAssetFactory.create(assetData)));
			//@formatter:on
			initAssets(catalogToAssetMap, processingData);
		}
	}

	@Override
	public void processUpdate(AmwayDamProcessingData processingData)
	{
		Map<CatalogVersionModel, AmwayDamAssetModel> assetsToUpdate = processingData.getCatalogToAssetMapForUpdate();

		if (MapUtils.isNotEmpty(assetsToUpdate))
		{
			if (UPDATE.equals(processingData.getOperationType()))
			{
				removeAssetItemsBeforeUpdate(assetsToUpdate.values());
			}
			initAssets(assetsToUpdate, processingData);
		}
	}

	private void removeAssetItemsBeforeUpdate(Collection<AmwayDamAssetModel> assetsToUpdate)
	{
		List<AmwayDamAssetItemModel> assetItemsToRemove = getAssetsItemsToRemove(assetsToUpdate);
		amwayDamAssetItemService.removeAssetItemsWithFiles(assetItemsToRemove);
	}

	private List<AmwayDamAssetItemModel> getAssetsItemsToRemove(Collection<AmwayDamAssetModel> assetsToUpdate)
	{
		//@formatter:off
		return assetsToUpdate.stream()
				.map(amwayDamAssetItemService::getAssetItemsForAsset)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		//@formatter:on
	}

	private void initAssets(Map<CatalogVersionModel, AmwayDamAssetModel> assetsToInit, AmwayDamProcessingData processingData)
	{
		Set<CatalogVersionModel> catalogVersions = assetsToInit.keySet();
		Map<CatalogVersionModel, List<AmwayDamExtendedRenditionData>> catalogToRenditions = getExtendedRenditionsData(
				catalogVersions, processingData);

		AmwayDamAssetData assetData = processingData.getAssetData();
		String assetQualifier = getAssetQualifier(processingData);
		for (CatalogVersionModel catalogVersion : catalogVersions)
		{
			AmwayDamExtendedAssetData extendedAssetData = new AmwayDamExtendedAssetData();
			extendedAssetData.setAssetData(assetData);
			extendedAssetData.setCatalogVersion(catalogVersion);
			extendedAssetData.setAssetQualifier(assetQualifier);
			//@formatter:off
			List<AmwayDamExtendedRenditionData> renditions = Optional.ofNullable(catalogToRenditions.get(catalogVersion))
				  .orElse(emptyList());
			//@formatter:on
			extendedAssetData.setExtendedRenditionDataList(renditions);

			AmwayDamAssetModel asset = assetsToInit.get(catalogVersion);
			amwayDamAssetReverseConverter.convert(extendedAssetData, asset);
		}

		Collection<AmwayDamAssetModel> assets = assetsToInit.values();
		amwayDamAssetService.saveDamAssets(assets);

		amwayDamAssetItemService.downloadFilesForAssets(assets);
	}

	private Map<CatalogVersionModel, List<AmwayDamExtendedRenditionData>> getExtendedRenditionsData(
			Set<CatalogVersionModel> catalogVersions, AmwayDamProcessingData processingData)
	{
		Collection<AmwayDamRenditionData> suitableRenditions = amwayDamRenditionFilterStrategy.getSuitableRenditions(
				processingData.getAssetData().getRenditions());
		//@formatter:off
		return suitableRenditions.stream()
			  .map(rendition -> getCatalogToRendition(catalogVersions, rendition))
			  .flatMap(catalogToRendition -> catalogToRendition.entrySet().stream())
			  .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toList())));
		//@formatter:on
	}

	private Map<CatalogVersionModel, AmwayDamExtendedRenditionData> getCatalogToRendition(Set<CatalogVersionModel> catalogVersions,
			AmwayDamRenditionData rendition)
	{
		AmwayDamAssetFormatModel mediaFormat = amwayDamMediaFormatService.getAssetFormat(rendition);
		if (mediaFormat == null)
		{
			return emptyMap();
		}

		String renditionCode = amwayDamAssetItemCodeStrategy.generateCode(rendition);
		Map<CatalogVersionModel, AmwayDamExtendedRenditionData> catalogToRenditionMap = new HashMap<>();
		for (CatalogVersionModel catalogVersion : catalogVersions)
		{
			AmwayDamExtendedRenditionData extendedRenditionData = new AmwayDamExtendedRenditionData();
			extendedRenditionData.setCatalogVersion(catalogVersion);
			extendedRenditionData.setRenditionData(rendition);
			extendedRenditionData.setRenditionCode(renditionCode);
			extendedRenditionData.setMediaFormat(mediaFormat);

			catalogToRenditionMap.put(catalogVersion, extendedRenditionData);
		}

		return catalogToRenditionMap;
	}

	private String getAssetQualifier(AmwayDamProcessingData processingData)
	{
		List<AmwayDamAssetModel> existedAssets = processingData.getExistedAssets();
		if (isEmpty(existedAssets))
		{
			return amwayDamAssetQualifierStrategy.generateQualifier(processingData.getAssetData());
		}

		return existedAssets.get(0).getQualifier();
	}

	@Override
	public void processRemove(AmwayDamProcessingData processingData)
	{
		List<AmwayDamAssetModel> assets = processingData.getAssetsForRemove();
		assets.forEach(amwayDamAssetService::removeDamAssetWithItems);
	}

	@Override
	public void processMetadata(AmwayDamProcessingData processingData)
	{
		AmwayDamAssetData assetData = processingData.getAssetData();
		String assetId = assetData.getAssetId();
		amwayDamAssetMetadataService.removeMetadataByAssetId(assetId);
		amwayDamAssetMetadataService.createMetadataForAsset(assetData);
	}

	@Override
	public void processRemoveMetadata(AmwayDamProcessingData processingData)
	{
		List<AmwayDamAssetModel> assets = processingData.getAssetsForRemove();
		if (CollectionUtils.isNotEmpty(assets))
		{
			//@formatter:off
			Optional<String> assetId = assets.stream()
					.map(AmwayDamAssetModel::getAssetId)
					.findFirst();
			//@formatter:on
			assetId.ifPresent(amwayDamAssetMetadataService::removeMetadataByAssetId);
		}
	}
}
