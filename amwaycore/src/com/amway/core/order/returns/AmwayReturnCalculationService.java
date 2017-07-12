/**
 *
 */
package com.amway.core.order.returns;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.promotions.model.PromotionResultModel;


/**
 * The calculation service is used to do all the calculation related to returns process
 */
public interface AmwayReturnCalculationService
{
	/**
	 * evaluate unit return value of each line
	 *
	 * @param order
	 */
	void calculateReturnPrice(OrderModel order);

	/**
	 * This method will be used to set initial return price (Mostly will be base price of product) excluding promotional
	 * discounts. We are keeping the logic for promotions through out same so we will be setting return price on each
	 * entry irrespective of promotion applied or not
	 *
	 * @param order OrderModel
	 */
	void setUnitReturnPriceForAllEntries(OrderModel order);

	/**
	 * This method is used to calculate unit discount price for all consumed OrderEntries in order level Promotions and
	 * update order accordingly
	 *
	 * @param order OrderModel
	 */
	void calculateUnitReturnPricePerEntry(OrderModel order);

	/**
	 * This method will be used to evaluate unit discount price for product level promotions and update Order Entry
	 * accordingly
	 *
	 * @param promotion
	 */
	void calculateUnitReturnPriceForEntry(final PromotionResultModel promotion);
}
