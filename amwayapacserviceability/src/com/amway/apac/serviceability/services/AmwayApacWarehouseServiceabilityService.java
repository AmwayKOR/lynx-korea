package com.amway.apac.serviceability.services;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;


/**
 * Service to find the serviceable warehouse for the user pincode
 *
 * @author Shubham Goyal
 */
public interface AmwayApacWarehouseServiceabilityService
{
	/**
	 * Get the warehouse from the postal code.
	 *
	 * @param postalCode
	 *           the postal code
	 * @param baseSite
	 * @return the warehouse model
	 */
	WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite);

	/**
	 * This service checks if the postal code is serviceable or not
	 *
	 * @param postalCode
	 * @param baseSite
	 * @param region
	 *
	 * @return true if the postal code is serviceable else false
	 */
	Boolean isPostalCodeServiceable(final String postalCode, BaseSiteModel baseSite, final RegionModel region);

	/**
	 * Get the warehouse Serviceability from the postal code.
	 *
	 * @param postalCode
	 * @param baseSite
	 * @return List of regions for given postal code
	 */
	List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite);

	/**
	 * This service checks if the postal code is serviceable or not for current basestore
	 *
	 * @param postalCode
	 * @return true if the postal code is serviceable else false
	 */
	Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode);
}
