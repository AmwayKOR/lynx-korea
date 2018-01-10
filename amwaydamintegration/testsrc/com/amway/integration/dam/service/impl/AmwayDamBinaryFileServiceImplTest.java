package com.amway.integration.dam.service.impl;

import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import de.hybris.bootstrap.annotations.UnitTest;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.client.AmwayDamClient;
import com.amway.integration.dam.data.AmwayBinaryFileRequest;


/**
 * Unit Test for {@link AmwayDamBinaryFileServiceImpl}
 */
@UnitTest
public class AmwayDamBinaryFileServiceImplTest
{
	private static final String ANY_PATH = "/any/path";
	private static final String ANY_PATH_CONSTANT = "/any_constant";
	private static final String ANY_RENDITION_ID = "rendition_id";
	private static final String INSTANCE_PROP_NAME = "urlPath";

	@InjectMocks
	private AmwayDamBinaryFileServiceImpl amwayDamBinaryFileService;

	@Mock
	private AmwayDamClient amwayDamClient;

	@Mock
	private InputStream response;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		setField(amwayDamBinaryFileService, INSTANCE_PROP_NAME, ANY_PATH_CONSTANT);
	}

	@Test
	public void shouldCallDamClientWithPathUrlPathAndRenditionIdWhenRetrieveBinaryData()
	{
		AmwayBinaryFileRequest request = new AmwayBinaryFileRequest();
		request.setPath(ANY_PATH);
		request.setRenditionId(ANY_RENDITION_ID);

		amwayDamBinaryFileService.executeEvent(request);

		verify(amwayDamClient).executeBinaryRequest(ANY_PATH, ANY_PATH_CONSTANT, ANY_RENDITION_ID);
	}

	@Test
	public void shouldReturnResponseWhenNeedToExtractOutput()
	{
		InputStream result = amwayDamBinaryFileService.extractOutput(response);

		Assert.assertEquals(response, result);
	}
}