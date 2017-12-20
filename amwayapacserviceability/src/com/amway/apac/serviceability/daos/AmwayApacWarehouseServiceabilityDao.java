package com.amway.apac.serviceability.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;

import com.amway.apac.serviceability.model.AmwayApacAbstractWarehouseServiceabilityModel;


/**
 * Warehouse servicability Dao interface
 *
 * @author Shubham Goyal
 */
public interface AmwayApacWarehouseServiceabilityDao
{
	/**
	 * Get the warehouse serving the inventory from the postal code and base site given
	 *
	 * @param postalCode
	 *           the postal code
	 * @param baseSite
	 *           The given basesite
	 *
	 * @throws IllegalArgumentException
	 * @returns serviceable warehouse for postalcode and basesite.
	 *
	 */
	WarehouseModel getServiceableWarehouse(final String postalCode, final BaseSiteModel baseSite);

	/**
	 * @param postalcode
	 *           Postal code for which warehouse needed
	 * @param baseSite
	 *           basesite for which warehouse needed
	 * @param region
	 *           region filter for finding warehouse
	 * @return warehouse for given Postalcode, Basesite and Region
	 */
	WarehouseModel getServiceableWarehouseForPostalCodeRegion(final String postalcode, final BaseSiteModel baseSite,
			RegionModel region);

	/**
	 * @param postalcode
	 *           Postal code for which warehouse needed
	 * @param baseSite
	 *           basesite for which warehouse needed
	 * @return List of serviceabilities for given postalcode and basesite
	 */
	List<AmwayApacAbstractWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String postalcode,
			final BaseSiteModel baseSite);
}
