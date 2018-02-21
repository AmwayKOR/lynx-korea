package com.amway.integration.dam.dao.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * Unit test for {@link AmwayDamAssetDaoImpl}
 */
@UnitTest
public class AmwayDamAssetDaoImplTest
{
	@InjectMocks
	private AmwayDamAssetDaoImpl amwayDamAssetDao;

	@Mock
	private FlexibleSearchService flexibleSearchService;

	@Mock
	private SearchResult searchResult;
	@Mock
	private List expectedAssets;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		amwayDamAssetDao.setFlexibleSearchService(flexibleSearchService);
		when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(searchResult);
	}

	@Test
	public void shouldFindAssetsForInactiveCatalogsByPathWhenTheyAreExist()
	{
		String assetPath = "anyAssetPath";

		when(searchResult.getResult()).thenReturn(expectedAssets);
		List<AmwayDamAssetModel> resultedAssets = amwayDamAssetDao.findAssetsForInactiveCatalogsByPath(assetPath);

		assertThat(resultedAssets).isSameAs(expectedAssets);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTryingToFindAssetsForInactiveCatalogsByNullPath()
	{
		String path = null;

		amwayDamAssetDao.findAssetsForInactiveCatalogsByPath(path);
	}

	@Test
	public void shouldFindAssetsForInactiveCatalogsByAssetIdWhenTheyAreExist()
	{
		String definedAssetId = "anyDefinedAssetId";
		when(searchResult.getResult()).thenReturn(expectedAssets);

		List<AmwayDamAssetModel> resultedAssets = amwayDamAssetDao.findAssetsForInactiveCatalogsByAssetId(definedAssetId);

		assertThat(resultedAssets).isSameAs(expectedAssets);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTryingToFindAssetsForInactiveCatalogsByNullAssetId()
	{
		String nullAssetId = null;

		amwayDamAssetDao.findAssetsForInactiveCatalogsByAssetId(nullAssetId);
	}
}
