/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.dms.data.RegistrationResultData;
import com.amway.core.register.services.RegistrationProcessDecision;
import com.amway.integration.dms.services.CustomerRegistrationOutput;
import com.amway.integration.dms.services.Message;
import com.amway.integration.dms.services.MessageDetails;
import com.amway.integration.dms.services.NewABOPartyInfo;



/**
 * Populator implementation for {@link CustomerRegistrationOutput} as source and {@link RegistrationResultData} as
 * target type.
 */
public class DmsCustomerRegistrationOutputReversePopulator
		implements Populator<CustomerRegistrationOutput, RegistrationResultData<RegistrationProcessDecision>>
{

	@Override
	public void populate(final CustomerRegistrationOutput source, final RegistrationResultData<RegistrationProcessDecision> target)
			throws ConversionException
	{
		if (source.getReturnCd() == 1)
		{
			target.setDecision(RegistrationProcessDecision.ACCEPT);
			target.setAboNumber(source.getModuleId());
			for (final NewABOPartyInfo aboPartyInfo : source.getNewABOPartyList())
			{
				target.setPartyId(aboPartyInfo.getPartyId());
			}
		}
		else
		{
			target.setDecision(RegistrationProcessDecision.UNKNOWN);
		}
		if (CollectionUtils.isNotEmpty(source.getMessageDetails()))
		{
			final MessageDetails responseMessage = source.getMessageDetails().get(0);
			if (CollectionUtils.isNotEmpty(responseMessage.getMessage()))
			{
				final Message message = responseMessage.getMessage().get(0);
				target.setReturnMessage(message.getReturnMessage());
				target.setReturnCd(message.getReturnCd());
			}
		}
		else
		{
			target.setReturnMessage(source.getReturnMessage());
			target.setReturnCd(source.getReturnCd());
		}
	}
}
