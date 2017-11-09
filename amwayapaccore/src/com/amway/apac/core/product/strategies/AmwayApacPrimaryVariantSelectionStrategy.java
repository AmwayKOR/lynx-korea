package com.amway.apac.core.product.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import com.amway.apac.core.model.AmwayApacVariantProductModel;


/**
 * Strategy interface for selection of primary variant of a variant base product
 *
 * @author Shubham Goyal
 */
public interface AmwayApacPrimaryVariantSelectionStrategy
{

	/**
	 * Resolves the primary variant for the base variant hierarchy.
	 *
	 * @param baseProduct
	 *           base product
	 * @return primary variant
	 *
	 * @throws IllegalArgumentException
	 *            if the baseProduct parameter is null
	 */
	AmwayApacVariantProductModel getPrimaryVariant(final ProductModel baseProduct);
}
