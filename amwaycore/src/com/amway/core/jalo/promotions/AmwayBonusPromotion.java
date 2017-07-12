package com.amway.core.jalo.promotions;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryConsumed;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.result.PromotionOrderView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.dgc.VMID;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * Amway Bonus Promotion
 */
public class AmwayBonusPromotion extends GeneratedAmwayBonusPromotion
{
	private final static Logger LOG = Logger.getLogger(AmwayBonusPromotion.class.getName());
	private final static String POINTVALUE = "pointValue";
	private final static String BUSINESSVOLUME = "businessVolume";

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}


	/**
	 * To evaluate the promotion
	 *
	 * @param ctx
	 * @param promoContext
	 */
	@Override
	public List<PromotionResult> evaluate(final SessionContext ctx, final PromotionEvaluationContext promoContext)
	{
		final List results = new ArrayList();
		final String name = getCode() + ": " + getTitle();
		final PromotionsManager.RestrictionSetResult rsr = findEligibleProductsInBasket(ctx, promoContext); //products in the cart

		final Double points = getPoints();
		final BigDecimal pointsBD = new BigDecimal(points).setScale(2, RoundingMode.HALF_UP);

		//Make sure product A and product B exist in the cart
		if (rsr.isAllowedToContinue() && (!(rsr.getAllowedProducts().isEmpty())) && points != null && pointsBD.compareTo(BigDecimal.ZERO) != 0
				&& getProducts().size() == 2 && rsr.getAllowedProducts().size() == 2)
		{

			if (CollectionUtils.isEqualCollection(rsr.getAllowedProducts(), getProducts()))
			{
				final PromotionOrderView order = promoContext.createView(ctx, this, rsr.getAllowedProducts());

				promoContext.startLoggingConsumed(this);
				order.consume(ctx, order.getTotalQuantity(ctx));

				// Get a list of all the order entries that were consumed during this run

				final AbstractOrder abstractOrder = promoContext.getOrder();
				try
				{
					BigDecimal totalBV = new BigDecimal("0");
					BigDecimal totalPV = new BigDecimal("0");

					for (final AbstractOrderEntry abstractOrderEntry : abstractOrder.getEntries())
					{
						if (getProducts().contains(abstractOrderEntry.getProduct()))
						{
							final Double orderPointValue = abstractOrderEntry.getAttribute(POINTVALUE) != null ?
									(Double) abstractOrderEntry.getAttribute(POINTVALUE) :
									Double.valueOf(0);

							final Double orderBusinessVolume = abstractOrderEntry.getAttribute(BUSINESSVOLUME) != null ?
									(Double) abstractOrderEntry.getAttribute(BUSINESSVOLUME) :
									Double.valueOf(0);

							final BigDecimal orderPV = new BigDecimal(orderPointValue.doubleValue()).setScale(2, RoundingMode.HALF_UP);
							final BigDecimal orderBV = new BigDecimal(orderBusinessVolume.doubleValue())
									.setScale(2, RoundingMode.HALF_UP);

							final BigDecimal promoPoints = new BigDecimal(points.doubleValue()).setScale(2, RoundingMode.HALF_UP);

							final BigDecimal pointValPercantage = orderPV.multiply(promoPoints).divide(new BigDecimal("100"))
									.setScale(2, RoundingMode.HALF_UP);
							final BigDecimal businessVolumePercentage = orderBV.multiply(promoPoints).divide(new BigDecimal("100"))
									.setScale(2, RoundingMode.HALF_UP);

							final BigDecimal pvToAssignOnOrder = orderPV.add(pointValPercantage).setScale(2, RoundingMode.HALF_UP);
							final BigDecimal bvToAssignOnOrder = orderBV.add(businessVolumePercentage).setScale(2, RoundingMode.HALF_UP);

							abstractOrderEntry.setAttribute(POINTVALUE, Double.valueOf(pvToAssignOnOrder.doubleValue()));
							abstractOrderEntry.setAttribute(BUSINESSVOLUME, Double.valueOf(bvToAssignOnOrder.doubleValue()));

							LOG.info("Promotion Applied" + name + " : Point Value updated to :" + pvToAssignOnOrder.doubleValue()
									+ "and BusinessVolume updated to : " + bvToAssignOnOrder.doubleValue() + "for order entry"
									+ abstractOrderEntry.getProduct().getCode());

							totalPV = totalPV.add(pvToAssignOnOrder);
							totalBV = totalBV.add(bvToAssignOnOrder);
						}
					}

					abstractOrder.setAttribute(POINTVALUE, Double.valueOf(totalPV.doubleValue()));
					abstractOrder.setAttribute(BUSINESSVOLUME, Double.valueOf(totalBV.doubleValue()));
				}
				catch (final JaloBusinessException e)
				{
					LOG.error(e.getMessage(), e);
				}
				catch (final JaloInvalidParameterException e)
				{
					LOG.error(e.getMessage(), e);
				}
				final PromotionResult result = PromotionsManager.getInstance()
						.createPromotionResult(ctx, this, promoContext.getOrder(), order.getTotalQuantity(ctx));
				final List<PromotionOrderEntryConsumed> consumed = promoContext.finishLoggingAndGetConsumed(this, true);
				result.setConsumedEntries(ctx, consumed);

				results.add(result);

			}
		}
		return results;
	}

	protected static String makeActionGUID()
	{
		return "Action[" + new VMID().toString() + "]";
	}

	/**
	 * To get the result description of promotion
	 *
	 * @param ctx
	 * @param promotionResult
	 * @param locale
	 */
	@Override
	public String getResultDescription(final SessionContext ctx, final PromotionResult promotionResult, final Locale locale)
	{
		final AbstractOrder order = promotionResult.getOrder(ctx);
		if (order != null)
		{
			final Double points = getPoints();

			return "Amway Bonus Promotion is applied and got " + points + "% more PV and BV on Order";
		}
		return StringUtils.EMPTY;
	}
}
