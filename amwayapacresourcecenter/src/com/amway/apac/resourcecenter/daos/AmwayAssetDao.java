package com.amway.apac.resourcecenter.daos;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import com.amway.apac.resourcecentre.model.media.AmwayAssetAlbumModel;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * DAO Interface for Assets
 *
 * @author Ashish Sabal
 */
public interface AmwayAssetDao
{

	/**
	 * Gets the assets for component ID.
	 *
	 * @param componentId
	 *           the component id
	 * @param pageableData
	 *           the pageable data
	 * @param catalogVersion
	 *           the catalog version
	 * @param year
	 *           the year
	 * @return the assets
	 *
	 * @throws IllegalArgumentException
	 */
	SearchPageData<AmwayAssetModel> getAssets(final String componentId, final PageableData pageableData,
			final CatalogVersionModel catalogVersion, final String year);

	/**
	 * Gets the assets for product.
	 *
	 * @param product
	 *           the product
	 * @param pageableData
	 *           the pageable data
	 * @param catalogVersion
	 *           the catalog version
	 * @param year
	 *           the year
	 * @return the assets for product
	 *
	 * @throws IllegalArgumentException
	 */
	SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final CatalogVersionModel catalogVersion, final String year);

	/**
	 * Gets the assets albums for component ID.
	 *
	 * @param componentId
	 *           the component id
	 * @param pageableData
	 *           the pageable data
	 * @param catalogVersion
	 *           the catalog version
	 * @param year
	 *           the year
	 * @return the assets albums
	 *
	 * @throws IllegalArgumentException
	 */
	SearchPageData<AmwayAssetAlbumModel> getAssetsAlbums(final String componentId, final PageableData pageableData,
			final CatalogVersionModel catalogVersion, final String year);

	/**
	 * Gets the assets album media for component ID.
	 *
	 * @param catalogVersion
	 *           the catalog version
	 * @param componentId
	 *           the component id
	 * @return the assets album media
	 *
	 * @throws IllegalArgumentException
	 */
	List<MediaContainerModel> getAssetsAlbumMedia(final CatalogVersionModel catalogVersion, final String componentId);
}