/**
 *
 */
package com.amway.apac.core.product.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import com.amway.apac.core.model.AmwayApacVariantProductModel;


/**
 *
 */
public interface AmwayApacPrimaryVariantSelectionStrategy
{

	AmwayApacVariantProductModel getPrimaryVariant(final ProductModel baseProduct);
}
