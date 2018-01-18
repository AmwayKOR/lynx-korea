package com.amway.apac.resourcecenter.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import com.amway.apac.resourcecentre.model.media.AmwayAssetAlbumModel;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * The Interface AmwayApacAssetService.
 *
 * @author Ashish Sabal
 */
public interface AmwayApacAssetService
{

	/**
	 * Gets the assets.
	 *
	 * @param componentId
	 *           the component id
	 * @param pageableData
	 *           the pageable data
	 * @param year
	 *           the year
	 * @return the assets
	 *
	 * @throws IllegalArgumentException
	 */
	SearchPageData<AmwayAssetModel> getAssets(final String componentId, final PageableData pageableData, final String year);

	/**
	 * Gets the assets for product.
	 *
	 * @param product
	 *           the product
	 * @param pageableData
	 *           the pageable data
	 * @param year
	 *           the year
	 * @return the assets for product
	 *
	 * @throws IllegalArgumentException
	 */
	SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final String year);

	/**
	 * Gets the assets albums.
	 *
	 * @param componentId
	 *           the component id
	 * @param pageableData
	 *           the pageable data
	 * @param year
	 *           the year
	 * @return the assets albums
	 *
	 * @throws IllegalArgumentException
	 */
	SearchPageData<AmwayAssetAlbumModel> getAssetsAlbums(final String componentId, final PageableData pageableData,
			final String year);

	/**
	 * Gets the assets album media.
	 *
	 * @param componentId
	 *           the component id
	 * @return the assets album media
	 *
	 * @throws IllegalArgumentException
	 */
	List<MediaContainerModel> getAssetsAlbumMedia(final String componentId);

}