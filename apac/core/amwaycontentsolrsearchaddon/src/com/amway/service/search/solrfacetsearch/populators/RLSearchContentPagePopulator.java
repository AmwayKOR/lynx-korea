/**
 *
 */
package com.amway.service.search.solrfacetsearch.populators;

import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;


public class RLSearchContentPagePopulator<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>
		implements
		Populator<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE>>
{
	private int maximumPageSize = 100;

	protected int getMaximumPageSize()
	{
		return this.maximumPageSize;
	}

	public void setMaximumPageSize(final int maximumPageSize)
	{
		this.maximumPageSize = maximumPageSize;
	}

	@Override
	public void populate(final SearchQueryPageableData<SolrSearchQueryData> source,
			final SolrSearchRequest<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE> target)
	{
		if (target.getPageableData() != null)
		{
			final int pageSize = Math.min(getMaximumPageSize(), target.getPageableData().getPageSize());
			if (pageSize > 0)
			{
				target.getSearchQuery().setPageSize(pageSize);
			}

			final int currentPage = target.getPageableData().getCurrentPage();
			if (currentPage < 0)
			{
				return;
			}
			target.getSearchQuery().setOffset(currentPage);
		}
		else
		{
			target.getSearchQuery().setOffset(0);
		}
	}
}
