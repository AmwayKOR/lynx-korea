package com.amway.apac.facades.product.impl;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.impl.DefaultPriceDataFactory;
import de.hybris.platform.europe1.model.PriceRowModel;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Created by MY020221 on 9/28/2017.
 */
public class DefaultAmwayApacPriceDataFactory extends DefaultPriceDataFactory implements AmwayApacPriceDataFactory {
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

    protected PriceData createPriceData()
    {
        return new PriceData();
    }

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
