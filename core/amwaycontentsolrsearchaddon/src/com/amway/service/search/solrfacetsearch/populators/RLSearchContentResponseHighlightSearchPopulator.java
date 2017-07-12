/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2012 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.amway.service.search.solrfacetsearch.populators;


import de.hybris.platform.commerceservices.converter.Populator;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.search.impl.SolrSearchResult;

import com.amway.facade.search.facetdata.ContentSearchPageData;


/**
 */
public class RLSearchContentResponseHighlightSearchPopulator<STATE, ITEM>
		implements Populator<SolrSearchResponse, ContentSearchPageData<STATE, ITEM>>
{
	@Override
	public void populate(final SolrSearchResponse source, final ContentSearchPageData<STATE, ITEM> target)
			throws ConversionException
	{
		if (source.getSearchResult() != null)
		{
			target.setHighlighting(((SolrSearchResult) source.getSearchResult()).getSolrObject().getHighlighting());
		}
	}
}
