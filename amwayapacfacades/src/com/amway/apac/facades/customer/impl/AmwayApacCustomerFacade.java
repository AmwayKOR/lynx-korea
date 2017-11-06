/**
 *
 */
package com.amway.apac.facades.customer.impl;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.facades.customer.impl.DefaultAmwayCustomerFacade;


/**
 * @author Shubham Goyal
 */
public class AmwayApacCustomerFacade extends DefaultAmwayCustomerFacade
{


	@Override
	public void loginSuccess()
	{
		getAmwayAccountCommerceService().setCurrentAccount(getCurrentCustomerUid());
		getAmwayAccountCommerceService().saveLoggedInCustomerInfo(
				getAmwayAccountService().getAccountProfile(AmwaycoreConstants.AmwayProfileDetailLevels.FULLDETAIL), null);
		super.loginSuccess();
	}


}
