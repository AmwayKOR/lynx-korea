package com.amway.integration.dam.service.impl;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.dao.AmwayDamAssetMetadataDao;
import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.model.AmwayDamAssetMetadataModel;


/**
 * Unit-test for {@link AmwayDamAssetMetadataServiceImpl}
 */
@UnitTest
public class AmwayDamAssetMetadataServiceImplTest
{
	private static final String ANY_ASSET_ID = "any_asset";

	@InjectMocks
	private AmwayDamAssetMetadataServiceImpl amwayDamAssetMetadataService;

	@Mock
	private ModelService modelService;
	@Mock
	private AmwayDamAssetMetadataDao amwayDamAssetMetadataDao;
	@Mock
	private Converter<AmwayDamAssetData, List<AmwayDamAssetMetadataModel>> amwayDamAssetMetadataReverseConverter;

	@Mock
	private AmwayDamAssetData validAssetData;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldFindStoredMetadataWhenMetadataRemovedByAssetId()
	{
		amwayDamAssetMetadataService.removeMetadataByAssetId(ANY_ASSET_ID);

		verify(amwayDamAssetMetadataDao).findByAssetId(ANY_ASSET_ID);
	}

	@Test
	public void shouldRemoveStoredMetadataWhenMetadataRemovedByAssetId()
	{
		amwayDamAssetMetadataService.removeMetadataByAssetId(ANY_ASSET_ID);

		verify(modelService).removeAll(anyList());
	}

	@Test
	public void shouldConvertRowMetadataFromAssetIntoModel()
	{
		amwayDamAssetMetadataService.createMetadataForAsset(validAssetData);

		verify(amwayDamAssetMetadataReverseConverter).convert(eq(validAssetData), anyList());
	}

	@Test
	public void shouldSaveMetadataFromAsset()
	{
		amwayDamAssetMetadataService.createMetadataForAsset(validAssetData);

		verify(modelService).saveAll(anyList());
	}
}