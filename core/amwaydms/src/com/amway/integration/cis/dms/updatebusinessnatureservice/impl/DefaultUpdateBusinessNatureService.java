package com.amway.integration.cis.dms.updatebusinessnatureservice.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;
import com.amway.dms.data.UpdateBusinessNatureInputRequestData;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/**
 * Service implementation for to update the business nature.
 *
 * port to amwaydms2
 */

public class DefaultUpdateBusinessNatureService
		extends AbstractDmsService<CommonResponseFieldsData, UpdateBusinessNatureInputRequestData, ReturnInfoService>
		implements DmsService<UpdateBusinessNatureInputRequestData, CommonResponseFieldsData>
{
	private static Logger LOG = Logger.getLogger(DefaultUpdateBusinessNatureService.class);

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		final RestResponse<ReturnInfoService> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(), input,
						ReturnInfoService.class);

		Assert.notNull(dmsResultRestResponse, "Updation Failure");

		return dmsResultRestResponse.getResult();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("Invalid feilds entry");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

}
