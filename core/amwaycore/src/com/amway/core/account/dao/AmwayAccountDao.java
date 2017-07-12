/**
 *
 */
package com.amway.core.account.dao;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;
import com.amway.core.model.AmwayUserIdentityModel;


/**
 * Data access to {@link com.amway.core.model.AmwayAccountModel}
 */
public interface AmwayAccountDao
{
	/**
	 * find Account for the given uid
	 *
	 * @param uid
	 * @return the AmwayAccountModel
	 * @uid uid of the account
	 */
	List<AmwayAccountModel> getAccount(String uid);


	/**
	 * @param searchKey
	 * @return the AmwayAccountModel
	 * @uid uid of name of the account order by uid
	 */
	List<AmwayAccountModel> getAccountsForUidOrName(String searchKey);


	AmwayBusinessRestrictionModel getBusinessRestrictionFromCode(String accountCode);

	/**
	 * Look up account by name or name fragment
	 *
	 * @return the AmwayAccountModel
	 * @uid Name fragment account order by name
	 */
	List<AmwayAccountModel> lookupAccountsByName(String searchKey);


	/**
	 * Look up account by id or id fragment
	 *
	 * @return the AmwayAccountModel
	 * @uid id fragment account order by id
	 */
	public List<AmwayAccountModel> lookupAccountsById(final String searchKey);

	/**
	 * Look up account and party by party id or party id fragment
	 *
	 * @return List of CustomerModels
	 * @uid Party id fragment account/customer sorted by party id
	 */
	List<CustomerModel> lookupAccountsCustomersByPartyId(String searchKey);

	/**
	 * Look up account by id or id fragment
	 *
	 * @return the AmwayAccountModel
	 * @uid Id fragment account sorted by account Id
	 */
	List<CustomerModel> lookupAccountsCustomersByUid(String searchKey);

	/**
	 * Look up account by id or id fragment
	 *
	 * @return the AmwayAccountModel
	 * @uid Id fragment account order by Id
	 */
	List<CustomerModel> lookupAccountsCustomersByPartyName(String searchKey);

	/**
	 * Look up customer by email
	 *
	 * @return the CustomerModel
	 */
	List<CustomerModel> lookupAccountsCustomersByEmail(String searchKey);

	/**
	 * @param userIdentities
	 * @return CustomerModel
	 */
	List<CustomerModel> getCustomerForAmwayUserIdentity(final AmwayUserIdentityModel userIdentities);

}
