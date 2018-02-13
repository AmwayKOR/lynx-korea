package com.amway.integration.dam.populator;

import static com.amway.integration.dam.enums.AmwayDamOperation.*;
import static java.util.Collections.emptyList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.enums.AmwayDamOperation;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.integration.dam.service.AmwayDamAssetService;
import com.amway.integration.dam.service.AmwayDamCatalogVersionService;
import com.amway.integration.dam.service.AmwayDamService;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamCommonProcessingDataPopulator implements Populator<AmwayDamQueueEntryModel, AmwayDamProcessingData>
{
	@Autowired
	private AmwayDamService<String, AmwayDamAssetData> amwayDamAssetInfoService;
	@Autowired
	private AmwayDamCatalogVersionService amwayDamCatalogVersionService;
	@Autowired
	private AmwayDamService<String, List<AmwayDamRenditionData>> amwayDamRenditionInfoService;
	@Autowired
	private AmwayDamAssetService amwayDamAssetService;

	@Override
	public void populate(AmwayDamQueueEntryModel source, AmwayDamProcessingData target)
	{
		String assetPath = source.getPath();
		AmwayDamOperation operation = source.getOperation();
		AmwayDamAssetData assetData = amwayDamAssetInfoService.process(assetPath);

		target.setAssetPath(assetPath);
		target.setOperationType(operation);
		target.setAssetData(assetData);

		if (assetData != null)
		{
			List<AmwayDamAssetModel> storedDamAssets = amwayDamAssetService.getAssetsForInactiveCatalogsByAssetId(assetData.getAssetId());
			List<CatalogVersionModel> catalogVersions = amwayDamCatalogVersionService.getCatalogVersionsForAssetData(assetData);

			if (CREATE.equals(operation))
			{
				target.setCatalogsForCreateAssets(catalogVersions);
				populateRenditions(assetPath, assetData);
			}
			else if (UPDATE.equals(operation) || METADATA_UPDATE.equals(operation))
			{
				//@formatter:off
				List<CatalogVersionModel> listToCreate = catalogVersions.stream()
						.filter(catalogVersion -> isNotExistAssetForCatalogVersion(storedDamAssets, catalogVersion))
						.collect(toList());
				List<AmwayDamAssetModel> listToDelete = storedDamAssets.stream()
						.filter(asset -> !isExistCatalogVersionForAsset(catalogVersions, asset))
						.collect(toList());
				Map<CatalogVersionModel, AmwayDamAssetModel> mapToUpdate = storedDamAssets.stream()
						.filter(asset -> isExistCatalogVersionForAsset(catalogVersions, asset))
						.collect(toMap(AmwayDamAssetModel::getCatalogVersion, identity()));
				//@formatter:on
				target.setCatalogsForCreateAssets(listToCreate);
				target.setCatalogToAssetMapForUpdate(mapToUpdate);
				target.setAssetsForRemove(listToDelete);

				boolean isNeedPopulateRenditions = UPDATE.equals(operation) || isNotEmpty(listToCreate) || isNotEmpty(listToDelete);
				if (isNeedPopulateRenditions)
				{
					populateRenditions(assetPath, assetData);
				}
			}

			target.setExistedAssets(storedDamAssets);
			assetData.setPath(assetPath);
		}
	}

	private void populateRenditions(String assetPath, AmwayDamAssetData assetData)
	{
		//@formatter:off
		List<AmwayDamRenditionData> renditionsData = Optional.ofNullable(amwayDamRenditionInfoService.process(assetPath))
			  .orElse(emptyList());
		//@formatter:on
		assetData.setRenditions(renditionsData);
		renditionsData.forEach(rendition -> rendition.setAsset(assetData));
	}

	private boolean isNotExistAssetForCatalogVersion(List<AmwayDamAssetModel> assets, CatalogVersionModel catalogVersion)
	{
		//@formatter:off
		return assets.stream()
				.map(AmwayDamAssetModel::getCatalogVersion)
				.noneMatch(catalogVersion::equals);
		//@formatter:on
	}

	private boolean isExistCatalogVersionForAsset(List<CatalogVersionModel> catalogVersions, AmwayDamAssetModel asset)
	{
		//@formatter:off
		return catalogVersions.stream()
				.anyMatch(asset.getCatalogVersion()::equals);
		//@formatter:on
	}
}
