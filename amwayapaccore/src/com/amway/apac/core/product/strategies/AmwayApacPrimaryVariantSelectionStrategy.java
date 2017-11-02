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

	AmwayApacVariantProductModel getPrimaryVariant(final ProductModel baseProduct);
}
