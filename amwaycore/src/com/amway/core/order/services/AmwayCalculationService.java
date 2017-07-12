package com.amway.core.order.services;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.CalculationService;


/**
 * Calculation service.
 */
public interface AmwayCalculationService extends CalculationService
{
	/**
	 * To retrieve ABO & Retail prices for item
	 *
	 * @param abstractOrderEntryModel
	 */
	void setAboRetailBasePrices(AbstractOrderEntryModel abstractOrderEntryModel);

	/**
	 * To calculate the price of gift wrap.
	 *
	 * @param abstractOrderModel
	 */
	void calculateGiftWrapPrice(final AbstractOrderModel abstractOrderModel);
}
