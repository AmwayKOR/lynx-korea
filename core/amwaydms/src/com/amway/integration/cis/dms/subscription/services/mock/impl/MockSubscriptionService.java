package com.amway.integration.cis.dms.subscription.services.mock.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.DmsSubscriptionResultData;
import com.amway.core.dms.data.SubscriptionInputData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.SubscriptionResponse;
import com.amway.integration.dms.services.SubsrciptionSvcData;




/**
 * Mock service for subscription.
 */
public class MockSubscriptionService
		extends AbstractDmsService<DmsSubscriptionResultData, SubscriptionInputData, SubscriptionResponse>
		implements DmsService<SubscriptionInputData, DmsSubscriptionResultData>
{

	@Override
	public DmsSubscriptionResultData process(final SubscriptionInputData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
	 */
	@Override
	protected DmsSubscriptionResultData createResultObject()
	{
		return new DmsSubscriptionResultData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected DmsSubscriptionResultData createDefaultResult()
	{
		final DmsSubscriptionResultData result = createResultObject();
		result.setReturnMessage("Unsuccessful");
		result.setReturnCd(-1);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected SubscriptionResponse executeEvent(final Object input)
	{

		final SubscriptionResponse responce = new SubscriptionResponse();
		final SubsrciptionSvcData SvcData = new SubsrciptionSvcData();
		SvcData.setSubscriptionId(convertToJAXBString("subscriptionId", "50"));
		SvcData.setCancelCd(convertToJAXBString("cancelCd", "Y"));
		SvcData.setIsPublicationCd(convertToJAXBString("isPublicationCd", "N"));
		SvcData.setLanguageCd(convertToJAXBString("languageCd", "pt"));
		SvcData.setRestoreCd(convertToJAXBString("restoreCd", "N"));
		SvcData.setSubscribeFlag(convertToJAXBString("subscribeFlag", "N"));
		SvcData.setSubscriptionName(convertToJAXBString("subscriptionName", "Auto-Renewal"));
		SvcData.setPauseCd(convertToJAXBString("pauseCd", "N"));

		responce.getSubscriptionList().add(SvcData);
		responce.setReturnCd(1);
		responce.setReturnMessage("Success");
		return responce;
	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		if (StringUtils.isNotEmpty(elementValue))
		{
			return elementValue;
		}
		return StringUtils.EMPTY;
	}


}
