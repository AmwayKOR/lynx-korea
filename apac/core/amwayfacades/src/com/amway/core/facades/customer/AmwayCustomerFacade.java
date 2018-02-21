package com.amway.core.facades.customer;

import com.amway.facades.data.AmwayAccountData;
import com.amway.facades.data.AmwayAccountDataList;
import de.hybris.platform.commercefacades.customer.CustomerFacade;


/**
 * Interface for amway customer
 */
public interface AmwayCustomerFacade extends CustomerFacade
{
	/**
	 * To get the current account
	 *
	 * @return AmwayAccountData
	 */
	public AmwayAccountData getCurrentAccount();

	/**
	 * Get account and default customer to the primary party
	 *
	 * @param accountId
	 * @return
	 */
	public AmwayAccountData loadAccountCustomerForPOS(String accountId, String user_id);

	/**
	 * Lookup up account by name and return accounts with parties
	 *
	 * @param searchKey
	 * @return
	 */
	public AmwayAccountDataList lookupAccountsByName(String searchKey);

	/**
	 * Lookup up account by Id and return accounts with parties
	 *
	 * @param searchKey
	 * @return
	 */
	public AmwayAccountDataList lookupAccountsById(String searchKey);

	/**
	 * Look up account and party by party id or party id fragment
	 *
	 * @return AmwayAccountDataList
	 * @uid Party id fragment account/customer sorted by party id
	 */
	public AmwayAccountDataList lookupAccountsCustomersByPartyId(String searchKey);

	/**
	 * Look up account by id or id fragment
	 *
	 * @return the AmwayAccountDataList
	 * @uid Id fragment account sorted by account Id
	 */
	public AmwayAccountDataList lookupAccountsCustomersByUid(String searchKey);

	/**
	 * Look up account by id or id fragment
	 *
	 * @return the AmwayAccountDataList
	 * @uid Id fragment account order by Id
	 */
	public AmwayAccountDataList lookupAccountsCustomersByPartyName(String searchKey);

	/**
	 * Look up customer by email or fragment
	 *
	 * @return the AmwayAccountDataList
	 * @uid Id fragment account order by Id
	 */
	public AmwayAccountDataList lookupAccountsCustomersByEmail(String searchKey);
	
	
	/**
	 * search the account on account number or name or a fragment
	 *
	 * @return the AmwayAccountDataList
	 */
	public AmwayAccountDataList lookupAccountsByUidOrName(String searchKey);

}
