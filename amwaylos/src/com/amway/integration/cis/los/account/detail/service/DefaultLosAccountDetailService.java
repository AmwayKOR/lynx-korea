/**
 *
 */
package com.amway.integration.cis.los.account.detail.service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.pojo.DetailResponse;
import com.amway.integration.cis.los.pojo.LosAccountRequest;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;


/**
 * service to get the los account detail
 */
public class DefaultLosAccountDetailService
		extends AbstractLosService<LosAccountDetailResponseData, LosAccountRequestData, DetailResponse>
		implements LosService<LosAccountRequestData, LosAccountDetailResponseData>
{

	private static final Logger LOG = Logger.getLogger(DefaultLosAccountDetailService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected DetailResponse executeEvent(final Object input)
	{
		LOG.info("Calling webservice los/account/detail.....");
		final RestResponse<DetailResponse> restResponse = getLosClient()
				.executeLosAccountDetailRequest(getXclientRefId(), getUrlPath(),
						new JAXBElement(new QName("", "losAccountRequest"), LosAccountRequest.class, input), DetailResponse.class);

		Assert.notNull(restResponse, "Failed to get los account details");
		return restResponse.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createResultObject()
	 */
	@Override
	protected LosAccountDetailResponseData createResultObject()
	{
		return new LosAccountDetailResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected LosAccountDetailResponseData createDefaultResult()
	{
		final LosAccountDetailResponseData accountDetailResponseData = createResultObject();
		accountDetailResponseData.setReturnCode(-1);
		accountDetailResponseData.setReturnMessage("Failed to get los account details");

		return accountDetailResponseData;
	}


}
