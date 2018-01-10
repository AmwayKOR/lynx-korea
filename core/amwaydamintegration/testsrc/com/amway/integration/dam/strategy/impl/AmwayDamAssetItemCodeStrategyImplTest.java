package com.amway.integration.dam.strategy.impl;

import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Before;
import org.junit.Test;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamAssetTypeEnum;
import com.amway.integration.dam.data.AmwayDamRenditionData;


/**
 * Unit Test for {@link AmwayDamAssetItemCodeStrategyImpl}
 */
@UnitTest
public class AmwayDamAssetItemCodeStrategyImplTest
{
	private final AmwayDamAssetItemCodeStrategyImpl amwayDamAssetItemCodeStrategy = new AmwayDamAssetItemCodeStrategyImpl();

	private final AmwayDamRenditionData renditionData = new AmwayDamRenditionData();
	private final AmwayDamAssetData assetData = new AmwayDamAssetData();

	@Before
	public void setUp()
	{
		assetData.setPath("/any/path/to/asset");
		renditionData.setAsset(assetData);
		renditionData.setWidth(100);
		renditionData.setHeight(100);
		renditionData.setType(AmwayDamAssetTypeEnum.DOCUMENT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRenditionDataIsNull()
	{
		amwayDamAssetItemCodeStrategy.generateCode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRenditionTypeIsNull()
	{
		renditionData.setType(null);

		amwayDamAssetItemCodeStrategy.generateCode(renditionData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenAssetDataIsNull()
	{
		renditionData.setAsset(null);

		amwayDamAssetItemCodeStrategy.generateCode(renditionData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenPathToAssetIsNull()
	{
		assetData.setPath(null);

		amwayDamAssetItemCodeStrategy.generateCode(renditionData);
	}

	@Test
	public void shouldGenerateCodeWithoutResolutionWhenWidthIsNull()
	{
		renditionData.setWidth(null);

		String result = amwayDamAssetItemCodeStrategy.generateCode(renditionData);

		assertTrue(result.startsWith("DOCUMENT_asset"));
	}

	@Test
	public void shouldGenerateCodeWithoutResolutionWhenHeightIsNull()
	{
		renditionData.setHeight(null);

		String result = amwayDamAssetItemCodeStrategy.generateCode(renditionData);

		assertTrue(result.startsWith("DOCUMENT_asset"));
	}

	@Test
	public void shouldGenerateCodeWithResolutionWhenWidthAndHeightNotNull()
	{
		String result = amwayDamAssetItemCodeStrategy.generateCode(renditionData);

		assertTrue(result.startsWith("DOCUMENT_100_100_asset"));
	}

	@Test
	public void shouldGenerateCodeWithLastPartOfPathWhenPathIsNotNull()
	{
		assetData.setPath("any/other/path");

		String result = amwayDamAssetItemCodeStrategy.generateCode(renditionData);

		assertTrue(result.startsWith("DOCUMENT_100_100_path"));
	}
}