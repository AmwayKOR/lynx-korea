package com.amway.apac.facades.search.solrfacetsearch.impl;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commercefacades.search.solrfacetsearch.impl.DefaultSolrProductSearchFacade;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;

import org.apache.commons.lang.StringUtils;


/**
 * Default Solr Product search facade overridden to set custom filtered raw query.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayApacSolrProductSearchFacade extends DefaultSolrProductSearchFacade<ProductData>
{

	/**
	 * Overriding OOTB implementation to populate filter raw queries as will.
	 *
	 * @param searchState
	 *           the search state
	 * @param categoryCode
	 *           the category code
	 * @return the solr search query data
	 */
	@Override
	protected SolrSearchQueryData decodeState(final SearchStateData searchState, final String categoryCode)
	{
		final SolrSearchQueryData searchQueryData = super.decodeState(searchState, categoryCode);

		// populating filter raw queries in the solr search query data.
		if (StringUtils.isNotBlank(searchState.getFilterRawQueries()))
		{
			searchQueryData.setRawQuery(searchState.getFilterRawQueries());
		}

		return searchQueryData;
	}
}
