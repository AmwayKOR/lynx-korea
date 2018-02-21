package com.amway.integration.dam.validate.impl;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Test;

import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.exception.AmwayDamException;
import com.amway.integration.dam.model.AmwayDamAssetModel;


/**
 * Unit Test for {@link AmwayDamRemoveEventValidatorImpl}
 */
@UnitTest
public class AmwayDamRemoveEventValidatorImplTest
{
	private final AmwayDamRemoveEventValidatorImpl amwayDamRemoveEventValidator = new AmwayDamRemoveEventValidatorImpl();

	private final AmwayDamProcessingData processingData = new AmwayDamProcessingData();
	private final AmwayDamAssetModel asset = mock(AmwayDamAssetModel.class);

	@Test(expected = AmwayDamException.class)
	public void shouldThrowExceptionWhenAssetForRemoveIsNull()
	{
		processingData.setAssetsForRemove(null);

		amwayDamRemoveEventValidator.validate(processingData);
	}

	@Test(expected = AmwayDamException.class)
	public void shouldThrowExceptionWhenAssetForRemoveIsEmpty()
	{
		processingData.setAssetsForRemove(emptyList());

		amwayDamRemoveEventValidator.validate(processingData);
	}

	@Test(expected = AmwayDamException.class)
	public void shouldThrowExceptionWhenAllAssetsAlreadyRemoved()
	{
		when(asset.getRemoved()).thenReturn(Boolean.TRUE);
		processingData.setAssetsForRemove(singletonList(asset));

		amwayDamRemoveEventValidator.validate(processingData);
	}

	@Test
	public void shouldNotThrowExceptionWhenExistAssetsForRemove()
	{
		processingData.setAssetsForRemove(singletonList(asset));

		amwayDamRemoveEventValidator.validate(processingData);
	}
}