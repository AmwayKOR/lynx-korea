package com.amway.integration.dam.service.impl;

import static java.util.Collections.emptyMap;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.client.AmwayDamClient;
import com.amway.integration.dam.data.AmwayDamRenditionData;


/**
 * Unit Test for {@link AmwayDamRenditionInfoServiceImpl}
 */
@UnitTest
public class AmwayDamRenditionInfoServiceImplTest
{
	private static final String ANY_PATH = "/any/path";
	private static final String ANY_PATH_CONSTANT = "/any_constant";
	private static final String INSTANCE_PROP_NAME = "urlPath";

	@InjectMocks
	private AmwayDamRenditionInfoServiceImpl amwayDamRenditionInfoService;

	@Mock
	private AmwayDamClient amwayDamClient;
	@Mock
	private Converter<Map<String, Object>, List<AmwayDamRenditionData>> amwayDamRenditionDataConverter;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		setField(amwayDamRenditionInfoService, INSTANCE_PROP_NAME, ANY_PATH_CONSTANT);
	}

	@Test
	public void shouldCallDamClientWithPathAndUrlPathWhenRetrieveRenditionData()
	{
		amwayDamRenditionInfoService.executeEvent(ANY_PATH);

		verify(amwayDamClient).executeRestRequest(ANY_PATH, ANY_PATH_CONSTANT);
	}

	@Test
	public void shouldCallConverterWhenNeedToExtractOutput()
	{
		Map<String, Object> response = emptyMap();

		amwayDamRenditionInfoService.extractOutput(response);

		verify(amwayDamRenditionDataConverter).convert(eq(response), anyList());
	}
}