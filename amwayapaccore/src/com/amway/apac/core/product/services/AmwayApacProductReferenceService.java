package com.amway.apac.core.product.services;

import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.product.CommerceProductReferenceService;
import de.hybris.platform.commerceservices.product.data.ReferenceData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

/**
 * @author Rajesh Dhiman
 * @param <TYPE>
 *           Enum type defining the types of references
 * @param <TARGET>
 *           The type which is given as the reference
 */
public interface AmwayApacProductReferenceService<TYPE, TARGET> extends CommerceProductReferenceService<TYPE, TARGET> {

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
    public List<ReferenceData<ProductReferenceTypeEnum, ProductModel>> getProductReferencesForCategory(
            final CategoryModel category, final List<TYPE> referenceTypes, final Integer limit);
}
