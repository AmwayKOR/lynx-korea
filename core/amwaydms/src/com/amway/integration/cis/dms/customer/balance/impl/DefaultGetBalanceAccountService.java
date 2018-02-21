package com.amway.integration.cis.dms.customer.balance.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.util.Assert;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AccountBalanceRequest;
import com.amway.integration.dms.services.AccountBalanceResponse;
import com.hybris.commons.client.RestResponse;


/**
 * Service for to get the account balance information.
 *
 * port to amwaydms2
 */
public class DefaultGetBalanceAccountService
		extends AbstractDmsService<GetBalanceResponseData, CommonRequestFieldsData, AccountBalanceResponse>
		implements DmsService<CommonRequestFieldsData, GetBalanceResponseData>
{

	@Override
	protected AccountBalanceResponse executeEvent(final Object input)
	{
		final RestResponse<AccountBalanceResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						input,
						AccountBalanceResponse.class);
		Assert.notNull(dmsResultRestResponse, "Get Account Balance failure");
		return dmsResultRestResponse.getResult();

	}

	@Override
	protected GetBalanceResponseData createDefaultResult()
	{
		final GetBalanceResponseData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get account Balance information");
		return getBalanceResult;
	}

	@Override
	protected GetBalanceResponseData createResultObject()
	{
		return new GetBalanceResponseData();
	}
}
