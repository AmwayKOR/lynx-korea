package com.amway.amwayordermanagementwebservices.converters;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.ordermanagementfacade.returns.data.RefundMethodData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.model.RefundMethodModel;


public class AmwayRefundMethodConverter implements Populator<RefundMethodModel, RefundMethodData>
{
	@Override
	public void populate(RefundMethodModel source, RefundMethodData target) throws ConversionException
	{
		target.setAccountNumber(source.getAccountNumber());
		target.setMode(source.getMode() == null ? null : source.getMode().getCode());
		target.setTransactionId(source.getTransactionId());
	}
}
