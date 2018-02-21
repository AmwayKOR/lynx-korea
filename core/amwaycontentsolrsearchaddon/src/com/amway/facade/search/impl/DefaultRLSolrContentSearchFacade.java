/**
 *
 */
package com.amway.facade.search.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.search.data.AutocompleteSuggestionData;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.ProductSearchAutocompleteService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.AutocompleteSuggestion;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.facade.content.data.ContentData;
import com.amway.facade.search.ContentSearchFacade;
import com.amway.facade.search.facetdata.ContentCategorySearchPageData;
import com.amway.facade.search.facetdata.ContentSearchPageData;
import com.amway.service.search.ContentSearchService;
import de.hybris.platform.solrfacetsearch.daos.SolrFacetSearchConfigDao;


public class DefaultRLSolrContentSearchFacade<ITEM extends ContentData> implements ContentSearchFacade<ITEM>
{
	private ContentSearchService<SolrSearchQueryData, SearchResultValueData, ContentCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>> contentSearchService;
	private Converter<ContentCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ContentCategorySearchPageData<SearchStateData, ITEM, CategoryData>> contentCategorySearchPageConverter;
	private Converter<SearchQueryData, SolrSearchQueryData> searchQueryDecoder;
	private Converter<AutocompleteSuggestion, AutocompleteSuggestionData> autocompleteSuggestionConverter;
	private ProductSearchAutocompleteService<AutocompleteSuggestion> autocompleteService;
	private ThreadContextService threadContextService;
	private SolrFacetSearchConfigDao facetSearchConfigDao;

	/**
	 * @return the facetSearchConfigDao
	 */
	public SolrFacetSearchConfigDao getFacetSearchConfigDao()
	{
		return facetSearchConfigDao;
	}

	/**
	 * @param facetSearchConfigDao the facetSearchConfigDao to set
	 */
	public void setFacetSearchConfigDao(final SolrFacetSearchConfigDao facetSearchConfigDao)
	{
		this.facetSearchConfigDao = facetSearchConfigDao;
	}

	/**
	 * @return the contentCategorySearchPageConverter
	 */
	public Converter<ContentCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ContentCategorySearchPageData<SearchStateData, ITEM, CategoryData>> getContentCategorySearchPageConverter()
	{
		return contentCategorySearchPageConverter;
	}

	/**
	 * @param contentCategorySearchPageConverter the contentCategorySearchPageConverter to set
	 */
	public void setContentCategorySearchPageConverter(
			final Converter<ContentCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ContentCategorySearchPageData<SearchStateData, ITEM, CategoryData>> contentCategorySearchPageConverter)
	{
		this.contentCategorySearchPageConverter = contentCategorySearchPageConverter;
	}

	/**
	 * @return the contentSearchService
	 */
	public ContentSearchService<SolrSearchQueryData, SearchResultValueData, ContentCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>> getContentSearchService()
	{
		return contentSearchService;
	}

	/**
	 * @param contentSearchService the contentSearchService to set
	 */
	public void setContentSearchService(
			final ContentSearchService<SolrSearchQueryData, SearchResultValueData, ContentCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>> contentSearchService)
	{
		this.contentSearchService = contentSearchService;
	}

	/**
	 * @return the searchQueryDecoder
	 */
	public Converter<SearchQueryData, SolrSearchQueryData> getSearchQueryDecoder()
	{
		return searchQueryDecoder;
	}

	/**
	 * @param searchQueryDecoder the searchQueryDecoder to set
	 */
	public void setSearchQueryDecoder(final Converter<SearchQueryData, SolrSearchQueryData> searchQueryDecoder)
	{
		this.searchQueryDecoder = searchQueryDecoder;
	}

	protected Converter<AutocompleteSuggestion, AutocompleteSuggestionData> getAutocompleteSuggestionConverter()
	{
		return autocompleteSuggestionConverter;
	}

	@Required
	public void setAutocompleteSuggestionConverter(
			final Converter<AutocompleteSuggestion, AutocompleteSuggestionData> autocompleteSuggestionConverter)
	{
		this.autocompleteSuggestionConverter = autocompleteSuggestionConverter;
	}

	protected ProductSearchAutocompleteService<AutocompleteSuggestion> getAutocompleteService()
	{
		return autocompleteService;
	}

	@Required
	public void setAutocompleteService(final ProductSearchAutocompleteService<AutocompleteSuggestion> autocompleteService)
	{
		this.autocompleteService = autocompleteService;
	}

	protected ThreadContextService getThreadContextService()
	{
		return threadContextService;
	}

	@Required
	public void setThreadContextService(final ThreadContextService threadContextService)
	{
		this.threadContextService = threadContextService;
	}

	@Override
	public ContentSearchPageData<SearchStateData, ITEM> textSearch(final SearchStateData searchState,
			final PageableData pageableData)
	{
		Assert.notNull(searchState, "SearchStateData must not be null.");

		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<ContentSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public ContentSearchPageData<SearchStateData, ITEM> execute()
					{
						return getContentCategorySearchPageConverter()
								.convert(getContentSearchService().searchAgain(decodeState(searchState, null), pageableData));
					}
				});
	}

	protected SolrSearchQueryData decodeState(final SearchStateData searchState, final String categoryCode)
	{
		final SolrSearchQueryData searchQueryData = getSearchQueryDecoder().convert(searchState.getQuery());
		if (categoryCode != null)
		{
			searchQueryData.setCategoryCode(categoryCode);
		}
		return searchQueryData;
	}
}
