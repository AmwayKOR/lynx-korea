package com.amway.integration.cis.v3.services;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.cis.v3.swagger.Account;
import com.amway.integration.cis.v3.swagger.AccountResponse;
import com.amway.integration.cis.v3.util.RequestContext;
import com.hybris.commons.client.RestResponse;

public class DefaultDmsGetAmwayAccountService
		extends AbstractDmsService<AmwayProfileResponseData, AmwayProfileRequestData, Account>
		implements DmsService<AmwayProfileRequestData, AmwayProfileResponseData>
{
	private static final Logger LOG = Logger.getLogger(DefaultDmsGetAmwayAccountService.class);

	@Override
	protected AmwayProfileResponseData createResultObject()
	{
		return new AmwayProfileResponseData();
	}

	@Override
	protected AmwayProfileResponseData createDefaultResult()
	{
		return new AmwayProfileResponseData();
	}

	@Override
	protected Account executeEvent(Object input)
	{
		LOG.info("Calling webservice GET accounts/...");
		AmwayProfileRequestData request = (AmwayProfileRequestData) input;
		String localVarPath =
			RequestContext.addContextParams(
					new RequestContext<>(request.getSalesPlanAff(), request.getAboNum(), null), getUrlPath());

		final RestResponse<AccountResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsGetRequest(getXclientRefId(), localVarPath, input, AccountResponse.class);
		Assert.notNull(dmsResultRestResponse, "Failed to get amway account information");
		AccountResponse result = dmsResultRestResponse.getResult();
		return result.getAccount();
	}

}
