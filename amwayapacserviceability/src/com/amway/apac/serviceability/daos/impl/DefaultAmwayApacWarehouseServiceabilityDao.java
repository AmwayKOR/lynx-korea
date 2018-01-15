package com.amway.apac.serviceability.daos.impl;

import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.BASESITE_STRING;
import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.REGION_STRING;
import static com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel.REGION;
import static com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel.SITE;
import static com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel.POSTALCODE;
import static com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel.MAJORPOSTALCODE;
import static com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel.MINORPOSTALCODE;
import static com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel._TYPECODE;
import static de.hybris.platform.core.model.ItemModel.PK;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import static org.springframework.util.Assert.hasText;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants;
import com.amway.apac.serviceability.daos.AmwayApacWarehouseServiceabilityDao;
import com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel;


/**
 * Default implementation for {@link AmwayApacWarehouseServiceabilityDao}
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacWarehouseServiceabilityDao implements AmwayApacWarehouseServiceabilityDao
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacWarehouseServiceabilityDao.class);

	private FlexibleSearchService flexibleSearchService;

	/**
	 * Query for searching warehouse serviceablity against given baseSite, majorPostalCode, minorPostalCode and region.
	 * The priority list for matches in decreasing order is as follow: 1. Matching majorPostalCode and minorPostalCode
	 * both. 2. Mathcing minorPostalCode only with serviceability having null majorPostalCode. 3. Serviceablity with both
	 * the major and minor code, null.
	 */
	private static final String WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE_WITH_REGION = new StringBuilder(200).append("SELECT ")
			.append(PK).append(" FROM {").append(_TYPECODE).append(" } WHERE ((({").append(MAJORPOSTALCODE).append("} = ?")
			.append(MAJORPOSTALCODE).append(" AND {").append(MINORPOSTALCODE).append("} = ?").append(MINORPOSTALCODE)
			.append(") OR ({").append(MAJORPOSTALCODE).append("} = ?").append(MAJORPOSTALCODE).append(" AND {")
			.append(MINORPOSTALCODE).append("} is NULL) OR ({").append(MAJORPOSTALCODE).append("} is NULL AND {")
			.append(MINORPOSTALCODE).append("} is NULL)) AND ({").append(SITE).append("} = ?").append(SITE).append(") AND ({")
			.append(REGION).append("} = ?").append(REGION).append(")) ORDER BY {").append(MAJORPOSTALCODE).append("} desc , {")
			.append(MINORPOSTALCODE).append("} desc").toString();

	/**
	 * Query for searching warehouse serviceablity against given baseSite, majorPostalCode and minorPostalCode. The
	 * priority list for matches in decreasing order is as follow: 1. Matching majorPostalCode and minorPostalCode both.
	 * 2. Mathcing minorPostalCode only with serviceability having null majorPostalCode. 3. Serviceablity with both the
	 * major and minor code, null.
	 */
	private static final String WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE = new StringBuilder(200).append("SELECT ").append(PK)
			.append(" FROM {").append(_TYPECODE).append(" } WHERE ((({").append(MAJORPOSTALCODE).append("} = ?")
			.append(MAJORPOSTALCODE).append(" AND {").append(MINORPOSTALCODE).append("} = ?").append(MINORPOSTALCODE)
			.append(") OR ({").append(MAJORPOSTALCODE).append("} = ?").append(MAJORPOSTALCODE).append(" AND {")
			.append(MINORPOSTALCODE).append("} is NULL) OR ({").append(MAJORPOSTALCODE).append("} is NULL AND {")
			.append(MINORPOSTALCODE).append("} is NULL)) AND ({").append(SITE).append("} = ?").append(SITE).append(")) ORDER BY {")
			.append(MAJORPOSTALCODE).append("} desc , {").append(MINORPOSTALCODE).append("} desc").toString();

	/**
	 * Query for searching warehouse serviceablity against given baseSite, postalCode and region. The priority list for
	 * matches in decreasing order is as follow: 1. Matching postalCode 2. Serviceablity with both the major and minor
	 * code, null.
	 */
	private static final String WAREHOUSE_QUERY_POSTAL_CODE_WITH_REGION = new StringBuilder(200).append("SELECT ").append(PK)
			.append(" FROM {").append(_TYPECODE).append(" } WHERE ((({").append(POSTALCODE).append("} = ?").append(POSTALCODE)
			.append(") OR ({").append(POSTALCODE).append("} is NULL )) AND ({").append(SITE).append("} = ?").append(SITE)
			.append(") AND ({").append(REGION).append("} = ?").append(REGION).append(")) ORDER BY {").append(POSTALCODE)
			.append("} desc ").toString();

	/**
	 * Query for searching warehouse serviceablity against given postalCode and baseSite. The priority list for matches
	 * in decreasing order is as follow: 1. Matching postalCode 2. Serviceablity with both the major and minor code,
	 * null.
	 */
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
		hasText(minorPostalCode);
		hasText(majorPostalCode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);
		validateParameterNotNullStandardMessage(REGION_STRING, region);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE_WITH_REGION);
		query.addQueryParameter(MAJORPOSTALCODE, majorPostalCode);
		query.addQueryParameter(MINORPOSTALCODE, minorPostalCode);
		query.addQueryParameter(SITE, baseSite);
		query.addQueryParameter(REGION, region);
		query.setResultClassList(Arrays.asList(AmwayWarehouseServiceabilityModel.class));
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching warehouse serviceablity for majorPostalCode [")
					.append(majorPostalCode).append("], minorPostalCode [").append(minorPostalCode).append("] , region [")
					.append(region.getIsocode()).append("] and basesite [").append(baseSite.getName()).append("].").toString());
		}
		return getFlexibleSearchService().<AmwayWarehouseServiceabilityModel> search(query).getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String minorPostalCode,
			final String majorPostalCode, final BaseSiteModel baseSite)
	{
		hasText(minorPostalCode);
		hasText(majorPostalCode);
		validateParameterNotNullStandardMessage(AmwayapacserviceabilityConstants.BASESITE_STRING, baseSite);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_MAJOR_MINOR_POSTAL_CODE);
		query.addQueryParameter(MAJORPOSTALCODE, majorPostalCode);
		query.addQueryParameter(MINORPOSTALCODE, minorPostalCode);
		query.addQueryParameter(SITE, baseSite);
		query.setResultClassList(Arrays.asList(AmwayWarehouseServiceabilityModel.class));
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching warehouse serviceablity for majorPostalCode [")
					.append(majorPostalCode).append("], minorPostalCode [").append(minorPostalCode).append("] and basesite [")
					.append(baseSite.getName()).append("].").toString());
		}
		return getFlexibleSearchService().<AmwayWarehouseServiceabilityModel> search(query).getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPostcodeWarehouseServiceabilityModel> getServiceableWarehouseListForPostalCodeRegion(final String postalcode,
			final BaseSiteModel baseSite, final RegionModel region)
	{
		hasText(postalcode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);
		validateParameterNotNullStandardMessage(REGION_STRING, region);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_POSTAL_CODE_WITH_REGION);
		query.addQueryParameter(POSTALCODE, postalcode);
		query.addQueryParameter(SITE, baseSite);
		query.addQueryParameter(REGION, region);
		query.setResultClassList(Arrays.asList(AmwayPostcodeWarehouseServiceabilityModel.class));
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching warehouse serviceablity for postalCode [").append(postalcode)
					.append("], region [").append(region.getIsocode()).append("] and basesite [").append(baseSite.getName())
					.append("].").toString());
		}
		return getFlexibleSearchService().<AmwayPostcodeWarehouseServiceabilityModel> search(query).getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayPostcodeWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String postalcode,
			final BaseSiteModel baseSite)
	{
		hasText(postalcode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_QUERY_POSTAL_CODE);
		query.addQueryParameter(POSTALCODE, postalcode);
		query.addQueryParameter(SITE, baseSite);
		query.setResultClassList(Arrays.asList(AmwayPostcodeWarehouseServiceabilityModel.class));
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching warehouse serviceablity for postalCode [").append(postalcode)
					.append("] and basesite [").append(baseSite.getName()).append("].").toString());
		}
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
