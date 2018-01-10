package com.amway.integration.dam.service.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.PARAM_MAX_HEIGHT;
import static com.amway.integration.dam.constants.AmwayDamConstants.PARAM_MAX_WIDTH;
import static com.amway.integration.dam.data.AmwayDamAssetTypeEnum.DOCUMENT;
import static java.util.Collections.emptyList;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.enumeration.EnumerationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fest.assertions.Assertions;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.dao.AmwayDamAssetFormatDao;
import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.enums.AmwayDamAssetTypeEnum;
import com.amway.integration.dam.model.AmwayDamAssetFormatModel;


/**
 * Unit Test {@link AmwayDamMediaFormatServiceImpl}
 */
@UnitTest
public class AmwayDamMediaFormatServiceImplTest
{
	private static final Integer NUMBER_LESS_THAN_ZERO = -1;
	private static final Integer NUMBER_MORE_THAN_ZERO = 1;

	private static final Double NUMBER_LESS_THAN_ONE = 0.5;
	private static final Double NUMBER_MORE_THAN_ONE = 1.5;


	@InjectMocks
	private AmwayDamMediaFormatServiceImpl assetFormatService;

	@Mock
	private EnumerationService enumerationService;
	@Mock
	private AmwayDamAssetFormatDao amwayDamAssetFormatDao;

	List<AmwayDamAssetFormatModel> assetFormatsWithoutSizes;
	@Mock
	private AmwayDamAssetFormatModel firstAssetFormatWithoutSizes;
	@Mock
	private AmwayDamAssetFormatModel secondAssetFormatWithoutSizes;

	List<AmwayDamAssetFormatModel> assetFormatsWithSizes;
	@Mock
	private AmwayDamAssetFormatModel firstAssetFormatWithSizes;
	@Mock
	private AmwayDamAssetFormatModel secondAssetFormatWithSizes;
	@Mock
	private AmwayDamRenditionData renditionData;

	private static final String VALID_FORMAT_TYPE = "document";

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		assetFormatsWithoutSizes = new ArrayList<>();
		assetFormatsWithoutSizes.add(firstAssetFormatWithoutSizes);
		assetFormatsWithoutSizes.add(secondAssetFormatWithoutSizes);

		assetFormatsWithSizes = new ArrayList<>();
		assetFormatsWithSizes.add(firstAssetFormatWithSizes);
		assetFormatsWithSizes.add(secondAssetFormatWithSizes);

		when(enumerationService.getEnumerationValue(AmwayDamAssetTypeEnum._TYPECODE, DOCUMENT.name())).thenReturn(
				AmwayDamAssetTypeEnum.DOCUMENT);

		when(renditionData.getType()).thenReturn(DOCUMENT);
	}

	@Test
	public void shouldGetFormatTypeForRenditionData()
	{
		String formatType = assetFormatService.getFormatType(renditionData);

		Assertions.assertThat(formatType).isEqualTo(VALID_FORMAT_TYPE);
	}

	@Test
	public void shouldGetFirstAssetFormatWithoutSizesWhenRenditionWidthLessThanZero()
	{
		when(renditionData.getWidth()).thenReturn(NUMBER_LESS_THAN_ZERO);
		when(amwayDamAssetFormatDao.findByAssetTypeWithoutSize(VALID_FORMAT_TYPE)).thenReturn(assetFormatsWithoutSizes);

		AmwayDamAssetFormatModel assetFormat = assetFormatService.getAssetFormat(renditionData);

		Assertions.assertThat(assetFormat).isEqualTo(firstAssetFormatWithoutSizes);
	}

	@Test
	public void shouldGetFirstAssetFormatWithoutSizesWhenRenditionHeightLessThanZero()
	{
		when(renditionData.getWidth()).thenReturn(NUMBER_MORE_THAN_ZERO);
		when(renditionData.getHeight()).thenReturn(NUMBER_LESS_THAN_ZERO);
		when(amwayDamAssetFormatDao.findByAssetTypeWithoutSize(VALID_FORMAT_TYPE)).thenReturn(assetFormatsWithoutSizes);

		AmwayDamAssetFormatModel assetFormat = assetFormatService.getAssetFormat(renditionData);

		Assertions.assertThat(assetFormat).isEqualTo(firstAssetFormatWithoutSizes);
	}

	@Test
	public void shouldGetFirstAssetFormatWithoutSizesWhenRenditionWidthIsNull()
	{
		when(renditionData.getWidth()).thenReturn(null);
		when(renditionData.getHeight()).thenReturn(NUMBER_MORE_THAN_ZERO);
		when(amwayDamAssetFormatDao.findByAssetTypeWithoutSize(VALID_FORMAT_TYPE)).thenReturn(assetFormatsWithoutSizes);

		AmwayDamAssetFormatModel assetFormat = assetFormatService.getAssetFormat(renditionData);

		Assertions.assertThat(assetFormat).isEqualTo(firstAssetFormatWithoutSizes);
	}

	@Test
	public void shouldGetFirstAssetFormatWithoutSizesWhenRenditionHeightIsNull()
	{
		when(renditionData.getHeight()).thenReturn(null);
		when(renditionData.getWidth()).thenReturn(NUMBER_MORE_THAN_ZERO);
		when(amwayDamAssetFormatDao.findByAssetTypeWithoutSize(VALID_FORMAT_TYPE)).thenReturn(assetFormatsWithoutSizes);

		AmwayDamAssetFormatModel assetFormat = assetFormatService.getAssetFormat(renditionData);

		Assertions.assertThat(assetFormat).isEqualTo(firstAssetFormatWithoutSizes);
	}

	@Test
	public void shouldNotGetAssetFormatWithoutSizesWhenNoAssetFormatsWithoutSizes()
	{
		when(renditionData.getWidth()).thenReturn(NUMBER_LESS_THAN_ZERO);

		when(amwayDamAssetFormatDao.findByAssetTypeWithoutSize(anyString())).thenReturn(emptyList());

		AmwayDamAssetFormatModel assetFormat = assetFormatService.getAssetFormat(renditionData);

		Assertions.assertThat(assetFormat).isNull();
	}

	@Test
	public void shouldFindingAssetFormatWithWidthNotLessThanZeroWhenRenditionWidthIsLessThanDeviation()
	{
		assetFormatService.setImageSizeDeviation(NUMBER_MORE_THAN_ONE);

		when(renditionData.getWidth()).thenReturn(0);
		when(renditionData.getHeight()).thenReturn(NUMBER_MORE_THAN_ZERO);

		when(amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(anyMap())).thenReturn(assetFormatsWithSizes);

		assetFormatService.getAssetFormat(renditionData);

		verify(amwayDamAssetFormatDao).findByAssetTypeWidthIntervalAndHeightInterval(
				(Map<String, Object>) argThat(Matchers.<String, Object>hasEntry(PARAM_MAX_WIDTH, 0)));
	}

	@Test
	public void shouldFindingAssetFormatWithHeightNotLessThanZeroWhenRenditionHeightIsLessThanDeviation()
	{
		assetFormatService.setImageSizeDeviation(NUMBER_MORE_THAN_ONE);

		when(renditionData.getHeight()).thenReturn(0);
		when(renditionData.getWidth()).thenReturn(NUMBER_MORE_THAN_ZERO);

		when(amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(anyMap())).thenReturn(assetFormatsWithSizes);

		assetFormatService.getAssetFormat(renditionData);

		verify(amwayDamAssetFormatDao).findByAssetTypeWidthIntervalAndHeightInterval(
				(Map<String, Object>) argThat(Matchers.<String, Object>hasEntry(PARAM_MAX_HEIGHT, 0)));
	}

	@Test
	public void shouldFindingAssetFormatWithWidthNotMoreThanMaxIntegerValueWhenTopBorderOfDeviationMoreThanInteger()
	{
		assetFormatService.setImageSizeDeviation(NUMBER_MORE_THAN_ONE);

		when(renditionData.getWidth()).thenReturn(Integer.MAX_VALUE);
		when(renditionData.getHeight()).thenReturn(NUMBER_MORE_THAN_ZERO);

		when(amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(anyMap())).thenReturn(assetFormatsWithSizes);

		assetFormatService.getAssetFormat(renditionData);

		verify(amwayDamAssetFormatDao).findByAssetTypeWidthIntervalAndHeightInterval((Map<String, Object>) argThat(
				Matchers.<String, Object>hasEntry(PARAM_MAX_WIDTH, Integer.valueOf(Integer.MAX_VALUE))));
	}

	@Test
	public void shouldFindingAssetFormatWithHeightNotMoreThanMaxIntegerValueWhenTopBorderOfDeviationMoreThanInteger()
	{
		assetFormatService.setImageSizeDeviation(NUMBER_MORE_THAN_ONE);

		when(renditionData.getHeight()).thenReturn(Integer.MAX_VALUE);
		when(renditionData.getWidth()).thenReturn(NUMBER_MORE_THAN_ZERO);

		when(amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(anyMap())).thenReturn(assetFormatsWithSizes);

		assetFormatService.getAssetFormat(renditionData);

		verify(amwayDamAssetFormatDao).findByAssetTypeWidthIntervalAndHeightInterval((Map<String, Object>) argThat(
				Matchers.<String, Object>hasEntry(PARAM_MAX_HEIGHT, Integer.valueOf(Integer.MAX_VALUE))));
	}

	@Test
	public void shouldNotGetAssetFormatWithSizeWhenNoAssetFormatsWithSizes()
	{
		assetFormatService.setImageSizeDeviation(NUMBER_LESS_THAN_ONE);
		when(renditionData.getWidth()).thenReturn(NUMBER_MORE_THAN_ZERO);
		when(renditionData.getHeight()).thenReturn(NUMBER_MORE_THAN_ZERO);

		when(amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(anyMap())).thenReturn(emptyList());

		AmwayDamAssetFormatModel assetFormat = assetFormatService.getAssetFormat(renditionData);

		Assertions.assertThat(assetFormat).isNull();
	}

}
