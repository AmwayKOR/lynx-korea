package com.amway.apac.core.i18n.services;

import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.core.model.c2l.CountryModel;


/**
 * @author Shubham Goyal
 */
public interface AmwayApacCommerceCommonI18NService extends CommerceCommonI18NService
{

	/**
	 * gets the country using affiliate number
	 *
	 * @param countryCode
	 * @return country
	 */
	CountryModel getCountryForCode(final String countryCode);
}
