package com.amway.integration.dam.processor.impl;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.service.AmwayDamAssetMetadataService;
import com.amway.integration.dam.service.AmwayDamAssetService;


/**
 * Unit test for {@link AmwayDamAssetProcessorImpl}.
 */
@UnitTest
public class AmwayDamAssetProcessorImplTest
{
	private static final String ANY_ASSET_ID = "any_asset_id";

	@InjectMocks
	private AmwayDamAssetProcessorImpl amwayDamAssetProcessor;

	@Mock
	private AmwayDamAssetService amwayDamAssetService;
	@Mock
	private AmwayDamAssetMetadataService amwayDamAssetMetadataService;

	@Mock
	private AmwayDamProcessingData processingData;
	@Mock
	private AmwayDamAssetData validAssetData;
	@Mock
	private AmwayDamAssetModel validAssetForRemove;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRemovePreviouslyStoredAssetMetadataForDefinedAssetDuringProcessMetadata()
	{
		when(processingData.getAssetData()).thenReturn(validAssetData);
		when(validAssetData.getAssetId()).thenReturn(ANY_ASSET_ID);

		amwayDamAssetProcessor.processMetadata(processingData);

		verify(amwayDamAssetMetadataService).removeMetadataByAssetId(ANY_ASSET_ID);
	}


	@Test
	public void shouldRemoveMetadataOfAssetsForRemoved()
	{
		when(validAssetForRemove.getAssetId()).thenReturn(ANY_ASSET_ID);
		when(processingData.getAssetsForRemove()).thenReturn(singletonList(validAssetForRemove));

		amwayDamAssetProcessor.processRemoveMetadata(processingData);

		verify(amwayDamAssetMetadataService).removeMetadataByAssetId(ANY_ASSET_ID);
	}

	@Test
	public void shouldNotRemoveMetadataWhenNoAssetsForRemove()
	{
		when(processingData.getAssetsForRemove()).thenReturn(emptyList());

		amwayDamAssetProcessor.processRemoveMetadata(processingData);

		verifyZeroInteractions(amwayDamAssetMetadataService);
	}
}
