package com.amway.integration.dam.dao.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
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

import com.amway.integration.dam.model.AmwayDamAssetMetadataModel;


/**
 * Unit test for {@link AmwayDamAssetMetadataDaoImpl}
 */
@UnitTest
public class AmwayDamAssetMetadataDaoImplTest
{
	@InjectMocks
	private AmwayDamAssetMetadataDaoImpl amwayDamAssetMetadataDao;

	@Mock
	private FlexibleSearchService flexibleSearchService;

	@Mock
	private SearchResult searchResult;
	@Mock
	private List expectedAssetMetaData;

	private static final String VALID_ASSET_ID = "anyAssetId";
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		amwayDamAssetMetadataDao.setFlexibleSearchService(flexibleSearchService);
		when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(searchResult);
	}

	@Test
	public void shouldFindAssetMetadataByAssetId()
	{
		when(searchResult.getResult()).thenReturn(expectedAssetMetaData);
		List<AmwayDamAssetMetadataModel> resultedAssetMetaData = amwayDamAssetMetadataDao.findByAssetId(VALID_ASSET_ID);

		assertThat(resultedAssetMetaData).isSameAs(expectedAssetMetaData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTryingToFindAssetMetadataByNullAssetId()
	{
		String nullAssetId = null;

		amwayDamAssetMetadataDao.findByAssetId(nullAssetId);
	}
}
