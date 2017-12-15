package com.amway.apac.recentlyvieweditemsaddon.services;

import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

/**
 * Created by MY020221 on 12/13/2017.
 */
public interface AmwayApacProductSearchService<STATE, ITEM, RESULT extends ProductSearchPageData<STATE, ITEM>>{
    /**
     *
     * @param productCodes
     * @param pageableData
     * @return
     */
    RESULT productCodesSearch(String productCodes, PageableData pageableData);
}