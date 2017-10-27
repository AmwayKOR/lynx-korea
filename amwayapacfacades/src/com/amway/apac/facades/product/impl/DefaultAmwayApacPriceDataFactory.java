package com.amway.apac.facades.product.impl;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import com.amway.facades.order.data.AmwayValueData;
import com.amway.facades.product.impl.AmwayPriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.impl.DefaultPriceDataFactory;
import de.hybris.platform.europe1.model.PriceRowModel;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Created by MY020221 on 9/28/2017.
 *
 * Overriding create() method of AmwayPriceDataFactory for APAC.
 */
public class DefaultAmwayApacPriceDataFactory extends AmwayPriceDataFactory implements AmwayApacPriceDataFactory {
    /**
     *
     * @param priceRowModel This is the PriceRowModel
     * @return PriceData
     */
    @Override
    public PriceData create(PriceRowModel priceRowModel) {
        Assert.notNull(priceRowModel, "Parameter priceRowModel cannot be null.");

        final PriceData priceData = createPriceData();

        priceData.setValue(BigDecimal.valueOf(priceRowModel.getPrice()));
        priceData.setCurrencyIso(priceRowModel.getCurrency().getIsocode());
        priceData.setFormattedValue(formatPrice(priceData.getValue(), priceRowModel.getCurrency()));

        final AmwayValueData amwayValueData = createAmwayValueData(priceRowModel.getPointValue(), priceRowModel.getBusinessVolume());
        priceData.setAmwayValue(amwayValueData);

        return priceData;
    }

    /**
     *
     * @return PriceData
     */
    protected PriceData createPriceData()
    {
        return new PriceData();
    }

    /**
     *
     * @param pointValue This is the Point Value
     * @param businessVolume This is the Business Volume
     * @return AmwayVolumeData This is the combination of Point Value and Business Volume
     */
    protected AmwayValueData createAmwayValueData(double pointValue, double businessVolume) {
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
