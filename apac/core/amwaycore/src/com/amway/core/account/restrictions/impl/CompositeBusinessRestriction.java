/**
 *
 */
package com.amway.core.account.restrictions.impl;

import com.amway.core.account.restrictions.BusinessRestriction;
import com.amway.core.account.restrictions.BusinessRestrictionList;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.platform.core.model.order.CartModel;

import java.util.List;

/**
 */
public class CompositeBusinessRestriction extends AbstractBusinessRestriction implements BusinessRestrictionList
{
	private List<BusinessRestriction> restrictions;

	/**
	 * {@link #evaluate(CartModel, AmwayAccountModel)}
	 */
	@Override
	public boolean evaluate(final CartModel cartModel, final AmwayAccountModel accountModel)
	{
		boolean bEval = true;

		if(getRestrictions() != null) {
			for(final BusinessRestriction restriction: getRestrictions()) {
				bEval = restriction.evaluate(cartModel, accountModel);
				if(!bEval) {
					break;
				}
			}
		}

		return bEval;
	}

	/**
	 * {@link #evaluate(AmwayAccountModel)}
	 */
	@Override
	public boolean evaluate(final AmwayAccountModel accountModel)
	{
		boolean bEval = true;

		if(getRestrictions() != null) {
			for(final BusinessRestriction restriction: getRestrictions()) {
				bEval = restriction.evaluate(accountModel);
				if(!bEval) {
					break;
				}
			}
		}

		return bEval;
	}

	@Override
	public List<BusinessRestriction> getRestrictions() {
		return restrictions;
	}

	@Override
	public void setRestrictions(List<BusinessRestriction> restrictions) {
		this.restrictions = restrictions;
	}
}
