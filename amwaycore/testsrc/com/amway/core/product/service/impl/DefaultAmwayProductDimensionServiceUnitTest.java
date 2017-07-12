/**
 *
 */
package com.amway.core.product.service.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayDimensionDescriptorModel;
import com.amway.core.model.AmwayDimensionModel;


@UnitTest
public class DefaultAmwayProductDimensionServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayProductDimensionService dimensionService = new DefaultAmwayProductDimensionService();
	private ProductModel product;
	private AmwayDimensionModel dimensionModel;
	private AmwayDimensionDescriptorModel lenght;
	private AmwayDimensionDescriptorModel width;
	private AmwayDimensionDescriptorModel height;
	private AmwayDimensionDescriptorModel weight;
	private AmwayDimensionDescriptorModel volume;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		lenght = Mockito.mock(AmwayDimensionDescriptorModel.class);
		width = Mockito.mock(AmwayDimensionDescriptorModel.class);
		height = Mockito.mock(AmwayDimensionDescriptorModel.class);
		weight = Mockito.mock(AmwayDimensionDescriptorModel.class);
		volume = Mockito.mock(AmwayDimensionDescriptorModel.class);
		dimensionModel = Mockito.mock(AmwayDimensionModel.class);
		when(dimensionModel.getLength()).thenReturn(lenght);
		when(dimensionModel.getWidth()).thenReturn(width);
		when(dimensionModel.getWeight()).thenReturn(weight);
		when(dimensionModel.getVolume()).thenReturn(volume);
		when(dimensionModel.getHeight()).thenReturn(height);
		product = Mockito.mock(ProductModel.class);
		when(product.getDimensions()).thenReturn(dimensionModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.product.service.impl.DefaultAmwayProductDimensionService#getDimensions(de.hybris.platform.core.model.product.ProductModel)}
	 * .
	 */
	@Test
	public void testGetDimensions()
	{
		final AmwayDimensionDescriptorModel dimention = dimensionService.getDimension(product, AmwayDimensionModel.LENGTH);
		Assert.assertNotNull(dimention);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.product.service.impl.DefaultAmwayProductDimensionService#getDimension(de.hybris.platform.core.model.product.ProductModel, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetDimension()
	{
		final Map<String, AmwayDimensionDescriptorModel> dimensionDescriptorsMap = dimensionService.getDimensions(product);
		Assert.assertNotNull(dimensionDescriptorsMap);
		Assert.assertEquals(5, CollectionUtils.size(dimensionDescriptorsMap));
	}

}
