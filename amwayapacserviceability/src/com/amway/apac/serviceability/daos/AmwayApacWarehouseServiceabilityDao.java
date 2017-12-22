package com.amway.apac.serviceability.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;

import java.util.List;

import com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel;


/**
 * Warehouse servicability Dao interface
 *
 * @author Shubham Goyal
 */
public interface AmwayApacWarehouseServiceabilityDao
{

	/**
	 * Returns a list of warehouse serviceablities for given Postal Code and Base Site and Region.
	 *
	 * @param majorPostalCode
	 *           Postal code for which warehouse needed
	 * @param minorPostalCode
	 *           Postal code for which warehouse needed
	 * @param baseSite
	 *           basesite for which warehouse needed
	 * @param region
	 *           region filter for finding warehouse
	 * @return warehouse for given Postalcode, Basesite and Region
	 */
	List<AmwayWarehouseServiceabilityModel> getServiceableWarehouseListForPostalCodeRegion(final String minorPostalCode,
			final String majorPostalCode, final BaseSiteModel baseSite, RegionModel region);

	/**
	 * Returns a list of warehouse serviceablities {@link AbstractAmwayWarehouseServiceabilityModel} for given Postal
	 * Code and Base Site.
	 * 
	 * @param majorPostalCode
	 *           Postal code for which warehouse needed
	 * @param minorPostalCode
	 *           Postal code for which warehouse needed
	 * @param baseSite
	 *           basesite for which warehouse needed
	 * @return List of serviceabilities for given postalcode and basesite
	 */
	List<AmwayWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String minorPostalCode,
			final String majorPostalCode, final BaseSiteModel baseSite);

	/**
	 * Returns a list of warehouse serviceablities for given Postal Code and Base Site and Region.
	 *
	 * @param postalcode
	 *           Postal code for which warehouse needed
	 * @param baseSite
	 *           basesite for which warehouse needed
	 * @param region
	 *           region filter for finding warehouse
	 * @return warehouse for given Postalcode, Basesite and Region
	 */
	List<AmwayPostcodeWarehouseServiceabilityModel> getServiceableWarehouseListForPostalCodeRegion(final String postalcode,
			final BaseSiteModel baseSite, RegionModel region);

	/**
	 * Returns a list of warehouse serviceablities {@link AbstractAmwayWarehouseServiceabilityModel} for given Postal
	 * Code and Base Site.
	 *
	 * @param postalcode
	 *           Postal code for which warehouse needed
	 * @param baseSite
	 *           basesite for which warehouse needed
	 * @return List of serviceabilities for given postalcode and basesite
	 */
	List<AmwayPostcodeWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String postalcode,
			final BaseSiteModel baseSite);
}
