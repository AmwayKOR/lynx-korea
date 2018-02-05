package com.amway.apac.core.account.services;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;


/**
 * Service layer for user account.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacAccountService extends AmwayAccountService
{
	/**
	 * Returns list of amway accounts with given aboId and Affiliate Country Code.
	 *
	 * @param aboId
	 * @param affiliateCountryCode
	 * @return List<AmwayAccountModel>
	 * @throws IllegalArgumentException
	 *            if aboId is null.
	 */
	List<AmwayAccountModel> getAmwayAccount(final String aboId, final String affiliateCountryCode);

	/**
	 * Returns {@link AccountClassificationEnum} as classification for given amwayAccount
	 *
	 * @param amwayAccount
	 * @return AccountClassificationEnum
	 */
	AccountClassificationEnum getClassificationForAccount(final AmwayAccountModel amwayAccount);

	/**
	 * Returns AmwayAccount for the given customer.
	 *
	 * @param customer
	 * @return AmwayAccount for the customer
	 */
	AmwayAccountModel getAmwayAccount(CustomerModel customer);

	/**
	 * Returns MOP restriction for the AmwayAccount.
	 *
	 * @param amwayAccount
	 * @return {@link AmwayBusinessRestrictionModel} for the amwayAccount.
	 */
	AmwayBusinessRestrictionModel getMOPRestriction(AmwayAccountModel amwayAccount);
}
