/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.SubscriptionDataResponse;
import com.amway.core.subscription.service.SubscriptionDecision;
import com.amway.core.subscription.service.SubscriptionErrorCode;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Populator implementation for {@link ReturnInfoService} as source and {@link SubscriptionDataResponse} as target type.
 */
public class DmsUpdateSubscriptionOutputPopulator
		implements Populator<ReturnInfoService, SubscriptionDataResponse<SubscriptionDecision, SubscriptionErrorCode>>
{

	@Override
	public void populate(final ReturnInfoService source,
			final SubscriptionDataResponse<SubscriptionDecision, SubscriptionErrorCode> target) throws ConversionException
	{
		if (source.getReturnCd() == 1)
		{
			target.setDecision(SubscriptionDecision.SUCCESS);
		}
		else
		{
			target.setDecision(SubscriptionDecision.UNKNOWN);
		}
		target.setReturnCd(source.getReturnCd());
		target.setReturnMessage(source.getReturnMessage());
	}

}
