package com.amway.core.order.hook.impl;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.hook.CommerceCartCalculationMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.session.SessionService;

import org.springframework.beans.factory.annotation.Required;

import com.amway.core.order.services.AmwayCalculationService;
import com.amway.core.payment.service.AmwayPaymentService;


/**
 * Cart calculation
 */
public class AmwayCartCalculationHook implements CommerceCartCalculationMethodHook
{
	private AmwayCalculationService amwayCalculationService;
	private AmwayPaymentService amwayPaymentService;
	private SessionService sessionService;

	/**
	 * To calculate the price of gift wrap and to adjust the amount of payment.
	 */
	@Override
	public void afterCalculate(final CommerceCartParameter parameter)
	{
		final CartModel cartModel = parameter.getCart();
		getAmwayCalculationService().calculateGiftWrapPrice(cartModel);
		final SalesApplication currentChannel = sessionService.getCurrentSession().getAttribute("currentChannel");
		if(!SalesApplication.POS.equals(currentChannel))
		{
			getAmwayPaymentService().adjustPaymentAmount(cartModel);
		}
	}

	@Override
	public void beforeCalculate(final CommerceCartParameter arg0)
	{
		// YTODO Auto-generated method stub
	}

	/**
	 * @return amwayCalculationService
	 */
	public AmwayCalculationService getAmwayCalculationService()
	{
		return amwayCalculationService;
	}

	/**
	 * @param amwayCalculationService the amwayCalculationService to set
	 */
	public void setAmwayCalculationService(final AmwayCalculationService amwayCalculationService)
	{
		this.amwayCalculationService = amwayCalculationService;
	}

	/**
	 * @return amwayPaymentService
	 */
	public AmwayPaymentService getAmwayPaymentService()
	{
		return amwayPaymentService;
	}

	/**
	 * @param amwayPaymentService the amwayPaymentService to set
	 */
	public void setAmwayPaymentService(final AmwayPaymentService amwayPaymentService)
	{
		this.amwayPaymentService = amwayPaymentService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService() {
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	@Required
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
}
