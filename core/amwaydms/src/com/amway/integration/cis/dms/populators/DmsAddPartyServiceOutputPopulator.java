/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.integration.dms.services.AddPartyResponse;
import com.amway.core.register.services.RegistrationProcessDecision;
import com.amway.core.dms.data.RegistrationResultData;


/**
 * Populate the response for AddPartyService into hybris pojo's
 */
public class DmsAddPartyServiceOutputPopulator
		implements Populator<AddPartyResponse, RegistrationResultData<RegistrationProcessDecision>>
{

	@Override
	public void populate(final AddPartyResponse source, final RegistrationResultData<RegistrationProcessDecision> target)
			throws ConversionException
	{
		target.setReturnMessage(source.getReturnMessage());
		target.setReturnCd(source.getReturnCd());
		target.setDecision(RegistrationProcessDecision.UNKNOWN);
		if (source.getReturnCd() == 1)
		{
			target.setDecision(RegistrationProcessDecision.ACCEPT);
			target.setPartyId(source.getPartyId());
		}
	}

}
