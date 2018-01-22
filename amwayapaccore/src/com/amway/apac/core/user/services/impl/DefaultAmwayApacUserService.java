package com.amway.apac.core.user.services.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.impl.DefaultUserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.services.BaseStoreService;

import com.amway.apac.core.user.daos.AmwayApacUserDao;
import com.amway.apac.core.user.services.AmwayApacUserService;


/**
 * Default implementation of {@link AmwayApacUserService}
 * 
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacUserService extends DefaultUserService implements AmwayApacUserService
{
	private BaseStoreService baseStoreService;

	private AmwayApacUserDao amwayApacUserDao;

	@Override
	public UserModel getUserForUIDAndAmwayAccount(final String id)
	{
		ServicesUtil.validateParameterNotNull(id, "The given userID is null!");
		final String affiliacteNo = getBaseStoreService().getCurrentBaseStore().getAffiliateNumber();
		final UserModel user = getAmwayApacUserDao().getUsersForUidAndAboID(id, affiliacteNo);
		if (user == null)
		{
			throw new UnknownIdentifierException("Cannot find user with uid '" + id + "'");
		}
		return user;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return the amwayApacUserDao
	 */
	public AmwayApacUserDao getAmwayApacUserDao()
	{
		return amwayApacUserDao;
	}

	/**
	 * @param amwayApacUserDao
	 *           the amwayApacUserDao to set
	 */
	public void setAmwayApacUserDao(final AmwayApacUserDao amwayApacUserDao)
	{
		this.amwayApacUserDao = amwayApacUserDao;
	}
}
