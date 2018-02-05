package com.amway.apac.resourcecenter.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;

import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * The Interface AmwayApacAssetService.
 *
 * @author Ashish Sabal
 */
public interface AmwayApacAssetService
{

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
	 *            if product or pageableData is null
	 */
	SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final String year);
}