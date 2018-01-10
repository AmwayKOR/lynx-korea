package com.amway.amwayordermanagementwebservices.converters;

import de.hybris.platform.commercefacades.order.data.InvoiceInfoData;
import de.hybris.platform.commercefacades.order.data.TaxValueData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.ordermanagementfacade.returns.data.RefundInfoData;
import de.hybris.platform.ordermanagementfacade.returns.data.ReturnRequestData;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.util.TaxValue;

import java.math.BigDecimal;

import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBonusPeriodData;
import com.amway.core.model.AmwayBonusPeriodModel;
import com.amway.core.model.AmwayOrderPeriodData;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.model.RefundInfoModel;
import com.amway.facades.data.AmwayAccountData;
import com.amway.model.AmwayInvoiceModel;


public class AmwayOMSReturnPopulator implements Populator<ReturnRequestModel, ReturnRequestData>
{

	private Converter<AmwayAccountModel, AmwayAccountData> accountConverter;

	private Converter<AmwayInvoiceModel, InvoiceInfoData> amwayInvoiceConverter;

	private Converter<AmwayOrderPeriodModel, AmwayOrderPeriodData> orderPeriodConverter;

	private Converter<AmwayBonusPeriodModel, AmwayBonusPeriodData> bonusPeriodConverter;

	private Converter<RefundInfoModel, RefundInfoData> amwayRefundInfoConverter;

	private Converter<TaxValue, TaxValueData> taxValueConverter;

	@Override
	public void populate(ReturnRequestModel source, ReturnRequestData target) throws ConversionException
	{
		target.setFullReturn(source.isFullReturn());
		target.setDate(source.getCreationtime());
		target.setReturnDate(source.getReturnDate());
		target.setShippingRequired(source.isShippingRequired());
		target.setInvoiceRequired(source.isInvoiceRequired());
		if (source.getAccount() != null)
		{
			target.setReturnAbo(getAccountConverter().convert(source.getAccount()));
		}
		if (source.getShippingStatus() != null)
		{
			target.setShippingStatus(source.getShippingStatus().getCode());
		}
		if (source.getRefundStatus() != null)
		{
			target.setRefundStatus(source.getRefundStatus().getCode());
		}
		target.setExternalOrderCode(source.getExternalOrderCode());
		target.setInvoiceInfo(getAmwayInvoiceConverter().convertAll(source.getInvoiceInfo()));
		if (source.getReturnPeriod() != null)
		{
			target.setReturnPeriod(orderPeriodConverter.convert(source.getReturnPeriod()));
		}
		if (source.getReturnBonusPeriod() != null)
		{
			target.setReturnBonusPeriod(bonusPeriodConverter.convert(source.getReturnBonusPeriod()));
		}

		if (source.getRefundInfos() != null)
		{
			target.setRefundInfo(amwayRefundInfoConverter.convertAll(source.getRefundInfos()));
		}
		target.setBusinessVolume(source.getBusinessVolume());
		target.setPointValue(source.getPointValue());
		target.setTotalTaxValues(taxValueConverter.convertAll(source.getTotalTaxValues()));

		fillTotalPriceWithTax(source, target);
	}

	private void fillTotalPriceWithTax(ReturnRequestModel source, ReturnRequestData target)
	{
		PriceData totalPriceWithTax = new PriceData();
		BigDecimal totalPriceWithTaxValue = source.getReturnEntries().stream()//
				.map(e -> new BigDecimal((e.getPriceWithTax() * e.getExpectedQuantity())))//
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		totalPriceWithTax.setValue(totalPriceWithTaxValue);
		totalPriceWithTax.setCurrencyIso(source.getCurrency() == null ? null : source.getCurrency().getIsocode());
		target.setTotalPriceWithTax(totalPriceWithTax);
	}

	public Converter<AmwayAccountModel, AmwayAccountData> getAccountConverter()
	{
		return accountConverter;
	}

	public void setAccountConverter(Converter<AmwayAccountModel, AmwayAccountData> accountConverter)
	{
		this.accountConverter = accountConverter;
	}

	public Converter<AmwayInvoiceModel, InvoiceInfoData> getAmwayInvoiceConverter()
	{
		return amwayInvoiceConverter;
	}

	public void setAmwayInvoiceConverter(Converter<AmwayInvoiceModel, InvoiceInfoData> amwayInvoiceConverter)
	{
		this.amwayInvoiceConverter = amwayInvoiceConverter;
	}

	public Converter<AmwayOrderPeriodModel, AmwayOrderPeriodData> getOrderPeriodConverter()
	{
		return orderPeriodConverter;
	}

	public void setOrderPeriodConverter(Converter<AmwayOrderPeriodModel, AmwayOrderPeriodData> orderPeriodConverter)
	{
		this.orderPeriodConverter = orderPeriodConverter;
	}

	public Converter<AmwayBonusPeriodModel, AmwayBonusPeriodData> getBonusPeriodConverter()
	{
		return bonusPeriodConverter;
	}

	public void setBonusPeriodConverter(Converter<AmwayBonusPeriodModel, AmwayBonusPeriodData> bonusPeriodConverter)
	{
		this.bonusPeriodConverter = bonusPeriodConverter;
	}

	public Converter<RefundInfoModel, RefundInfoData> getAmwayRefundInfoConverter()
	{
		return amwayRefundInfoConverter;
	}

	public void setAmwayRefundInfoConverter(Converter<RefundInfoModel, RefundInfoData> amwayRefundInfoConverter)
	{
		this.amwayRefundInfoConverter = amwayRefundInfoConverter;
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
