package com.amway.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.model.returns.AmwayReturnRequestTypeModel;
import com.amway.core.returns.data.AmwayReturnRequestTypeData;


/**
 * Populates fields from return type model to data object.
 */
public class AmwayReturnRequestTypePopulator implements Populator<AmwayReturnRequestTypeModel, AmwayReturnRequestTypeData>
{
	@Override
	public void populate(AmwayReturnRequestTypeModel source, AmwayReturnRequestTypeData target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setLabelRequired(source.isLabelRequired());
		target.setNoShippingRequired(source.isNoShippingRequired());
	}
}
