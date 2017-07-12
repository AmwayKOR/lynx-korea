/**
 *
 */
package com.amway.service.search.solrfacetsearch.populators;


import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.converter.Populator;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.SolrFacetSearchConfigSelectionStrategy;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.search.FacetSearchService;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchQuery.Operator;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;


public class SearchResourceSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>
		implements
		Populator<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest<FacetSearchConfig, IndexedType, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE>>
{
	private FacetSearchConfigService facetSearchConfigService;
	private FacetSearchService facetSearchService;
	private CommonI18NService commonI18NService;
	private BaseSiteService baseSiteService;
	private BaseStoreService baseStoreService;
	private CMSSiteService cmsSiteService;
	private CatalogVersionService catalogVersionService;
	private SolrFacetSearchConfigSelectionStrategy solrFacetSearchConfigSelectionStrategy;

	protected FacetSearchConfigService getFacetSearchConfigService()
	{
		return this.facetSearchConfigService;
	}

	@Required
	public void setFacetSearchConfigService(final FacetSearchConfigService facetSearchConfigService)
	{
		this.facetSearchConfigService = facetSearchConfigService;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return this.commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return this.baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	protected CatalogVersionService getCatalogVersionService()
	{
		return this.catalogVersionService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	@Override
	public void populate(
			final SearchQueryPageableData<SolrSearchQueryData> source,
			final SolrSearchRequest<FacetSearchConfig, IndexedType, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE> target)
	{
		target.setSearchQueryData(source.getSearchQueryData());
		target.setPageableData(source.getPageableData());

		final Collection catalogVersions = getSessionContentCatalogVersions();
		if (CollectionUtils.isEmpty(catalogVersions))
		{
			throw new ConversionException("Missing solr facet search indexed catalog versions");
		}

		target.setCatalogVersions(new ArrayList(catalogVersions));
		try
		{
			target.setFacetSearchConfig(getFacetSearchConfig());
		}
		catch (final FacetConfigServiceException ex)
		{
			throw new ConversionException("Exception looking up the SOLR search configuration", ex);
		}
		catch (final NoValidSolrConfigException e)
		{
			throw new ConversionException("No valid solrFacetSearchConfig found for the current context", e);
		}

		target.setIndexedType(getIndexedType(target.getFacetSearchConfig()));

		target.setSearchQuery(createSearchQuery(target.getFacetSearchConfig(), target.getIndexedType(), source.getSearchQueryData()
				.getFreeTextSearch()));
		target.getSearchQuery().setCatalogVersions(target.getCatalogVersions());
		target.getSearchQuery().setCurrency(getCommonI18NService().getCurrentCurrency().getIsocode());
		target.getSearchQuery().setLanguage(getCommonI18NService().getCurrentLanguage().getIsocode());

		target.getSearchQuery().setUserQuery(source.getSearchQueryData().getFreeTextSearch());

		target.getSearchQuery().setEnableSpellcheck(true);
	}

	protected Collection<CatalogVersionModel> getSessionContentCatalogVersions()
	{
		final List<ContentCatalogModel> contentCatalogs = getCmsSiteService().getCurrentSite().getContentCatalogs();

		final Collection<CatalogVersionModel> sessionCatalogVersions = getCatalogVersionService().getSessionCatalogVersions();

		final Collection result = new ArrayList();
		for (final CatalogVersionModel sessionCatalogVersion : sessionCatalogVersions)
		{
			if (!(contentCatalogs.contains(sessionCatalogVersion.getCatalog())))
			{
				continue;
			}
			result.add(sessionCatalogVersion);
		}

		return result;
	}

	protected FacetSearchConfig getFacetSearchConfig() throws FacetConfigServiceException, NoValidSolrConfigException
	{
		return getFacetSearchConfigService()
				.getConfiguration(baseSiteService.getCurrentBaseSite().getUid() + "ResourceIndexConfig");
	}

	protected IndexedType getIndexedType(final FacetSearchConfig config)
	{
		final IndexConfig indexConfig = config.getIndexConfig();

		final Collection indexedTypes = indexConfig.getIndexedTypes().values();
		if (!CollectionUtils.isEmpty(indexedTypes))
		{
			return ((IndexedType) indexedTypes.iterator().next());
		}

		return null;
	}

	protected SearchQuery createSearchQuery(final FacetSearchConfig config, final IndexedType indexedType,
			final String freeTextSearch)
	{
		SearchQuery searchQuery;

		if (config.getSearchConfig().isLegacyMode())
		{
			searchQuery = new SearchQuery(config, indexedType);
			searchQuery.setDefaultOperator(Operator.OR);
			searchQuery.setUserQuery(freeTextSearch);
		}
		else
		{
			searchQuery = facetSearchService.createFreeTextSearchQuery(config, indexedType, freeTextSearch);
		}

		return searchQuery;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return this.baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	protected SolrFacetSearchConfigSelectionStrategy getSolrFacetSearchConfigSelectionStrategy()
	{
		return this.solrFacetSearchConfigSelectionStrategy;
	}



	/**
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * @param cmsSiteService
	 *           the cmsSiteService to set
	 */
	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	@Required
	public void setSolrFacetSearchConfigSelectionStrategy(
			final SolrFacetSearchConfigSelectionStrategy solrFacetSearchConfigSelectionStrategy)
	{
		this.solrFacetSearchConfigSelectionStrategy = solrFacetSearchConfigSelectionStrategy;
	}

	public FacetSearchService getFacetSearchService()
	{
		return facetSearchService;
	}

	@Required
	public void setFacetSearchService(final FacetSearchService facetSearchService)
	{
		this.facetSearchService = facetSearchService;
	}
}
