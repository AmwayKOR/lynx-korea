package com.amway.apac.facades.product;

import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;


/**
 * The Interface AmwayApacProductFacade.
 *
 * @author Ashish Sabal
 */
public interface AmwayApacProductFacade extends ProductFacade
{

	/**
	 * Gets the recently viewed product data.
	 *
	 * @param list
	 *           the list
	 * @return the recently viewed product data
	 */
	List<ProductData> getRecentlyViewedProductData(final List<String> list);

	/**
	 * Retrieves product references of a given category
	 *
	 * @param category
	 *           the category
	 * @param referenceTypes
	 *           the product reference types to return.
	 * @param limit
	 *           maximum number of references to retrieve. If null, all available references will be retrieved.
	 *
	 * @throws IllegalArgumentException
	 *            if parameter category is null.
	 * @return the product references
	 */
	List<ProductData> getCategoryProductReferencesForCode(final CategoryModel category,
														  final List<ProductReferenceTypeEnum> referenceTypes,
														  final Integer limit);

    /**
     * Retrieves product references of a product given its code.
     *
     * @param code
     *           the product code
     * @param referenceTypes
     *           the product reference types to return.
     * @param limit
     *           maximum number of references to retrieve. If null, all available references will be retrieved.
     * @throws IllegalArgumentException
     *            if the product code is null or empty
     * @return the product references
     */
    List<ProductData> getProductReferencesProductDataForCode(final String code,
                                                             final List<ProductReferenceTypeEnum> referenceTypes, final Integer limit);
}
