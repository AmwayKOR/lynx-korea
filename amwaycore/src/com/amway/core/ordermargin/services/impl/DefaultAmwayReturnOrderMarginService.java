package com.amway.core.ordermargin.services.impl;

import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.amway.core.model.AmwayOrderMarginModel;
import com.amway.core.ordermargin.services.AmwayReturnOrderMarginService;


/**
 * Default implementation
 *
 * @param <o>
 */
public class DefaultAmwayReturnOrderMarginService<o extends ReturnRequestModel> extends AbstractBusinessService
		implements AmwayReturnOrderMarginService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayReturnOrderMarginService.class);
	private double marginPercentage;

	/**
	 * Calculates the order margins for the given return order at entry level and order level and assigns those values.
	 *
	 * @param order
	 */
	@Override
	public void calculateAndAssignOrderMargin(final ReturnRequestModel order)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("calculating order margins for order:" + order.getCode());
		}

		for (final ReturnEntryModel returnOrderEntry : order.getReturnEntries())
		{
			calcuateOrderMarginOrderEntry(returnOrderEntry);
		}
		calculateOrderMarginOrder(order);
	}

	protected void calcuateOrderMarginOrderEntry(final ReturnEntryModel returnEntry)
	{
		//retrieve the ABO price row in all the scenarios, because only ABO price rows have pv , bv information
		if (returnEntry.getOrderEntry().getAboBasePrice() != null)
		{
			final long quantity = returnEntry.getOrderEntry().getQuantity().longValue();
			final double orderEntryPrice = returnEntry.getOrderEntry().getBasePrice().doubleValue() * quantity;
			final double orderEntryGroupPrice = returnEntry.getOrderEntry().getAboBasePrice().doubleValue() * quantity;

			final double orderEntryMarginValue = BigDecimal.valueOf(orderEntryPrice)
					.subtract(BigDecimal.valueOf(orderEntryGroupPrice)).doubleValue();

			final AmwayOrderMarginModel amwayOrderEntryMargin = returnEntry.getAmwayOrderMargin() == null ?
					(AmwayOrderMarginModel) getModelService().create(AmwayOrderMarginModel.class) :
					returnEntry.getAmwayOrderMargin();

			amwayOrderEntryMargin.setMargin(Double.valueOf(orderEntryMarginValue * getMarginPercentage()));
			amwayOrderEntryMargin.setPrice(returnEntry.getOrderEntry().getAboBasePrice());

			returnEntry.setAmwayOrderMargin(amwayOrderEntryMargin);
			getModelService().save(returnEntry);
		}
	}

	protected void calculateOrderMarginOrder(final ReturnRequestModel order)
	{
		double orderMarginValue = 0.0;
		double orderMarginPrice = 0.0;

		final AmwayOrderMarginModel amwayOrderMargin = new AmwayOrderMarginModel();

		for (final ReturnEntryModel returnEntry : order.getReturnEntries())
		{
			final AmwayOrderMarginModel amwayOrderEntryMargin = returnEntry.getAmwayOrderMargin();
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
		order.setAmwayOrderMargin(amwayOrderMargin);
		getModelService().save(order);
	}

	/**
	 * @return marginPercentage
	 */
	public double getMarginPercentage()
	{
		return marginPercentage;
	}

	/**
	 * @param marginPercentage the marginPercentage to set
	 */
	public void setMarginPercentage(final double marginPercentage)
	{
		this.marginPercentage = marginPercentage;
	}


}
