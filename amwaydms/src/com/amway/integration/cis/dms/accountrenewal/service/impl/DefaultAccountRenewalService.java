/**
 *
 */
package com.amway.integration.cis.dms.accountrenewal.service.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.renewal.data.AccountRenewalRequestData;
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
 * service for renewal of account
 */
@Deprecated
public class DefaultAccountRenewalService
		extends AbstractDmsService<CommonResponseFieldsData, AccountRenewalRequestData, ReturnInfoService>
		implements DmsService<AccountRenewalRequestData, CommonResponseFieldsData>
{

	private static final Logger LOG = Logger.getLogger(DefaultAccountRenewalService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
	 */
	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("Failed to renew the account");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		LOG.info("Calling webservice RenewalService/processABORenewal.....");
		final RestResponse<ReturnInfoService> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(), input,
						ReturnInfoService.class);
		Assert.notNull(dmsResultRestResponse, "Failed to renew the account");

		return dmsResultRestResponse.getResult();
	}
}
