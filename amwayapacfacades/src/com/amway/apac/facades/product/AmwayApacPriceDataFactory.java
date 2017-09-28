package com.amway.apac.facades.product;

import com.amway.facades.product.impl.AmwayPriceDataFactory;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.europe1.model.PriceRowModel;

/**
 * Created by MY020221 on 9/28/2017.
 */
public interface AmwayApacPriceDataFactory extends PriceDataFactory{
    PriceData create(PriceRowModel priceRowModel);
}
