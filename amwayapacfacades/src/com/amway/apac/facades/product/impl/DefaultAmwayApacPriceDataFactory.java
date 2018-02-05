package com.amway.apac.facades.product.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.PRICE_ROW_STRING;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.math.BigDecimal;

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
	 * {@inheritDoc}
	 */
	@Override
	public PriceData create(final PriceRowModel priceRow)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(PRICE_ROW_STRING, priceRow);

		final PriceData priceData = createPriceData();

		priceData.setValue(BigDecimal.valueOf(priceRow.getPrice().doubleValue()));
		priceData.setCurrencyIso(priceRow.getCurrency().getIsocode());
		priceData.setFormattedValue(formatPrice(priceData.getValue(), priceRow.getCurrency()));

		final AmwayValueData amwayValueData = createAmwayValueData(priceRow.getPointValue(), priceRow.getBusinessVolume());
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
		final AmwayValueData amwayValueData = new AmwayValueData();
		amwayValueData.setPointValue(pointValue);
		amwayValueData.setBusinessVolume(businessVolume);
		return amwayValueData;
	}

}
