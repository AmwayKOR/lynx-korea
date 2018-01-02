package com.amway.apac.core.i18n.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.i18n.impl.DefaultCommerceCommonI18NService;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.i18n.services.AmwayApacCommerceCommonI18NService;


/**
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCommerceCommonI18NService extends DefaultCommerceCommonI18NService
		implements AmwayApacCommerceCommonI18NService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacCommerceCommonI18NService.class);

	private CMSSiteService cmsSiteService;
	private GenericDao<CountryModel> genericDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountryModel getCurrentCountry()
	{
		return getCmsSiteService().getCurrentSite().getDefaultCountry();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountryModel getCountryForCode(final String countryCode)
	{
		validateParameterNotNullStandardMessage("countryCode", countryCode);
		LOG.debug("Get Country By Code : " + countryCode);
		final List<CountryModel> countries = getGenericDao().find(Collections.singletonMap(CountryModel.COUNTRYCODE, countryCode));
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
	 * @return the genericDao
	 */
	public GenericDao<CountryModel> getGenericDao()
	{
		return genericDao;
	}

	/**
	 * @param genericDao
	 *           the genericDao to set
	 */
	@Required
	public void setGenericDao(final GenericDao<CountryModel> genericDao)
	{
		this.genericDao = genericDao;
	}

}
