package com.amway.apac.serviceability.daos.impl;

import static com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel.REGION;
import static com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel.SITE;
import static com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel.POSTALCODE;
import static com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel.MAJORPOSTALCODE;
import static com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel.MINORPOSTALCODE;
import static com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel._TYPECODE;
import static de.hybris.platform.core.model.ItemModel.PK;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Arrays;
import java.util.List;

import com.amway.apac.serviceability.daos.AmwayApacWarehouseServiceabilityDao;
import com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel;


/**
 * Default implementation for {@link AmwayApacWarehouseServiceabilityDao}
 *
 */
public class DefaultAmwayApacWarehouseServiceabilityDao implements AmwayApacWarehouseServiceabilityDao
{
	private FlexibleSearchService flexibleSearchService;

	private static final String WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE_WITH_REGION = new StringBuilder(200).append("SELECT ")
			.append(PK).append(" FROM {").append(_TYPECODE).append(" } WHERE ((({").append(MAJORPOSTALCODE).append("} = ?")
			.append(MAJORPOSTALCODE).append(" AND {").append(MINORPOSTALCODE).append("} = ?").append(MINORPOSTALCODE)
			.append(") OR ({").append(MAJORPOSTALCODE).append("} = ?").append(MAJORPOSTALCODE).append(" AND {")
			.append(MINORPOSTALCODE).append("} is NULL) OR ({").append(MAJORPOSTALCODE).append("} is NULL AND {")
			.append(MINORPOSTALCODE).append("} is NULL)) AND ({").append(SITE).append("} = ?").append(SITE).append(") AND ({")
			.append(REGION).append("} = ?").append(REGION).append(")) ORDER BY {").append(MAJORPOSTALCODE).append("} desc , {")
			.append(MINORPOSTALCODE).append("} desc").toString();


	private static final String WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE = new StringBuilder(200).append("SELECT ").append(PK)
			.append(" FROM {").append(_TYPECODE).append(" } WHERE ((({").append(MAJORPOSTALCODE).append("} = ?")
			.append(MAJORPOSTALCODE).append(" AND {").append(MINORPOSTALCODE).append("} = ?").append(MINORPOSTALCODE)
			.append(") OR ({").append(MAJORPOSTALCODE).append("} = ?").append(MAJORPOSTALCODE).append(" AND {")
			.append(MINORPOSTALCODE).append("} is NULL) OR ({").append(MAJORPOSTALCODE).append("} is NULL AND {")
			.append(MINORPOSTALCODE).append("} is NULL)) AND ({").append(SITE).append("} = ?").append(SITE).append(")) ORDER BY {")
			.append(MAJORPOSTALCODE).append("} desc , {").append(MINORPOSTALCODE).append("} desc").toString();


	private static final String WAREHOUSE_QUERY_POSTAL_CODE_WITH_REGION = new StringBuilder(200).append("SELECT ").append(PK)
			.append(" FROM {").append(_TYPECODE).append(" } WHERE ((({").append(POSTALCODE).append("} = ?").append(POSTALCODE)
			.append(") OR ({").append(POSTALCODE).append("} is NULL )) AND ({").append(SITE).append("} = ?").append(SITE)
			.append(") AND ({").append(REGION).append("} = ?").append(REGION).append(")) ORDER BY {").append(POSTALCODE)
			.append("} desc ").toString();

	private static final String WAREHOUSE_QUERY_POSTAL_CODE = new StringBuilder(200).append("SELECT ").append(ItemModel.PK)
			.append(" FROM {").append(_TYPECODE).append(" } WHERE ((({").append(POSTALCODE).append("} = ?").append(POSTALCODE)
			.append(") OR ({").append(POSTALCODE).append("} is NULL )) AND ({").append(SITE).append("} = ?").append(SITE)
			.append(")) ORDER BY {").append(POSTALCODE).append("} desc ").toString();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayWarehouseServiceabilityModel> getServiceableWarehouseListForPostalCodeRegion(final String minorPostalCode,
			final String majorPostalCode, final BaseSiteModel baseSite, final RegionModel region)
	{

		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE_WITH_REGION);
		query.addQueryParameter(MAJORPOSTALCODE, majorPostalCode);
		query.addQueryParameter(MINORPOSTALCODE, minorPostalCode);
		query.addQueryParameter(SITE, baseSite);
		query.addQueryParameter(REGION, region);
		query.setResultClassList(Arrays.asList(AmwayWarehouseServiceabilityModel.class));
		return getFlexibleSearchService().<AmwayWarehouseServiceabilityModel> search(query).getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String minorPostalCode,
			final String majorPostalCode, final BaseSiteModel baseSite)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE);
		query.addQueryParameter(MAJORPOSTALCODE, majorPostalCode);
		query.addQueryParameter(MINORPOSTALCODE, minorPostalCode);
		query.addQueryParameter(SITE, baseSite);
		query.setResultClassList(Arrays.asList(AmwayWarehouseServiceabilityModel.class));
		return getFlexibleSearchService().<AmwayWarehouseServiceabilityModel> search(query).getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPostcodeWarehouseServiceabilityModel> getServiceableWarehouseListForPostalCodeRegion(final String postalcode,
			final BaseSiteModel baseSite, final RegionModel region)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_POSTAL_CODE_WITH_REGION);
		query.addQueryParameter(POSTALCODE, postalcode);
		query.addQueryParameter(SITE, baseSite);
		query.addQueryParameter(REGION, region);
		query.setResultClassList(Arrays.asList(AmwayPostcodeWarehouseServiceabilityModel.class));
		return getFlexibleSearchService().<AmwayPostcodeWarehouseServiceabilityModel> search(query).getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPostcodeWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String postalcode,
			final BaseSiteModel baseSite)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_POSTAL_CODE);
		query.addQueryParameter(POSTALCODE, postalcode);
		query.addQueryParameter(SITE, baseSite);
		query.setResultClassList(Arrays.asList(AmwayPostcodeWarehouseServiceabilityModel.class));
		return getFlexibleSearchService().<AmwayPostcodeWarehouseServiceabilityModel> search(query).getResult();
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
}
