package com.amway.apac.serviceability.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;

import java.util.List;

import com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel;


/**
 * DAO layer for warehouse serviceability.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacWarehouseServiceabilityDao
{

	/**
	 * Returns a list of warehouse serviceablities {@link AbstractAmwayWarehouseServiceabilityModel} for given Postal
	 * Code and Base Site.
	 *
	 * @param postalcode
	 *           Postal code for which warehouse needed
	 * @param baseSite
	 *           basesite for which warehouse needed
	 * @return List of serviceabilities for given postalcode and basesite
	 * @throws IllegalArgumentException
	 *            if postalCode or baseSite is null.
	 */
	List<AmwayPostcodeWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String postalcode,
			final BaseSiteModel baseSite);
}
