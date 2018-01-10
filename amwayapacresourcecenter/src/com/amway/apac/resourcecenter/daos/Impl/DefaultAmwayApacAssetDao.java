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
package com.amway.apac.resourcecenter.daos.Impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.amway.apac.resourcecenter.daos.AmwayApacAssetDao;
import com.amway.apac.resourcecenter.enums.AmwayApacAssetsSort;
import com.amway.lynxresourcecentre.model.media.AmwayAssetAlbumModel;
import com.amway.lynxresourcecentre.model.media.AmwayAssetModel;


/**
 * Default implementation of {@link AmwayApacAssetDao}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacAssetDao implements AmwayApacAssetDao
{
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ssz";
	private static final String CATALOG_VERSION_PARAM = "catalogVersion";
	private static final String COMPONENT_ID_PARAM = "componentId";
	private static final String ERROR_MESSAGE_NULL_CATALOG_VERSION = "CatalogVersion must not be null";
	private static final String ERROR_MESSAGE_NULL_PAGEABLE_DATA = "pageableData must not be null";
	private static final String ERROR_MESSAGE_NULL_COMPONENT = "Component must not be null";

	public static final String ASSET_QUERY = "SELECT {a.pk} FROM {AmwayResSectCompForAssetRel as pw JOIN AmwayResourceSectionComponent as p ON {pw.source} = {p.pk} JOIN AbstractAmwayAsset as a ON {pw.target}={a.pk}} WHERE {p.uid} IN (?componentId) AND {p.catalogVersion} IN (?catalogVersion) ";
	public static final String PRODUCT_QUERY = "SELECT {a.pk} FROM {AmwayProd2AssetRel as pw JOIN Product as p ON {pw.source} = {p.pk} JOIN AbstractAmwayAsset as a ON {pw.target}={a.pk}} WHERE {p.pk} IN (?product) AND {p.catalogVersion} IN (?catalogVersion) ";
	public static final String ASSET_ALBUM_QUERY = "SELECT {a.pk} FROM {AmwayPhotoSectCompForAssetAlbumRel as pw JOIN AmwayPhotoGallerySectionComponent as p ON {pw.source} = {p.pk} JOIN AmwayAssetAlbum as a ON {pw.target}={a.pk} LEFT JOIN AmwayAlbum2AmwayAccount as ac ON {ac.source}={a.pk}} WHERE {p.uid} IN (?componentId) AND {p.catalogVersion} IN (?catalogVersion) AND (({ac.target} IN ({{SELECT {pk} FROM {AmwayAccount} WHERE {primaryParty}=?currentUser}})) OR ({ac.target} IS NULL)) ";
	public static final String ASSET_ALBUM_MEDIA_QUERY = "SELECT {a.pk} FROM {AmwayAssetAlbum2MediaContainerRel as pw JOIN AmwayAssetAlbum as p ON {pw.source} = {p.pk} JOIN MediaContainer as a ON {pw.target}={a.pk}} WHERE {p.assetId} IN (?componentId) AND {a.catalogVersion} IN (?catalogVersion) ";

	private FlexibleSearchService flexibleSearchService;
	private PagedFlexibleSearchService pagedFlexibleSearchService;
	private UserService userService;
	private SessionService sessionService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.lynxcore.asset.dao.LynxAssetDao#getAssets(java.lang.String,
	 * de.hybris.platform.commerceservices.search.pagedata.PageableData,
	 * de.hybris.platform.catalog.model.CatalogVersionModel, java.lang.String)
	 */
	@Override
	public SearchPageData<AmwayAssetModel> getAssets(final String componentId, final PageableData pageableData,
			final CatalogVersionModel catalogVersion, final String year)
	{
		ServicesUtil.validateParameterNotNull(componentId, ERROR_MESSAGE_NULL_COMPONENT);
		ServicesUtil.validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		ServicesUtil.validateParameterNotNull(catalogVersion, ERROR_MESSAGE_NULL_CATALOG_VERSION);


		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(COMPONENT_ID_PARAM, componentId);
		queryParams.put(CATALOG_VERSION_PARAM, catalogVersion);


		return getAssets(queryParams, pageableData, year, ASSET_QUERY);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.amway.lynxcore.asset.dao.LynxAssetDao#getAssetsForProduct(de.hybris.platform.core.model.product.ProductModel,
	 * de.hybris.platform.commerceservices.search.pagedata.PageableData,
	 * de.hybris.platform.catalog.model.CatalogVersionModel, java.lang.String)
	 */
	@Override
	public SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final CatalogVersionModel catalogVersion, final String year)
	{
		ServicesUtil.validateParameterNotNull(product, "Product must not be null");
		ServicesUtil.validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		ServicesUtil.validateParameterNotNull(catalogVersion, ERROR_MESSAGE_NULL_CATALOG_VERSION);

		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("product", product);
		queryParams.put(CATALOG_VERSION_PARAM, catalogVersion);

		return getAssets(queryParams, pageableData, year, PRODUCT_QUERY);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.lynxcore.asset.dao.LynxAssetDao#getAssetsAlbums(java.lang.String,
	 * de.hybris.platform.commerceservices.search.pagedata.PageableData,
	 * de.hybris.platform.catalog.model.CatalogVersionModel, java.lang.String)
	 */
	@Override
	public SearchPageData<AmwayAssetAlbumModel> getAssetsAlbums(final String componentId, final PageableData pageableData,
			final CatalogVersionModel catalogVersion, final String year)
	{
		ServicesUtil.validateParameterNotNull(componentId, ERROR_MESSAGE_NULL_COMPONENT);
		ServicesUtil.validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		ServicesUtil.validateParameterNotNull(catalogVersion, ERROR_MESSAGE_NULL_CATALOG_VERSION);

		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(COMPONENT_ID_PARAM, componentId);
		queryParams.put(CATALOG_VERSION_PARAM, catalogVersion);
		queryParams.put("currentUser", userService.getCurrentUser());


		return getAssets(queryParams, pageableData, year, ASSET_ALBUM_QUERY);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.lynxcore.asset.dao.LynxAssetDao#getAssetsAlbumMedia(de.hybris.platform.catalog.model.
	 * CatalogVersionModel, java.lang.String)
	 */
	@Override
	public List<MediaContainerModel> getAssetsAlbumMedia(final CatalogVersionModel catalogVersion, final String componentId)
	{
		ServicesUtil.validateParameterNotNull(componentId, ERROR_MESSAGE_NULL_COMPONENT);

		final Map queryParams = new HashMap();
		queryParams.put(COMPONENT_ID_PARAM, componentId);
		queryParams.put(CATALOG_VERSION_PARAM, catalogVersion);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(ASSET_ALBUM_MEDIA_QUERY);
		query.addQueryParameters(queryParams);
		final SearchResult<MediaContainerModel> result = flexibleSearchService.search(query);
		return result.getResult();

	}

	/**
	 * Gets the assets.
	 *
	 * @param <T>
	 *           the generic type
	 * @param queryParams
	 *           the query params
	 * @param pageableData
	 *           the pageable data
	 * @param year
	 *           the year
	 * @param typQuery
	 *           the typ query
	 * @return the assets
	 */
	protected <T> SearchPageData<T> getAssets(final Map<String, Object> queryParams, final PageableData pageableData,
			final String year, final String typQuery)
	{
		final List<SortQueryData> sortQueries = buildSearchQuery(queryParams, year, typQuery);

		return getPagedFlexibleSearchService().search(sortQueries, AmwayApacAssetsSort.LATEST_DATE.getCode(), queryParams,
				pageableData);
	}

	protected List<SortQueryData> buildSearchQuery(final Map<String, Object> queryParams, final String year, final String typQuery)
	{
		final String yearQuery = generateYearQuery(queryParams, year);

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[]
		{ createSortQueryData(AmwayApacAssetsSort.LATEST_DATE.getCode(),
				typQuery + yearQuery + AmwayApacAssetsSort.LATEST_DATE.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.TITTLE_ASCENDING.getCode(),
						typQuery + yearQuery + AmwayApacAssetsSort.TITTLE_ASCENDING.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.ID_ASCENDING.getCode(),
						typQuery + yearQuery + AmwayApacAssetsSort.ID_ASCENDING.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.TITTLE_DESCENDING.getCode(),
						typQuery + yearQuery + AmwayApacAssetsSort.TITTLE_DESCENDING.getQuery()), });

		return sortQueries;
	}

	/**
	 * Creates the sort query data.
	 *
	 * @param sortCode
	 *           the sort code
	 * @param query
	 *           the query
	 * @return the sort query data
	 */
	protected SortQueryData createSortQueryData(final String sortCode, final String query)
	{
		final SortQueryData result = new SortQueryData();
		result.setSortCode(sortCode);
		result.setQuery(query);
		return result;
	}

	/**
	 * @param queryParams
	 * @param year
	 * @return query string with start and end date
	 */
	protected String generateYearQuery(final Map<String, Object> queryParams, final String year)
	{
		String yearQuery = StringUtils.EMPTY;
		if (StringUtils.isNotEmpty(year))
		{
			final Calendar cal = new GregorianCalendar(Integer.parseInt(year), 0, 1);
			final Date start = cal.getTime();
			final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			final String startDate = dateFormat.format(start);

			//set date to last day of year
			final Calendar calender = new GregorianCalendar(Integer.parseInt(year) + 1, 0, 1);

			final Date end = calender.getTime();
			final String endDate = dateFormat.format(end);
			queryParams.put("startDate", startDate);
			queryParams.put("endDate", endDate);

			yearQuery = "AND {a.date} >= ?startDate AND {a.date} < ?endDate ";
		}
		return yearQuery;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the pagedFlexibleSearchService
	 */
	public PagedFlexibleSearchService getPagedFlexibleSearchService()
	{
		return pagedFlexibleSearchService;
	}

	/**
	 * @param pagedFlexibleSearchService
	 *           the pagedFlexibleSearchService to set
	 */
	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}
