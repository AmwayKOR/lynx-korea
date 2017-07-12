/**
 *
 */
package com.amway.core.ordermargin.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.amway.core.model.AmwayOrderMarginModel;
import com.amway.core.ordermargin.services.AmwayOrderMarginService;


/**
 * Default implementation for order margin service.
 *
 * @param <o>
 */
public class DefaultAmwayOrderMarginService<o extends AbstractOrderModel> extends AbstractBusinessService
		implements AmwayOrderMarginService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayOrderMarginService.class);

	/**
	 * Calculates the order margins for the given order at entry level and order level and assigns those values.
	 *
	 * @param order
	 */
	@Override
	public void calculateAndAssignOrderMargin(final AbstractOrderModel order)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("calculating order margins for order:" + order.getCode());
		}

		for (final AbstractOrderEntryModel orderEntry : order.getEntries())
		{
			calcuateOrderMarginOrderEntry(orderEntry);
		}
		calculateOrderMarginOrder(order);
	}

	protected void calcuateOrderMarginOrderEntry(final AbstractOrderEntryModel orderEntry)
	{
		//retrieve the ABO price row in all the scenarios, because only ABO price rows have pv , bv information
		if (orderEntry.getAboBasePrice() != null)
		{
			final long quantity = orderEntry.getQuantity().longValue();
			final double orderEntryPrice = orderEntry.getBasePrice().doubleValue() * quantity;
			final double orderEntryGroupPrice = orderEntry.getAboBasePrice().doubleValue() * quantity;

			final double orderEntryMarginValue = BigDecimal.valueOf(orderEntryPrice)
					.subtract(BigDecimal.valueOf(orderEntryGroupPrice)).doubleValue();

			final AmwayOrderMarginModel amwayOrderEntryMargin = orderEntry.getMargin() == null ?
					(AmwayOrderMarginModel) getModelService().create(AmwayOrderMarginModel.class) :
					orderEntry.getMargin();

			amwayOrderEntryMargin.setMargin(Double.valueOf(orderEntryMarginValue));
			amwayOrderEntryMargin.setPrice(orderEntry.getAboBasePrice());

			orderEntry.setMargin(amwayOrderEntryMargin);
			getModelService().save(orderEntry);
		}
	}

	protected void calculateOrderMarginOrder(final AbstractOrderModel order)
	{
		double orderMarginValue = 0.0;
		double orderMarginPrice = 0.0;

		final AmwayOrderMarginModel amwayOrderMargin = new AmwayOrderMarginModel();

		for (final AbstractOrderEntryModel orderEntry : order.getEntries())
		{
			final AmwayOrderMarginModel amwayOrderEntryMargin = orderEntry.getMargin();
			if (amwayOrderEntryMargin != null)
			{
				orderMarginValue = BigDecimal.valueOf(amwayOrderEntryMargin.getMargin().doubleValue())
						.add(BigDecimal.valueOf(orderMarginValue)).doubleValue();
				orderMarginPrice = BigDecimal.valueOf(amwayOrderEntryMargin.getPrice().doubleValue())
						.add(BigDecimal.valueOf(orderMarginPrice)).doubleValue();
			}
		}

		amwayOrderMargin.setMargin(Double.valueOf(orderMarginValue));
		amwayOrderMargin.setPrice(Double.valueOf(orderMarginPrice));
		order.setMargin(amwayOrderMargin);
		getModelService().save(order);
	}
}
