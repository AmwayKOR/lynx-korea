package com.amway.integration.dam.service.impl;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.data.AmwayBinaryFileRequest;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.service.AmwayDamService;
import com.amway.integration.dam.strategy.AmwayDamAssetItemFileNameStrategy;


/**
 * Unit-test for {@link AmwayDamAssetItemServiceImpl}
 */
@UnitTest
public class AmwayDamAssetItemServiceImplTest
{
	private static final String ANY_FILE_NAME = "any.file";
	private static final String ANY_MIME_TYPE = "any/mime";

	@InjectMocks
	private AmwayDamAssetItemServiceImpl amwayDamAssetItemService;

	@Mock
	private AmwayDamService<AmwayBinaryFileRequest, InputStream> amwayDamBinaryFileService;
	@Mock
	private AmwayDamAssetItemFileNameStrategy amwayDamAssetItemFileNameStrategy;
	@Mock
	private MediaService mediaService;
	@Mock
	private ModelService modelService;

	@Mock
	private InputStream binaryFileStream;
	@Mock
	private MediaModel anyMedia;
	@Mock
	private AmwayDamAssetModel validAsset;
	@Mock
	private AmwayDamAssetItemModel validAssetItem;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		when(amwayDamBinaryFileService.process(any(AmwayBinaryFileRequest.class))).thenReturn(binaryFileStream);
		when(amwayDamAssetItemFileNameStrategy.getFileName(any())).thenReturn(ANY_FILE_NAME);
		when(validAssetItem.getMime()).thenReturn(ANY_MIME_TYPE);
	}

	@Test
	public void shouldDownloadFilesWhenAssetItemsAreDefined()
	{
		Collection<MediaModel> assetItems = singletonList(validAssetItem);

		Collection<AmwayDamAssetModel> assets = new ArrayList<>();
		assets.add(validAsset);

		when(validAsset.getMedias()).thenReturn(assetItems);

		amwayDamAssetItemService.downloadFilesForAssets(assets);

		verify(mediaService).setStreamForMedia(validAssetItem, binaryFileStream, ANY_FILE_NAME, ANY_MIME_TYPE);
	}

	@Test
	public void shouldNotDownloadFilesWhenAssetsAreNotDefined()
	{
		Collection<AmwayDamAssetModel> assets = emptyList();

		amwayDamAssetItemService.downloadFilesForAssets(assets);

		verifyZeroInteractions(mediaService);
	}

	@Test
	public void shouldNotDownloadAnyFilesForAssetWithoutAssetItems()
	{
		Collection<AmwayDamAssetModel> assets = singletonList(validAsset);

		when(validAsset.getMedias()).thenReturn(emptyList());

		amwayDamAssetItemService.downloadFilesForAssets(assets);

		verifyZeroInteractions(mediaService);
	}

	@Test
	public void shouldNotDownloadFilesForNotAssetItems()
	{
		Collection<MediaModel> assetItems = singletonList(anyMedia);
		Collection<AmwayDamAssetModel> assets = singletonList(validAsset);

		when(validAsset.getMedias()).thenReturn(assetItems);

		amwayDamAssetItemService.downloadFilesForAssets(assets);

		verifyZeroInteractions(mediaService);
	}

	@Test
	public void shouldRemoveFilesForDefinedAssetItems()
	{
		Collection<AmwayDamAssetItemModel> assetItems = singletonList(validAssetItem);

		amwayDamAssetItemService.removeAssetItemsWithFiles(assetItems);

		verify(mediaService).removeDataFromMediaQuietly(validAssetItem);
	}

	@Test
	public void shouldNotRemoveAnyFilesWhenAssetItemsAreNotDefined()
	{
		Collection<AmwayDamAssetItemModel> assetItems = emptyList();

		amwayDamAssetItemService.removeAssetItemsWithFiles(assetItems);

		verifyZeroInteractions(mediaService);
	}

	@Test
	public void shouldRemoveDefinedAssetItems()
	{
		Collection<AmwayDamAssetItemModel> assetItems = singletonList(validAssetItem);

		amwayDamAssetItemService.removeAssetItemsWithFiles(assetItems);

		verify(modelService).remove(validAssetItem);
	}

	@Test
	public void shouldNotRemoveAnyAssetItemsWhenAssetItemsAreNotDefined()
	{
		Collection<AmwayDamAssetItemModel> assetItems = emptyList();

		amwayDamAssetItemService.removeAssetItemsWithFiles(assetItems);

		verifyZeroInteractions(modelService);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenTryingToGetAssetItemsForNullAsset()
	{
		AmwayDamAssetModel asset = null;

		amwayDamAssetItemService.getAssetItemsForAsset(asset);
	}

	@Test
	public void shouldGetEmptyListOfAssetItemsForAssetWithoutAssetItems()
	{
		when(validAsset.getMedias()).thenReturn(emptyList());

		List<AmwayDamAssetItemModel> assetItems = amwayDamAssetItemService.getAssetItemsForAsset(validAsset);

		assertTrue(assetItems.isEmpty());
	}

	@Test
	public void shouldGetOnlyAssetItemsForAssetWithMedias()
	{
		Collection<MediaModel> mediasOfAsset = singletonList(anyMedia);

		when(validAsset.getMedias()).thenReturn(mediasOfAsset);

		List<AmwayDamAssetItemModel> assetItems = amwayDamAssetItemService.getAssetItemsForAsset(validAsset);

		assertFalse(assetItems.contains(anyMedia));
	}

	@Test
	public void shouldGetAssetItemsForDefinedAsset()
	{
		Collection<MediaModel> mediasOfAsset = singletonList(validAssetItem);

		when(validAsset.getMedias()).thenReturn(mediasOfAsset);

		List<AmwayDamAssetItemModel> assetItems = amwayDamAssetItemService.getAssetItemsForAsset(validAsset);

		assertTrue(assetItems.contains(validAssetItem));
	}
}
