package com.amway.integration.dam.service.impl;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.data.AmwayBinaryFileRequest;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.service.AmwayDamAssetItemService;
import com.amway.integration.dam.service.AmwayDamService;
import com.amway.integration.dam.strategy.AmwayDamAssetItemFileNameStrategy;
import com.amway.core.annotations.AmwayBean;

/**
 * DAM service for working with {@link AmwayDamAssetItemModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetItemServiceImpl implements AmwayDamAssetItemService
{
	@Autowired
	private AmwayDamService<AmwayBinaryFileRequest, InputStream> amwayDamBinaryFileService;
	@Autowired
	private AmwayDamAssetItemFileNameStrategy amwayDamAssetItemFileNameStrategy;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private ModelService modelService;

	@Override
	public void downloadFilesForAssets(Collection<AmwayDamAssetModel> assets)
	{
		if (CollectionUtils.isNotEmpty(assets))
		{
			//@formatter:off
			assets.stream()
				  .map(this::getAssetItemsForAsset)
				  .flatMap(List::stream)
				  .forEach(this::downloadFileForAssetItem);
			//@formatter:on
		}
	}

	private void downloadFileForAssetItem(AmwayDamAssetItemModel assetItem)
	{
		AmwayBinaryFileRequest fileRequest = new AmwayBinaryFileRequest();
		fileRequest.setPath(assetItem.getPath());
		fileRequest.setRenditionId(assetItem.getRealFileName());
		InputStream inputStream = amwayDamBinaryFileService.process(fileRequest);
		String originalFileName = amwayDamAssetItemFileNameStrategy.getFileName(assetItem);
		mediaService.setStreamForMedia(assetItem, inputStream, originalFileName, assetItem.getMime());
	}

	@Override
	public void removeAssetItemsWithFiles(Collection<? extends AmwayDamAssetItemModel> assetItems)
	{
		if (CollectionUtils.isNotEmpty(assetItems))
		{
			//@formatter:off
			assetItems.stream()
					.peek(mediaService::removeDataFromMediaQuietly)
					.forEach(modelService::remove);
			//@formatter:on
		}
	}

	@Override
	public List<AmwayDamAssetItemModel> getAssetItemsForAsset(AmwayDamAssetModel asset)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("asset", asset);
		Collection<MediaModel> mediaItems = asset.getMedias();
		if (CollectionUtils.isNotEmpty(mediaItems))
		{
			//@formatter:off
			return mediaItems.stream()
				  .filter(AmwayDamAssetItemModel.class::isInstance)
				  .map(AmwayDamAssetItemModel.class::cast)
				  .collect(Collectors.toList());
			//@formatter:on
		}

		return Collections.emptyList();
	}

}
