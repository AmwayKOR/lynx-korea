package com.amway.integration.cis.dms.taxservices.mock.impl;

import java.util.Collections;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.data.TaxDetailsData;
import com.amway.core.dms.data.TaxIdDetailsResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyTaxIdDetailsResponse;
import com.amway.integration.dms.services.TaxDetailsOutput;


/**
 * Mock Service for to get tax id details.
 */

public class MockGetPartyTaxService
		extends AbstractDmsService<TaxIdDetailsResponseData, TaxDetailsData, GetPartyTaxIdDetailsResponse>
		implements DmsService<TaxDetailsData, TaxIdDetailsResponseData>
{

	@Override
	public TaxIdDetailsResponseData process(final TaxDetailsData requestData)
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
	protected TaxIdDetailsResponseData createResultObject()
	{
		// YTODO Auto-generated method stub
		return new TaxIdDetailsResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected TaxIdDetailsResponseData createDefaultResult()
	{
		final TaxIdDetailsResponseData responseData = new TaxIdDetailsResponseData();
		responseData.setReturnCd(-1);
		responseData.setReturnMessage("Fail to get Tex Info");
		return responseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected GetPartyTaxIdDetailsResponse executeEvent(final Object input)
	{
		final GetPartyTaxIdDetailsResponse response = new GetPartyTaxIdDetailsResponse();
		final TaxDetailsOutput taxDetails = new TaxDetailsOutput();
		taxDetails.setPartyId("171956");
		taxDetails.setCountryTaxCd(convertToJAXBString("countryTaxCd", "BR"));
		taxDetails.setTaxTypeCd(convertToJAXBString("taxTypeCd", "CPF"));
		taxDetails.setTaxTypeExpireDate(convertToJAXBString("taxTypeExpireDate", "2020-04-02T21:00:00-03:00"));
		taxDetails.setTaxId(convertToJAXBString("taxId", "02198196964"));
		response.setPartyTaxDetailsList(Collections.singletonList(taxDetails));
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;

	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		if (StringUtils.isNotEmpty(elementValue))
		{
			return elementValue;
		}
		return null;
	}
}
