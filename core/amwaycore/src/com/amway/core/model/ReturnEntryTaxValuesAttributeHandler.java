package com.amway.core.model;

import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.TaxValue;

import java.util.Collection;


public class ReturnEntryTaxValuesAttributeHandler
		implements DynamicAttributeHandler<Collection<TaxValue>, ReturnEntryModel>
{
	@Override
	public Collection<TaxValue> get(final ReturnEntryModel model)
	{
		String values = model.getTaxValuesInternal();
		return TaxValue.parseTaxValueCollection(values);
	}

	@Override
	public void set(ReturnEntryModel model, Collection<TaxValue> discountValues)
	{
		final String convertedValues = TaxValue.toString(discountValues);
		model.setTaxValuesInternal(convertedValues);
	}
}
