/**
 *
 */
package com.amway.integration.cis.dms.creditprofile.services.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.CreditProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.CreditProfileResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to get the credit profile information.
 */
@Deprecated
public class DefaultPartyCreditProfileService
		extends AbstractDmsService<CreditProfileResponseData, CommonRequestFieldsData, CreditProfileResponse>
		implements DmsService<CommonRequestFieldsData, CreditProfileResponseData>
{
	private static final Logger LOG = Logger.getLogger(DefaultPartyCreditProfileService.class);

	@Override
	protected CreditProfileResponse executeEvent(final Object input)
	{
		final RestResponse<CreditProfileResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						CreditProfileResponse.class);
		Assert.notNull(dmsResultRestResponse, "Get Credit Profile failure");
		return dmsResultRestResponse.getResult();
	}

	@Override
	protected CreditProfileResponseData createDefaultResult()
	{
		final CreditProfileResponseData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get credit profile");
		return getBalanceResult;
	}

	@Override
	protected CreditProfileResponseData createResultObject()
	{
		return new CreditProfileResponseData();
	}
}
