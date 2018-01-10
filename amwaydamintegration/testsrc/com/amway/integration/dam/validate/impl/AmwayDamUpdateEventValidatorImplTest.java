package com.amway.integration.dam.validate.impl;

import static java.util.Collections.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.exception.AmwayDamException;
import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * Unit Test for {@link AmwayDamUpdateEventValidatorImpl}
 */
@UnitTest
public class AmwayDamUpdateEventValidatorImplTest
{
	private final AmwayDamUpdateEventValidatorImpl amwayDamUpdateEventValidator = new AmwayDamUpdateEventValidatorImpl();

	private final AmwayDamProcessingData processingData = new AmwayDamProcessingData();
	private final CatalogVersionModel catalogVersion = mock(CatalogVersionModel.class);
	private final AmwayDamAssetModel asset = mock(AmwayDamAssetModel.class);

	@Before
	public void setUp()
	{
		AmwayDamAssetData assetData = new AmwayDamAssetData();
		processingData.setAssetData(assetData);
	}

	@Test
	public void shouldNotThrowExceptionWhenUpdateMapNotContainsRemovedAssets()
	{
		when(asset.getRemoved()).thenReturn(Boolean.FALSE);
		processingData.setCatalogToAssetMapForUpdate(singletonMap(catalogVersion, asset));

		amwayDamUpdateEventValidator.validate(processingData);
	}

	@Test
	public void shouldNotThrowExceptionWhenNothingToUpdateButExistAssetsForCreation()
	{
		processingData.setCatalogToAssetMapForUpdate(emptyMap());
		processingData.setCatalogsForCreateAssets(singletonList(catalogVersion));

		amwayDamUpdateEventValidator.validate(processingData);
	}
}