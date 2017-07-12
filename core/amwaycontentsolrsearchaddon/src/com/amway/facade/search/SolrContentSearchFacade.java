/**
 *
 */
package com.amway.facade.search;

import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import com.amway.facade.content.data.ContentData;
import com.amway.facade.search.facetdata.ContentSearchPageData;


public interface SolrContentSearchFacade<ITEM extends ContentData>
{
	ContentSearchPageData<SearchStateData, ITEM> textSearch(final SearchStateData searchState, final PageableData pageableData);
}
