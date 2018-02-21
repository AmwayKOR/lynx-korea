package com.amway.integration.dam.strategy.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.amway.integration.dam.model.AmwayDamAssetItemModel;


/**
 * Unit Test for {@link AmwayDamAssetItemFileNameStrategyImpl}
 */
@UnitTest
public class AmwayDamAssetItemFileNameStrategyImplTest
{
	private final AmwayDamAssetItemFileNameStrategyImpl amwayDamAssetItemFileNameStrategy = new AmwayDamAssetItemFileNameStrategyImpl();

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRenditionIsNull()
	{
		amwayDamAssetItemFileNameStrategy.getFileName(null);
	}

	@Test
	public void shouldReturnFileNameAsRealFileNameFromAssetItem()
	{
		String fileName = "any.file";
		AmwayDamAssetItemModel assetItem = mock(AmwayDamAssetItemModel.class);
		when(assetItem.getRealFileName()).thenReturn(fileName);

		String result = amwayDamAssetItemFileNameStrategy.getFileName(assetItem);

		assertEquals(fileName, result);
	}
}