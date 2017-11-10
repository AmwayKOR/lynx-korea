package com.amway.apac.facades.customer.impl;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.facades.customer.impl.DefaultAmwayCustomerFacade;


/**
 * Overriding {@link DefaultAmwayCustomerFacade} to update for APAC.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCustomerFacade extends DefaultAmwayCustomerFacade
{
	/**
	 * Overriding OOTB implementation to set the customer account and user price group in session.
	 */
	@Override
	public void loginSuccess()
	{
		super.loginSuccess();
		getAmwayAccountCommerceService().setCurrentAccount(getCurrentUser());
		getAmwayAccountCommerceService().saveLoggedInCustomerInfo(
				getAmwayAccountService().getAccountProfile(AmwaycoreConstants.AmwayProfileDetailLevels.FULLDETAIL), null);
	}

}
