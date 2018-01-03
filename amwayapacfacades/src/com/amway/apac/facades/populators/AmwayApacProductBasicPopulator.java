package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductBasicPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;


/**
 * Overriding OOTB {@link ProductBasicPopulator} to populate as per APAC requirements.
 *
 * @author Shubham Goyal
 */
public class AmwayApacProductBasicPopulator extends ProductBasicPopulator<ProductModel, ProductData>
{

	/**
	 * Overriding OOTB {@link ProductBasicPopulator} to populate as per APAC requirements.
	 */
	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		super.populate(productModel, productData);
		productData.setMultidimensional(Boolean.valueOf(CollectionUtils.isNotEmpty(productModel.getVariants())));
		productData.setIsSellable(Boolean.valueOf(BooleanUtils.isNotFalse(productModel.getIsSellable())));
	}
}
