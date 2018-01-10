package com.amway.amwayordermanagementwebservices.converters;

import de.hybris.platform.commercefacades.order.data.TaxValueData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.ordermanagementfacade.returns.data.ReturnEntryData;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.util.TaxValue;

import java.math.BigDecimal;
import java.util.stream.Collectors;


public class AmwayReturnEntryPopulator implements Populator<ReturnEntryModel, ReturnEntryData>
{

	private Converter<TaxValue, TaxValueData> taxValueConverter;

	@Override
	public void populate(ReturnEntryModel source, ReturnEntryData target) throws ConversionException
	{
		if (source.getOrderEntry() != null)
		{
			int orderEntryNumber = source.getOrderEntry().getEntryNumber() == null ? 0 : source.getOrderEntry().getEntryNumber();
			target.setEntryNumber(orderEntryNumber);
		}
		target.setSaleableQuantity(source.getSaleableQuantity());
		target.setReturnReason(source.getReturnReason() == null ? null : source.getReturnReason().getCode());
		if (source.getSerialNumbers() != null)
		{
			target.setSerialNumbers(source.getSerialNumbers().stream().map(sn -> sn.getSerialNumber()).collect(Collectors.toList()));
		}
		if (source instanceof RefundEntryModel)
		{
			RefundEntryModel refundEntry = (RefundEntryModel) source;
			PriceData refundPriceData = new PriceData();
			refundPriceData.setCurrencyIso(refundEntry.getCurrency().getIsocode());
			refundPriceData.setValue(refundEntry.getAmount());
			target.setRefundAmount(refundPriceData);
		}
		target.setNotes(source.getNotes());
		target.setBusinessVolume(source.getBusinessVolume());
		target.setPointValue(source.getPointValue());
		target.setTaxValues(taxValueConverter.convertAll(source.getTaxValues()));
		double priceWithTaxValue = source.getPriceWithTax();
		PriceData priceWithTax = new PriceData();
		priceWithTax.setValue(BigDecimal.valueOf(priceWithTaxValue));
		if (source.getReturnRequest() != null && source.getReturnRequest().getCurrency() != null)
		{
			priceWithTax.setCurrencyIso(source.getReturnRequest().getCurrency().getIsocode());
		}
		target.setPriceWithTax(priceWithTax);
	}

	public Converter<TaxValue, TaxValueData> getTaxValueConverter()
	{
		return taxValueConverter;
	}

	public void setTaxValueConverter(Converter<TaxValue, TaxValueData> taxValueConverter)
	{
		this.taxValueConverter = taxValueConverter;
	}
}
