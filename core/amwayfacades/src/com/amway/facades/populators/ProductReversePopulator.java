package com.amway.facades.populators;

import com.amway.core.model.ProductAttributeModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import com.amway.core.enums.ProductAttributesTypeEnum;


/**
 * @author Aliaksei Sery
 */
public class ProductReversePopulator implements Populator<ProductData, ProductModel>
{

	@Resource(name = "modelService")
	ModelService modelService;

	/**
	 * Populate product model with product data values. Only items that can be modified will be updated:
	 * <ul>
	 * <li>name
	 * <li>description
	 * <li>code
	 * <li>summary
	 * <li>map of productAttributes
	 * </ul>
	 *
	 * @param productData  product DTO
	 * @param productModel product model
	 * @throws ConversionException
	 */
	@Override
	public void populate(final ProductData productData, final ProductModel productModel) throws ConversionException
	{
		productModel.setName(productData.getName());
		productModel.setCode(productData.getCode());
		productModel.setDescription(productData.getDescription());
		productModel.setSummary(productData.getSummary());

		populateProductAttributes(productData, productModel);
	}

	/**
	 * Populates map of product attributes
	 *
	 * @param productData  product DTO
	 * @param productModel product model
	 */
	private void populateProductAttributes(final ProductData productData, final ProductModel productModel)
	{
		final List<ProductAttributeModel> targetProductAttributes = new <ProductAttributeModel>ArrayList();
		final Map<String, String> sourceProductAttributes = productData.getProductAttributes();
		if (sourceProductAttributes != null && !sourceProductAttributes.isEmpty())
		{
			final Set<String> keys = sourceProductAttributes.keySet();
			for (final Iterator<String> it = keys.iterator(); it.hasNext(); )
			{
				final String key = it.next();
				final String attributeValue = sourceProductAttributes.get(key);
				final ProductAttributesTypeEnum enumKey = ProductAttributesTypeEnum.valueOf(key);
				ProductAttributeModel attributeModel = new ProductAttributeModel();
				attributeModel.setAttributeType(enumKey);
				attributeModel.setAttributeValue(attributeValue);
				targetProductAttributes.add(attributeModel);
			}
		}

		productModel.setProductAttributes(targetProductAttributes);
	}
}
