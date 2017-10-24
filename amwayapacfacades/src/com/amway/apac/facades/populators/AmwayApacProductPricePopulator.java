package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.util.Config;

import java.util.Collection;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import com.amway.facades.populators.AmwayProductPricePopulator;


/**
 * Created by MY020221 on 9/28/2017.
 */
public class AmwayApacProductPricePopulator extends AmwayProductPricePopulator
{
	protected static final String RETAIL_PRICE_GROUP = "amwayapaccore.defaultRetailPriceGroup";

	private AmwayApacPriceDataFactory amwayApacPriceDataFactory;

	public AmwayApacPriceDataFactory getAmwayApacPriceDataFactory()
	{
		return amwayApacPriceDataFactory;
	}

	public void setAmwayApacPriceDataFactory(final AmwayApacPriceDataFactory amwayApacPriceDataFactory)
	{
		this.amwayApacPriceDataFactory = amwayApacPriceDataFactory;
	}

	protected PriceRowModel getPriceRow(final ProductModel product, final String group)
	{
		final Collection<PriceRowModel> prices = product.getEurope1Prices();
		final PriceRowModel priceRow = prices.stream().filter(p -> group.equalsIgnoreCase(p.getUg().getCode())).findFirst().get();
		return priceRow;
	}

	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		final PriceRowModel priceRow = getPriceRow(productModel, Config.getParameter(DISTRIBUTOR_PRICE_GROUP));
		if (priceRow != null)
		{
			final PriceData priceData = amwayApacPriceDataFactory.create(priceRow);
			productData.setPrice(priceData);
		}

		final PriceRowModel retailPriceRow = getPriceRow(productModel, Config.getParameter(RETAIL_PRICE_GROUP));
		if (retailPriceRow != null)
		{
			final PriceData retailPriceData = amwayApacPriceDataFactory.create(retailPriceRow);
			productData.setRetailPrice(retailPriceData);
		}
	}
}
