/**
 *
 */
package com.amway.integration.cis.dms.blockprevservices.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.BlockPrevRequestData;
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
 * Service for to process the block prev details.
 */
@Deprecated
public class DefaultProcessBlockPrevService
		extends AbstractDmsService<CommonResponseFieldsData, BlockPrevRequestData, ReturnInfoService>
		implements DmsService<BlockPrevRequestData, CommonResponseFieldsData>
{
	private static final Logger LOG = Logger.getLogger(DefaultProcessBlockPrevService.class);

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to process block prev details");
		return getBalanceResult;
	}

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		final RestResponse<ReturnInfoService> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						ReturnInfoService.class);
		Assert.notNull(dmsResultRestResponse, "Process Block Prev Service failure");
		return dmsResultRestResponse.getResult();
	}

}
