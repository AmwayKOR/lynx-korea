package com.amway.core.order.hook.impl;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.core.order.returns.AmwayReturnCalculationService;
import com.amway.core.order.services.AmwayOrderProrationService;
import com.amway.core.payment.strategies.AmwayAdjustOverpaymentStrategy;
import com.amway.core.stock.service.AmwayCommerceStockService;


/**
 * Place Order
 */
public class AmwayPlaceOrderMethodHookImpl implements CommercePlaceOrderMethodHook
{
	private CartService cartService;
	private ModelService modelService;
	private AmwayCommerceStockService commerceStockService;
	private AmwayOrderProrationService amwayOrderProrationService;
	private AmwayReturnCalculationService returnCalculationService;
	
	private AmwayAdjustOverpaymentStrategy adjustOverpaymentStrategy;

	/**
	 * @deprecated Only in core for POS testing.  Override this!
	 *
	 * To calculate return price for all entries after place order.
	 *
	 * @param commerceCheckoutParameter
	 * @param commerceOrderResult
	 */
	@Override
	@Deprecated
	public void afterPlaceOrder(final CommerceCheckoutParameter commerceCheckoutParameter,
			final CommerceOrderResult commerceOrderResult)
	{
		if (null != commerceOrderResult && null != commerceOrderResult.getOrder())
		{
			final OrderModel order = commerceOrderResult.getOrder();
			// Remove cart
			getCartService().removeSessionCart();
			final Object clientIpAddress = JaloSession.getCurrentSession().getAttribute("clientIpAddress");
			if (clientIpAddress != null && StringUtils.isNotEmpty(clientIpAddress.toString()))
			{
				order.setClientipaddress(clientIpAddress.toString());
				getModelService().save(order);
			}
			//TO-DO deprecated from HE-369
			getCommerceStockService().reserve(order);
			getModelService().refresh(order);
			getAmwayOrderProrationService().prorate(order);
			getModelService().refresh(order);

			getReturnCalculationService().calculateReturnPrice(order);
		}
	}

	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter paramCommerceCheckoutParameter)
	{
		// what check should be here? This gets called after payment authorization
	}

	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter paramCommerceCheckoutParameter,
			final CommerceOrderResult paramCommerceOrderResult)
	{
		 adjustOverpaymentStrategy.adjustOverpayments(paramCommerceOrderResult.getOrder());
	}

	/**
	 * @return cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
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
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return commerceStockService
	 */
	public AmwayCommerceStockService getCommerceStockService()
	{
		return commerceStockService;
	}

	/**
	 * @param commerceStockService the commerceStockService to set
	 */
	public void setCommerceStockService(final AmwayCommerceStockService commerceStockService)
	{
		this.commerceStockService = commerceStockService;
	}

	/**
	 * @return amwayOrderProrationService
	 */
	public AmwayOrderProrationService getAmwayOrderProrationService()
	{
		return amwayOrderProrationService;
	}

	/**
	 * @param amwayOrderProrationService the amwayOrderProrationService to set
	 */
	public void setAmwayOrderProrationService(final AmwayOrderProrationService amwayOrderProrationService)
	{
		this.amwayOrderProrationService = amwayOrderProrationService;
	}

	/**
	 * @return returnCalculationService
	 */
	public AmwayReturnCalculationService getReturnCalculationService()
	{
		return returnCalculationService;
	}

	/**
	 * @param returnCalculationService the returnCalculationService to set
	 */
	public void setReturnCalculationService(final AmwayReturnCalculationService returnCalculationService)
	{
		this.returnCalculationService = returnCalculationService;
	}
	
	public AmwayAdjustOverpaymentStrategy getAdjustOverpaymentStrategy() {
		return adjustOverpaymentStrategy;
	}

	@Required
	public void setAdjustOverpaymentStrategy(final AmwayAdjustOverpaymentStrategy adjustOverpaymentStrategy) {
		this.adjustOverpaymentStrategy = adjustOverpaymentStrategy;
	}
}
