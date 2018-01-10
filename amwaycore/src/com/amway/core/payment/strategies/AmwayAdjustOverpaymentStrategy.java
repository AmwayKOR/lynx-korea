/**
 *
 */
package com.amway.core.payment.strategies;

import de.hybris.platform.core.model.order.OrderModel;

/**
 * The AmwayAdjustOverpaymentStrategy to adjust the overpaid payments.
 *
 * @author arjunduggal
 *
 */
public interface AmwayAdjustOverpaymentStrategy {

    /**
     * Method to adjust the overpayments for the order.
     *
     * @param order
     *            the order model
     */
    void adjustOverpayments(final OrderModel order);

}
