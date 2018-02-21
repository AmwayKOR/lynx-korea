package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.SubscriptionInputData;
import com.amway.integration.dms.services.SubscriptionInputRequest;


/**
 * Populator implementation for {@link SubscriptionInputData} as source and {@link SubscriptionInputRequest} as target
 * type
 */
public class DmsSubscriptionInputPopulator implements Populator<SubscriptionInputData, SubscriptionInputRequest>
{
	@Override
	public void populate(final SubscriptionInputData source, final SubscriptionInputRequest target) throws ConversionException
	{

		if (source.getAffNo() != null && source.getIboNo() != null)
		{
			target.setAboNum(source.getIboNo());
			target.setSalesPlanAff(source.getAffNo());
		}
	}
}
