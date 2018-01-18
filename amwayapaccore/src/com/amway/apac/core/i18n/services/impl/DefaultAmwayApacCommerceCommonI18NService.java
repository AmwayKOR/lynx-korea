package com.amway.apac.core.i18n.services.impl;

import static org.springframework.util.Assert.hasLength;

import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.i18n.impl.DefaultCommerceCommonI18NService;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.i18n.services.AmwayApacCommerceCommonI18NService;


/**
 * Overriding OOTB implementation for APAC requirements and default implementation for
 * {@link AmwayApacCommerceCommonI18NService}
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCommerceCommonI18NService extends DefaultCommerceCommonI18NService
		implements AmwayApacCommerceCommonI18NService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmwayApacCommerceCommonI18NService.class);

	private CMSSiteService cmsSiteService;
	private GenericDao<CountryModel> amwayApacCountryDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountryModel getCountryForCode(final String countryCode)
	{
		hasLength(countryCode);
		LOG.info("Get Country By Code : " + countryCode);
		final List<CountryModel> countries = getAmwayApacCountryDao()
				.find(Collections.singletonMap(CountryModel.COUNTRYCODE, countryCode));
		return CollectionUtils.isEmpty(countries) ? null : countries.get(0);
	}

	/**
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * @param cmsSiteService
	 *           the cmsSiteService to set
	 */
	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @return the amwayApacCountryDao
	 */
	public GenericDao<CountryModel> getAmwayApacCountryDao()
	{
		return amwayApacCountryDao;
	}

	/**
	 * @param amwayApacCountryDao
	 *           the amwayApacCountryDao to set
	 */
	@Required
	public void setAmwayApacCountryDao(final GenericDao<CountryModel> amwayApacCountryDao)
	{
		this.amwayApacCountryDao = amwayApacCountryDao;
	}

}
