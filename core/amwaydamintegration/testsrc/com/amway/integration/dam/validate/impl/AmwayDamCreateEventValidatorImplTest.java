package com.amway.integration.dam.validate.impl;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Before;
import org.junit.Test;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.exception.AmwayDamException;
import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * Unit Test for {@link AmwayDamCreateEventValidatorImpl}
 */
@UnitTest
public class AmwayDamCreateEventValidatorImplTest
{
	private final AmwayDamCreateEventValidatorImpl amwayDamCreateEventValidator = new AmwayDamCreateEventValidatorImpl();

	private final AmwayDamProcessingData processingData = new AmwayDamProcessingData();
	private final AmwayDamAssetModel storedAsset = mock(AmwayDamAssetModel.class);

	@Before
	public void setUp()
	{
		processingData.setExistedAssets(singletonList(storedAsset));
		processingData.setAssetData(new AmwayDamAssetData());
	}

	@Test(expected = AmwayDamException.class)
	public void shouldThrowExceptionWhenExistRemovedAssets()
	{
		when(storedAsset.getRemoved()).thenReturn(Boolean.TRUE);

		amwayDamCreateEventValidator.validate(processingData);
	}

	@Test(expected = AmwayDamException.class)
	public void shouldThrowExceptionWhenExistStoredAssets()
	{
		processingData.setExistedAssets(singletonList(storedAsset));

		amwayDamCreateEventValidator.validate(processingData);
	}

	@Test
	public void shouldNotThrowExceptionWhenStoredAssetsIsEmpty()
	{
		processingData.setExistedAssets(emptyList());

		amwayDamCreateEventValidator.validate(processingData);
	}
}