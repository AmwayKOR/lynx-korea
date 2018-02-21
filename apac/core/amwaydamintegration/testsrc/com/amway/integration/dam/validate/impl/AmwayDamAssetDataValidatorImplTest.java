package com.amway.integration.dam.validate.impl;

import static com.amway.integration.dam.enums.AmwayDamOperation.CREATE;
import static java.util.Collections.singletonList;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Before;
import org.junit.Test;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.exception.AmwayDamException;


/**
 * Unit Test for {@link AmwayDamAssetDataValidatorImpl}
 */
@UnitTest
public class AmwayDamAssetDataValidatorImplTest
{
	private final AmwayDamAssetDataValidatorImpl amwayDamAssetDataValidator = new AmwayDamAssetDataValidatorImpl();

	private final AmwayDamProcessingData processingData = new AmwayDamProcessingData();

	@Before
	public void setUp()
	{
		processingData.setOperationType(CREATE);
	}

	@Test(expected = AmwayDamException.class)
	public void shouldThrowExceptionWhenAssetDataIsNull()
	{
		processingData.setAssetData(null);

		amwayDamAssetDataValidator.validate(processingData);
	}

	@Test(expected = AmwayDamException.class)
	public void shouldThrowExceptionWhenCountriesAreNotSet()
	{
		AmwayDamAssetData assetData = new AmwayDamAssetData();
		processingData.setAssetData(assetData);

		amwayDamAssetDataValidator.validate(processingData);
	}

	@Test
	public void shouldNotThrowExceptionWhenAssetDataNotNullAndCountriesAreExist()
	{
		AmwayDamAssetData assetData = new AmwayDamAssetData();

		assetData.setCountries(singletonList("country"));
		processingData.setAssetData(assetData);

		amwayDamAssetDataValidator.validate(processingData);
	}

}