package com.amway.integration.cis.los.bonusadjustment.service;

import com.amway.core.los.data.BonusOrderRequestData;
import com.amway.core.los.data.BonusOrderResultData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;


/**
 * Service for Los Bonus Adjustment.
 */
public class DefaultLosBonusAdjustmentService extends AbstractLosService<BonusOrderResultData, BonusOrderRequestData, String>
		implements LosService<BonusOrderRequestData, BonusOrderResultData>
{
	@Override
	protected BonusOrderResultData createResultObject()
	{
		return new BonusOrderResultData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected BonusOrderResultData createDefaultResult()
	{
		final BonusOrderResultData bonusOrderResultData = createResultObject();
		bonusOrderResultData.setReturnMessage("ERROR IN BONUS ADJUSTMENT");
		bonusOrderResultData.setDecision("ERROR");
		return bonusOrderResultData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
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
