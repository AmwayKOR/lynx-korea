/**
 *
 */
package com.amway.facade.search;


import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import com.amway.facade.content.data.ResourceData;
import com.amway.facade.search.facetdata.ResourceSearchPageData;


public interface ResourceSearchFacade<ITEM extends ResourceData>
{
	ResourceSearchPageData<SearchStateData, ResourceData> textSearch(SearchStateData searchState, final PageableData pageableData);
}
