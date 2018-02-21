/**
 *
 */
package com.amway.core.product.service.impl;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.amway.core.model.AmwayDimensionDescriptorModel;
import com.amway.core.model.AmwayDimensionModel;
import com.amway.core.product.service.AmwayProductDimensionService;


/**
 * Service to retrieve the details of Dimensions of a product
 */
public class DefaultAmwayProductDimensionService implements AmwayProductDimensionService
{

	/**
	 * Retrieve all dimensions associated to product
	 *
	 * @param productModel
	 * @return dimensionDescriptorsMap
	 */
	@Override
	public Map<String, AmwayDimensionDescriptorModel> getDimensions(final ProductModel productModel)
	{
		Assert.notNull(productModel, "Product Model cannot be null");
		final Map<String, AmwayDimensionDescriptorModel> dimensionDescriptorsMap = new HashMap();
		final AmwayDimensionModel dimensionModel = productModel.getDimensions();
		if (dimensionModel != null)
		{
			dimensionDescriptorsMap.put(AmwayDimensionModel.LENGTH, dimensionModel.getLength());
			dimensionDescriptorsMap.put(AmwayDimensionModel.WIDTH, dimensionModel.getWidth());
			dimensionDescriptorsMap.put(AmwayDimensionModel.HEIGHT, dimensionModel.getHeight());
			dimensionDescriptorsMap.put(AmwayDimensionModel.WEIGHT, dimensionModel.getWeight());
			dimensionDescriptorsMap.put(AmwayDimensionModel.VOLUME, dimensionModel.getVolume());
		}

		return dimensionDescriptorsMap;
	}

	/**
	 * Retrieve dimension descriptor for the product
	 *
	 * @param productModel
	 * @param dimensionType
	 * @return AmwayDimensionDescriptorModel
	 */
	@Override
	public AmwayDimensionDescriptorModel getDimension(final ProductModel productModel, final String dimensionType)
	{
		Assert.notNull(productModel, "Product Model cannot be null");
		Assert.notNull(dimensionType, "dimensionType cannot be null");
		return getDimensions(productModel).get(dimensionType);
	}
}
