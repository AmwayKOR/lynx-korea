package com.amway.facades.amwayaccount.populators;

import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import com.amway.facades.data.AmwayAccountData;
import de.hybris.platform.converters.Populator;


/**
 * Populator for Amway Account.
 */
public class AmwayAccountPopulator implements Populator<AmwayAccountModel, AmwayAccountData>
{
	/**
	 * To populate the data from source to target.
	 *
	 * @param source
	 * @param target
	 */
	@Override
	public void populate(AmwayAccountModel source, AmwayAccountData target)
	{
		String businessNature;
		target.setCode(source.getCode());
		target.setName(source.getName());
		AmwayBusinessNature nature = source.getBusinessNature();
		if (AmwayBusinessNature.AMWAYBUSINESSNATURE_1.equals(nature))
		{
			businessNature = "AMWAY BUSINESS OWNER";
		}
		else if (AmwayBusinessNature.AMWAYBUSINESSNATURE_4.equals(nature))
		{
			businessNature = "CLIENT";
		}
		else
		{
			businessNature = "EMPLOYEE";
		}
		target.setAccountType(businessNature);
	}
}
