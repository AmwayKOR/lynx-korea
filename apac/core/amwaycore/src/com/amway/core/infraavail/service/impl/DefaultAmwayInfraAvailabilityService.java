/**
 *
 */
package com.amway.core.infraavail.service.impl;

import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.infraavail.dao.AmwayInfraAvailabilityDao;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityService;
import com.amway.core.model.AmwayInfraAvailabilityModel;


/**
 * To check the availability of amway infrastructure service
 */
public class DefaultAmwayInfraAvailabilityService implements AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel>
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayInfraAvailabilityService.class);//NOPMD

	private AmwayInfraAvailabilityDao amwayInfraAvailabilityDao;
	private BaseStoreService baseStoreService;

	/**
	 * gets the Infrastructure availability for the given code.
	 *
	 * @param code
	 */
	@Override
	public AmwayInfraAvailabilityModel getInfraAvailForCode(final String code)
	{
		ServicesUtil.validateParameterNotNull("code", code);
		final BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
		return getInfraAvailForStoreAndCode(code, currentBaseStore);
	}

	/**
	 * gets the Infrastructure availability for the given code and current base store.
	 *
	 * @param code
	 * @param currentBaseStore
	 */
	@Override
	public AmwayInfraAvailabilityModel getInfraAvailForStoreAndCode(final String code, final BaseStoreModel currentBaseStore)
	{

		if (currentBaseStore != null)
		{
			final List<AmwayInfraAvailabilityModel> infraAvails = amwayInfraAvailabilityDao.findInfraAvail(code, currentBaseStore.getUid());
			if (CollectionUtils.isNotEmpty(infraAvails))
			{
				return infraAvails.get(0);
			}
		}
		return null;
	}

	/**
	 * @return amwayInfraAvailabilityDao
	 */
	public AmwayInfraAvailabilityDao getAmwayInfraAvailabilityDao()
	{
		return amwayInfraAvailabilityDao;
	}

	/**
	 * @param amwayInfraAvailabilityDao the amwayInfraAvailabilityDao to set
	 */
	public void setAmwayInfraAvailabilityDao(final AmwayInfraAvailabilityDao amwayInfraAvailabilityDao)
	{
		this.amwayInfraAvailabilityDao = amwayInfraAvailabilityDao;
	}

	/**
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}


}
