package com.amway.integration.cis.dms.ecommservice.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
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
 * Service to update ecomm details to Magic
 */
@Deprecated
public class DefaultUpdatePartyEcommService
		extends AbstractDmsService<CommonResponseFieldsData, AddUpdatePartyEcommRequestData, ReturnInfoService>
		implements DmsService<AddUpdatePartyEcommRequestData, CommonResponseFieldsData>
{

	private static final Logger LOG = Logger.getLogger(DefaultUpdatePartyEcommService.class);

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData responseData = createResultObject();
		responseData.setReturnCd(-1);
		responseData.setReturnMessage("Failed to Update Ecomm Details");
		return responseData;
	}

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		LOG.info("Calling webservice EcommService/updatePartyEcomm.....");
		final RestResponse<ReturnInfoService> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(), input,
						ReturnInfoService.class);
		Assert.notNull(dmsResultRestResponse, "Failed to add party Ecomm details");
		return dmsResultRestResponse.getResult();
	}

}
