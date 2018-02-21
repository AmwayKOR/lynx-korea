package com.amway.integration.cis.los.order.service;

import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.BonusOrderResultData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;


/**
 * Default Implementation service for Los cancel order bonus.
 */
public class DefaultLosCancelOrderBonusService extends AbstractLosService<BonusOrderResultData, BonusOrderRequestData, String>
		implements LosService<BonusOrderRequestData, BonusOrderResultData>
{
	@Override
	protected BonusOrderResultData createResultObject()
	{
		return new BonusOrderResultData();
	}

	@Override
	protected BonusOrderResultData createDefaultResult()
	{
		final BonusOrderResultData bonusOrderResultData = createResultObject();
		bonusOrderResultData.setReturnMessage("ERROR IN BONUS ORDER DATA");
		bonusOrderResultData.setDecision("ERROR");
		return bonusOrderResultData;
	}

	@Override
	protected String executeEvent(final Object input)
	{
		final RestResponse restResponse = getLosClient().executeLosOrderRequest(getXclientRefId(), getUrlPath(), input);
		if (restResponse != null)
		{
			return String.valueOf(restResponse.getStatusCode());
		}
		return null;
	}
}
