package com.amway.core.subscription.service;

import com.amway.core.dms.data.DmsSubscriptionErrorData;
import com.amway.core.dms.data.DmsSubscriptionResultData;
import com.amway.core.dms.data.SubscriptionInputData;







/**
 * Interface for subscription service.
 */
public interface SubscriptionService
{
	/**
	 * @param inputData
	 * @return DmsSubscriptionResultData
	 */
	public DmsSubscriptionResultData<SubscriptionDecision, DmsSubscriptionErrorData<SubscriptionFieldType, SubscriptionErrorCode>> getSubscriptionChoice(
			SubscriptionInputData inputData);
}
