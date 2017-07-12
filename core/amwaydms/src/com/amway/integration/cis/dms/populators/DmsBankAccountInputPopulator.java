/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.BankAccountRequestData;
import com.amway.integration.dms.services.BankAccountDetailRequest;


/**
 * Populator implementation for {@link BankAccountRequestData} as source and {@link BankAccountDetailRequest} as target
 * type.
 */
public class DmsBankAccountInputPopulator extends AbstractDmsPopulator
		implements Populator<BankAccountRequestData, BankAccountDetailRequest>
{

	@Override
	public void populate(final BankAccountRequestData source, final BankAccountDetailRequest target) throws ConversionException
	{
		if (source != null)
		{
			target.setAboNum(source.getAboNum());
			target.setSalesPlanAff(source.getSalesPlanAff());
			target.setPartyId(source.getPartyId());
			target.setBankAcctUseCode(source.getBankAcctUseCode());
		}

	}
}
