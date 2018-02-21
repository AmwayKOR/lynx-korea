/**
 *
 */
package com.amway.service.search.solrfacetsearch.populators;

import de.hybris.platform.commerceservices.converter.Populator;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.facade.search.facetdata.ResourceSearchPageData;


public class DefaultSearchResourceResponseFreeTextSearchPopulator<STATE, ITEM>
		implements Populator<SolrSearchResponse, ResourceSearchPageData<STATE, ITEM>>
{

	@Override
	public void populate(final SolrSearchResponse source, final ResourceSearchPageData<STATE, ITEM> target)
			throws ConversionException
	{

		target.setFreeTextSearch(source.getRequest().getSearchText());
	}

}
