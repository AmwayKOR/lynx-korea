package com.amway.integration.cis.dms.subscription.services.impl;

import com.amway.core.dms.data.SubscriptionDataResponse;
import com.amway.core.dms.data.SubscriptionOptionRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;
import com.amway.integration.dms.services.SubscriptionResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Default Implementation for update subscription service.
 */
@Deprecated
public class DefaultUpdateSubscriptionService
		extends AbstractDmsService<SubscriptionDataResponse, SubscriptionOptionRequestData, ReturnInfoService>
		implements DmsService<SubscriptionOptionRequestData, SubscriptionDataResponse>
{
	private static Logger LOG = Logger.getLogger(DefaultUpdateSubscriptionService.class);

	@Override
	protected ReturnInfoService executeEvent(final Object dmsSubscriptionInput)
	{
		//see Deprecated comment above
		return null;
	}


	@Override
	protected SubscriptionDataResponse createDefaultResult()
	{
		final SubscriptionDataResponse commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("Invalid fields entry");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}


	@Override
	protected SubscriptionDataResponse createResultObject()
	{
		return new SubscriptionDataResponse();
	}


}
