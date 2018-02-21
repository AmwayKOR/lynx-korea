/**
 *
 */
package com.amway.core.order.returns.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.promotions.model.AbstractPromotionActionModel;
import de.hybris.platform.promotions.model.OrderPromotionModel;
import de.hybris.platform.promotions.model.PromotionOrderAdjustTotalActionModel;
import de.hybris.platform.promotions.model.PromotionOrderEntryConsumedModel;
import de.hybris.platform.promotions.model.PromotionResultModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.order.returns.AmwayReturnCalculationService;


/**
 * Implementation of {@link AmwayReturnCalculationService}
 */
public class DefaultAmwayReturnCalculationService implements AmwayReturnCalculationService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayReturnCalculationService.class);

	private ModelService modelService;

	@Override
	public void calculateReturnPrice(final OrderModel order)
	{
		// set unit return price for all entries as its base price
		setUnitReturnPriceForAllEntries(order);

		// Update unit return value
		if (CollectionUtils.isNotEmpty(order.getAllPromotionResults()))
		{
			calculateUnitReturnPricePerEntry(order);
		}
	}

	@Override
	public void setUnitReturnPriceForAllEntries(final OrderModel order)
	{
		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			entry.setUnitReturnPrice(entry.getBasePrice());
			modelService.save(entry);
			modelService.refresh(entry);
		}

		modelService.refresh(order);
	}

	@Override
	public void calculateUnitReturnPricePerEntry(final OrderModel order)
	{
		BigDecimal totalAdjustedPrice = BigDecimal.ZERO;

		for (final PromotionResultModel promoResult : order.getAllPromotionResults())
		{
			//update unit return price based on product level promotions
			if (promoResult.getPromotion() instanceof OrderPromotionModel && CollectionUtils.isNotEmpty(promoResult.getActions()))
			{
				totalAdjustedPrice = BigDecimal.valueOf(calculateTotalAdjustedPriceForOrder(totalAdjustedPrice.doubleValue(), promoResult.getActions()));
			}
			calculateUnitReturnPriceForEntry(promoResult);
		}

		if (totalAdjustedPrice.compareTo(BigDecimal.ZERO) != 0)
		{
			for (final AbstractOrderEntryModel entry : order.getEntries())
			{
				final double lineEntryDiscount =
						(totalAdjustedPrice.doubleValue() * entry.getTotalPrice().doubleValue()) / order.getSubtotal().doubleValue();
				entry.setUnitReturnPrice(Double.valueOf(
						entry.getUnitReturnPrice().doubleValue() - (lineEntryDiscount / entry.getQuantity().doubleValue())));
				getModelService().save(entry);
				getModelService().refresh(entry);
			}
		}
	}

	protected double calculateTotalAdjustedPriceForOrder(double totalAdjustedPrice,
			final Collection<AbstractPromotionActionModel> actions)
	{
		for (final AbstractPromotionActionModel promoAction : actions)
		{
			if (promoAction instanceof PromotionOrderAdjustTotalActionModel && promoAction.getMarkedApplied() != null && promoAction
					.getMarkedApplied().booleanValue())
			{
				totalAdjustedPrice += (-((PromotionOrderAdjustTotalActionModel) promoAction).getAmount().doubleValue());
			}
		}
		return totalAdjustedPrice;
	}

	@Override
	public void calculateUnitReturnPriceForEntry(final PromotionResultModel promotion)
	{
		for (final PromotionOrderEntryConsumedModel consumedEntry : promotion.getConsumedEntries())
		{
			consumedEntry.getOrderEntry().setUnitReturnPrice(consumedEntry.getAdjustedUnitPrice());
			getModelService().save(consumedEntry.getOrderEntry());
			getModelService().refresh(consumedEntry.getOrderEntry());
		}
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
