/**
 *
 */
package com.amway.core.infraavail.dao;

import java.util.List;

import com.amway.core.model.AmwayInfraAvailabilityModel;


/**
 * Data access to {@link com.amway.core.model.AmwayInfraAvailabilityModel}
 */
public interface AmwayInfraAvailabilityDao
{
	/**
	 * find AmwayInfraAvailability for the given code
	 *
	 * @param code
	 * @param baseStoreUid
	 * @return the AmwayInfraAvailabilityModel
	 * @code code of the AmwayInfraAvailability
	 */
	List<AmwayInfraAvailabilityModel> findInfraAvail(final String code, final String baseStoreUid);
}
