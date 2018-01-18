package com.amway.apac.recentlyvieweditemsaddon.services;

import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

/**
 * Created by MY020221 on 12/13/2017.
 */
public interface AmwayApacProductSearchService<STATE, ITEM, RESULT extends ProductSearchPageData<STATE, ITEM>> {
	/**
	 * Returns the products.
	 * 
	 * @param productCodes
	 * @param pageableData
	 * @return Searches Products
	 */
	RESULT productCodesSearch(final String productCodes, final PageableData pageableData);
}