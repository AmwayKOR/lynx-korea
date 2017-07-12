package com.amway.integration.cis.los.account.service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.pojo.LosAccountRequest;
import com.amway.integration.cis.los.pojo.LosResponse;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;


/**
 * Service to get the Los Account.
 */
public class DefaultLosAccountService extends AbstractLosService<LosAccountResponseData, LosAccountRequestData, LosResponse>
		implements LosService<LosAccountRequestData, LosAccountResponseData>
{
	private static final Logger LOG = Logger.getLogger(DefaultLosAccountService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected LosResponse executeEvent(final Object input)
	{
		LOG.info("Calling webservice los/account.....");
		final RestResponse<LosResponse> restResponse = getLosClient().executeLosAccountRequest(getXclientRefId(), getUrlPath(),
				new JAXBElement(new QName("", "losAccountRequest"), LosAccountRequest.class, input), LosResponse.class);

		Assert.notNull(restResponse, "Failed to get los accounts");
		return restResponse.getResult();
	}

	@Override
	protected LosAccountResponseData createResultObject()
	{
		return new LosAccountResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected LosAccountResponseData createDefaultResult()
	{
		final LosAccountResponseData losAccountResponseData = createResultObject();
		losAccountResponseData.setReturnCode(-1);
		losAccountResponseData.setReturnMessage("Failed to get los accounts");
		return losAccountResponseData;
	}
}
