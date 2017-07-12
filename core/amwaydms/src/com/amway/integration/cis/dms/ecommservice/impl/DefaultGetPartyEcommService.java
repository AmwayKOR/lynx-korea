/**
 *
 */
package com.amway.integration.cis.dms.ecommservice.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyEcommDetailsResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.creditprofile.services.impl.DefaultPartyCreditProfileService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyEcommResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service to get the party ecomm details from magic.
 */
@Deprecated
public class DefaultGetPartyEcommService
		extends AbstractDmsService<PartyEcommDetailsResponseData, CommonRequestFieldsData, GetPartyEcommResponse>
		implements DmsService<CommonRequestFieldsData, PartyEcommDetailsResponseData>
{
	private static final Logger LOG = Logger.getLogger(DefaultPartyCreditProfileService.class);

	@Override
	protected PartyEcommDetailsResponseData createResultObject()
	{
		return new PartyEcommDetailsResponseData();
	}

	@Override
	protected PartyEcommDetailsResponseData createDefaultResult()
	{
		final PartyEcommDetailsResponseData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get ecomm details");
		return getBalanceResult;
	}

	@Override
	protected GetPartyEcommResponse executeEvent(final Object input)
	{
		LOG.info("Calling webservice EcommService/getPartyEcomm.....");
		final RestResponse<GetPartyEcommResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						GetPartyEcommResponse.class);
		Assert.notNull(dmsResultRestResponse, "Get Party Ecomm failure");
		return dmsResultRestResponse.getResult();
	}

}
