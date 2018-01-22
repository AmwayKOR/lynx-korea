package com.amway.apac.fulfilmentprocess.actions.order;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction.Transition;
import de.hybris.platform.task.RetryLaterException;

import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.model.AmwayUserPromotionCountModel;
import com.amway.apac.core.product.services.AmwayApacProductService;


/**
 * Action triggered on order placement to update the purchased amount of pre launch product.
 *
 * @author Shubham Goyal
 */
public class AmwayApacPreLaunchProductCountAction extends AbstractOrderAction<OrderProcessModel>
{
	private AmwayApacProductService amwayApacProductService;

	/**
	 * Order process for creating or updating new {@link AmwayUserPromotionCountModel} for pre-launch product order.
	 */
	@Override
	public String execute(final OrderProcessModel orderProcess) throws RetryLaterException, Exception
	{
		final AbstractOrderModel orderModel = orderProcess.getOrder();
		final Map<String, Integer> productCodeToCount = getAmwayApacProductService().getPreLaunchConfigProducts(orderModel);
		if (MapUtils.isNotEmpty(productCodeToCount))
		{
			getAmwayApacProductService().updatePreLaunchProductCount(productCodeToCount, orderModel);
		}
		return Transition.OK.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getTransitions()
	{
		return Transition.getStringValues();
	}


	/**
	 * @return the amwayApacProductService
	 */
	public AmwayApacProductService getAmwayApacProductService()
	{
		return amwayApacProductService;
	}

	/**
	 * @param amwayApacProductService
	 *           the amwayApacProductService to set
	 */
	@Required
	public void setAmwayApacProductService(final AmwayApacProductService amwayApacProductService)
	{
		this.amwayApacProductService = amwayApacProductService;
	}

}
