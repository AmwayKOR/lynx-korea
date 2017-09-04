package com.amway.integration.cis.dms.partypreferenceservice.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PreferenceListResponse;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPreferenceResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to get the preference list from magic.
 */
@Deprecated
public class DefaultGetPreferenceListService
		extends AbstractDmsService<PreferenceListResponse, CommonRequestFieldsData, GetPreferenceResponse>
		implements DmsService<CommonRequestFieldsData, PreferenceListResponse>
{
	private static final Logger LOG = Logger.getLogger(DefaultGetPreferenceListService.class);

	@Override
	protected PreferenceListResponse createResultObject()
	{
		return new PreferenceListResponse();
	}

	@Override
	protected PreferenceListResponse createDefaultResult()
	{
		final PreferenceListResponse getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get preference list");
		return getBalanceResult;
	}

	@Override
	protected GetPreferenceResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}

}
