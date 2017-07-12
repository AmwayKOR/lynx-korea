/**
 *
 */
package com.amway.core.account.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.List;

import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.model.AmwayAccountModel;



/**
 * Interface dedicated to find account for the given uid
 */
public interface AmwayAccountService
{
	/**
	 * find account for given uid
	 *
	 * @param uid
	 * @return
	 */
	AmwayAccountModel findAccount(String uid);

	/**
	 * Lookup account by name or name fragment
	 *
	 * @param searchKey
	 * @return
	 */
	List<AmwayAccountModel> lookupAccountsByName(String searchKey);

	/**
	 * Lookup account by id or id fragment
	 *
	 * @param searchKey
	 * @return
	 */
	List<AmwayAccountModel> lookupAccountsById(String searchKey);

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
	 * @return List of CustomerModels
	 * @uid Id fragment account sorted by account Id
	 */
	List<CustomerModel> lookupAccountsCustomersByUid(String searchKey);

	/**
	 * Look up account party name id or fragment
	 *
	 * @return List of CustomerModels
	 * @uid Id fragment account order by Id
	 */
	List<CustomerModel> lookupAccountsCustomersByPartyName(String searchKey);

	/**
	 * Look up customer by email or fragment
	 *
	 * @return List of CustomerModels
	 * @uid Id fragment account order by Id
	 */
	List<CustomerModel> lookupAccountsCustomersByEmail(String searchKey);


	/**
	 * find accounts abo for given searchKey
	 *
	 * @param searchKey
	 * @return AmwayAccountModel
	 */
	List<AmwayAccountModel> findAccountsForAbo(String searchKey);

	/**
	 * call for getAmwayProfile Service to get data based upon service mode {FullDetails} or {MinDetail}
	 *
	 * @param serviceMode
	 * @return AmwayProfileResponseData
	 */
	AmwayProfileResponseData getAccountProfile(final String serviceMode);

	/**
	 * get Account Profile information for ABO Account
	 *
	 * @param serviceMode
	 * @param abstractOrderModel
	 * @return AmwayProfileResponseData
	 */
	AmwayProfileResponseData getAccountProfileForOrder(final String serviceMode, AbstractOrderModel abstractOrderModel);

	/**
	 * get Customer Account profile for ABO Account
	 *
	 * @param serviceMode
	 * @param userModel
	 * @return AmwayProfileResponseData
	 */
	AmwayProfileResponseData getCustomerAccountProfile(final String serviceMode, final UserModel userModel);

}
