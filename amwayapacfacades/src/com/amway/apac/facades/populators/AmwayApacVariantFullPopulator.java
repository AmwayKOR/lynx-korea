
package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.VariantFullPopulator;
import de.hybris.platform.commercefacades.product.data.BaseOptionData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


/**
 * Overriding {@link VariantFullPopulator} to update base options population.
 *
 * @author Shubham Goyal
 *
 */
public class AmwayApacVariantFullPopulator extends VariantFullPopulator<ProductModel, ProductData>
{

	/**
	 * Overriding OOTB implementation to update base options population.
	 */
	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		if (productModel instanceof VariantProductModel)
		{
			final ProductModel baseProduct = ((VariantProductModel) productModel).getBaseProduct();

			// Populate the list of base options
			final List<BaseOptionData> baseOptions = new ArrayList<>();
			if (productModel instanceof VariantProductModel)
			{
				populateBaseOptionDataForAttribute1(baseProduct, baseOptions, productModel);
				populateBaseOptionDataForAttribute2(baseProduct, baseOptions, productModel);
			}
			productData.setBaseOptions(baseOptions);
		}
	}


	/**
	 * Method populates Variant Attribute 1 values.
	 *
	 * @param baseProduct
	 *           Base Product
	 * @param baseOptions
	 *           Base Options
	 * @param currentProduct
	 *           Product for which base options to be populated
	 */
	protected void populateBaseOptionDataForAttribute2(final ProductModel baseProduct, final List<BaseOptionData> baseOptions,
			final ProductModel currentProduct)
	{
		final BaseOptionData baseOptionDataForAttribute2 = getBaseOptionDataConverter()
				.convert((VariantProductModel) currentProduct);
		final List<VariantOptionData> varintOptios2 = new ArrayList<>();
		if (StringUtils.isNotBlank(currentProduct.getVariantAttributeName2()))
		{
			for (final VariantProductModel var : baseProduct.getVariants())
			{
				if (currentProduct.getVariantAttribute1().equals(var.getVariantAttribute1()))
				{
					varintOptios2.add(getVariantOptionDataConverter().convert(var));
				}

			}
		}
		baseOptionDataForAttribute2.setOptions(varintOptios2);
		baseOptions.add(baseOptionDataForAttribute2);
	}

	/**
	 * Method populates Variant Attribute 2 values.
	 *
	 * @param baseProduct
	 *           Base Product
	 * @param baseOptions
	 *           Base Options
	 * @param currentProduct
	 *           Product for which base options to be populated
	 */
	protected void populateBaseOptionDataForAttribute1(final ProductModel baseProduct, final List<BaseOptionData> baseOptions,
			final ProductModel currentProduct)
	{
		final BaseOptionData baseOptionDataForAttribute1 = getBaseOptionDataConverter()
				.convert((VariantProductModel) currentProduct);
		final List<VariantOptionData> varintOptios1 = new ArrayList<>();

		if (StringUtils.isNotBlank(currentProduct.getVariantAttributeName1()))
		{
			final Set<String> valuesUsed = new HashSet<>();
			for (final VariantProductModel var : baseProduct.getVariants())
			{
				if (!valuesUsed.contains(var.getVariantAttribute1()))
				{
					varintOptios1.add(getVariantOptionDataConverter().convert(var));
					valuesUsed.add(var.getVariantAttribute1());
				}
			}
		}
		baseOptionDataForAttribute1.setOptions(varintOptios1);
		baseOptions.add(baseOptionDataForAttribute1);
	}


}