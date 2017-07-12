package com.amway.integration.cis.dms.creditprofile.services.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.PartyCreditPofileObject;
import com.amway.integration.dms.services.ReturnInfoService;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to update the party credit profile details.
 */
@Deprecated
public class DefaultUpdatePartyCreditProfileService
		extends AbstractDmsService<CommonResponseFieldsData, PartyCreditPofileObject, ReturnInfoService>
		implements DmsService<PartyCreditPofileObject, CommonResponseFieldsData>
{

	private static final Logger LOG = Logger.getLogger(DefaultUpdatePartyCreditProfileService.class);

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		LOG.info("Calling webservice UpdatePartyCreditProfileService....");
		final RestResponse<ReturnInfoService> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(), input,
						ReturnInfoService.class);
		Assert.notNull(dmsResultRestResponse, "Failed to update party credit profile details");

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
		commonResponseFieldsData.setReturnMessage("Failed to update party credit profile details");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}


}
