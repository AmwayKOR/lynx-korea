/**
 *
 */
package com.amway.integration.cis.dms.aboinformation.services.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AmwayProfileInput;
import com.amway.integration.dms.services.AmwayProfileOutput;
import com.hybris.commons.client.RestResponse;


/**
 * Service to get amway profile information from Magic
 */

public class DefaultAmwayProfileService
		extends AbstractDmsService<AmwayProfileResponseData, AmwayProfileRequestData, AmwayProfileOutput>
		implements DmsService<AmwayProfileRequestData, AmwayProfileResponseData>
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayProfileService.class);

	@Override
	protected AmwayProfileOutput executeEvent(final Object input)
	{
		LOG.info("Calling webservice AccountService/getAmwayProfile.....");
		final RestResponse<AmwayProfileOutput> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(), input, AmwayProfileOutput.class);
		Assert.notNull(dmsResultRestResponse, "Failed to get amway profile information");
		return dmsResultRestResponse.getResult();
	}

	@Override
	protected AmwayProfileResponseData createResultObject()
	{
		return new AmwayProfileResponseData();
	}


	@Override
	protected AmwayProfileResponseData createDefaultResult()
	{
		final AmwayProfileResponseData amwayProfileResponseData = createResultObject();
		amwayProfileResponseData.setReturnCd(-1);
		amwayProfileResponseData.setReturnMessage("Failed to get amway profile information");
		return amwayProfileResponseData;
	}
}
