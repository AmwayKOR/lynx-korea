/**
 *
 */
package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.OrderHistoryPopulator;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * @author Aaron Yong
 *
 */
public class AmwayApacOrderHistoryPopulator extends OrderHistoryPopulator
{
	private EnumerationService enumerationService;
	private PriceDataFactory priceDataFactory;
	private Converter<AbstractOrderEntryModel, OrderEntryData> orderEntryConverter;

	@Override
	public void populate(final OrderModel source, final OrderHistoryData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setGuid(source.getGuid());
		target.setPlaced(source.getDate());
		target.setStatus(source.getStatus());
		target.setStatusDisplay(source.getStatusDisplay());
		target.setItemQuantity(source.getEntries().size());
		if (source.getTotalPrice() != null)
		{
			BigDecimal totalPrice = BigDecimal.valueOf(source.getTotalPrice().doubleValue());
			if (Boolean.TRUE.equals(source.getNet()))
			{
				totalPrice = totalPrice.add(BigDecimal.valueOf(source.getTotalTax().doubleValue()));
			}
			target.setTotal(getPriceDataFactory().create(PriceDataType.BUY, totalPrice, source.getCurrency()));
		}
		//target.setOrderEntries(getOrderEntryConverter().convertAll(source.getEntries()));
	}

	protected Converter<AbstractOrderEntryModel, OrderEntryData> getOrderEntryConverter()
	{
		return this.orderEntryConverter;
	}

	@Required
	public void setOrderEntryConverter(final Converter<AbstractOrderEntryModel, OrderEntryData> converter)
	{
		this.orderEntryConverter = converter;
	}

	@Override
	protected EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	@Override
	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	@Override
	protected PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Override
	@Required
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}

}
