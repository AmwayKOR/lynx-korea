/**
 *
 */
package com.amway.core.product.service;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Map;

import com.amway.core.model.AmwayDimensionDescriptorModel;


/**
 * Service to retrieve the details of Dimensions of a product
 */
public interface AmwayProductDimensionService
{
	/**
	 * Retrieve all dimensions associated to product
	 *
	 * @param productModel
	 * @return Map<String, AmwayDimensionDescriptorModel>
	 */
	Map<String, AmwayDimensionDescriptorModel> getDimensions(ProductModel productModel);

	/**
	 * Retrieve dimension descriptor for the product
	 *
	 * @param productModel
	 * @param dimensionType
	 * @return AmwayDimensionDescriptorModel
	 */
	AmwayDimensionDescriptorModel getDimension(ProductModel productModel, String dimensionType);
}
