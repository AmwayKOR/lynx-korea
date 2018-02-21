/**
 *
 */
package com.amway.core.account.restrictions.impl;

import de.hybris.platform.core.model.order.CartModel;

import java.util.Date;

import org.apache.log4j.Logger;

import com.amway.core.account.restrictions.BusinessRestriction;
import com.amway.core.cms.services.evaluator.impl.CheckServiceKitEvaluator;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.util.AmwayDateHelper;


/**
 * evaluate account resignation restriction
 */
public class AccountExpirationRestriction extends AbstractBusinessRestriction
{

	private static final Logger LOG = Logger.getLogger(AccountExpirationRestriction.class);
	private CheckServiceKitEvaluator lateRenewalKitEvaluator;

	/**
	 * {@link #evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 */
	@Override
	public boolean evaluate(final CartModel cartModel, final AmwayAccountModel account)
	{
		final Date currentDate = AmwayDateHelper.getTimeForSiteTimeZone();
		if (account != null && AmwayBusinessNature.AMWAYBUSINESSNATURE_1.equals(account.getBusinessNature())
				&& account.getExpiryDate() != null && account.getExpiryDate().before(currentDate))
		{
			LOG.debug("Checking lateRenewalServiceKit is present in the cart.");
			return getLateRenewalKitEvaluator().evaluate(cartModel);
		}
		return true;
	}

	/**
	 * @return the lateRenewalKitEvaluator
	 */
	public CheckServiceKitEvaluator getLateRenewalKitEvaluator()
	{
		return lateRenewalKitEvaluator;
	}

	/**
	 * @param lateRenewalKitEvaluator the lateRenewalKitEvaluator to set
	 */
	public void setLateRenewalKitEvaluator(final CheckServiceKitEvaluator lateRenewalKitEvaluator)
	{
		this.lateRenewalKitEvaluator = lateRenewalKitEvaluator;
	}

}
