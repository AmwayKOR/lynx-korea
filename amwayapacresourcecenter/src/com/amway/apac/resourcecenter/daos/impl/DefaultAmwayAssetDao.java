package com.amway.apac.resourcecenter.daos.impl;

import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.CATALOG_VERSION_STRING;
import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.COMPONENT_STRING;
import static com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants.PAGEABLE_DATA_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.resourcecenter.constants.AmwayapacresourcecenterConstants;
import com.amway.apac.resourcecenter.daos.AmwayAssetDao;
import com.amway.apac.resourcecenter.enums.AmwayApacAssetsSort;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * Default implementation of {@link AmwayAssetDao}.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayAssetDao implements AmwayAssetDao
{
	/** The Constant CATALOG VERSION. */
	private static final String CATALOG_VERSION_PARAM = CATALOG_VERSION_STRING;

	/** The Constant PRODUCT. */
	private static final String PRODUCT_PARAM = "product";

	/** ASSET_QUERY to fetch assets with product and catalog. */
	private static final String PRODUCT_QUERY = "SELECT {a.pk} FROM {AmwayProd2AssetRel as pw JOIN Product as p ON {pw.source} = {p.pk} JOIN AbstractAmwayAsset as a ON {pw.target}={a.pk}} WHERE {p.pk} IN (?product) AND {p.catalogVersion} IN (?catalogVersion) ";

	/** The Constant YEAR query part. */
	private static final String YEAR_QUERY_PART = "AND {a.date} >= ?startDate AND {a.date} < ?endDate ";

	/** The flexible search service. */
	private FlexibleSearchService flexibleSearchService;

	/** The paged flexible search service. */
	private PagedFlexibleSearchService pagedFlexibleSearchService;

	/** The user service. */
	private UserService userService;

	/** The session service. */
	private SessionService sessionService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayAssetModel> getAssetsForProduct(final ProductModel product, final PageableData pageableData,
			final CatalogVersionModel catalogVersion, final String year)
	{
		validateParameterNotNullStandardMessage(COMPONENT_STRING, product);
		validateParameterNotNullStandardMessage(PAGEABLE_DATA_STRING, pageableData);
		validateParameterNotNullStandardMessage(CATALOG_VERSION_STRING, catalogVersion);

		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(PRODUCT_PARAM, product);
		queryParams.put(CATALOG_VERSION_PARAM, catalogVersion);

		return getAssets(queryParams, pageableData, year, PRODUCT_QUERY);
	}

	/**
	 * Gets the assets search page data.
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

	/**
	 * Builds the search query to sort assets data.
	 *
	 * @param queryParams
	 *           the query params
	 * @param year
	 *           the year
	 * @param typQuery
	 *           the typ query
	 * @return the list
	 */
	protected List<SortQueryData> buildSearchQuery(final Map<String, Object> queryParams, final String year, final String typQuery)
	{
		final String yearQuery = generateYearQuery(queryParams, year);

		return Arrays.asList(new SortQueryData[]
		{ createSortQueryData(AmwayApacAssetsSort.LATEST_DATE.getCode(),
				typQuery + yearQuery + AmwayApacAssetsSort.LATEST_DATE.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.TITTLE_ASCENDING.getCode(),
						typQuery + yearQuery + AmwayApacAssetsSort.TITTLE_ASCENDING.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.ID_ASCENDING.getCode(),
						typQuery + yearQuery + AmwayApacAssetsSort.ID_ASCENDING.getQuery()),
				createSortQueryData(AmwayApacAssetsSort.TITTLE_DESCENDING.getCode(),
						typQuery + yearQuery + AmwayApacAssetsSort.TITTLE_DESCENDING.getQuery()), });
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
	 * Generate year query.
	 *
	 * @param queryParams
	 *           the query params
	 * @param year
	 *           the year
	 * @return query string with start and end date
	 */
	protected String generateYearQuery(final Map<String, Object> queryParams, final String year)
	{
		String yearQuery = StringUtils.EMPTY;
		if (StringUtils.isNotEmpty(year))
		{
			final Calendar cal = new GregorianCalendar(Integer.parseInt(year), AmwayapacresourcecenterConstants.ZERO_INT.intValue(),
					AmwayapacresourcecenterConstants.ONE_INT.intValue());
			final Date start = cal.getTime();

			//set date to last day of year
			final Calendar calender = new GregorianCalendar(
					Integer.parseInt(year) + AmwayapacresourcecenterConstants.ONE_INT.intValue(),
					AmwayapacresourcecenterConstants.ZERO_INT.intValue(), AmwayapacresourcecenterConstants.ONE_INT.intValue());

			final Date end = calender.getTime();
			queryParams.put("startDate", start);
			queryParams.put("endDate", end);

			yearQuery = YEAR_QUERY_PART;
		}
		return yearQuery;
	}

	/**
	 * Gets the flexible search service.
	 *
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * Sets the flexible search service.
	 *
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * Gets the paged flexible search service.
	 *
	 * @return the pagedFlexibleSearchService
	 */
	public PagedFlexibleSearchService getPagedFlexibleSearchService()
	{
		return pagedFlexibleSearchService;
	}

	/**
	 * Sets the paged flexible search service.
	 *
	 * @param pagedFlexibleSearchService
	 *           the pagedFlexibleSearchService to set
	 */
	@Required
	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}

	/**
	 * Gets the user service.
	 *
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * Sets the user service.
	 *
	 * @param userService
	 *           the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * Gets the session service.
	 *
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * Sets the session service.
	 *
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}
