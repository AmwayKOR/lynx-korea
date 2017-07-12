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


import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.converter.Populator;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.impl.FacetSearchConfigDao;

import java.util.Collections;
import java.util.List;

import com.amway.facade.search.facetdata.ContentSearchPageData;


/**
 */
public class RLSearchContentResponseFreeTextSearchPopulator<STATE, ITEM>
		implements Populator<SolrSearchResponse, ContentSearchPageData<STATE, ITEM>>
{
	private FacetSearchConfigDao facetSearchConfigDao;
	private CMSSiteService cmsSiteService;

	@Override
	public void populate(final SolrSearchResponse source, final ContentSearchPageData<STATE, ITEM> target)
			throws ConversionException
	{
		target.setFreeTextSearch(source.getRequest().getSearchText());

	}

	@SuppressWarnings("unused")
	private List<String> getRelatedSearchTerms(final String term, final String configName)
	{
		return Collections.EMPTY_LIST;
	}

	public FacetSearchConfigDao getFacetSearchConfigDao()
	{
		return facetSearchConfigDao;
	}

	public void setFacetSearchConfigDao(final FacetSearchConfigDao facetSearchConfigDao)
	{
		this.facetSearchConfigDao = facetSearchConfigDao;
	}

	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}
}
