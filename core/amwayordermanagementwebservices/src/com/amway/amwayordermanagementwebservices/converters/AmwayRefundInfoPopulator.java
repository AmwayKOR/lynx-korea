package com.amway.amwayordermanagementwebservices.converters;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.ordermanagementfacade.returns.data.RefundInfoData;
import de.hybris.platform.ordermanagementfacade.returns.data.RefundMethodData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.amway.core.model.RefundInfoModel;
import com.amway.core.model.RefundMethodModel;



public class AmwayRefundInfoPopulator implements Populator<RefundInfoModel, RefundInfoData>
{

	private Converter<RefundMethodModel, RefundMethodData> amwayRefundMethodConverter;

	@Override
	public void populate(RefundInfoModel source, RefundInfoData target) throws ConversionException
	{
		if (source.getActualRefundMethod() != null)
		{
			target.setActualRefundMethod(amwayRefundMethodConverter.convert(source.getActualRefundMethod()));
		}
		if (source.getRequestedRefundMethod() != null)
		{
			target.setRequestedRefundMethod(amwayRefundMethodConverter.convert(source.getRequestedRefundMethod()));
		}
		target.setValue(source.getValue());
	}

	public Converter<RefundMethodModel, RefundMethodData> getAmwayRefundMethodConverter()
	{
		return amwayRefundMethodConverter;
	}

	public void setAmwayRefundMethodConverter(Converter<RefundMethodModel, RefundMethodData> amwayRefundMethodConverter)
	{
		this.amwayRefundMethodConverter = amwayRefundMethodConverter;
	}
}
