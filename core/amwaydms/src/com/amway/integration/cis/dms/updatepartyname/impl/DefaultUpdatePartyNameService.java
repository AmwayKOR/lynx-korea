package com.amway.integration.cis.dms.updatepartyname.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PartyNameDetailsRequestData;
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
 * Service to update party name info to Magic
 */
@Deprecated
public class DefaultUpdatePartyNameService
		extends AbstractDmsService<CommonResponseFieldsData, PartyNameDetailsRequestData, ReturnInfoService>
		implements DmsService<PartyNameDetailsRequestData, CommonResponseFieldsData>
{
	private static final Logger LOG = Logger.getLogger(DefaultUpdatePartyNameService.class);

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		LOG.info("Calling webservice PartyService/updatePartyName.....");
		final RestResponse<ReturnInfoService> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(), input,
						ReturnInfoService.class);
		Assert.notNull(dmsResultRestResponse, "Failed to update party name");

		return dmsResultRestResponse.getResult();
	}

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("Failed to update party name");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}

}
