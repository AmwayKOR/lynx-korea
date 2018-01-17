package com.amway.apac.resourcecenter.services.Impl;

import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.COMPONENT_STRING;
import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.PAGEABLEDATA_STRING;
import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.PRODUCT_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collection;
import java.util.List;

import com.amway.apac.resourcecenter.daos.AmwayAssetDao;
import com.amway.apac.resourcecenter.services.AmwayApacAssetService;
import com.amway.apac.resourcecentre.model.media.AmwayAssetAlbumModel;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * Default implementation of {@link AmwayApacAssetService}.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayApacAssetService implements AmwayApacAssetService
{
	/** The catalog service. */
	private CatalogService catalogService;

	/** The cms site service. */
	private CMSSiteService cmsSiteService;

	/** The amway asset dao. */
	private AmwayAssetDao amwayAssetDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayAssetModel> getAssets(final String componentId, final PageableData pageableData, final String year)
	{
		validateParameterNotNullStandardMessage(COMPONENT_STRING, componentId);
		validateParameterNotNullStandardMessage(PAGEABLEDATA_STRING, pageableData);

		return getAmwayAssetDao().getAssets(componentId, pageableData, getCatalogVersion(), year);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final String year)
	{
		validateParameterNotNullStandardMessage(PRODUCT_STRING, product);
		validateParameterNotNullStandardMessage(PAGEABLEDATA_STRING, pageableData);

		return getAmwayAssetDao().getAssetsForProduct(product, pageableData, getCmsSiteService().getCurrentCatalogVersion(), year);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayAssetAlbumModel> getAssetsAlbums(final String componentId, final PageableData pageableData,
			final String year)
	{
		validateParameterNotNullStandardMessage(COMPONENT_STRING, componentId);
		validateParameterNotNullStandardMessage(PAGEABLEDATA_STRING, pageableData);

		return getAmwayAssetDao().getAssetsAlbums(componentId, pageableData, getCatalogVersion(), year);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MediaContainerModel> getAssetsAlbumMedia(final String componentId)
	{
		validateParameterNotNullStandardMessage(COMPONENT_STRING, componentId);

		return getAmwayAssetDao().getAssetsAlbumMedia(getCatalogVersion(), componentId);
	}

	/**
	 * Gets the catalog version.
	 *
	 * @return the catalog version
	 */
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
	 * Gets the catalog service.
	 *
	 * @return the catalogService
	 */
	public CatalogService getCatalogService()
	{
		return catalogService;
	}

	/**
	 * Sets the catalog service.
	 *
	 * @param catalogService
	 *           the catalogService to set
	 */
	public void setCatalogService(final CatalogService catalogService)
	{
		this.catalogService = catalogService;
	}

	/**
	 * Gets the cms site service.
	 *
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * Sets the cms site service.
	 *
	 * @param cmsSiteService
	 *           the cmsSiteService to set
	 */
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * Gets the amway asset dao.
	 *
	 * @return the amwayAssetDao
	 */
	public AmwayAssetDao getAmwayAssetDao()
	{
		return amwayAssetDao;
	}

	/**
	 * Sets the amway asset dao.
	 *
	 * @param amwayAssetDao
	 *           the amwayAssetDao to set
	 */
	public void setAmwayAssetDao(final AmwayAssetDao amwayAssetDao)
	{
		this.amwayAssetDao = amwayAssetDao;
	}

}
