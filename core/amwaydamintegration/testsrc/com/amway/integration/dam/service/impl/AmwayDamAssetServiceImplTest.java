package com.amway.integration.dam.service.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.dao.AmwayDamAssetDao;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.service.AmwayDamAssetItemService;


/**
 * Unit test for {@link AmwayDamAssetServiceImpl}
 */
@UnitTest
public class AmwayDamAssetServiceImplTest
{
	@InjectMocks
	private AmwayDamAssetServiceImpl amwayDamAssetService;

	@Mock
	private AmwayDamAssetItemService amwayDamAssetItemService;
	@Mock
	private AmwayDamAssetDao amwayDamAssetDao;
	@Mock
	private ModelService modelService;

	private static final String VALID_ASSET_ID = "anyAssetId";
	@Mock
	AmwayDamAssetModel validAsset;
	@Mock
	List<AmwayDamAssetItemModel> validAssetItems;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(amwayDamAssetItemService.getAssetItemsForAsset(any(AmwayDamAssetModel.class))).thenReturn(mock(List.class));
	}

	@Test
	public void shouldGetAssetsForInactiveCatalogsByAssetId()
	{
		List<AmwayDamAssetModel> allAssetsForInactiveCatalogsById = mock(List.class);

		when(amwayDamAssetDao.findAssetsForInactiveCatalogsByAssetId(VALID_ASSET_ID)).thenReturn(allAssetsForInactiveCatalogsById);

		List<AmwayDamAssetModel> resultAssets = amwayDamAssetService.getAssetsForInactiveCatalogsByAssetId(VALID_ASSET_ID);

		assertThat(resultAssets).isSameAs(allAssetsForInactiveCatalogsById);
	}

	@Test
	public void shouldRemoveDefinedAsset()
	{
		when(amwayDamAssetItemService.getAssetItemsForAsset(validAsset)).thenReturn(validAssetItems);

		amwayDamAssetService.removeDamAssetWithItems(validAsset);

		verify(modelService).remove(validAsset);
	}

	@Test
	public void shouldRemoveAssetItemsWithFilesWhenRemoveDefinedAsset()
	{
		when(amwayDamAssetItemService.getAssetItemsForAsset(validAsset)).thenReturn(validAssetItems);

		amwayDamAssetService.removeDamAssetWithItems(validAsset);

		verify(amwayDamAssetItemService).removeAssetItemsWithFiles(validAssetItems);
	}
}
