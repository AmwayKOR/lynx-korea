/**
 *
 */
package com.amway.service.search;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import com.amway.facade.search.facetdata.ResourceSearchPageData;


/**
 * @author Lenovo
 */
public interface ResourceSearchService<STATE, ITEM, RESULT extends ResourceSearchPageData<STATE, ITEM>>
{
	/**
	 * Initiate a new search using simple free text query.
	 *
	 * @param text         the search text
	 * @param pageableData the page to return, can be null to use defaults
	 * @return the search results
	 */
	RESULT textSearch(String text, PageableData pageableData);

	/**
	 * Initiate a new search in category.
	 *
	 * @param categoryCode the code for category to search in
	 * @param pageableData the page to return, can be null to use defaults
	 * @return the search results
	 */
	RESULT categorySearch(String categoryCode, PageableData pageableData);

	/**
	 * Refine an exiting search. The query object allows more complex queries using facet selection. The SearchQueryData
	 * must have been obtained from the results of a call to either {@link #textSearch(String, PageableData)} or
	 * {@link #categorySearch(String, PageableData)}.
	 *
	 * @param searchQueryData the search query object
	 * @param pageableData    the page to return
	 * @return the search results
	 */
	RESULT searchAgain(STATE searchQueryData, PageableData pageableData);
}
