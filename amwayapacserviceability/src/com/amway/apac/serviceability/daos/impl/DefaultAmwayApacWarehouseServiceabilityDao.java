package com.amway.apac.serviceability.daos.impl;

import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.BASESITE_STRING;
import static com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel.SITE;
import static com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel.POSTALCODE;
import static com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel._TYPECODE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import static org.springframework.util.Assert.hasText;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.serviceability.daos.AmwayApacWarehouseServiceabilityDao;
import com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel;


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
