package com.amway.apac.core.product.services;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.product.CommerceProductReferenceService;
import de.hybris.platform.commerceservices.product.data.ReferenceData;

import java.util.List;


/**
 * @param <TYPE>
 *           Enum type defining the types of references
 * @param <TARGET>
 *           The type which is given as the reference
 *
 * @author Rajesh Dhiman
 */
public interface AmwayApacProductReferenceService<TYPE, TARGET> extends CommerceProductReferenceService<TYPE, TARGET>
{

	/**
	 * Retrieves category product references for a given category and product reference type.
	 *
	 * @param category
	 *           the category whose references are to be returned
	 * @param referenceTypes
	 *           the product reference types to return
	 * @param limit
	 *           maximum number of references to retrieve. If null, all available references will be retrieved.
	 * @return a collection product references.
	 */
	List<ReferenceData<TYPE, TARGET>> getProductReferencesForCategory(final CategoryModel category,
			final List<TYPE> referenceTypes, final Integer limit);
}
