/**
 *
 */
package com.amway.integration.cis.los.order.service;

import com.amway.core.los.data.OrderPeriodCloseResultData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.pojo.LosOrderPeriodRequest;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;


/**
 * Default Implementation of service for Los order period close.
 */
public class DefaultLosOrderPeriodCloseService
		extends AbstractLosService<OrderPeriodCloseResultData, LosOrderPeriodRequest, String>
		implements LosService<LosOrderPeriodRequest, OrderPeriodCloseResultData>
{

	@Override
	protected OrderPeriodCloseResultData createResultObject()
	{
		return new OrderPeriodCloseResultData();
	}

	@Override
	protected OrderPeriodCloseResultData createDefaultResult()
	{
		final OrderPeriodCloseResultData orderPeriodCloseResultData = createResultObject();
		orderPeriodCloseResultData.setReturnMessage("ERROR IN BONUS ORDER DATA");
		orderPeriodCloseResultData.setDecision("ERROR");

		return orderPeriodCloseResultData;
	}

	@Override
	protected String executeEvent(final Object input)
	{
		final RestResponse restResponse = getLosClient().executeLosRequest(getXclientRefId(), getUrlPath(), input, String.class);
		if (restResponse != null)
		{
			return String.valueOf(restResponse.getStatusCode());
		}
		return null;
	}
}
