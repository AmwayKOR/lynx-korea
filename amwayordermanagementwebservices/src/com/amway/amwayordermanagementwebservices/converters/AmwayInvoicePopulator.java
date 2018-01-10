package com.amway.amwayordermanagementwebservices.converters;

import de.hybris.platform.commercefacades.order.data.InvoiceInfoData;
import de.hybris.platform.commercefacades.order.data.InvoiceInfoEntryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Locale;
import java.util.stream.Collectors;

import com.amway.model.AmwayInvoiceModel;

/**
 * Invoice populator.
 */
public class AmwayInvoicePopulator implements Populator<AmwayInvoiceModel, InvoiceInfoData>
{
	private EnumerationService enumerationService;

	@Override
	public void populate(AmwayInvoiceModel source, InvoiceInfoData target) throws ConversionException
	{
		if (source.getType() != null)
		{
			target.setType(enumerationService.getEnumerationName(source.getType(), Locale.ENGLISH));
		}
		target.setDate(source.getDate());
		target.setCountry(source.getCountry().getIsocode());
		target.setNumber(source.getNumber());
		if (source.getInvoiceEntries() != null)
		{
			target.setInvoicedEntries(source.getInvoiceEntries().stream().map(e -> {
				Integer num = e.getOrderEntry().getEntryNumber();
				InvoiceInfoEntryData res = new InvoiceInfoEntryData();
				res.setOrderEntryNumber(num);
				return res;
			}).collect(Collectors.toList()));
		}
	}

	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	public void setEnumerationService(EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
