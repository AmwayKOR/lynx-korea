package com.amway.apac.core.account.service;

import java.util.List;

import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.model.AmwayAccountModel;


/**
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
	 */
	List<AmwayAccountModel> getAmwayAccount(final String aboId, final String affiliateCountryCode);

	/**
	 * Returns {@link AccountClassificationEnum} as classification for given amwayAccount
	 * 
	 * @param amwayAccount
	 * @return
	 */
	AccountClassificationEnum getClassificationForAccount(final AmwayAccountModel amwayAccount);
}
