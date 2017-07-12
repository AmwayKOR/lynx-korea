package com.amway.core.order.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.jalo.ProductBOGOFPromotion;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryConsumed;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.result.PromotionOrderResults;
import de.hybris.platform.promotions.result.WrappedOrderEntry;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.order.services.AmwayOrderProrationService;


/**
 * Default Implementation of amway order proration service.
 */
public class DefaultAmwayOrderProrationService implements AmwayOrderProrationService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayOrderProrationService.class);

	private ModelService modelService;
	private PromotionsService promotionsService;

	/**
	 * proration calcuation
	 *
	 * @param abstractOrderModel
	 */
	@Override
	public void prorate(final AbstractOrderModel abstractOrderModel)
	{
		Assert.notNull(abstractOrderModel, "Order model cannot be null");
		if (Boolean.TRUE.equals(abstractOrderModel.getProrated()))
		{
			LOG.info("Order " + abstractOrderModel.getCode() + " is already prorated skipping proration calcuation");
			//TODO:
			//removing the skip because of pos
		}
		try
		{
			final PromotionOrderResults promotionResults = getPromotionsService().getPromotionResults(abstractOrderModel);
			final List<PromotionResult> orderPromotions = promotionResults.getAppliedOrderPromotions();
			final List<PromotionResult> productPromotions = promotionResults.getAppliedProductPromotions();
			final List<WrappedOrderEntry> entriesNotInPromotions = promotionResults.getEntriesNotInPromotions();
			final List<WrappedOrderEntry> entriesWithPotentialPromotions = promotionResults.getEntriesWithPotentialPromotions();
			final List<WrappedOrderEntry> entriesWithoutPromotions = new ArrayList<WrappedOrderEntry>();
			entriesWithoutPromotions.addAll(entriesNotInPromotions);
			entriesWithoutPromotions.addAll(entriesWithPotentialPromotions);
			BigDecimal totalOrderDiscount = BigDecimal.ZERO.setScale(4);
			for (final PromotionResult orderPromotion : orderPromotions)
			{
				totalOrderDiscount = totalOrderDiscount
						.add(BigDecimal.valueOf(orderPromotion.getTotalDiscount()).setScale(4, RoundingMode.HALF_DOWN));
			}
			prorateOrderPromotionToEntries(totalOrderDiscount, productPromotions, entriesWithoutPromotions, abstractOrderModel);
			abstractOrderModel.setProrated(Boolean.TRUE);
			getModelService().save(abstractOrderModel);
			getModelService().refresh(abstractOrderModel);
		}
		catch (final Exception exc)
		{
			//TODO: just a double check to make sure all the scenarios are covered
			LOG.error(exc.getMessage(), exc);
		}
	}

	/**
	 * @param totalOrderDiscount
	 * @param productPromotions
	 * @param entriesNotInPromotions
	 * @param abstractOrderModel
	 */
	private void prorateOrderPromotionToEntries(final BigDecimal totalOrderDiscount, final List<PromotionResult> productPromotions,
			final List<WrappedOrderEntry> entriesNotInPromotions, final AbstractOrderModel abstractOrderModel)
	{
		final Set<PromotionOrderEntryConsumed> consumedEntries = new HashSet<PromotionOrderEntryConsumed>();
		for (final PromotionResult promotionResult : productPromotions)
		{
			consumedEntries.addAll(promotionResult.getConsumedEntries());
		}

		BigDecimal subTotalProrated = BigDecimal.ZERO.setScale(4);
		final Map<Integer, BigDecimal> proratedValues = new HashMap<Integer, BigDecimal>();
		for (final AbstractOrderEntryModel entry : abstractOrderModel.getEntries())
		{
			final BigDecimal entryTotalPrice = findAdjustedEntryPrice(consumedEntries, entriesNotInPromotions, entry);
			proratedValues.put(entry.getEntryNumber(), entryTotalPrice);
			subTotalProrated = subTotalProrated.add(entryTotalPrice);
		}

		for (final AbstractOrderEntryModel entry : abstractOrderModel.getEntries())
		{
			final BigDecimal proratedOrderDiscount = (proratedValues.get(entry.getEntryNumber())
					.divide(subTotalProrated, 2, RoundingMode.HALF_UP)).multiply(totalOrderDiscount);
			entry.setProRatedPrice(Double.valueOf(
					proratedValues.get(entry.getEntryNumber()).subtract(proratedOrderDiscount).setScale(4, RoundingMode.HALF_DOWN)
							.doubleValue()));
			getModelService().save(entry);
		}
	}

	/**
	 * @param consumedEntries
	 * @param entriesNotInPromotions
	 * @param entry
	 * @return
	 */
	private BigDecimal findAdjustedEntryPrice(final Set<PromotionOrderEntryConsumed> consumedEntries,
			final List<WrappedOrderEntry> entriesNotInPromotions, final AbstractOrderEntryModel entry)
	{
		final Integer entryNumber = entry.getEntryNumber();
		BigDecimal entryProratedPrice = BigDecimal.ZERO;
		boolean productBOGOFPromotion = false;
		for (final PromotionOrderEntryConsumed promotionOrderEntryConsumed : consumedEntries)
		{
			//ignore BOGO promotion
			if (entryNumber.equals(promotionOrderEntryConsumed.getOrderEntry().getEntryNumber()))
			{
				if (!(promotionOrderEntryConsumed.getPromotionResult().getPromotion() instanceof ProductBOGOFPromotion))
				{
					entryProratedPrice = entryProratedPrice
							.add(BigDecimal.valueOf(promotionOrderEntryConsumed.getAdjustedEntryPrice()));
				}
				else
				{
					productBOGOFPromotion = true;
				}
			}
		}

		for (final WrappedOrderEntry orderEntry : entriesNotInPromotions)
		{
			if (entryNumber.equals(orderEntry.getBaseOrderEntry().getEntryNumber()) && !productBOGOFPromotion)
			{
				entryProratedPrice = entryProratedPrice.add(BigDecimal.valueOf(orderEntry.getEntryPrice()));
			}
		}

		return BigDecimal.ZERO.equals(entryProratedPrice) ?
				BigDecimal.valueOf(entry.getTotalPrice().doubleValue()).setScale(4, RoundingMode.HALF_DOWN) :
				entryProratedPrice.setScale(4, RoundingMode.HALF_DOWN);
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the promotionsService
	 */
	public PromotionsService getPromotionsService()
	{
		return promotionsService;
	}

	/**
	 * @param promotionsService the promotionsService to set
	 */
	public void setPromotionsService(final PromotionsService promotionsService)
	{
		this.promotionsService = promotionsService;
	}
}
