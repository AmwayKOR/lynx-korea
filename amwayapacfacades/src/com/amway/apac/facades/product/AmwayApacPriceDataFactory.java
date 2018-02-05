package com.amway.apac.facades.product;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.europe1.model.PriceRowModel;


/**
 * Overriding {@link PriceDataFactory} to add new functionalities.
 *
 * @author Govind
 */
public interface AmwayApacPriceDataFactory extends PriceDataFactory
{
	/**
	 * Creates the {@link PriceData} for the price row given.
	 *
	 * @param priceRow
	 *           price row to be converted to data
	 * @return price data
	 * @throws IllegalArgumentException
	 *            if priceRow parameter is null
	 */
	PriceData create(final PriceRowModel priceRow);
}
