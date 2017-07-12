package com.amway.core.varaintproduct.service.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.varaintproduct.service.AmwayVariantProductService;


/**
 * Service implementation for to get variant product.
 */
public class AmwayVariantProductServiceImpl implements AmwayVariantProductService
{

	/**
	 * Service to get variant products.
	 *
	 * @param productModel
	 * @return variantProduct
	 */
	@Override
	public VariantProductModel getDefaultVariantProduct(final ProductModel productModel)
	{
		final VariantProductModel variantProduct = null;
		final Collection<VariantProductModel> variants = productModel.getVariants();
		if (CollectionUtils.isNotEmpty(variants))
		{
			for (final VariantProductModel variant : variants)
			{
				if (variant.getSequenceId() != null && 0 == variant.getSequenceId())
				{
					return variant;
				}
			}
			//if sequence id is not set get the first variant
			return variants.iterator().next();
		}
		return variantProduct;
	}

	/**
	 * @param product
	 */
	@Override
	public boolean hasSingleVariant(final ProductModel product)
	{
		if (product.getVariants().size() == 1)
		{
			return true;
		}
		return false;
	}

}
