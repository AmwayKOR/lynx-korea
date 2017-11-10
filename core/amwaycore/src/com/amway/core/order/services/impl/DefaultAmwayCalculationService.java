/*
 * mergefix
 */

package com.amway.core.order.services.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.constants.GeneratedEurope1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.impl.DefaultCalculationService;
import de.hybris.platform.order.strategies.calculation.OrderRequiresCalculationStrategy;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.PriceValue;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.core.constants.AmwaycoreConstants.PriceGroups;
import com.amway.core.enums.AmwayKitProductType;
import com.amway.core.model.AmwayKitProductModel;
import com.amway.core.order.services.AmwayCalculationService;
import com.amway.core.price.service.AmwayNetPriceService;

import javax.annotation.Resource;


/**
 * Default implementation of calculation service.
 */
public class DefaultAmwayCalculationService extends DefaultCalculationService implements AmwayCalculationService
{
	private static Logger LOG = Logger.getLogger(DefaultAmwayCalculationService.class);

	private ModelService modelService;
	private CommercePriceService commercePriceService;
	private AmwayNetPriceService amwayNetPriceService;
	private UserService userService;
	private BaseSiteService baseSiteService;
	private OrderRequiresCalculationStrategy orderRequiresCalculationStrategy;


	/**
	 * calculate the price for entries.
	 *
	 * @param order
	 * @param forceRecalculate
	 * @throws CalculationException
	 */
	@Override
	public void calculateEntries(final AbstractOrderModel order, final boolean forceRecalculate) throws CalculationException
	{
		super.calculateEntries(order, forceRecalculate);
		calculateAmwayValue(order, forceRecalculate);
	}

	/**
	 * To calculate the price of gift wrap.
	 *
	 * @param order
	 */
	@Override
	public void calculateGiftWrapPrice(final AbstractOrderModel order)
	{
		if (Boolean.TRUE.equals(order.getGiftWrap()))
		{
			final BaseSiteModel baseSite = getBaseSiteService().getCurrentBaseSite();
			if (baseSite.getGiftWrapPrice() != null)
			{
				final double total = order.getTotalPrice().doubleValue() + baseSite.getGiftWrapPrice().doubleValue();
				order.setGiftWrapPrice(baseSite.getGiftWrapPrice().doubleValue());
				order.setTotalPrice(Double.valueOf(CoreAlgorithms.round(total, 2)));
				getModelService().save(order);
				getModelService().refresh(order);
			}
		}
		else
		{
			order.setGiftWrapPrice(0);
			getModelService().save(order);
			getModelService().refresh(order);
		}
	}

	/**
	 * To calculate amway value
	 *
	 * @param order
	 * @param recalculate
	 */
	public void calculateAmwayValue(final AbstractOrderModel order, final boolean recalculate)
	{
		if (!(recalculate || requiresCalculation(order)))
		{
			return;
		}


		double totalBV = new Double(0).doubleValue();
		double totalPV = new Double(0).doubleValue();


		for (final AbstractOrderEntryModel abstractOrderEntryModel : order.getEntries())
		{
			//for free products skiping pv/bv calculation,so not added to total pv/bv
			if (!abstractOrderEntryModel.getGiveAway().booleanValue())
			{
				if ((abstractOrderEntryModel.getKitOrderEntry() == null)
						&& (!((abstractOrderEntryModel.getProduct() instanceof AmwayKitProductModel) && (AmwayKitProductType.BUNDLED
								.equals(((AmwayKitProductModel) abstractOrderEntryModel.getProduct()).getType())))))
				{
					{
						//retrieve the ABO price row in all the scenarios, because only ABO price rows have pv , bv information
						final PriceInformation info = getSessionService().executeInLocalView(new SessionExecutionBody()
						{
							@Override
							public Object execute()
							{
								getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
										UserPriceGroup.valueOf(PriceGroups.ABO_USER_PRICE_GROUP));
								return commercePriceService.getFromPriceForProduct(abstractOrderEntryModel.getProduct());
							}
						});

						if (info != null)
						{
							/*
							 * final PriceRow priceRow = (PriceRow) info.getQualifierValue("pricerow"); final PriceRowModel
							 * priceRowModel = modelService.get(priceRow.getPK());
							 */
							if ((abstractOrderEntryModel.getKitOrderEntry() == null)
									&& (!((abstractOrderEntryModel.getProduct() instanceof AmwayKitProductModel)
											&& (AmwayKitProductType.BUNDLED
													.equals(((AmwayKitProductModel) abstractOrderEntryModel.getProduct()).getType())))))
							{
								final long quantity = abstractOrderEntryModel.getQuantity().longValue();
								final double entryBV = ((Double) info.getQualifierValue("businessVolume")).doubleValue() * quantity;
								final double entryPV = ((Double) info.getQualifierValue("pointValue")).doubleValue() * quantity;

								totalBV += entryBV;
								totalPV += entryPV;

								if (LOG.isDebugEnabled())
								{
									LOG.debug("entry :" + abstractOrderEntryModel.getProduct().getCode() + " - bv :" + entryBV + " - pv :"
											+ entryPV);
								}

								abstractOrderEntryModel.setBusinessVolume(CoreAlgorithms.round(entryBV, 2));
								abstractOrderEntryModel.setPointValue(CoreAlgorithms.round(entryPV, 2));
								modelService.save(abstractOrderEntryModel);
							}
						}
					}
				}
			}
		}

		order.setBusinessVolume(CoreAlgorithms.round(totalBV, 2));
		order.setPointValue(CoreAlgorithms.round(totalPV, 2));

		if (LOG.isDebugEnabled())
		{
			LOG.debug("order :" + order.getCode() + " - bv :" + totalBV + " - pv :" + totalPV);
		}
		modelService.save(order);
	}

	/**
	 * To reset the values.
	 *
	 * @param entry
	 * @throws CalculationException
	 */
	@Override
	protected void resetAllValues(final AbstractOrderEntryModel entry) throws CalculationException
	{
		super.resetAllValues(entry);

		//Retrieve & Set ABO Price
		final AbstractOrderModel order = entry.getOrder();
		final PriceValue aboPriceValue = convertPriceIfNecessary(getAmwayNetPriceService().findABOBasePrice(entry),
				order.getNet().booleanValue(), order.getCurrency(), entry.getTaxValues());
		entry.setAboBasePrice(Double.valueOf(aboPriceValue.getValue()));

		//Retrieve & Set Retail Price
		final PriceValue retailPriceValue = convertPriceIfNecessary(getAmwayNetPriceService().findRetailBasePrice(entry),
				order.getNet().booleanValue(), order.getCurrency(), entry.getTaxValues());
		entry.setRetailBasePrice(Double.valueOf(retailPriceValue.getValue()));
	}





	/**
	 * To retrieve ABO & Retail prices for item
	 *
	 * @param abstractOrderEntryModel
	 */
	@Override
	public void setAboRetailBasePrices(final AbstractOrderEntryModel abstractOrderEntryModel)
	{

		final AbstractOrderModel order = abstractOrderEntryModel.getOrder();
		final boolean isGiveAwayItem = Boolean.TRUE.equals(abstractOrderEntryModel.getGiveAway());

		try
		{
			if (isGiveAwayItem)
			{
				//workaround to get prices of give away item, bypass pricefactory conditions
				abstractOrderEntryModel.setGiveAway(Boolean.FALSE);
				getModelService().save(abstractOrderEntryModel);
			}

			//Retrieve & Set ABO Price
			final PriceValue aboPriceValue = convertPriceIfNecessary(
					getAmwayNetPriceService().findABOBasePrice(abstractOrderEntryModel), order.getNet().booleanValue(),
					order.getCurrency(), abstractOrderEntryModel.getTaxValues());
			abstractOrderEntryModel.setAboBasePrice(Double.valueOf(aboPriceValue.getValue()));

			//Retrieve & Set Retail Price
			final PriceValue retailPriceValue = convertPriceIfNecessary(
					getAmwayNetPriceService().findRetailBasePrice(abstractOrderEntryModel), order.getNet().booleanValue(),
					order.getCurrency(), abstractOrderEntryModel.getTaxValues());
			abstractOrderEntryModel.setRetailBasePrice(Double.valueOf(retailPriceValue.getValue()));
		}
		catch (final Exception e)
		{
			LOG.error("Error in retrieving ABO & Retail prices for item " + abstractOrderEntryModel.getProduct().getCode()
					+ " on order " + order.getCode(), e);
		}
		finally
		{
			if (isGiveAwayItem)
			{
				//workaround to get prices of give away item, bypass pricefactory conditions
				abstractOrderEntryModel.setGiveAway(Boolean.TRUE);
			}
			getModelService().save(abstractOrderEntryModel);
		}
	}

	@Override
	public void calculateTotals(final AbstractOrderEntryModel entry, final boolean recalculate)
	{
		if (checkBundleEntry(entry))
		{
			if (recalculate || orderRequiresCalculationStrategy.requiresCalculation(entry))
			{
				// set total price as zero
				entry.setBasePrice(Double.valueOf(0));
				entry.setTotalPrice(Double.valueOf(0));
				entry.setTaxValues(Collections.EMPTY_LIST);
				getModelService().save(entry);
				setCalculatedStatus(entry);
			}
		}
		else
		{
			super.calculateTotals(entry, recalculate);
		}
	}
	
	/**
	 * Check  if kit entries not null 
	 *
	 * @param entry
	 * @return boolean
	 */
	protected boolean checkBundleEntry(final AbstractOrderEntryModel entry)
	{
		if (entry.getKitOrderEntry() != null)
		{
			return true;
		}
		return false;

	}

	protected void setCalculatedStatus(final AbstractOrderEntryModel entry)
	{
		entry.setCalculated(Boolean.TRUE);
		getModelService().save(entry);
	}


	/**
	 * @return modelService
	 */
	@Override
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return commercePriceService
	 */
	public CommercePriceService getCommercePriceService()
	{
		return commercePriceService;
	}

	/**
	 * @param commercePriceService
	 *           the commercePriceService to set
	 */
	public void setCommercePriceService(final CommercePriceService commercePriceService)
	{
		this.commercePriceService = commercePriceService;
	}

	/**
	 * @return amwayNetPriceService
	 */
	public AmwayNetPriceService getAmwayNetPriceService()
	{
		return amwayNetPriceService;
	}

	/**
	 * @param amwayNetPriceService
	 *           the amwayNetPriceService to set
	 */
	public void setAmwayNetPriceService(final AmwayNetPriceService amwayNetPriceService)
	{
		this.amwayNetPriceService = amwayNetPriceService;
	}

	/**
	 * @return userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 *
	 */
	@Override
	@Required
	public void setOrderRequiresCalculationStrategy(final OrderRequiresCalculationStrategy orderRequiresCalculationStrategy)
	{
		this.orderRequiresCalculationStrategy = orderRequiresCalculationStrategy;
		super.setOrderRequiresCalculationStrategy(orderRequiresCalculationStrategy);
	}



}
