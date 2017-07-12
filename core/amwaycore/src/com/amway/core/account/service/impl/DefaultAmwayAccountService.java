/**
 *
 */
package com.amway.core.account.service.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.amway.core.account.dao.AmwayAccountDao;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.exceptions.AmwayServiceException;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;


/**
 * Default implementation for Amway Account Service
 */
public class DefaultAmwayAccountService implements AmwayAccountService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayAccountService.class);//NOPMD

	private AmwayAccountDao amwayAccountDao;
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private AmwayAccountCommerceService amwayAccountCommerceService;
	private DmsService<AmwayProfileRequestData, AmwayProfileResponseData> amwayProfileService;
	private SessionService sessionService;


	/**
	 * {@link #findAccount(java.lang.String)}
	 */
	@Override
	public AmwayAccountModel findAccount(final String uid)
	{
		validateParameterNotNullStandardMessage("uid", uid);

		final List<AmwayAccountModel> messages = getAmwayAccountDao().getAccount(uid);

		ServicesUtil.validateIfAnyResult(messages, "no valid  account found for this uid :" + uid);
		ServicesUtil.validateIfSingleResult(messages, "more than one account found for this uid :" + uid, uid);
		return messages.get(0);
	}


	/**
	 * {@link #findAccountsForAbo(java.lang.String)}
	 */
	@Override
	public List<AmwayAccountModel> lookupAccountsByName(final String searchKey)
	{
		validateParameterNotNullStandardMessage("name", searchKey);

		final List<AmwayAccountModel> accounts = getAmwayAccountDao().lookupAccountsByName(searchKey);
		return accounts.size() > 100 ? accounts.subList(0, 100) : accounts;
	}

	/**
	 * {@link #findAccountsForAbo(java.lang.String)}
	 */
	@Override
	public List<AmwayAccountModel> lookupAccountsById(final String searchKey)
	{
		validateParameterNotNullStandardMessage("name", searchKey);

		final List<AmwayAccountModel> accounts = getAmwayAccountDao().lookupAccountsById(searchKey);
		return accounts.size() > 100 ? accounts.subList(0, 100) : accounts;
	}

	/**
	 * Look up account and party by party id or party id fragment
	 *
	 * @return List of CustomerModels
	 * @uid Party id fragment account/customer sorted by party id
	 */
	public List<CustomerModel> lookupAccountsCustomersByPartyId(String searchKey)
	{
		validateParameterNotNullStandardMessage("partyId", searchKey);

		final List<CustomerModel> customers = getAmwayAccountDao().lookupAccountsCustomersByPartyId(searchKey);
		return customers.size() > 100 ? customers.subList(0, 100) : customers;
	}

	/**
	 * Look up account by id or id fragment
	 *
	 * @return the CustomerModel
	 * @uid Id fragment account sorted by account Id
	 */
	public List<CustomerModel> lookupAccountsCustomersByUid(String searchKey)
	{
		validateParameterNotNullStandardMessage("UId", searchKey);

		final List<CustomerModel> customers = getAmwayAccountDao().lookupAccountsCustomersByUid(searchKey);
		return customers.size() > 100 ? customers.subList(0, 100) : customers;
	}

	/**
	 * Look up account by id or id fragment
	 *
	 * @return the CustomerModel
	 * @uid Id fragment account order by Id
	 */
	public List<CustomerModel> lookupAccountsCustomersByPartyName(String searchKey)
	{
		validateParameterNotNullStandardMessage("name", searchKey);

		final List<CustomerModel> customers = getAmwayAccountDao().lookupAccountsCustomersByPartyName(searchKey);
		return customers.size() > 100 ? customers.subList(0, 100) : customers;
	}

	/**
	 * Look up account by id or id fragment
	 *
	 * @return the CustomerModel
	 * @uid Id fragment account order by Id
	 */
	public List<CustomerModel> lookupAccountsCustomersByEmail(String searchKey)
	{
		validateParameterNotNullStandardMessage("name", searchKey);

		final List<CustomerModel> customers = getAmwayAccountDao().lookupAccountsCustomersByEmail(searchKey);
		return customers.size() > 100 ? customers.subList(0, 100) : customers;
	}


	/**
	 * {@link #findAccountsForAbo(java.lang.String)}
	 */
	@Override
	public List<AmwayAccountModel> findAccountsForAbo(final String searchKey)
	{
		validateParameterNotNullStandardMessage("uid", searchKey);
		validateParameterNotNullStandardMessage("name", searchKey);

		final List<AmwayAccountModel> accounts = getAmwayAccountDao().getAccountsForUidOrName(searchKey);
		return accounts.size() > 100 ? accounts.subList(0, 100) : accounts;
	}

	/**
	 * {@link #getAccountProfile(java.lang.String)}
	 */
	@Override
	public AmwayProfileResponseData getAccountProfile(final String serviceMode)
	{
		validateParameterNotNullStandardMessage("serviceMode", serviceMode);
		final AmwayAccountModel unit = getAmwayAccountCommerceService().getCurrentAccount();

		LOG.info("Magic call to get amway profile information for abo with abonum : " + unit.getCode());
		final AmwayProfileResponseData amwayProfileResponse = getAmwayProfileService()
				.process(createAmwayProfileRequestData(serviceMode, unit));

		if (amwayProfileResponse != null && amwayProfileResponse.getReturnCd() != 1)
		{
			LOG.error("Service call is unsuccessful.");
			throw new AmwayServiceException(amwayProfileResponse.getReturnMessage(), amwayProfileResponse.getReturnCd());
		}

		//To get Abo DOB for Anti-Fraud
		if (!CollectionUtils.isEmpty(amwayProfileResponse.getPartyDetailList()))
		{
			getSessionService().getCurrentSession().setAttribute("partyBirthDate",
					amwayProfileResponse.getPartyDetailList().get(0).getPartyPersonalDetailsData().getBirthdate());
		}
		LOG.info("Service call is successful.");
		return amwayProfileResponse;
	}

	/**
	 * {@link #getCustomerAccountProfile(java.lang.String, de.hybris.platform.core.model.user.UserModel)}
	 */
	@Override
	public AmwayProfileResponseData getCustomerAccountProfile(final String serviceMode, final UserModel userModel)
	{
		validateParameterNotNullStandardMessage("serviceMode", serviceMode);
		validateParameterNotNullStandardMessage("userModel", userModel);
		final AmwayAccountModel unit = getAmwayAccountCommerceService().getAccountFromUser(userModel);

		LOG.info("Magic call to get amway profile information for abo with abonum : " + unit.getCode());
		final AmwayProfileResponseData amwayProfileResponse = getAmwayProfileService()
				.process(createAmwayProfileRequestData(serviceMode, unit));
		if (amwayProfileResponse != null && amwayProfileResponse.getReturnCd() != 1)
		{
			LOG.error("Service call is unsuccessful.");
			throw new AmwayServiceException(amwayProfileResponse.getReturnMessage(), amwayProfileResponse.getReturnCd());
		}
		LOG.info("Service call is successful.");
		return amwayProfileResponse;
	}

	/**
	 * {@link #getAccountProfileForOrder(java.lang.String, de.hybris.platform.core.model.order.AbstractOrderModel)}
	 */
	@Override
	public AmwayProfileResponseData getAccountProfileForOrder(final String serviceMode,
			final AbstractOrderModel abstractOrderModel)
	{
		validateParameterNotNullStandardMessage("serviceMode", serviceMode);
		validateParameterNotNullStandardMessage("AmwayAccountModel", abstractOrderModel.getAccount());

		LOG.info("Magic call to get amway profile information for abo with abonum : " + abstractOrderModel.getAccount().getCode());
		final AmwayProfileResponseData amwayProfileResponse = getAmwayProfileService()
				.process(createAmwayProfileRequestData(serviceMode, abstractOrderModel));
		if (amwayProfileResponse != null && amwayProfileResponse.getReturnCd() != 1)
		{
			LOG.error("Service call is unsuccessful. Return code : " + amwayProfileResponse.getReturnCd());
			throw new AmwayServiceException(amwayProfileResponse.getReturnMessage(), amwayProfileResponse.getReturnCd());
		}
		if (amwayProfileResponse != null)
		{
			LOG.info("Service call is successful. Return code : " + amwayProfileResponse.getReturnCd());
		}
		return amwayProfileResponse;
	}

	private AmwayProfileRequestData createAmwayProfileRequestData(final String serviceMode, final AmwayAccountModel unit)
	{
		final AmwayProfileRequestData amwayProfileRequestData = new AmwayProfileRequestData();

		amwayProfileRequestData.setSalesPlanAff(getBaseStoreService().getCurrentBaseStore().getAffiliateNumber());
		if (unit != null)
		{
			amwayProfileRequestData.setAboNum(unit.getCode());
		}
		amwayProfileRequestData.setClientCntryCd(getBaseSiteService().getCurrentBaseSite().getDefaultCountry().getIsocode());
		amwayProfileRequestData.setDeltailLevelCd(serviceMode);

		return amwayProfileRequestData;
	}

	private AmwayProfileRequestData createAmwayProfileRequestData(final String serviceMode,
			final AbstractOrderModel abstractOrderModel)
	{
		final AmwayProfileRequestData amwayProfileRequestData = new AmwayProfileRequestData();

		amwayProfileRequestData.setSalesPlanAff(abstractOrderModel.getStore().getAffiliateNumber());
		if (abstractOrderModel.getAccount() != null)
		{
			amwayProfileRequestData.setAboNum(abstractOrderModel.getAccount().getCode());
		}
		amwayProfileRequestData.setClientCntryCd(abstractOrderModel.getSite().getDefaultCountry().getIsocode());
		amwayProfileRequestData.setDeltailLevelCd(serviceMode);

		return amwayProfileRequestData;
	}

	/**
	 * @return AmwayAccountDao
	 */
	public AmwayAccountDao getAmwayAccountDao()
	{
		return amwayAccountDao;
	}

	/**
	 * @param amwayAccountDao the amwayAccountDao to set
	 */
	public void setAmwayAccountDao(final AmwayAccountDao amwayAccountDao)
	{
		this.amwayAccountDao = amwayAccountDao;
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

	/**
	 * @return baseSiteService
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
	 * @return amwayProfileService
	 */
	public DmsService<AmwayProfileRequestData, AmwayProfileResponseData> getAmwayProfileService()
	{
		return amwayProfileService;
	}

	/**
	 * @param amwayProfileService the amwayProfileService to set
	 */
	public void setAmwayProfileService(final DmsService<AmwayProfileRequestData, AmwayProfileResponseData> amwayProfileService)
	{
		this.amwayProfileService = amwayProfileService;
	}

	/**
	 * @return amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService the amwayAccountCommerceService to set
	 */
	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
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


}
