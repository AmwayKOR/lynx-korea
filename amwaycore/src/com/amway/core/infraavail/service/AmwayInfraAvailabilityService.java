package com.amway.core.infraavail.service;

import de.hybris.platform.store.BaseStoreModel;

import com.amway.core.model.AmwayInfraAvailabilityModel;


/**
 * Interface dedicated for getting {@link AmwayInfraAvailabilityModel} assigned to {@link BaseStoreModel}
 *
 * @param <o>
 */
public interface AmwayInfraAvailabilityService<o extends AmwayInfraAvailabilityModel>
{
	/**
	 * gets the Infrastructure availability for the given code and current base store.
	 *
	 * @param code code of the Infrastructure availability
	 * @return AmwayInfraAvailabilityModel
	 */
	AmwayInfraAvailabilityModel getInfraAvailForCode(final String code);


	/**
	 * gets the infrastructure availability for current base store and given code
	 *
	 * @param code
	 * @param currentBaseStore
	 * @return AmwayInfraAvailabilityModel
	 */
	AmwayInfraAvailabilityModel getInfraAvailForStoreAndCode(final String code, final BaseStoreModel currentBaseStore);
}
