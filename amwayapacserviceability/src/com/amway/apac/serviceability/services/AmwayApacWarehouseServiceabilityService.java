package com.amway.apac.serviceability.services;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;


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
	 * Returns TRUE if the passes postalCode is serviceable for given baseSite and region.
	 *
	 * @param postalCode
	 *           Postal Code
	 * @param baseSite
	 *           Base Site
	 * @param region
	 *           Region
	 * @return true if the postal code is serviceable else false
	 * @throws IllegalArgumentException
	 *            if postalCode, region or baseSite is null.
	 */
	Boolean isPostalCodeServiceable(final String postalCode, final BaseSiteModel baseSite, final RegionModel region);

	/**
	 * Returns the warehouse serviceability for the given postalCode and baseSite.
	 *
	 * @param postalCode
	 *           Postal Code
	 * @param baseSite
	 *           Base Site
	 * @return List of regions for given postal code
	 * @throws IllegalArgumentException
	 *            if postalCode or baseSite is null.
	 */
	List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite);

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
