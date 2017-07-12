/**
 *
 */
package com.amway.core.order.interceptor.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.i18n.L10NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayOrderPeriodModel;


/**
 * Validate Interceptor for order periods for order
 */
public class OrderPeriodAbstractOrderModelValidateInterceptor implements ValidateInterceptor<AbstractOrderModel>
{
	private L10NService l10NService;

	/**
	 * Validate Interceptor for order periods for order
	 *
	 * @param orderModel
	 * @param ctx
	 * @throws InterceptorException
	 */
	@Override
	public void onValidate(final AbstractOrderModel orderModel, final InterceptorContext ctx) throws InterceptorException
	{
		if (orderModel instanceof OrderModel && ((OrderModel) orderModel).getOriginalVersion() != null)
		{
			//don't check for activeness if order snapshot is being created (cancel process)
			return;
		}

		final AmwayOrderPeriodModel orderPeriod = orderModel.getOrderPeriod();
		if (ctx.isModified(orderModel, OrderModel.ORDERPERIOD) && orderPeriod != null && !AmwayPeriodTypeEnum.ACTIVE
				.equals(orderPeriod.getStatus()))
		{
			throw new InterceptorException(getL10NService().getLocalizedString("error.order.orderperiod.notactive"));
		}

	}

	/**
	 * @return l10NService
	 */
	public L10NService getL10NService()
	{
		return l10NService;
	}

	/**
	 * @param l10nService the l10NService to set
	 */
	public void setL10NService(final L10NService l10nService)
	{
		l10NService = l10nService;
	}

}
