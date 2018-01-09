package com.amway.apac.fulfilmentprocess.actions.order;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction.Transition;
import de.hybris.platform.task.RetryLaterException;

import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.product.services.AmwayApacProductService;
import com.amway.core.model.AmwayAccountModel;


/**
 * Action triggered on order placement to update the purchased amount of pre launch product.
 *
 * @author Shubham Goyal
 */
public class AmwayApacPreLaunchProductCountAction extends AbstractOrderAction<OrderProcessModel>
{
	private AmwayApacProductService amwayApacProductService;

	@Override
	public String execute(final OrderProcessModel orderProcess) throws RetryLaterException, Exception
	{
		final AbstractOrderModel orderModel = orderProcess.getOrder();
		final Map<String, Integer> productCodeToCount = getAmwayApacProductService().getPreLaunchConfigProducts(orderModel);
		if (MapUtils.isNotEmpty(productCodeToCount))
		{
			final String amwayAccountCode = getNormalizedAmwayAccountCode(orderModel.getAccount());
			getAmwayApacProductService().updatePreLaunchProductCount(productCodeToCount, amwayAccountCode);
		}
		return Transition.OK.toString();
	}

	/**
	 * Get Amway Account Code with affiliate number and Amway Account
	 *
	 * @param amwayAccount
	 * @return
	 */
	public String getNormalizedAmwayAccountCode(final AmwayAccountModel amwayAccount)
	{
		return amwayAccount.getControllingAffiliate().getAffiliateNumber() + "-" + amwayAccount.getCode();
	}

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
