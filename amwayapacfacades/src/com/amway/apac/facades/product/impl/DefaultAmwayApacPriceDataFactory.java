package com.amway.apac.facades.product.impl;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.europe1.model.PriceRowModel;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import com.amway.facades.order.data.AmwayValueData;
import com.amway.facades.product.impl.AmwayPriceDataFactory;


/**
 * Overriding {@link AmwayPriceDataFactory} for APAC.
 *
 * @author Govind
 */
public class DefaultAmwayApacPriceDataFactory extends AmwayPriceDataFactory implements AmwayApacPriceDataFactory
{
	/**
	 * Error message when the price row model is empty.
	 */
	private static final String PARAMETER_PRICE_ROW_MODEL_CANNOT_BE_NULL = "Parameter priceRowModel cannot be null.";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PriceData create(final PriceRowModel priceRowModel)
	{
		Assert.notNull(priceRowModel, PARAMETER_PRICE_ROW_MODEL_CANNOT_BE_NULL);

		final PriceData priceData = createPriceData();

		priceData.setValue(BigDecimal.valueOf(priceRowModel.getPrice().doubleValue()));
		priceData.setCurrencyIso(priceRowModel.getCurrency().getIsocode());
		priceData.setFormattedValue(formatPrice(priceData.getValue(), priceRowModel.getCurrency()));

		final AmwayValueData amwayValueData = createAmwayValueData(priceRowModel.getPointValue(),
				priceRowModel.getBusinessVolume());
		priceData.setAmwayValue(amwayValueData);

		return priceData;
	}

	/**
	 * creates the amway value data for the pv and bv values given
	 * 
	 * @param pointValue
	 *           This is the Point Value
	 * @param businessVolume
	 *           This is the Business Volume
	 * @return AmwayVolumeData This is the combination of Point Value and Business Volume
	 */
	protected AmwayValueData createAmwayValueData(final double pointValue, final double businessVolume)
	{
		final AmwayValueData amwayValueData = createAmwayValueData();
		amwayValueData.setPointValue(pointValue);
		amwayValueData.setBusinessVolume(businessVolume);
		return amwayValueData;
	}

	protected AmwayValueData createAmwayValueData()
	{
		return new AmwayValueData();
	}
}
