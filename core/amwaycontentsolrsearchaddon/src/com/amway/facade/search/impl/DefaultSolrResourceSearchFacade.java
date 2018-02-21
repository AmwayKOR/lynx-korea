/**
 *
 */
package com.amway.facade.search.impl;

import de.hybris.platform.commercefacades.search.data.AutocompleteSuggestionData;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.ProductSearchAutocompleteService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.AutocompleteSuggestion;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.solrfacetsearch.daos.SolrFacetSearchConfigDao;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.facade.content.data.ResourceData;
import com.amway.facade.search.ResourceSearchFacade;
import com.amway.facade.search.facetdata.ResourceSearchPageData;
import com.amway.facade.search.facetdata.ResourseCategorySearchPageData;
import com.amway.service.search.ResourceSearchService;


public class DefaultSolrResourceSearchFacade<ITEM extends ResourceData> implements ResourceSearchFacade<ITEM>
{

	private ResourceSearchService<SolrSearchQueryData, SearchResultValueData, ResourseCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, MediaModel>> resourceSearchService;
	private Converter<ResourseCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, MediaModel>, ResourseCategorySearchPageData<SearchStateData, ITEM, MediaModel>> resourceCategorySearchPageConverter;
	private Converter<SearchQueryData, SolrSearchQueryData> searchQueryDecoder;
	private Converter<AutocompleteSuggestion, AutocompleteSuggestionData> autocompleteSuggestionConverter;
	private ProductSearchAutocompleteService<AutocompleteSuggestion> autocompleteService;
	private ThreadContextService threadContextService;
	private SolrFacetSearchConfigDao facetSearchConfigDao;

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
	 * @return the contentSearchService
	 */
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
	public ResourceSearchPageData<SearchStateData, ResourceData> textSearch(final SearchStateData searchState,
			final PageableData pageableData)
	{
		Assert.notNull(searchState, "SearchStateData must not be null.");

		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<ResourceSearchPageData<SearchStateData, ResourceData>, ThreadContextService.Nothing>()
				{
					@Override
					public ResourceSearchPageData<SearchStateData, ResourceData> execute()
					{
						return (ResourceSearchPageData<SearchStateData, ResourceData>) getResourceCategorySearchPageConverter()
								.convert(getResourceSearchService().searchAgain(decodeState(searchState, null), pageableData));
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


	public ResourceSearchService<SolrSearchQueryData, SearchResultValueData, ResourseCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, MediaModel>> getResourceSearchService()
	{
		return resourceSearchService;
	}

	public void setResourceSearchService(
			final ResourceSearchService<SolrSearchQueryData, SearchResultValueData, ResourseCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, MediaModel>> resourceSearchService)
	{
		this.resourceSearchService = resourceSearchService;
	}

	public Converter<ResourseCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, MediaModel>, ResourseCategorySearchPageData<SearchStateData, ITEM, MediaModel>> getResourceCategorySearchPageConverter()
	{
		return resourceCategorySearchPageConverter;
	}

	public void setResourceCategorySearchPageConverter(
			final Converter<ResourseCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, MediaModel>, ResourseCategorySearchPageData<SearchStateData, ITEM, MediaModel>> resourceCategorySearchPageConverter)
	{
		this.resourceCategorySearchPageConverter = resourceCategorySearchPageConverter;
	}
}
