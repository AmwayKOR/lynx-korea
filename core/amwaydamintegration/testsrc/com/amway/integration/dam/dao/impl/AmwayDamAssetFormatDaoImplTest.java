package com.amway.integration.dam.dao.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.model.AmwayDamAssetFormatModel;
import com.google.common.collect.ImmutableMap;


/**
 * Unit test for {@link AmwayDamAssetFormatDaoImpl}
 */
@UnitTest
public class AmwayDamAssetFormatDaoImplTest
{
	@InjectMocks
	private AmwayDamAssetFormatDaoImpl amwayDamAssetFormatDao;

	@Mock
	private FlexibleSearchService flexibleSearchService;

	@Mock
	private SearchResult searchResult;
	@Mock
	private List<AmwayDamAssetFormatModel> expectedValidAssetFormats;

	private static final String VALID_ASSET_TYPE = "assetType";
	private static final Integer VALID_INT_VALUE = 1;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		amwayDamAssetFormatDao.setFlexibleSearchService(flexibleSearchService);
		when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(searchResult);
		when(searchResult.getResult()).thenReturn(expectedValidAssetFormats);
	}

	@Test
	public void shouldFindAssetFormatsWithoutSizesByAssetType()
	{
		List<AmwayDamAssetFormatModel> resultedAssetFormats = amwayDamAssetFormatDao.findByAssetTypeWithoutSize(VALID_ASSET_TYPE);

		assertThat(resultedAssetFormats).isSameAs(expectedValidAssetFormats);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTryingToFindAssetFormatsWithoutSizesByNullAssetType()
	{
		String definedAssetType = null;

		amwayDamAssetFormatDao.findByAssetTypeWithoutSize(definedAssetType);
	}

	@Test
	public void shouldFindAssetFormatsByAssetTypeAndWidthAndHeightIntervals()
	{
		//@formatter:off
		Map<String, Object> assetFormatParameters = ImmutableMap.<String, Object>builder()
			  .put(PARAM_ASSET_TYPE, VALID_ASSET_TYPE)
			  .put(PARAM_MIN_HEIGHT, VALID_INT_VALUE)
			  .put(PARAM_MAX_HEIGHT, VALID_INT_VALUE)
			  .put(PARAM_MIN_WIDTH, VALID_INT_VALUE)
			  .put(PARAM_MAX_WIDTH, VALID_INT_VALUE)
			  .build();
		//@formatter:on
		List<AmwayDamAssetFormatModel> resultedAssetFormats = amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(
				assetFormatParameters);

		assertThat(resultedAssetFormats).isSameAs(expectedValidAssetFormats);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTryingToFindAssetFormatsByWidthHeightIntervalsAndNullAssetType()
	{
		Map<String, Object> assetFormatParameters = new HashMap<>();
		assetFormatParameters.put(PARAM_ASSET_TYPE, null);
		amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(assetFormatParameters);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionDuringFindingAssetFormatsByWidthHeightIntervalsAndWhenNullMinHeight()
	{
		Map<String, Object> assetFormatParameters = new HashMap<>();
		assetFormatParameters.put(PARAM_ASSET_TYPE, VALID_ASSET_TYPE);
		assetFormatParameters.put(PARAM_MIN_HEIGHT, null);

		amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(assetFormatParameters);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionDuringFindingAssetFormatsByWidthHeightIntervalsAndWhenNullMaxHeight()
	{
		Map<String, Object> assetFormatParameters = new HashMap<>();
		assetFormatParameters.put(PARAM_ASSET_TYPE, VALID_ASSET_TYPE);
		assetFormatParameters.put(PARAM_MIN_HEIGHT, VALID_INT_VALUE);
		assetFormatParameters.put(PARAM_MAX_HEIGHT, null);

		amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(assetFormatParameters);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionDuringFindingAssetFormatsByWidthHeightIntervalsAndWhenNullMinWidth()
	{
		Map<String, Object> assetFormatParameters = new HashMap<>();
		assetFormatParameters.put(PARAM_ASSET_TYPE, VALID_ASSET_TYPE);
		assetFormatParameters.put(PARAM_MIN_HEIGHT, VALID_INT_VALUE);
		assetFormatParameters.put(PARAM_MAX_HEIGHT, VALID_INT_VALUE);
		assetFormatParameters.put(PARAM_MIN_WIDTH, null);

		amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(assetFormatParameters);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionDuringFindingAssetFormatsByWidthHeightIntervalsAndWhenNullMaxWidth()
	{
		Map<String, Object> assetFormatParameters = new HashMap<>();
		assetFormatParameters.put(PARAM_ASSET_TYPE, VALID_ASSET_TYPE);
		assetFormatParameters.put(PARAM_MIN_HEIGHT, VALID_INT_VALUE);
		assetFormatParameters.put(PARAM_MAX_HEIGHT, VALID_INT_VALUE);
		assetFormatParameters.put(PARAM_MIN_WIDTH, VALID_INT_VALUE);
		assetFormatParameters.put(PARAM_MAX_WIDTH, null);

		amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(assetFormatParameters);
	}
}
