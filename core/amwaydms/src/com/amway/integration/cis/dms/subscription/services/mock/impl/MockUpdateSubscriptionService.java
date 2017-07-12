/**
 *
 */
package com.amway.integration.cis.dms.subscription.services.mock.impl;

import com.amway.core.dms.data.SubscriptionDataResponse;
import com.amway.core.dms.data.SubscriptionOptionRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.subscription.service.SubscriptionDecision;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Mock Service for to update the Subscription.
 */
public class MockUpdateSubscriptionService
		extends AbstractDmsService<SubscriptionDataResponse, SubscriptionOptionRequestData, ReturnInfoService>
		implements DmsService<SubscriptionOptionRequestData, SubscriptionDataResponse>
{

	@Override
	public SubscriptionDataResponse process(final SubscriptionOptionRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
	 */
	@Override
	protected SubscriptionDataResponse createResultObject()
	{
		return new SubscriptionDataResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected SubscriptionDataResponse createDefaultResult()
	{
		final SubscriptionDataResponse response = new SubscriptionDataResponse<>();
		response.setDecision(SubscriptionDecision.FAILURE);
		response.setReturnCd(-1);
		response.setReturnMessage("Fail to update subscription service");

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		final ReturnInfoService response = new ReturnInfoService();
		response.setReturnCd(1);
		response.setReturnMessage("subscription updated successhully");
		return response;
	}

}
