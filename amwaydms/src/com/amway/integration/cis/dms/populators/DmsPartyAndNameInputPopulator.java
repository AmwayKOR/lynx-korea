package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.party.data.AccountInfoData;
import com.amway.integration.dms.services.PartyNameDetailsInput;



/**
 * Populator implementation for {@link AccountInfoData} as source and {@link PartyNameDetailsInput} as target type.
 */
public class DmsPartyAndNameInputPopulator implements Populator<AccountInfoData, PartyNameDetailsInput>
{
	@Override
	public void populate(final AccountInfoData source, final PartyNameDetailsInput target) throws ConversionException
	{
		target.setAffNo(source.getAffiliateNumber());
	}
}
