package com.amway.integration.cis.dms.customer.balance.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.util.Assert;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AccountCreateBalanceRequest;
import com.amway.integration.dms.services.ReturnInfoService;
import com.hybris.commons.client.RestResponse;


/**
 * Service for to create the account balance information.
 *
 * port to amwaydms2
 */
public class DefaultCreateBalanceAccountService
		extends AbstractDmsService<CommonResponseFieldsData, AccountCreateBalanceRequest, ReturnInfoService>
		implements DmsService<AccountCreateBalanceRequest, CommonResponseFieldsData>
{

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		final RestResponse<ReturnInfoService> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						ReturnInfoService.class);
		Assert.notNull(dmsResultRestResponse, "Create Account Balance failure");
		return dmsResultRestResponse.getResult();

	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData createBalanceResult = createResultObject();
		createBalanceResult.setReturnCd(-1);
		createBalanceResult.setReturnMessage("Failed to get account Balance information");
		return createBalanceResult;
	}

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}
}
