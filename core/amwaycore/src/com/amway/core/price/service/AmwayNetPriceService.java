/**
 *
 */
package com.amway.core.price.service;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.util.PriceValue;


/**
 * Service for Amway Net Price.
 */
public interface AmwayNetPriceService extends PriceService
{
	/**
	 * Method to get ABOBasePrice of OrderEntry
	 *
	 * @param entry
	 * @return PriceValue
	 * @throws CalculationException
	 */
	PriceValue findABOBasePrice(final AbstractOrderEntryModel entry) throws CalculationException;

	/**
	 * Method to get RetailBasePrice of OrderEntry
	 *
	 * @param entry
	 * @return PriceValue
	 * @throws CalculationException
	 */
	PriceValue findRetailBasePrice(final AbstractOrderEntryModel entry) throws CalculationException;
}
