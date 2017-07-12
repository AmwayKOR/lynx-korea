package com.amway.integration.cis.dms.phoneservices.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyPhoneDetailsResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyPhoneResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to get the party phone details from magic.
 */
@Deprecated
public class DefaultGetPartyPhoneService
		extends AbstractDmsService<PartyPhoneDetailsResponseData, CommonRequestFieldsData, GetPartyPhoneResponse>
		implements DmsService<CommonRequestFieldsData, PartyPhoneDetailsResponseData>
{
	private static final Logger LOG = Logger.getLogger(DefaultGetPartyPhoneService.class);

	@Override
	protected PartyPhoneDetailsResponseData createResultObject()
	{
		return new PartyPhoneDetailsResponseData();
	}

	@Override
	protected PartyPhoneDetailsResponseData createDefaultResult()
	{
		final PartyPhoneDetailsResponseData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get ecomm details");
		return getBalanceResult;
	}

	@Override
	protected GetPartyPhoneResponse executeEvent(final Object input)
	{
		final RestResponse<GetPartyPhoneResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						GetPartyPhoneResponse.class);
		Assert.notNull(dmsResultRestResponse, "Get Party Ecomm failure");
		return dmsResultRestResponse.getResult();
	}

}
