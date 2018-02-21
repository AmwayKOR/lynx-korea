package com.amway.core.model;

import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.TaxValue;

import java.util.Collection;


public class ReturnRequestTotalTaxValuesAttributeHandler
		implements DynamicAttributeHandler<Collection<TaxValue>, ReturnRequestModel>
{
	@Override
	public Collection<TaxValue> get(final ReturnRequestModel model)
	{
		String values = model.getTotalTaxValuesInternal();
		return TaxValue.parseTaxValueCollection(values);
	}

	@Override
	public void set(ReturnRequestModel model, Collection<TaxValue> discountValues)
	{
		final String convertedValues = TaxValue.toString(discountValues);
		model.setTotalTaxValuesInternal(convertedValues);
	}
}
