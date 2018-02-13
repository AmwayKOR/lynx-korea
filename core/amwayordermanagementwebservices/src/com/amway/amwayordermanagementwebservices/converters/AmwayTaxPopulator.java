package com.amway.amwayordermanagementwebservices.converters;

import de.hybris.platform.commercefacades.order.data.TaxValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.util.TaxValue;

import org.springframework.util.Assert;


/**
 * Populates tax values.
 */
public class AmwayTaxPopulator implements Populator<TaxValue, TaxValueData>
{
	@Override
	public void populate(TaxValue source, TaxValueData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		target.setCode(source.getCode());
		target.setValue(source.getValue());
		target.setAppliedValue(source.getAppliedValue());
		target.setAbsolute(source.isAbsolute());
		target.setCurrencyIsoCode(source.getCurrencyIsoCode());
	}
}
