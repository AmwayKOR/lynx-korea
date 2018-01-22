package com.amway.apac.resourcecenter.daos;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;

import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * DAO Interface for Assets
 *
 * @author Ashish Sabal
 */
public interface AmwayAssetDao
{

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
}