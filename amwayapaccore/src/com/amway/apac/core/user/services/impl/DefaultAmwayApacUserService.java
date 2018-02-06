package com.amway.apac.core.user.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;

import com.amway.apac.core.user.daos.AmwayApacUserDao;
import com.amway.apac.core.user.services.AmwayApacUserService;
import com.amway.core.user.services.impl.DefaultAmwayUserService;


/**
 * Default implementation of {@link AmwayApacUserService}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacUserService extends DefaultAmwayUserService implements AmwayApacUserService
{
	private BaseStoreService baseStoreService;

	private AmwayApacUserDao amwayApacUserDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserModel getUserForUIDForCurrentAffiliate(final String id)
	{
		validateParameterNotNull(id, "The given userID is null!");
		final String affiliacteNo = getBaseStoreService().getCurrentBaseStore().getAffiliateNumber();
		final List<UserModel> users = getAmwayApacUserDao().getUsersForUIDandAffiliateCode(id, affiliacteNo);
		validateIfSingleResult(users, "Cannot find user with uid [" + id + "]",
				"Found " + users.size() + " users with the uid [" + id + "]");
		return users.iterator().next();
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
