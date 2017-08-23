package com.amway.integration.cis.dms.partypreferenceservice.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyPreferenceResponse;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyPreferenceResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to get the party preference details from magic.
 */
@Deprecated
public class DefaultGetPartyPreferenceService
		extends AbstractDmsService<PartyPreferenceResponse, CommonRequestFieldsData, GetPartyPreferenceResponse>
		implements DmsService<CommonRequestFieldsData, PartyPreferenceResponse>
{
	private static final Logger LOG = Logger.getLogger(DefaultGetPartyPreferenceService.class);

	@Override
	protected PartyPreferenceResponse createResultObject()
	{
		return new PartyPreferenceResponse();
	}

	@Override
	protected PartyPreferenceResponse createDefaultResult()
	{
		final PartyPreferenceResponse getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get party preference details");
		return getBalanceResult;
	}

	@Override
	protected GetPartyPreferenceResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}

}
