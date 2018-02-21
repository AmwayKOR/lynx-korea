package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.integration.cis.los.pojo.LosOrderPeriodRequest;


/**
 * Input populator for Los order period.
 */
public class LosOrderPeriodInputPopulator implements Populator<AmwayOrderPeriodModel, LosOrderPeriodRequest>
{
	@Override
	public void populate(final AmwayOrderPeriodModel source, final LosOrderPeriodRequest target) throws ConversionException
	{
		if (source != null)
		{
			target.setAmwayCountryCode(source.getStore().getAffiliateNumber());
			target.setIsoCountryCode(source.getStore().getCmsSites().iterator().next().getDefaultCountry().getIsocode());
			target.setOrderPeriodCloseTimestamp(source.getCode());
			target.setRequestApp("Hybris");
			target.setRequestUser("system");
		}

	}
}
