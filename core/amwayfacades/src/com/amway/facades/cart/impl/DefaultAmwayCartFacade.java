/* Implementation of Extended cart facade for Amway specific features */
package com.amway.facades.cart.impl;

import java.util.List;

import de.hybris.platform.commerceservices.order.CommerceCartRestoration;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import org.apache.log4j.Logger;

import com.amway.core.enums.AmwayCartType;
import com.amway.core.util.AmwayCartHelper;
import com.amway.facades.cart.AmwayCartFacade;

import de.hybris.platform.commercefacades.order.data.CartRestorationData;
import de.hybris.platform.commercefacades.order.impl.DefaultCartFacade;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


/**
 * Default Implementation
 */
public class DefaultAmwayCartFacade extends DefaultCartFacade implements AmwayCartFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayCartFacade.class);
	private CalculationService calculationService;
	private ModelService modelService;
	private static final String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

	/**
	 * Sets pickup date for each order entry in the cart
	 *
	 * @param pickupDate - valid pickup date
	 */
	public void addPickupDateTime(String pickupDate)
	{
		DateTime pickup = DateTime.parse(pickupDate, DateTimeFormat.forPattern(DATE_PATTERN));
		CartModel cartModel = getCommerceCartService()
				.getCartForCodeAndUser(getCartService().getSessionCart().getCode(), getUserService().getCurrentUser());
		for (AbstractOrderEntryModel orderEntryModel : cartModel.getEntries())
		{
			orderEntryModel.setPickupDateTime(pickup.toDate());
			modelService.save(orderEntryModel);
		}
	}

	/**
	 *  All other cart types are automatically set based on the channel
	 */
	@Override
	public void setCartType(String type)
	{


	}


	/**
	 * To calculates all totals.
	 * <p/>
	 * {@link #calculateGiftWrap(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 *
	 * @param order
	 * @throws CalculationException
	 */
	public void calculateGiftWrap(AbstractOrderModel order) throws CalculationException
	{
		getCalculationService().calculateTotals(order, false);
	}


	/**
	 * @return calculationService
	 */
	public CalculationService getCalculationService()
	{
		return calculationService;
	}

	/**
	 * @param calculationService the calculationService to set
	 */
	public void setCalculationService(CalculationService calculationService)
	{
		this.calculationService = calculationService;
	}

	/**
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}
}
