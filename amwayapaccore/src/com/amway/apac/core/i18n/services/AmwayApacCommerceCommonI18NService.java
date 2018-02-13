package com.amway.apac.core.i18n.services;

import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.core.model.c2l.CountryModel;


/**
 * Services related to Internationalization.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacCommerceCommonI18NService extends CommerceCommonI18NService
{

	/**
	 * Returns the {@link CountryModel} for countryCode (Affiliate Number)
	 *
	 * @param countryCode
	 *           Affiliate Code
	 * @return country
	 * @throws IllegalArgumentException
	 *            if countryCode is null or empty.
	 */
	CountryModel getCountryForCode(final String countryCode);
}
