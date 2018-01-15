/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.apac.resourcecenter.services.Impl;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Collection;
import java.util.List;

import com.amway.apac.resourcecenter.daos.AmwayAssetDao;
import com.amway.apac.resourcecenter.services.AmwayApacAssetService;
import com.amway.apac.resourcecentre.model.media.AmwayAssetAlbumModel;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * Default implementation of {@link AmwayApacAssetService}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacAssetService implements AmwayApacAssetService
{
	private static final String ERROR_MESSAGE_NULL_PAGEABLE_DATA = "pageableData must not be null";
	private static final String ERROR_MESSAGE_NULL_COMPONENT = "Component id must not be null";

	private CatalogService catalogService;
	private CMSSiteService cmsSiteService;
	private AmwayAssetDao amwayAssetDao;

	@Override
	public SearchPageData<AmwayAssetModel> getAssets(final String componentId, final PageableData pageableData, final String year)
	{
		ServicesUtil.validateParameterNotNull(componentId, ERROR_MESSAGE_NULL_COMPONENT);
		ServicesUtil.validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		return getAmwayAssetDao().getAssets(componentId, pageableData, getCatalogVersion(), year);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final String year)
	{
		ServicesUtil.validateParameterNotNull(product, "Product cannot be null");
		ServicesUtil.validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		return getAmwayAssetDao().getAssetsForProduct(product, pageableData, getCmsSiteService().getCurrentCatalogVersion(), year);
	}

	@Override
	public SearchPageData<AmwayAssetAlbumModel> getAssetsAlbums(final String componentId, final PageableData pageableData,
			final String year)
	{
		ServicesUtil.validateParameterNotNull(componentId, ERROR_MESSAGE_NULL_COMPONENT);
		ServicesUtil.validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		return getAmwayAssetDao().getAssetsAlbums(componentId, pageableData, getCatalogVersion(), year);
	}

	@Override
	public List<MediaContainerModel> getAssetsAlbumMedia(final String componentId)
	{
		ServicesUtil.validateParameterNotNull(componentId, ERROR_MESSAGE_NULL_COMPONENT);
		return getAmwayAssetDao().getAssetsAlbumMedia(getCatalogVersion(), componentId);
	}

	protected CatalogVersionModel getCatalogVersion()
	{
		CatalogVersionModel catalogVersion = getCatalogService().getDefaultCatalog() == null ? null
				: getCatalogService().getDefaultCatalog().getActiveCatalogVersion();
		if (catalogVersion == null)
		{
			final Collection<CatalogVersionModel> catalogs = getCatalogService().getSessionCatalogVersions();
			for (final CatalogVersionModel cvm : catalogs)
			{
				if (cvm.getCatalog() instanceof ContentCatalogModel && cvm.getActive().booleanValue())
				{
					catalogVersion = cvm;
					break;
				}
			}
		}

		return catalogVersion;
	}

	/**
	 * @return the catalogService
	 */
	public CatalogService getCatalogService()
	{
		return catalogService;
	}

	/**
	 * @param catalogService
	 *           the catalogService to set
	 */
	public void setCatalogService(final CatalogService catalogService)
	{
		this.catalogService = catalogService;
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
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @return the amwayAssetDao
	 */
	public AmwayAssetDao getAmwayAssetDao()
	{
		return amwayAssetDao;
	}

	/**
	 * @param amwayAssetDao
	 *           the amwayAssetDao to set
	 */
	public void setAmwayAssetDao(final AmwayAssetDao amwayAssetDao)
	{
		this.amwayAssetDao = amwayAssetDao;
	}

}
