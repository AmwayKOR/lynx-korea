package com.amway.integration.cis.los.order.service;

import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.BonusOrderResultData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;


/**
 * Default Implementation of service for Lose order bonus service.
 */
public class DefaultLosReturnOrderBonusService extends AbstractLosService<BonusOrderResultData, BonusOrderRequestData, String>
		implements LosService<BonusOrderRequestData, BonusOrderResultData>
{
	private final Logger LOG = Logger.getLogger(DefaultLosReturnOrderBonusService.class);

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
		LOG.info("Executing Bonus Return order requet " + input);
		final RestResponse restResponse = getLosClient().executeLosOrderRequest(getXclientRefId(), getUrlPath(), input);
		LOG.info("Response of Bonus Return order requet " + restResponse);
		if (restResponse != null)
		{
			return String.valueOf(restResponse.getStatusCode());
		}
		return null;
	}
}
