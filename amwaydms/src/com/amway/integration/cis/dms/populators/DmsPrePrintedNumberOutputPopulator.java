package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PrePrintedNumberResultData;
import com.amway.integration.dms.services.ValidatePrePrntdNmbrResponse;


/**
 * Populator implementation for {@link ValidatePrePrntdNmbrResponse} as source and {@link PrePrintedNumberResultData} as
 * target type.
 */
public class DmsPrePrintedNumberOutputPopulator implements Populator<ValidatePrePrntdNmbrResponse, PrePrintedNumberResultData>
{

	@Override
	public void populate(final ValidatePrePrntdNmbrResponse source, final PrePrintedNumberResultData target)
			throws ConversionException
	{
		target.setReturnCd(source.getReturnCd());
		target.setReturnMessage(source.getReturnMessage());
		target.setPrePrintedNum(source.getPrePrintedNum());
		target.setServiceItemCode(source.getServiceItemId());
	}

}
