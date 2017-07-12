package com.amway.core.varaintproduct.service;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;


/**
 * service to get varaint details
 */
public interface AmwayVariantProductService
{
	/**
	 * To get variant product.
	 *
	 * @param product
	 * @return VariantProductModel
	 */
	VariantProductModel getDefaultVariantProduct(final ProductModel product);

	/**
	 * To checks varinats
	 *
	 * @param product
	 * @return boolean
	 */
	boolean hasSingleVariant(final ProductModel product);

}
