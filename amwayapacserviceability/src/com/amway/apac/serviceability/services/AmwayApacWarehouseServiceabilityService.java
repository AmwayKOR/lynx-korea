package com.amway.apac.serviceability.services;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;


/**
 * Service layer for warehouse serviceablity.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacWarehouseServiceabilityService
{
	/**
	 * Returns a serviceable warehouse for the postalCode and baseSite.
	 *
	 * @param postalCode
	 *           Postal Code
	 * @param baseSite
	 *           Base Site
	 * @return the warehouse model
	 * @throws IllegalArgumentException
	 *            if postalCode or baseSite is null.
	 */
	WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite);


	/**
	 * This service checks if the postal code is serviceable or not for current basestore
	 *
	 * @param postalCode
	 *           Postal Code
	 * @return true if the postal code is serviceable else false
	 * @throws IllegalArgumentException
	 *            if postalCode is null.
	 */
	Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode);
}
