package com.amway.integration.cis.dms.partypreferenceservice.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PartyPreferenceData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service to update the party preference details.
 */
@Deprecated
public class DefaultUpdatePartyPreferenceService
		extends AbstractDmsService<CommonResponseFieldsData, PartyPreferenceData, ReturnInfoService>
		implements DmsService<PartyPreferenceData, CommonResponseFieldsData>
{
	private static final Logger LOG = Logger.getLogger(DefaultUpdatePartyPreferenceService.class);

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{

		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("Failed to update party preference details");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}

}
