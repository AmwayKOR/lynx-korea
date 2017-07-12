/**
 *
 */
package com.amway.core.subscription.service;

import java.util.List;

import com.amway.core.dms.data.DmsSubscriptionErrorData;
import com.amway.core.dms.data.SubscriptionData;
import com.amway.core.dms.data.SubscriptionDataResponse;




/**
 * Interface for to update the subscription service.
 */
public interface UpdateSubscriptionService
{

	/**
	 * @param subscriptionDatalist
	 * @return SubscriptionDataResponse
	 */
	SubscriptionDataResponse<SubscriptionDecision, DmsSubscriptionErrorData<SubscriptionFieldType, SubscriptionErrorCode>> updateSubscriptionList(
			List<SubscriptionData> subscriptionDatalist);

}
