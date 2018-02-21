package com.amway.apac.core.product.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.product.data.ReferenceData;
import de.hybris.platform.commerceservices.product.impl.DefaultCommerceProductReferenceService;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.amway.apac.core.model.AmwayCategoryProductReferenceModel;
import com.amway.apac.core.product.services.AmwayApacProductReferenceService;


/**
 * Product References Service implementation.
 *
 * @author Rajesh Dhiman
 */
public class DefaultAmwayApacProductReferenceService extends DefaultCommerceProductReferenceService
		implements AmwayApacProductReferenceService<ProductReferenceTypeEnum, ProductModel>
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReferenceData<ProductReferenceTypeEnum, ProductModel>> getProductReferencesForCategory(
			final CategoryModel category, final List<ProductReferenceTypeEnum> referenceTypes, final Integer limit)
	{
		validateParameterNotNull(category, "Category must not be null");
		validateParameterNotNull(referenceTypes, "Parameter referenceType must not be null");

		final List<ReferenceData<ProductReferenceTypeEnum, ProductModel>> result = new ArrayList<>();

		final List<AmwayCategoryProductReferenceModel> references = getAllActiveCategoryProductReferencesFromSourceOfType(category,
				referenceTypes);
		if (references != null && !references.isEmpty())
		{
			for (final AmwayCategoryProductReferenceModel reference : references)
			{
				final ProductModel targetProduct = reference.getTarget();

				final ReferenceData<ProductReferenceTypeEnum, ProductModel> referenceData = createReferenceData();
				referenceData.setTarget(targetProduct);
				referenceData.setDescription(reference.getDescription());
				referenceData.setQuantity(reference.getQuantity());
				referenceData.setReferenceType(reference.getReferenceType());
				result.add(referenceData);

				// Check the limit
				if (limit != null && result.size() >= limit.intValue())
				{
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Get all the active product references for category with specific Type (i.e. SIMILAR)
	 *
	 * @param category
	 * @param referenceTypes
	 * @return
	 */
	protected List<AmwayCategoryProductReferenceModel> getAllActiveCategoryProductReferencesFromSourceOfType(
			final CategoryModel category, final List<ProductReferenceTypeEnum> referenceTypes)
	{
		final Collection<AmwayCategoryProductReferenceModel> allReferences = getProductReferencesForCategory(category);
		if (allReferences != null && !allReferences.isEmpty())
		{
			final List<AmwayCategoryProductReferenceModel> matchingReferences = new ArrayList<>();

			for (final AmwayCategoryProductReferenceModel reference : allReferences)
			{
				if (reference != null && Boolean.TRUE.equals(reference.getActive())
						&& referenceTypes.contains(reference.getReferenceType())
						&& ArticleApprovalStatus.APPROVED.equals(reference.getTarget().getApprovalStatus()))
				{
					matchingReferences.add(reference);
				}
			}

			return matchingReferences;
		}
		return Collections.emptyList();

	}

	/**
	 * Get category references
	 *
	 * @param category
	 * @return
	 */
	protected Collection<AmwayCategoryProductReferenceModel> getProductReferencesForCategory(final CategoryModel category)
	{
		return (Collection<AmwayCategoryProductReferenceModel>) getCategoryAttribute(category, CategoryModel.PRODUCTREFERENCES);
	}

	/**
	 * Get an attribute value from a category. If the attribute value is null and the category has a super category then
	 * the same attribute will be requested from the super category.
	 *
	 * @param category
	 *           the category
	 * @param attribute
	 *           the name of the attribute to lookup
	 * @return the value of the attribute
	 */
	protected Object getCategoryAttribute(final CategoryModel category, final String attribute)
	{
		Object value = getModelService().getAttributeValue(category, attribute);

		if ((CollectionUtils.isNotEmpty(category.getSupercategories()))
				&& (value == null || (value instanceof Collection && ((Collection) value).isEmpty())))
		{
			for (final CategoryModel superCategory : category.getSupercategories())
			{
				value = getCategoryAttribute(superCategory, attribute);

				if (value == null || (value instanceof Collection && ((Collection) value).isEmpty()))
				{
					break;
				}
			}
		}
		return value;
	}
}
