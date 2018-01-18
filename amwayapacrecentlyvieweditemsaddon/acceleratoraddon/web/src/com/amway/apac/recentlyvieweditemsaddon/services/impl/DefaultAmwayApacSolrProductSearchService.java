package com.amway.apac.recentlyvieweditemsaddon.services.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryTermData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.impl.DefaultSolrProductSearchService;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.recentlyvieweditemsaddon.services.AmwayApacProductSearchService;

/**
 * Created by Govind on 12/13/2017.
 */
public class DefaultAmwayApacSolrProductSearchService<ITEM extends ProductData> extends DefaultSolrProductSearchService
		implements AmwayApacProductSearchService {

	private Converter<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData>> productCategorySearchPageConverter;
	private ThreadContextService threadContextService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductSearchPageData<SearchStateData, ITEM> productCodesSearch(final String productCodes,
			final PageableData pageableData) {
		final SolrSearchQueryData searchQueryData = createSearchQueryData();

		searchQueryData.setFilterTerms(Collections.<SolrSearchQueryTermData> emptyList());
		searchQueryData.setRawQuery(productCodes);

		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<ProductSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>() {
					@Override
					public ProductSearchPageData<SearchStateData, ITEM> execute() {
						return getProductCategorySearchPageConverter().convert(doSearch(searchQueryData, pageableData));
					}
				});
	}

	/**
	 * @return the threadContextService
	 */
	public ThreadContextService getThreadContextService() {
		return threadContextService;
	}

	/**
	 * @param threadContextService
	 *            the threadContextService to set
	 */
	@Required
	public void setThreadContextService(final ThreadContextService threadContextService) {
		this.threadContextService = threadContextService;
	}

	/**
	 * @return the productCategorySearchPageConverter
	 */
	public Converter<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData>> getProductCategorySearchPageConverter() {
		return productCategorySearchPageConverter;
	}

	/**
	 * @param productCategorySearchPageConverter
	 *            the productCategorySearchPageConverter to set
	 */
	@Required
	public void setProductCategorySearchPageConverter(
			final Converter<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData>> productCategorySearchPageConverter) {
		this.productCategorySearchPageConverter = productCategorySearchPageConverter;
	}
}
