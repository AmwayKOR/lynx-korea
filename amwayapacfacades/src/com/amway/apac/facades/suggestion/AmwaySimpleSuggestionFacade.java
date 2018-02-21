package com.amway.apac.facades.suggestion;

import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;

import com.amway.facades.suggestion.SimpleSuggestionFacade;


/**
 * Exntending OOTB implementation to include new methods introduced in Hybris 6.4
 *
 * @author Parvesh Goyal
 */
public interface AmwaySimpleSuggestionFacade extends SimpleSuggestionFacade
{
	/**
	 * Adding the method created in Hybris 6.4. A better impl of the intention behind the above method.
	 *
	 * @param referenceTypes
	 * @param excludePurchased
	 * @param limit
	 * @return a list of suggestions
	 */
	List<ProductData> getSuggestionsForProductsInCart(List<ProductReferenceTypeEnum> referenceTypes, boolean excludePurchased,
			Integer limit);

}
