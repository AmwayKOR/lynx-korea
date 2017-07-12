package com.amway.core.session;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.customer.service.AmwayCustomerAccountService;
import com.amway.core.exceptions.AmwayServiceException;
import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.service.AmwayAccountCommerceService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.amway.core.account.service.AmwayAccountService;

import com.amway.core.dms.data.AmwayProfileResponseData;

import com.amway.core.los.data.LosAccountResponseData;


import com.amway.core.session.callable.MagicCallable;


public class MagicSessionDataHelper
{
	private static final Logger LOG = Logger.getLogger(MagicSessionDataHelper.class);


	protected ExecutorService executor;

	//	//session level dms responses
	protected AmwayProfileResponseData accountDetails;
	protected AmwaySessionCommonData lynxSessionCommonData;
	protected LosAccountDetailResponseData losAccountDetailsData;
	private AmwayCustomerAccountService amwayCustomerAccountService;
	private AmwayAccountService amwayAccountService;
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private SessionService sessionService;


	protected Future loadAsync(final String methodName, final String sessionId, final Object invokeObject)
	{
		final MagicCallable callable = new MagicCallable(methodName, sessionId, invokeObject);
		return executor.submit(callable);
	}

	/**
	 * @return the accountDetails
	 */
	public AmwayProfileResponseData getAccountDetails(final boolean refresh)
	{
		if (refresh)
		{
			try
			{
				return refresh ?
						(accountDetails = amwayAccountService
								.getAccountProfile(AmwaycoreConstants.AmwayProfileDetailLevels.FULLDETAIL)) :
						accountDetails;
			}
			catch (final AmwayServiceException servExc)
			{
				LOG.debug(servExc.getMessage(), servExc);
			}
		}
		return accountDetails;
	}


	/**
	 * @return the business info
	 */
	public LosAccountDetailResponseData getLosAccountsDetails(final boolean refresh)
	{
		try
		{

			if (refresh)
			{
				losAccountDetailsData = getAmwayCustomerAccountService().getBusinessInfo();
			}
			return losAccountDetailsData;

		}
		catch (final AmwayServiceException servExc)
		{
			LOG.debug(servExc.getMessage(), servExc);
		}
		return null;
	}

	/**
	 * @return the lynxCustomerAccountService
	 */
	public AmwayCustomerAccountService getAmwayCustomerAccountService()
	{
		return amwayCustomerAccountService;
	}

	/**
	 * @return the amwayAccountService
	 */
	public AmwayAccountService getAmwayAccountService()
	{
		return amwayAccountService;
	}

	/**
	 * @param amwayCustomerAccountService the lynxCustomerAccountService to set
	 */
	public void setAmwayCustomerAccountService(final AmwayCustomerAccountService amwayCustomerAccountService)
	{
		this.amwayCustomerAccountService = amwayCustomerAccountService;
	}

	/**
	 * @param amwayAccountService the amwayAccountService to set
	 */
	public void setAmwayAccountService(final AmwayAccountService amwayAccountService)
	{
		this.amwayAccountService = amwayAccountService;
	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}


	public void setUp(final CustomerModel customer, final Long accountNumber)
	{

	}


	public void clear()
	{
		this.lynxSessionCommonData = new AmwaySessionCommonData();
		accountDetails = null;
		losAccountDetailsData = null;

	}
}
