package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PartyProfileAndNameInputRequestData;
import com.amway.integration.dms.services.PartyProfileAndNameInputRequest;


/**
 * Populator implementation for {@link PartyProfileAndNameInputRequestData} as source and
 * {@link PartyProfileAndNameInputRequest} as target type.
 */
public class DmsPartyProfileAndNameInputPopulator
		implements Populator<PartyProfileAndNameInputRequestData, PartyProfileAndNameInputRequest>
{


	@Override
	public void populate(final PartyProfileAndNameInputRequestData source, final PartyProfileAndNameInputRequest target)
			throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setPartyId(source.getPartyId());
		target.setPersonNametypeCd(source.getPersonNametypeCd());
		target.setPrimaryFlg(source.getPrimaryFlg());
		target.setSalesPlanAff(source.getSalesPlanAff());
	}

}
