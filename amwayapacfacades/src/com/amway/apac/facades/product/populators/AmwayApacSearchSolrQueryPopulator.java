package com.amway.apac.facades.product.populators;

import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchSolrQueryPopulator;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.commons.lang.StringUtils;

/**
 * Created by MY020221 on 12/13/2017.
 */
public class AmwayApacSearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE> extends SearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>{
    /**
     * Testing
     * @param source
     * @param target
     */
    @Override
    public void populate(final SearchQueryPageableData<SolrSearchQueryData> source,
                         final SolrSearchRequest<FacetSearchConfig, IndexedType, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE> target)
    {
        super.populate(source, target);
        if(StringUtils.isNotEmpty(source.getSearchQueryData().getRawQuery())){
            target.getSearchQuery().addRawQuery(source.getSearchQueryData().getRawQuery());
        }
    }
}