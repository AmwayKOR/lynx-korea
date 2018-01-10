package com.amway.integration.dam.service.impl;

import static java.util.Collections.emptyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.client.AmwayDamClient;
import com.amway.integration.dam.data.AmwayDamAssetData;


/**
 * Unit Test for {@link AmwayDamAssetInfoServiceImpl}
 */
@UnitTest
public class AmwayDamAssetInfoServiceImplTest
{
	private static final String ANY_PATH = "/any/path";
	private static final String ANY_PATH_CONSTANT = "/any_constant";
	private static final String INSTANCE_PROP_NAME = "urlPath";

	@InjectMocks
	private AmwayDamAssetInfoServiceImpl amwayDamAssetInfoService;

	@Mock
	private AmwayDamClient amwayDamClient;
	@Mock
	private Converter<Map<String, Object>, AmwayDamAssetData> amwayDamAssetDataConverter;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		setField(amwayDamAssetInfoService, INSTANCE_PROP_NAME, ANY_PATH_CONSTANT);
	}

	@Test
	public void shouldCallDamClientWithPathAndUrlPathWhenRetrieveAssetData()
	{
		amwayDamAssetInfoService.executeEvent(ANY_PATH);

		verify(amwayDamClient).executeRestRequest(ANY_PATH, ANY_PATH_CONSTANT);
	}

	@Test
	public void shouldCallConverterWhenNeedToExtractOutput()
	{
		Map<String, Object> response = emptyMap();
		AmwayDamAssetData assetData = new AmwayDamAssetData();
		when(amwayDamAssetDataConverter.convert(response)).thenReturn(assetData);

		AmwayDamAssetData result = amwayDamAssetInfoService.extractOutput(response);

		verify(amwayDamAssetDataConverter).convert(response);
		Assert.assertEquals(assetData, result);
	}
}