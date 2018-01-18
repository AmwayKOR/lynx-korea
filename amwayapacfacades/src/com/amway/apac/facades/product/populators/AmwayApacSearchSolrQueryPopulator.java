package com.amway.apac.facades.product.populators;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.SOURCE_STRING;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.TARGET_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchSolrQueryPopulator;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.commons.lang.StringUtils;


/**
 * Populator to convert solr search raw query data to solr search request
 *
 * Created by MY020221 on 12/13/2017.
 *
 * @param <INDEXED_PROPERTY_TYPE>
 *           the generic type
 * @param <INDEXED_TYPE_SORT_TYPE>
 *           the generic type
 */
public class AmwayApacSearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>
		extends SearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>
{

	/**
	 * Populator default method
	 *
	 * @param source
	 *           the source
	 * @param target
	 *           the target
	 * 
	 * @throws IllegalArgumentException
	 */
	@Override
	public void populate(final SearchQueryPageableData<SolrSearchQueryData> source,
			final SolrSearchRequest<FacetSearchConfig, IndexedType, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE> target)
	{
		validateParameterNotNullStandardMessage(TARGET_STRING, source);
		validateParameterNotNullStandardMessage(SOURCE_STRING, target);

		super.populate(source, target);
		if (StringUtils.isNotEmpty(source.getSearchQueryData().getRawQuery()))
		{
			target.getSearchQuery().addRawQuery(source.getSearchQueryData().getRawQuery());
		}
	}
}