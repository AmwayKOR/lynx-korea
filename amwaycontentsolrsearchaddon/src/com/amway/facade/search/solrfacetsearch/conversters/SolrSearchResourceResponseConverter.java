/**
 *
 */
package com.amway.facade.search.solrfacetsearch.conversters;

import de.hybris.platform.commerceservices.converter.impl.AbstractPopulatingConverter;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.core.model.media.MediaModel;

import com.amway.facade.search.facetdata.ResourseCategorySearchPageData;


public class SolrSearchResourceResponseConverter<ITEM> extends
		AbstractPopulatingConverter<SolrSearchResponse, ResourseCategorySearchPageData<SolrSearchQueryData, ITEM, MediaModel>>
{
	// AbstractPopulatingConverter

	@Override
	protected ResourseCategorySearchPageData<SolrSearchQueryData, ITEM, MediaModel> createTarget()
	{
		return new ResourseCategorySearchPageData<SolrSearchQueryData, ITEM, MediaModel>();
	}

	@Override
	public void populate(final SolrSearchResponse source,
			final ResourseCategorySearchPageData<SolrSearchQueryData, ITEM, MediaModel> target)
	{
		super.populate(source, target);
	}
}
