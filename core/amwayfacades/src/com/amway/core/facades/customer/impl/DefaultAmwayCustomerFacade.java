package com.amway.core.facades.customer.impl;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.facades.customer.AmwayCustomerFacade;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.facades.data.AmwayAccountData;
import com.amway.core.session.MagicSessionDataHelper;

import com.amway.facades.data.AmwayAccountDataList;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Default Implementation
 */
public class DefaultAmwayCustomerFacade extends DefaultCustomerFacade implements AmwayCustomerFacade
{

	private AmwayAccountCommerceService amwayAccountCommerceService;
	private Converter<AmwayAccountModel, AmwayAccountData> amwayAccountConverter;
	private Populator<CustomerModel, CustomerData> amwayCustomerPopulator;
	private MagicSessionDataHelper magicSessionDataHelper;
	private AmwayAccountService amwayAccountService;

	private static final Logger LOG = Logger.getLogger(DefaultAmwayCustomerFacade.class);

	@Override
	public AmwayAccountData getCurrentAccount()
	{
		return getAmwayAccountConverter().convert(getAmwayAccountCommerceService().getCurrentAccount());
	}


	public AmwayAccountData loadAccountCustomerForPOS(String accountUid, String user_id)
	{
		AmwayAccountModel account = amwayAccountService.findAccount(accountUid);

		CustomerData customerData = new CustomerData();
		for (CustomerModel customer : account.getParties())
		{
			if (customer.getCustomerID().equals(user_id))
			{
				getAmwayCustomerPopulator().populate(customer, customerData);
				refreshAccountForPOS(account, customer);
				//Account must be loaded again due to refresh from Magic DMS... will now have latest
				account = amwayAccountService.findAccount(accountUid);
				break;
			}
		}

		AmwayAccountData accountData = getAmwayAccountConverter().convert(account);
		accountData.setPrimaryParty(customerData);
		return accountData;
	}

	public AmwayAccountDataList lookupAccountsByName(String searchKey)
	{

		final List<AmwayAccountModel> accounts = amwayAccountService.lookupAccountsByName(searchKey);
		return commonAccountResult(accounts);

	}

	public AmwayAccountDataList lookupAccountsById(String searchKey)
	{

		final List<AmwayAccountModel> accounts = amwayAccountService.lookupAccountsById(searchKey);
		return commonAccountResult(accounts);

	}

	public AmwayAccountDataList lookupAccountsCustomersByPartyId(String searchKey)
	{
		final List<CustomerModel> parties = amwayAccountService.lookupAccountsCustomersByPartyId(searchKey);
		return customerToCommonAccountResult(parties);
	}


	public AmwayAccountDataList lookupAccountsCustomersByUid(String searchKey)
	{
		final List<CustomerModel> parties = amwayAccountService.lookupAccountsCustomersByUid(searchKey);
		return customerToCommonAccountResult(parties);
	}

	public AmwayAccountDataList lookupAccountsCustomersByPartyName(String searchKey)
	{
		final List<CustomerModel> parties = amwayAccountService.lookupAccountsCustomersByPartyName(searchKey);
		return customerToCommonAccountResult(parties);
	}

	public AmwayAccountDataList lookupAccountsCustomersByEmail(String searchKey)
	{
		final List<CustomerModel> parties = amwayAccountService.lookupAccountsCustomersByEmail(searchKey);
		return customerToCommonAccountResult(parties);
	}

	/**
	 * load Account and Customer data from DMS
	 *
	 * @param account
	 * @param customer
	 */
	private void refreshAccountForPOS(AmwayAccountModel account, CustomerModel customer)
	{

		try
		{
			if (account != null)
			{
				amwayAccountCommerceService.setCurrentAccount(customer);
				//No session for profile data with POS
				amwayAccountCommerceService.saveLoggedInCustomerInfo(magicSessionDataHelper.getAccountDetails(true),
						magicSessionDataHelper.getLosAccountsDetails(true), customer);

			}
		}
		catch (Exception exc)
		{
			LOG.error("Error refreshing amway account", exc);
		}

	}

	/**
	 * @param accounts
	 * @return
	 */
	private AmwayAccountDataList commonAccountResult(List<AmwayAccountModel> accounts)
	{

		//Setup return data object
		AmwayAccountDataList amwayAccountDataList = new AmwayAccountDataList();
		amwayAccountDataList.setAccounts(new ArrayList<AmwayAccountData>());

		for (AmwayAccountModel accountModel : accounts)
		{
			//Send account to data populator
			AmwayAccountData accountData = getAmwayAccountConverter().convert(accountModel);
			//Load up parties for the account
			HashSet<CustomerData> customerSet = new HashSet<CustomerData>();
			for (CustomerModel customerModel : accountModel.getParties())
			{
				CustomerData customerData = new CustomerData();
				getAmwayCustomerPopulator().populate(customerModel, customerData);
				customerSet.add(customerData);
			}
			accountData.setParties(customerSet);
			//Add account to the list
			amwayAccountDataList.getAccounts().add(accountData);
		}
		return amwayAccountDataList;

	}

	/**
	 * @param parties
	 * @return
	 */
	private AmwayAccountDataList customerToCommonAccountResult(List<CustomerModel> parties)
	{

		//Setup return data object
		AmwayAccountDataList amwayAccountDataList = new AmwayAccountDataList();
		amwayAccountDataList.setAccounts(new ArrayList<AmwayAccountData>());
		for (CustomerModel customerModel : parties)
		{
			for (AmwayAccountModel accountModel : customerModel.getAccounts())
			{
				//Send account to data populator
				AmwayAccountData accountData = getAmwayAccountConverter().convert(accountModel);
				//Load up parties for the account
				HashSet<CustomerData> customerSet = new HashSet<CustomerData>();
				CustomerData customerData = new CustomerData();
				getAmwayCustomerPopulator().populate(customerModel, customerData);
				customerSet.add(customerData);
				accountData.setParties(customerSet);
				//Add account to the list
				amwayAccountDataList.getAccounts().add(accountData);
			}
		}
		return amwayAccountDataList;

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
	public void setAmwayAccountCommerceService(AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	/**
	 * @return amwayAccountConverter
	 */
	public Converter<AmwayAccountModel, AmwayAccountData> getAmwayAccountConverter()
	{
		return amwayAccountConverter;
	}

	/**
	 * @param amwayAccountConverter
	 */
	public void setAmwayAccountConverter(Converter<AmwayAccountModel, AmwayAccountData> amwayAccountConverter)
	{
		this.amwayAccountConverter = amwayAccountConverter;
	}

	public Populator<CustomerModel, CustomerData> getAmwayCustomerPopulator()
	{
		return amwayCustomerPopulator;
	}

	public void setAmwayCustomerPopulator(Populator<CustomerModel, CustomerData> amwayCustomerPopulator)
	{
		this.amwayCustomerPopulator = amwayCustomerPopulator;
	}

	public AmwayAccountService getAmwayAccountService()
	{
		return amwayAccountService;
	}

	public void setAmwayAccountService(AmwayAccountService amwayAccountService)
	{
		this.amwayAccountService = amwayAccountService;
	}

	public MagicSessionDataHelper getMagicSessionDataHelper()
	{
		return magicSessionDataHelper;
	}

	public void setMagicSessionDataHelper(MagicSessionDataHelper magicSessionDataHelper)
	{
		this.magicSessionDataHelper = magicSessionDataHelper;
	}
}
