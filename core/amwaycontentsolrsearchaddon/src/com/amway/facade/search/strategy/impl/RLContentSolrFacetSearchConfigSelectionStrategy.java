/**
 *
 */
package com.amway.facade.search.strategy.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.SolrFacetSearchConfigSelectionStrategy;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.solrfacetsearch.daos.SolrFacetSearchConfigDao;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


public class RLContentSolrFacetSearchConfigSelectionStrategy implements SolrFacetSearchConfigSelectionStrategy
{
	private BaseSiteService baseSiteService;
	private BaseStoreService baseStoreService;
	private CatalogVersionService catalogVersionService;
	private SolrFacetSearchConfigDao facetSearchConfigDao;
	private CMSSiteService cmsSiteService;



	/**
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * @param cmsSiteService the cmsSiteService to set
	 */
	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	@Override
	public SolrFacetSearchConfigModel getCurrentSolrFacetSearchConfig() throws NoValidSolrConfigException
	{
		SolrFacetSearchConfigModel result = getSolrConfigForBaseSite();
		if (result == null)
		{
			result = getSolrConfigForBaseStore();
		}
		if (result == null)
		{
			result = getSolrConfigForProductCatalogVersions();
		}
		if (result == null)
		{
			throw new NoValidSolrConfigException(
					"No Valid SolrFacetSearchConfig configured neither for base site/base store/session product catalog versions.");
		}
		return result;
	}

	protected SolrFacetSearchConfigModel getSolrConfigForProductCatalogVersions()
	{
		final Collection sessionProductCatalogVersions = getSessionProductCatalogVersions();
		for (final SolrFacetSearchConfigModel solrConfigModel : this.facetSearchConfigDao.findAllFacetSearchConfigs())
		{
			if ((solrConfigModel.getCatalogVersions() != null) && (solrConfigModel.getCatalogVersions()
					.containsAll(sessionProductCatalogVersions)))
			{
				return solrConfigModel;
			}
		}
		return null;
	}

	protected SolrFacetSearchConfigModel getSolrConfigForBaseStore()
	{
		final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
		if (currentBaseStore != null)
		{
			return currentBaseStore.getSolrContentFacetSearchConfiguration();
		}
		return null;
	}

	protected SolrFacetSearchConfigModel getSolrConfigForBaseSite()
	{
		final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
		if (currentBaseSite != null)
		{
			return currentBaseSite.getSolrContentFacetSearchConfiguration();
		}
		return null;
	}

	protected Collection<CatalogVersionModel> getSessionProductCatalogVersions()
	{
		final List<ContentCatalogModel> contentCatalogs = getCmsSiteService().getCurrentSite().getContentCatalogs();
		//final BaseSiteModel currentSite = getBaseSiteService().getCurrentBaseSite();
		//final List productCatalogs = getBaseSiteService().getProductCatalogs(currentSite);

		final Collection<CatalogVersionModel> sessionCatalogVersions = getCatalogVersionService().getSessionCatalogVersions();

		final Collection result = new ArrayList();
		if ((sessionCatalogVersions != null) && (contentCatalogs != null))
		{
			for (final CatalogVersionModel sessionCatalogVersion : sessionCatalogVersions)
			{
				if (!(contentCatalogs.contains(sessionCatalogVersion.getCatalog())))
				{
					continue;
				}
				result.add(sessionCatalogVersion);
			}
		}

		return result;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return this.baseSiteService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return this.baseStoreService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	protected CatalogVersionService getCatalogVersionService()
	{
		return this.catalogVersionService;
	}

	@Required
	public void setFacetSearchConfigDao(final SolrFacetSearchConfigDao facetSearchConfigDao)
	{
		this.facetSearchConfigDao = facetSearchConfigDao;
	}

	protected SolrFacetSearchConfigDao getFacetSearchConfigDao()
	{
		return this.facetSearchConfigDao;
	}
}
