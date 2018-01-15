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
package com.amway.apac.resourcecenter.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import com.amway.apac.resourcecentre.model.media.AmwayAssetAlbumModel;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * @author Ashish Sabal
 *
 */
public interface AmwayApacAssetService
{
	SearchPageData<AmwayAssetModel> getAssets(final String componentId, final PageableData pageableData, final String year);

	SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final String year);

	SearchPageData<AmwayAssetAlbumModel> getAssetsAlbums(final String componentId, final PageableData pageableData,
			final String year);

	List<MediaContainerModel> getAssetsAlbumMedia(final String componentId);

}