/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.RegistrationResultData;
import com.amway.core.register.services.RegistrationProcessDecision;
import com.amway.integration.dms.services.ReturnInfoService;



/**
 * Populator implementation for {@link ReturnInfoService} as source and {@link RegistrationResultData} as target type.
 */
public class DmsReturnInfoOutputPopulator
		implements Populator<ReturnInfoService, RegistrationResultData<RegistrationProcessDecision>>
{

	@Override
	public void populate(final ReturnInfoService source, final RegistrationResultData<RegistrationProcessDecision> target)
			throws ConversionException
	{
		target.setReturnMessage(source.getReturnMessage());
		if (source.getReturnCd() == 1)
		{
			target.setDecision(RegistrationProcessDecision.ACCEPT);
		}
		else
		{
			target.setDecision(RegistrationProcessDecision.UNKNOWN);
		}
	}

}
