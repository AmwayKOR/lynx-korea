/**
 *
 */
package com.amway.integration.cis.dms.subscription.services.impl;

import com.amway.core.dms.data.DmsSubscriptionResultData;
import com.amway.core.dms.data.SubscriptionInputData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.subscription.service.SubscriptionDecision;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.SubscriptionResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service implementation for subscription.
 */
@Deprecated
public class DefaultSubscriptionService
		extends AbstractDmsService<DmsSubscriptionResultData, SubscriptionInputData, SubscriptionResponse>
		implements DmsService<SubscriptionInputData, DmsSubscriptionResultData>
{
	private static Logger LOG = Logger.getLogger(DefaultSubscriptionService.class);

	@Override
	protected SubscriptionResponse executeEvent(final Object input)
	{
		final RestResponse<SubscriptionResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						input,
						SubscriptionResponse.class);

		Assert.notNull(dmsResultRestResponse, "Updation Failure");
		return dmsResultRestResponse.getResult();
	}

	@Override
	protected DmsSubscriptionResultData createDefaultResult()
	{
		final DmsSubscriptionResultData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setDecision(SubscriptionDecision.FAILURE);
		return commonResponseFieldsData;
	}

	@Override
	protected DmsSubscriptionResultData createResultObject()
	{
		return new DmsSubscriptionResultData();
	}

}
