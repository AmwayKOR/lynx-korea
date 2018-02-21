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
package com.amway.facade.search.solrfacetsearch.conversters;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.converter.impl.AbstractPopulatingConverter;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;

import com.amway.facade.search.facetdata.ContentCategorySearchPageData;


/**
 */
public class RLSolrSearchContentResponseConverter<ITEM> extends
		AbstractPopulatingConverter<SolrSearchResponse, ContentCategorySearchPageData<SolrSearchQueryData, ITEM, CategoryModel>>
{
	// AbstractPopulatingConverter

	@Override
	protected ContentCategorySearchPageData<SolrSearchQueryData, ITEM, CategoryModel> createTarget()
	{
		return new ContentCategorySearchPageData<SolrSearchQueryData, ITEM, CategoryModel>();
	}

}
