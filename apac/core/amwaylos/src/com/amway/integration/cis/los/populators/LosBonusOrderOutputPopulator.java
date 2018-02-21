package com.amway.integration.cis.los.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.lang.StringUtils;

import com.amway.core.los.data.BonusOrderResultData;
import com.hybris.commons.rest.resources.HttpStatus;


/**
 * Output populator for Los bonus order
 */
public class LosBonusOrderOutputPopulator implements Populator<String, BonusOrderResultData>
{
	@Override
	public void populate(final String source, final BonusOrderResultData target) throws ConversionException
	{
		if (StringUtils.isNotBlank(source))
		{
			final int statusCode = Integer.valueOf(source).intValue();
			if (statusCode == (HttpStatus.NoContent.CODE) || statusCode == (HttpStatus.Created.CODE)
					|| statusCode == (HttpStatus.OK.CODE))
			{
				target.setReturnMessage(source);
				target.setDecision("ACCEPT");
			}
		}
		else
		{
			target.setReturnMessage(source);
			target.setDecision("REJECT");
		}
	}
}
