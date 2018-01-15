package com.amway.apac.serviceability.strategy;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;


/**
 * Strategy to evaluate serviceable warehouse against the postal code on the basis of available serviceablity data.
 * 
 * @author Shubham Goyal
 */
public interface AmwayApacWarehouseServiceabilitySelectionStrategy
{
	/**
	 * Get the warehouse from the postal code.
	 *
	 * @param postalCode
	 *           The postal code for which serviceable warehouse needed
	 * @param baseSite
	 *           The base site for which serviceable warehouse needed
	 * @return the warehouse model
	 * @throws IllegalArgumentException
	 *            if postalCode or baseSite is null.
	 */
	WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite);

	/**
	 * This service checks if the postal code is serviceable or not
	 *
	 * @param postalCode
	 *           The postal code for which serviceablity need to be evaluated
	 * @param baseSite
	 *           The base site for which serviceablity need to be evaluated
	 * @param region
	 *           The region for which serviceablity need to be evaluated
	 *
	 * @return true if the postal code is serviceable else false
	 * @throws IllegalArgumentException
	 *            if postalCode, region or baseSite is null.
	 */
	Boolean isPostalCodeServiceable(final String postalCode, final BaseSiteModel baseSite, final RegionModel region);

	/**
	 * Returns the list of regions for given postalcode and basesite.
	 *
	 * @param postalCode
	 *           Postal Code
	 * @param baseSite
	 *           Base Site
	 * @return List of regions
	 * @throws IllegalArgumentException
	 *            if postalCode or baseSite is null.
	 */
	List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite);

	/**
	 * This service checks if the postal code is serviceable or not for current basestore
	 *
	 * @param postalCode
	 *           Postal Code
	 * @return true if the postal code is serviceable.
	 * @throws IllegalArgumentException
	 *            if postalCode is null.
	 */
	public Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode);


}
