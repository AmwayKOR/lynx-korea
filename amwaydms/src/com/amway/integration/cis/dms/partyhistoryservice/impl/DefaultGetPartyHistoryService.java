package com.amway.integration.cis.dms.partyhistoryservice.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyHistoryDataResponse;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.PartyHistoryResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service to get party history details from magic.
 */
@Deprecated
public class DefaultGetPartyHistoryService
		extends AbstractDmsService<PartyHistoryDataResponse, CommonRequestFieldsData, PartyHistoryResponse>
		implements DmsService<CommonRequestFieldsData, PartyHistoryDataResponse>
{
	private static final Logger LOG = Logger.getLogger(DefaultGetPartyHistoryService.class);

	@Override
	protected PartyHistoryDataResponse createResultObject()
	{
		return new PartyHistoryDataResponse();
	}

	@Override
	protected PartyHistoryDataResponse createDefaultResult()
	{
		final PartyHistoryDataResponse getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get ecomm details");
		return getBalanceResult;
	}

	@Override
	protected PartyHistoryResponse executeEvent(final Object input)
	{
		final RestResponse<PartyHistoryResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						PartyHistoryResponse.class);
		Assert.notNull(dmsResultRestResponse, "Get Party Ecomm failure");
		return dmsResultRestResponse.getResult();
	}

}
